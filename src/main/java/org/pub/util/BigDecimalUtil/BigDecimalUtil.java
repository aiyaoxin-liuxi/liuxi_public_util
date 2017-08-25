package org.pub.util.BigDecimalUtil;

import java.math.BigDecimal;

/**
 * BigDecimal 相关
 * 
 * @author 刘熙
 *
 * 2017-5-17
 */
public class BigDecimalUtil {
	
	 static java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
	
	/**
	 * 空bigdecimal转为0
	 * @param b
	 * @return
	 */
	public static BigDecimal EmptyToNull(BigDecimal b){
		return null == b ? new BigDecimal(0) : b;
	}
	
	/**
	 * BigDecimal空值填充保留两位小数（四舍五入）
	 * @param obj
	 * @return
	 */
	public BigDecimal BigDecimalNotNull_UP(BigDecimal b){
		return b == null ? new BigDecimal(0) : b.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * BigDecimal空值填充
	 * @param obj
	 * @return
	 */
	public BigDecimal BigDecimalNotNull(BigDecimal b){
		return b == null ? new BigDecimal(0) : b;
	}
	
	/**
	 * BigDecimal比较————大于等于
	 * 
	 * 		左大于等于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalGreaterThanAndEqual(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2) == 1 || val1.compareTo(val2) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * BigDecimal比较————小于等于
	 * 
	 * 		左小于等于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalLessThanAndEqual(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2) == -1 || val1.compareTo(val2) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * BigDecimal比较————大于
	 * 
	 * 		左大于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalGreaterThan(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == 1 ? true : false;
	}
	
	/**
	 * BigDecimal比较————小于
	 * 
	 * 		左小于右返回true
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalLessThan(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == -1 ? true : false;
	}
	
	/**
	 * BigDecimal比较————等于
	 * 
	 * 		左等于右返回true
	 * 
	 * @param val1	
	 * @param val2
	 * @return
	 */
	public static boolean BigDecimalequal(BigDecimal val1, BigDecimal val2){
		return val1.compareTo(val2) == 0 ? true : false;
	}
	
	public static void main(String[] args) {
		System.out.println(add(new BigDecimal(0.01),null));
	}
	/**
	 * 
	  *相加  保留2位
	  * @Title: add
	  * @Description: TODO
	  * @param @param b1
	  * @param @param b2
	  * @param @return    设定文件
	  * @return BigDecimal    返回类型
	  * @throws
	 */
	public static BigDecimal add(BigDecimal b1, BigDecimal b2){
		return new BigDecimal(df.format(EmptyToNull(b1).add(EmptyToNull(b2))));
	}
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
}
