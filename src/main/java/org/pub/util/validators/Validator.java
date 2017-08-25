package org.pub.util.validators;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.pub.util.BigDecimalUtil.BigDecimalUtil;

/**
 * 数据验证
 *
 */
public class Validator {
	
	/**
	 * 判断某对象是否为空.
	 * @param obj
	 * @return true:空；false不为空
	 */
	public static boolean isNullOrEmpty(Object obj) {

		boolean result = true;
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
		} else if (obj instanceof Collection) {
			result = ((Collection<?>) obj).size() == 0;
		} else if (obj instanceof Map) {
			result = ((java.util.Map<?,?>) obj).isEmpty();
		}else if(obj instanceof BigDecimal){
			// 不能小于0
			result = BigDecimalUtil.BigDecimalLessThan((BigDecimal)obj, new BigDecimal(0)) ? true : false;
		} else {
			result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true : false;
		}
		return result;
	}

    //-------------------------------------------------------------------------- StringValidator
    /**
     * 字符串是否为必须字段
     */
    public static boolean required(String str) {
        if (StringValidator.required(str) == null) {
            return true;
        }
        return false;
    }

    /**
     * 字符串长度验证
     */
    public static boolean stringLength(String str, int min, int max) {
        if (StringValidator.stringLength(str, min, max, null) == null) {
            return true;
        }
        return false;
    }

    //-------------------------------------------------------------------------- DateValidator
    /**
     * 日期时间验证
     */
    public static boolean datetime(Date date, String min, String max) {
        if (DateValidator.datetime(date, min, max) == null) {
            return true;
        }
        return false;
    }

    /**
     * 日期时间验证
     */
    public static boolean datetime(Date date, String min, String max, String format) {
        if (DateValidator.datetime(date, min, max, format, null) == null) {
            return true;
        }
        return false;
    }

    /**
     * 日期验证
     */
    public static boolean date(Date date, String min, String max) {
        if (DateValidator.date(date, min, max) == null) {
            return true;
        }
        return false;
    }

    /**
     * 日期验证
     */
    public static boolean date(Date date, String min, String max, String format) {
        if (DateValidator.date(date, min, max, format, null) == null) {
            return true;
        }
        return false;
    }

    //-------------------------------------------------------------------------- DoubleValidator
    /**
     * double.包含验证
     */
    public static boolean doubleIncl(double num, double min, double max) {
        if (DoubleValidator.doubleIncl(num, min, max) == null) {
            return true;
        }
        return false;
    }

    /**
     * double.排除验证
     */
    public static boolean doubleExcl(double num, double min, double max) {
        if (DoubleValidator.doubleExcl(num, min, max) == null) {
            return true;
        }
        return false;
    }

    //-------------------------------------------------------------------------- IntValidator
    /**
     * int.包含验证
     */
    public static boolean intIncl(int num, int min, int max) {
        if (IntValidator.intIncl(num, min, max) == null) {
            return true;
        }
        return false;
    }

    /**
     * int.排除验证
     */
    public static boolean intExcl(int num, int min, int max) {
        if (IntValidator.intExcl(num, min, max) == null) {
            return true;
        }
        return false;
    }
}
