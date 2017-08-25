/*
 * SocketUtil.java
 */
package org.pub.util.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * SocketUtil
 *
 */
public class SocketUtil {

    private static final Logger logger = Logger.getLogger(SocketUtil.class.getName());
    public static final String charset = "GBK";

    /**
     * 发送报文
     */
    public static String sendData(String server_ip, int server_port, String send_data) {
        logger.debug("  server_ip=" + server_ip);
        logger.debug("server_port=" + server_port);
        logger.debug("  send_data=" + send_data);
        if (server_ip == null || server_ip.trim().equals("")) {
            throw new java.lang.IllegalArgumentException("server_ip is null!");
        }
        if (server_port <= 0) {
            throw new java.lang.IllegalArgumentException("server_port is error!");
        }
        if (send_data == null || send_data.trim().equals("")) {
            throw new java.lang.IllegalArgumentException("send_data is error!");
        }
        String receive_data = null;
        Socket socket = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            socket = new Socket(server_ip, server_port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF(send_data);
            dos.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(dis, charset));
            receive_data = br.readLine();
            dis.close();
            dos.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new java.lang.RuntimeException(ex);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dis.close();
                }
                if (socket != null) {
                    dis.close();
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        logger.debug("receive_data=" + receive_data);
        return receive_data;
    }
}
