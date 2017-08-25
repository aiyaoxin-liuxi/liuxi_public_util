package org.pub.util.amount;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 货币转换
 * 
 * 		货币元和分的转换
 * 
 * @author 刘熙
 *
 * 2017-5-17
 */
public class Amount {
	
	
	/**
	 * 将元字符串转换为分
	 * 
	 * @param amountStr 转换的元
	 * 
	 * @return 转换后的分
	 */
	public static long parseAmountStr2Long(String amountStr) {
		if (amountStr == null || "".equals(amountStr)) {
			return 0L;
		}
		double amount = Double.parseDouble(amountStr);
		Double db = amount * 100;
		DecimalFormat df = new DecimalFormat("#");
		String s = df.format(db);
		return Long.parseLong(s);
	}

	/**
	 * 将分转换为元
	 * 
	 * @param amountStr 转换的分
	 * @return	转换后的元
	 */
	public static String parseAmountLong2Str(Long amountLong) {
		if (amountLong == null) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double d = amountLong / 100d;
		String s = df.format(d);
		return s;
	}

	/**
	 * 将元转换为分
	 * 不要小数
	 * @param yuan
	 * @return
	 */
	public static String yuan2Fen(Double yuan) {
		Double dFen = yuan * 100;
		Long lFen = dFen.longValue();
		DecimalFormat df = new DecimalFormat("0");
		return df.format(lFen);
	}
	
	/**
	 * 去掉小数点
	 */
	public static String spitStr(String amountStr) {
		int index = amountStr.indexOf(".");
		if (index != -1) {
			amountStr = amountStr.substring(0, index);
		}
		return amountStr;
	}
	
	/**
	 * 元转换为分
	 * @param yuan
	 * @return
	 */
	public static String fromYuanToFen(final String yuan) {  
        String fen = "";  
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");  
        Matcher matcher = pattern.matcher(yuan);  
        if (matcher.matches()) {  
            try {  
                NumberFormat format = NumberFormat.getInstance();  
                Number number = format.parse(yuan);  
                double temp = number.doubleValue() * 100.0;  
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
                format.setGroupingUsed(false);  
                // 设置返回数的小数部分所允许的最大位数  
                format.setMaximumFractionDigits(0);  
                fen = format.format(temp);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }else{  
//        	logger.info("元转分---参数格式不正确!");  
        }  
        return fen;  
    }  
	
	
	public static void main(String[] args) {
		System.out.println("1");
	}

}
