package com.liuxi.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.liuxi.util.id.GlobalVar;

public class Util {
    static Logger logger = Logger.getLogger("exchdaily");
	public static SimpleDateFormat sdfHHmmss = new SimpleDateFormat("HHmmss");
	
	public static String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		int port = request.getServerPort();
		String basePath = null;
		String proto=request.getHeader("X-Forwarded-Proto");
		String scheme =  request.getScheme();
		String sche=(String)request.getSession().getAttribute("scheme");

		if(sche==null || sche.trim().equals("")){
		    if(proto!=null){
		        scheme=proto;
		    }
		}else{
		    scheme=sche;
		}
		if(80 == port){
		    basePath = scheme+"://"+request.getServerName() + path;
		}else{
		    basePath = scheme+"://"+request.getServerName()+":" + port + path;
		}
		return basePath;
	}
	
	/**
	 * 验证金额
	 */
	public static boolean checkMoney(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * strDate to format Date
	 * @param strDate
	 * @param format
	 * @return
	 */
    public static Date convert(String strDate,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//String strDate = "2013-4-7 17:43:01";
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
    public static Date convert(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String strDate = "2013-4-7 17:43:01";
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
    
    /**
     * getYesterDay
     * 
     * @param format
     * @return yesterday
     */
    public static String getYesterDay(String format){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.add(Calendar.DATE,   -1);
    	String yesterday = new SimpleDateFormat(format).format(cal.getTime());
    	return yesterday;
    }
	
	public static String convertToStr(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return fmt.format(date);
	}
	
	/**
	 * pattern 'yyyy-MM-dd'
	 */
	public static String getSimpleDateStr(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
	
	public static String cDodatetime(String doDate,String doTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//String strDate = "2013-4-7 17:43:01";
		Date date = null;
		try {
			date = sdf.parse(doDate+doTime);
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return fmt.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String currentDate() {
		return convertToStr(new Date());
	}
	
	public static String getDodate(String format) {
		SimpleDateFormat fmt=new SimpleDateFormat(format);
		return fmt.format(new Date());
	}
	
	public static String getDodate(Date date,String format) {
		SimpleDateFormat fmt=new SimpleDateFormat(format);
		return fmt.format(date);
	}
	
    public static Date getLongDate(Date date,int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
	public static String doubletoStr(Double d) {
		if (d != null) {
			return d.toString();
		} else {
			return "";
		}
	}
	public static String killNull(String param) {
		if (null != param && param.equalsIgnoreCase("null")) {
			return "";
		} else if (param == null) {
			return "";
		} else {
			return param;
		}
	}
	public static String getStr(String param) {
		if (null != param && !"".equals(param)) {
			return String.valueOf(param);
		} else {
			return "";
		}
	}
	public static Double getDouble(String param) {
		double d = 0d;
		if (null != param && !"".equals(param)) {
			try {
				d = Double.parseDouble(param);
			} catch (Exception ex) {
				return 0d;
			}
		}
		return d;
	}
	public static String MapToStr(Map<String, String> properties) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
        	sb.append(entry.getKey());
        	sb.append("=");
        	sb.append(entry.getValue());
        	sb.append(",");
        	
        }

        return sb.toString();
    }
	
	
	public static boolean isUrl(String url) {
        if(url == null) {
            return false;
        }
        String regEx = "https?://[a-zA-Z0-9._-]+[:]?[0-9]*+[=&#$%!/\\a-zA-Z0-9.?_-]*+";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(url);
        return matcher.matches();
        
    }
	
	/**
	 * 生成key1（order_parent.trid）
	 * key1=yyMMddHHmmss+3位流水号
	 * @return
	 */
	public static String genKey1() {
		//生成yyMMddHHmmss
		String date = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		int intVal=0;
		String sn = "";
		synchronized (GlobalVar.Key1Sn) {
		    if (!GlobalVar.Key1Sn.getKey().equals(date)) {
		        GlobalVar.Key1Sn.setKey(date);
		        GlobalVar.Key1Sn.setValue(0);
            } else {
                GlobalVar.Key1Sn.setValue(GlobalVar.Key1Sn.getValue()+1);
            }
		    intVal = GlobalVar.Key1Sn.getValue();
		    
			if (intVal < 10) {
				sn = "00"+String.valueOf(intVal);
			} else if (intVal >=10 && intVal < 100) {
				sn = "0"+String.valueOf(intVal);
			}
		}
//		return SysConfig.get("sys.config.id")+date+sn;
		return null;
	}
	/**
	 * 线上支付使用
	 * Key2=key1（15）+流水号（1）+银行（3）+支付方式（1）
	 * @param key1=order_parent.trid
	 * @param tran_type
	 * @param acc_bank
	 * @return
	 * @throws Exception 
	 */
	public static String genKey2(String key1,String index) throws Exception {
		if (null == key1) {
			throw new Exception("key1为空!");
		}
		if (index == null) {
		    index = "0";
        }
		//return key1+String.valueOf(intVal)+acc_bank+tran_type;
		return key1+index;
	}
	/**
	 * 管理类使用
	 * @param key1
	 * @return
	 * @throws Exception
	 */
	public static String genKey2(String key1) throws Exception {
        if (null == key1) {
            throw new Exception("key1为空!");
        }
        
        return genKey2("0"+key1,genRandomNum(1));
    }
	public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }
	/**
	 * 获取客户端IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    logger.info("x-forwarded-for>>>>>" + ip);

	    ip = request.getHeader("Proxy-Client-IP");
	    logger.info("Proxy-Client-IP>>>>>" + ip);

	    ip = request.getHeader("WL-Proxy-Client-IP");
	    logger.info("WL-Proxy-Client-IP>>>>>" + ip);

	    ip = request.getRemoteHost();
	    logger.info("RemoteHost>>>>>>>>>>" + ip);

	    ip = request.getRemoteAddr();
	    logger.info("remoteAddr>>>>>>>>>>" + ip);
	    try
	    {
	      String host = InetAddress.getLocalHost().getHostAddress();
	      logger.info("host>>>>>>>>>>" + host);
	    } catch (UnknownHostException e) {
	      e.printStackTrace();
	    }
	    return ip;
	}
	
	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        int i = 0;
        int j = 0;
        for (int l = hex.length(); i < l; j++) {
            String swap = "" + arr[(i++)] + "" + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();

            i++;
        }

        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }
    public static int toInt2(byte[] b) {
        int tmp = 0;
        if (b[1] < 0) {
            tmp = b[1] + 256;
        } else
            tmp = b[1];
        return b[0] * 256 + tmp;
    }
    public static String [] split(String source,String regex) {
        if (null == source || regex == null) {
            return null;
        }
        if (source.lastIndexOf(regex) != -1) {
            source = source + " ";
        }
        return source.split(regex);
    }
    public static String getEbcAccTypeByAccBank(String accBank) {
        if (null == accBank || "".equals(accBank)) {
            logger.info("银行编码为空");
            return null;
        }
        return accBank.substring(1, accBank.length());
    }
    
    public static String validatorRetrunMessage(String str){
    	if(str != null && str.trim().length()>0&&!str.trim().toLowerCase().equals("null")){
    		return str.trim();
    	}else{
    		return "";
    	}
    }
    public static String getCurrencyCode(String idx){
    	if(idx==null||idx.trim().length()==0||idx.trim().equalsIgnoreCase("null")){
//    		return Constants.CURRENCY_RMB;
    		return null;
    	}
    	return "RM";
    }

    
    public static boolean checkEmail(String email){
    	boolean bl =false;
    	//String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$"; 
    	String str="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
    	Pattern pattern = Pattern.compile(str);  
    	Matcher matcher = pattern.matcher(email);  
    	if(matcher.matches()){
    		bl=true;
    	}  
    	return bl;
    }

    public static boolean checkMobile(String mobile){
    	boolean bl =false;
    	String str="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"; 
    	Pattern pattern = Pattern.compile(str);  
    	Matcher matcher = pattern.matcher(mobile);  
    	if(matcher.matches()){
    		bl=true;
    	}
    	return bl;
    }
    
    
    public static void main(String args[]){
    	System.out.println(getYesterDay("yyyyMMdd"));
    	
    	
    	String numRegex = "^[0-9]*$";
    	
    	String abc="1a23";
    	System.out.println(abc.matches(numRegex));
    }
}
