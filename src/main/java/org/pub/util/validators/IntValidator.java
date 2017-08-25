package org.pub.util.validators;

/**
 * int验证
 *
 */
public class IntValidator {

    /**
     * int.包含验证
     */
    public static String intIncl(int num, int min, int max) {
        return intIncl(num, min, max, null);
    }

    /**
     * int.包含验证
     */
    public static String intIncl(int num, int min, int max, String msg) {
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
     * int.排除验证
     */
    public static String intExcl(int num, int min, int max) {
        return intExcl(num, min, max, null);
    }

    /**
     * int.排除验证
     */
    public static String intExcl(int num, int min, int max, String msg) {
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
//            System.out.println(">>>>> " + intIncl(196, 201, 205));
//            System.out.println(">>>>> " + intIncl(202, 201, 205));
//            System.out.println(">>>>> " + intIncl(206, 201, 205));
//
//            System.out.println("");
//
//            System.out.println(">>>>> " + intExcl(196, 201, 205));
//            System.out.println(">>>>> " + intExcl(202, 201, 205));
//            System.out.println(">>>>> " + intExcl(206, 201, 205));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
