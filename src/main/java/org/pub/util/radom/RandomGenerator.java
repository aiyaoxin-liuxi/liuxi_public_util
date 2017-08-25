package org.pub.util.radom;

import java.util.Random;
import java.util.UUID;

/**
 * 
 *
 * <strong>RandomGenerator</strong>. <br> 
 * <strong>Description :  随机字串生成工具类.</strong> <br>
 * <strong>Create on : 2016年9月21日 上午9:39:47</strong>. <br>
 * <p>
 * <strong>Copyright (C) zhl Co.,Ltd.</strong> <br>
 * </p>
 * @author zts zhaotisheng@qq.com <br>
 * @version <strong>zhl-0.1.0</strong> <br>
 * <br>
 * <strong>修改历史: .</strong> <br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class RandomGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String randomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 获取一定长度的随机阿拉伯数字字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String randomDigital(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 获取10位当前时间加一定长度的随机阿拉伯数字字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String randomTimeDigital(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String time = Long.toString(System.currentTimeMillis() / 1000);
        return time+sb.toString();
    }
    
    /**
     * 获取当前时间毫秒数加一定长度的随机阿拉伯数字字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String randomTimeMillis(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String time = Long.toString(System.currentTimeMillis());
        return time+sb.toString();
    }
    
    
    
    /**
     * @author by zts Jun 17, 2015
     *
     * @desc 获取36位UUID字符串.
     * @param isLine 是否生成连接线, 默认true为生成
     * @return
     */
    public static String randomUUID(boolean isLine) {
    	String uuid = UUID.randomUUID().toString();
    	if(!isLine){
    		uuid = uuid.replace("-", "");
    	}
        return uuid;
    }
    
}
