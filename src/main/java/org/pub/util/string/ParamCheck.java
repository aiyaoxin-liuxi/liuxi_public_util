package org.pub.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamCheck {
	
	// 手机号验证
	private static final String CHECK_MOBILE = "^((13[0-9])|(15[0-9])|147|(17[0-9])|(18[0-9]))[0-9]{8}$";
	// 数字验证
	private static final String CHECK_DIGIT = "^\\d+$";
	// 身份证号验证
	private static final String CHECK_IDCARD = "(^\\d{18}$)|(^\\d{15}$)";
	// 特殊字符验证
	private static final String CHECK_SPECIALCHARACTER = "[`#$^&*()+=|{}\\[\\]<>/~@#￥……&*——+|{}]";
	// 金额验证（小数点后两位）
	private static final String CHECK_DOUBLE = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
	// 验证图片后缀
	private static final String CHECK_IMG = "^\\.(jpg|gif|png|bmp|jpeg|JPG|GIF|PNG|BMP|JPEG)$";
	// 邮箱验证
	private static final String CHECK_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	// 中文验证
	private static final String CHECK_CH = "^[\u4e00-\u9fa5]{0,}$";
	// 英文和数字
	private static final String CHECK_EN_NO = "^[A-Za-z0-9]+$";
	
	/**
	 * 验证是否为手机号
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Pattern compile = Pattern.compile(CHECK_MOBILE);
		Matcher matcher = compile.matcher(mobile);
		return matcher.matches();
	} 

	/**
	 * 验证是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		Pattern compile = Pattern.compile(CHECK_DIGIT);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}

	/**
	 * 验证是否为身份证号
	 * @param str
	 * @return
	 */
	public static boolean isIDNo(String str) {
		Pattern compile = Pattern.compile(CHECK_IDCARD);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}

//	/**
//	 * 验证是否存在特殊字符
//	 * @param str
//	 * @return
//	 */
//	public static boolean specialCharacter(String str) {
//		Pattern compile = Pattern.compile(CHECK_SPECIALCHARACTER);
//		Matcher matcher = compile.matcher(str);
//		return matcher.matches();
//	}
	
	/**
	 * 验证是否包含特殊字符，包含返回true
	 * @param str
	 * @return
	 */
	public static boolean haveSpecialCharacter(String str) {
		Pattern compile = Pattern.compile(CHECK_SPECIALCHARACTER);
		Matcher matcher = compile.matcher(str);
		return matcher.find();
	}
	
	/**
	 * 验证是否是金额，小数点后两位
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern compile = Pattern.compile(CHECK_DOUBLE);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证图片后缀
	 * @param suffix
	 * @return
	 */
	public static boolean isImgSuffix(String suffix) {
		Pattern compile = Pattern.compile(CHECK_IMG);
		Matcher matcher = compile.matcher(suffix);
		return matcher.matches();
	}
	
	/**
	 * 验证电子邮箱
	 * @param mail
	 * @return
	 */
	public static boolean isEmail(String mail){
		Pattern compile = Pattern.compile(CHECK_EMAIL);
		Matcher matcher = compile.matcher(mail);
		return matcher.matches();
	}
	
	/**
	 * 中文验证
	 * @param mobile
	 * @return
	 */
	public static boolean isCh(String str) {
		Pattern compile = Pattern.compile(CHECK_CH);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 英文和数字验证
	 * @param mobile
	 * @return
	 */
	public static boolean isEnAndNo(String str) {
		Pattern compile = Pattern.compile(CHECK_EN_NO);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	
	public static void main(String[] args) {
		System.out.println(isEnAndNo("123a"));
	}
	
	
	
	
	
	public static boolean isFixedTelephone(String str) {
		Pattern compile = Pattern.compile("^[0-9-]+$");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	public static boolean isURL(String str_url){  
        String strRegex = "^((https|http|ftp|rtsp|mms)://)"   
        + "(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@   
        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184   
        + "|" // 允许IP和DOMAIN（域名）  
        + "(([0-9a-zA-Z_!~*'()-]+\\.)*" // 域名- www.   
        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\\." // 二级域名   
        + "[a-zA-Z]{2,6})|([0-9a-zA-Z]{1,}))" // first level domain- .com or .museum 
        + "(:[0-9]{1,5})?" // 端口- :80   (爱农给的是5位的"http://27.115.49.122:38280/bas/FrontTrans"; 20160803修改)
        + "((/?)|" // a slash isn't required if there is no file name   
        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
        Pattern pattern = Pattern.compile(strRegex);
        Matcher m=pattern.matcher(str_url);  
        return m.matches();
    }
}
