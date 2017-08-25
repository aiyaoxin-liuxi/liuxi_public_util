/*
 * Uuid.java
 * Copyright org.javaplus, Inc. All rights reserved.
 * org.javaplus PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.pub.util.uuid;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import org.pub.util.security.Md5;

/**
 * This class is a generating service class format of UUIDs (Universally Unique
 * IDentifiers), also known as GUIDs (Globally Unique IDentifiers). A UUID is
 * 128 bits long, and if generated according to the particular mechanisms, is
 * either guaranteed to be different from all other UUIDs/GUIDs generated until
 * 3400 A.D. or extremely likely to be different (depending on the mechanism
 * chosen).
 *
 * <p> <b>Background</b>
 *
 * <p> A UUID is an identifier that is unique across both space and time, with
 * respect to the space of all UUIDs. To be precise, the UUID consists of a
 * finite bit space. Thus the time value used for constructing a UUID is limited
 * and will roll over in the future (approximately at A.D. 3400, based on the
 * specified algorithm).
 *
 * <p> In its most general form, all that can be said of the UUID format is that
 * a UUID is 16 octets, and that some bits of octet 8 of the UUID called the
 * variant field determine finer structure. The variant field determines the
 * layout of the UUID. That is, the interpretation of all other bits in the UUID
 * depends on the setting of the bits in the variant field. The variant field
 * consists of a variable number of the msbs of octet 8 of the UUID.
 *
 * <p> This implementation class uses two most significant bits of octet 8 of
 * the UUID as variant field, and the value is set as follow:
 *
 * <pre>
 *     Msb0    Msb1
 *
 *      1       0
 * </pre>
 *
 * <p> The following table gives the string format of a UUID specified herein.
 *
 * <pre>
 *     UUID = &lt;time_low&gt; "-" &lt;time_mid&gt; "-"
 *            &lttime_high_and_version&gt "-"
 *            &ltclock_seq_hi_and_varian&gt;&lt;clock_seq_low&gt; "-" &lt;node&gt;
 * </pre>
 *
 * <p> For example
 *
 * <pre>
 *     1F52AC91-8C97-11D6-8BA2-006067061E14
 * </pre>
 *
 * <p> The following table gives the bytes order of UUIDs.
 *
 * <pre>
 *  0                   1                   2                   3
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                          time_low                             |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |       time_mid                |         time_hi_and_version   |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |clk_seq_hi_var |  clk_seq_low  |         node (0-1)            |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                         node (2-5)                            |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * </pre>
 *
 * <p> This class implements version 1, time-based version of UUIDs. That is, we
 * use four most significant bits of octet 6 to indicate our version of
 * implementation. The following table gives the format.
 *
 * <pre>
 *     Msb0   Msb1   Msb2   Msb3   Version   Description
 *
 *      0      0      0      1        1      The time-based version
 * </pre>
 *
 * <p> <b>Implementaion and usage</b>
 *
 * <p> Class Uuid uses a resouce file (uuid.properties) located in the classpath
 * to store node ID used by UUIDs. When deploying this class, application should
 * modify the value of Node ID to reflect the IEEE 802.1 network card address of
 * the host. For example, assuming the physical address of the host is
 * "00-60-67-06-1E-14", the value for node ID should be "006067061E14", and
 * saved as:
 *
 * <pre>
 *     uuid.nodeid=006067061E14;
 * </pre>
 *
 * <p> If the resource file cannot be found or it contains no or invalid value
 * of node ID, a 47 bit cryptographic quality random number will be
 * automatically generated with the seed generated from some system specific
 * properties, and use it as the low 47 bits of the node ID, with the most
 * significant bit of the first octet of the node ID set to 1. This bit is the
 * unicast/multicast bit, which will never be set in IEEE 802.1 addresses
 * obtained from network cards; hence, there can never be a conflict between
 * UUIDs generated by machines with and without network cards.
 *
 * <p> Everytime class Uuid is initalized by classloader, a pseudorandom clock
 * sequence number will be generated with system clock as seed. This prevent
 * conflicts even when system clock has gone backward.
 *
 * <p> <b>Reference</b>
 *
 * <p> For more detail information about UUIDs/GUIDs, please refer to the
 * Internet-Draft standard track, UUIDs and GUIDs, by Paul J. Leach, Microsoft.
 *
 * @author Stephen Suen
 * @version $Revision: 1.2 $ $Date: 2008/03/24 07:07:24 $
 * @since Framework 1.0
 */
public class Uuid {

    private static final String RESOURCE_NAME = "org.javaplus.util.uuid";
    private static final String KEY_NODE_ID = "uuid.nodeid";
    private static final byte MASK_VERSION_TIMEBASED = (byte) 0x10; // Time-based version
    private static final byte MASK_VERSION_REV = (byte) 0x20; // Reserved for DCE Security version
    private static final byte MASK_VERSION_NAMEBASED = (byte) 0x30; // Name-based version
    private static final byte MASK_VERSION_RANDOM = (byte) 0x40; // Randomly or pseudo-randomly generated version
    private static final byte MASK_VARIANT_NCS = (byte) 0x00; // Reserved, NCS backward compatibility
    private static final byte MASK_VARIANT_RFC = (byte) 0x80; // The variant specified in this document
    private static final byte MASK_VARIANT_MS = (byte) 0xC0; // Reserved, Microsoft Corporation GUID
    private static final byte MASK_VARIANT_REV = (byte) 0xE0; // Reserved for future definition
    private static final long MAX_CLOCK_ADJUSTMENT = 10000;
    private static final int MASK_CLOCK_SEQUENCE = 0x3FFF;
    private static final long OS_TIME_OFFSET;

    static {
        // Offset between UUID formatted times and OS formatted times.
        // UUID UTC base time is October 15, 1582.
        // OS base time is January 1, 1970
        TimeZone tz = TimeZone.getTimeZone("UTC");
        GregorianCalendar calendar = new GregorianCalendar(tz);
        OS_TIME_OFFSET = -calendar.getGregorianChange().getTime();
    }
    private static String nodeId;
    private static long lastSystemTime;
    private static int clockSequence;
    private static int clockAdjustment;

    static {
        // Try to load nonvolatile nide ID record from resource file.
        boolean resourceExists = false;
        Properties props = new Properties();
        try {
            Locale currentLocal = Locale.getDefault();
            ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME, currentLocal);
            Enumeration enumr = rb.getKeys();
            while (enumr.hasMoreElements()) {
                String key = (String) enumr.nextElement();
                String val = (String) rb.getString(key);
                props.put(key, val);
            }
            resourceExists = true;
        } catch (MissingResourceException mre) {
        }

        // Try to load UUID states from the reource
        if (resourceExists) {
            nodeId = props.getProperty(KEY_NODE_ID);
            if (nodeId != null) {
                try {
                    byte[] bytes = Hexadecimal.parseUnsignedBytes(nodeId);
                    nodeId = Hexadecimal.toString(bytes);
                    if (nodeId.length() != 12) {
                        nodeId = null;
                    }
                } catch (NumberFormatException nfe) {
                    nodeId = null;
                }
            }
        }

        // If node id is invalid, generate one without IEEE 802.1 address
        if (nodeId == null) {
            nodeId = genNodeId();
        }

        // Initializes record of the last system time
        lastSystemTime = System.currentTimeMillis();

        // Generate pseudorandom clock sequence.
        // Because saving last timestamp to nonvolatile resource file is
        // considered to be time-comsuming, so we never do it. Instead,
        // we change clock sequence whenever UUID generating service restart.
        Random random = new Random(lastSystemTime);
        clockSequence = random.nextInt(MASK_CLOCK_SEQUENCE + 1);
        if (clockSequence == 0) {
            clockSequence = 1;
        }

        // Initialize clock adjustment value
        clockAdjustment = 0;
    }

    // Methos to generate node ID without IEEE 802.1 address
    private static String genNodeId() {
        String hostId = "";
        try {
            InetAddress inet = InetAddress.getLocalHost();
            hostId += inet.getHostAddress() + " ";
            hostId += inet.getHostName() + " ";
        } catch (UnknownHostException uhe) {
        }

        hostId +=
                System.getProperty("os.version") + " "
                + System.getProperty("user.name") + " "
                + System.getProperty("java.version");

        byte[] node_id = new byte[6];
        byte[] digest = Md5.getDigest(hostId);
        for (int i = 0, j = 0; i < 16; i++) {
            node_id[j++] ^= digest[i];
            if (j == 6) {
                j = 0;
            }
        }
        node_id[0] |= 0x80;  // set the multicast bit

        return Hexadecimal.toString(node_id);
    }

    // Method to get a new timestamp for UUID
    // UUIDs may be created at a rate greater than the system clock
    // resolution. Therefore, the system must also maintain an
    // adjustment value to be added to the lower-order bits of the
    // time. Logically,each time the system clock ticks, the
    // adjustment value is cleared.
    private static long getTimestamp() {
        long now = System.currentTimeMillis();
        if (now > lastSystemTime) {
            clockAdjustment = 0; // clear clock adjustment value
        }

        if (now == lastSystemTime) {
            clockAdjustment++;
            if (clockAdjustment >= MAX_CLOCK_ADJUSTMENT) { // clock overrun
                clockAdjustment = 0;
                // stall until system clock changes
                while (now == lastSystemTime) {
                    try {
                        Thread.sleep(0, 100);
                    } catch (InterruptedException ie) {
                    }
                    now = System.currentTimeMillis();
                }
            }
        }

        // If system clock has gone backward, change clock sequence
        if (now < lastSystemTime) {
            clockSequence = (clockSequence + 1) % (MASK_CLOCK_SEQUENCE + 1);
            if (clockSequence == 0) {
                clockSequence = 1;
            }
        }

        lastSystemTime = now;

        long timestamp = now + OS_TIME_OFFSET;
        timestamp = timestamp * MAX_CLOCK_ADJUSTMENT;
        timestamp += clockAdjustment;
        return timestamp;
    }

    // Method to format a Uuid into string representation
    private static String formatUuid(long timestamp, int clockSeqence, String nodeId) {
        int time_low = (int) (timestamp & 0x00000000FFFFFFFFL);

        int time_hi = (int) ((timestamp & 0xFFFFFFFF00000000L) >>> 32);

        byte[] time_mid = new byte[2];
        time_mid[0] = (byte) ((time_hi & 0x0000FF00) >>> 8);
        time_mid[1] = (byte) (time_hi & 0x000000FF);

        byte[] time_hi_and_version = new byte[2];
        time_hi_and_version[0] = (byte) ((time_hi & 0x0F000000) >>> 24);
        time_hi_and_version[0] |= MASK_VERSION_TIMEBASED;
        time_hi_and_version[1] = (byte) ((time_hi & 0x00FF0000) >>> 16);

        byte[] clock_seq_and_variant = new byte[2];
        clock_seq_and_variant[0] = (byte) ((clockSequence & 0x00003F00) >>> 8);
        clock_seq_and_variant[0] |= MASK_VARIANT_RFC;
        clock_seq_and_variant[1] = (byte) (clockSequence & 0x000000FF);

        String result = "";
        result += Hexadecimal.toString(time_low) + "-";
        result += Hexadecimal.toString(time_mid) + "-";
        result += Hexadecimal.toString(time_hi_and_version) + "-";
        result += Hexadecimal.toString(clock_seq_and_variant) + "-";
        result += nodeId;

        return result;

    }
    private long m_timestamp;
    private int m_clockSequence;
    private String m_nodeId;
    private String m_uuid;

    /**
     * Private constructor. This class cannot be instantiated from outside of
     * this class.
     *
     * @param uuid String representation for the new Uuid object
     */
    private Uuid(long timestamp, int clockSeqence, String nodeId) {
        m_timestamp = timestamp;
        m_clockSequence = clockSequence;
        m_nodeId = nodeId;
        m_uuid = formatUuid(m_timestamp, m_clockSequence, m_nodeId);
    }

    /**
     * Generates a new Uuid instance. This methos is thread-safe.
     *
     * @return the new Uuid instance
     */
    public static synchronized Uuid getInstance() {
        long timestamp = getTimestamp();
        return new Uuid(timestamp, clockSequence, nodeId);
    }

    /**
     * Get the String representation for this Uuid instance.
     *
     * @return the String representation for this Uuid instance
     */
    @Override
    public String toString() {
        return m_uuid;
    }

    /**
     * Returns a hashcode for this Uuid. The hashcode for a Uuid Object is
     * justthe hashcode of the String object representing this Uuid.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return m_uuid.hashCode();
    }

    /**
     * Compares this Uuid to the specified object. The result is true if and
     * only if the argument is not null and is a Uuid object that has the same
     * string representation but ignore case as this Object.
     *
     * @param anObject the object to compare this Uuid against.
     * @return true if the Uuid are equal; false otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Uuid) {
            Uuid anotherUuid = (Uuid) anObject;
            return m_uuid.equalsIgnoreCase(anotherUuid.m_uuid);
        }
        return false;
    }

    /**
     * Get node ID for this Uuid object.
     *
     * @return string of node ID for this Uuid object
     */
    public String getNodeId() {
        return m_nodeId;
    }

    /**
     * Convenient main method for purpose of testing UUIDs generating service
     * performance.
     *
     * @param args environment arguments
     */
    public static void main(String[] args) {
        System.out.println("\nCalculating UUID generating service performance...\n");

        long count = 100000;
        long before = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            getInstance();
        }
        long duration = System.currentTimeMillis() - before;
        System.out.println("Generated " + count + " UUIDs in " + duration + " milliseconds.");
        long average = count * 1000 / duration;
        System.out.println("We can generate " + average + " UUIDs per second in average.\n");
        System.out.println("Here are some samples:");

        for (int i = 0; i < 10; i++) {
            System.out.println("    " + getInstance());
        }

        System.out.println(getInstance().getNodeId());
    }
}
