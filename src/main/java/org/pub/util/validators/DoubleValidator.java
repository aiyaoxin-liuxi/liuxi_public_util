package org.pub.util.validators;

/**
 * double验证
 *
 */
public class DoubleValidator {

    /**
     * double.包含验证
     */
    public static String doubleIncl(double num, double min, double max) {
        return doubleIncl(num, min, max, null);
    }

    /**
     * double.包含验证
     */
    public static String doubleIncl(double num, double min, double max, String msg) {
//        System.out.print("num=" + num + "\tmin=" + min + "\tmax=" + max + "\t");
        if (msg == null || msg.equals("")) {
            msg = "[${num}]必须在[${min}]到[${max}]之间！";
        }
        msg = msg.replace("${num}", num + "");
        msg = msg.replace("${min}", min + "");
        msg = msg.replace("${max}", max + "");
        if (num < min || num > max) {
            return msg;
        }
        return null;
    }

    /**
     * double.排除验证
     */
    public static String doubleExcl(double num, double min, double max) {
        return doubleExcl(num, min, max, null);
    }

    /**
     * double.排除验证
     */
    public static String doubleExcl(double num, double min, double max, String msg) {
//        System.out.print("num=" + num + "\tmin=" + min + "\tmax=" + max + "\t");
        if (msg == null || msg.equals("")) {
            msg = "[${num}]不能在[${min}]到[${max}]之间！";
        }
        msg = msg.replace("${num}", num + "");
        msg = msg.replace("${min}", min + "");
        msg = msg.replace("${max}", max + "");
        if (num > min && num < max) {
            return msg;
        }
        return null;
    }
//    public static void main(String[] args) {
//        try {
//            System.out.println(">>>>> " + doubleIncl(19.6, 20.1, 20.5));
//            System.out.println(">>>>> " + doubleIncl(20.2, 20.1, 20.5));
//            System.out.println(">>>>> " + doubleIncl(20.6, 20.1, 20.5));
//
//            System.out.println("");
//
//            System.out.println(">>>>> " + doubleExcl(19.6, 20.1, 20.5));
//            System.out.println(">>>>> " + doubleExcl(20.2, 20.1, 20.5));
//            System.out.println(">>>>> " + doubleExcl(20.6, 20.1, 20.5));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
