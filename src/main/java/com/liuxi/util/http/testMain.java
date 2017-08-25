package com.liuxi.util.http;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pub.util.json.JsonUtil;


public class testMain {
	public static boolean isNumber(String str){   
	     Pattern pattern=Pattern.compile("^(([1-9]+)|([0-9]+\\.[0-9]{1,2}))$"); // 判断小数点后2位的数字的正则表达式  
	     Matcher match=pattern.matcher(str);   
	     if(match.matches()==false){   
	        return false;   
	     }else{   
	        return true;   
	     }   
	 }
	public static boolean isPositiveInteger(String pageNumber){
		Pattern pattern=Pattern.compile("^[1-9]\\d*$");
		Matcher match=pattern.matcher(pageNumber);  
		 if(match.matches()==false){   
			 return false;   
		 }else{   
		     return true;   
		 } 
	}
	/**
	 * 子订单生成接口
	 * 调用订单系统接口
	 */
	public static String getOrderListCreate(Map<String,Object> mapList){
		Map<String,Object> map = mapList;
		return JsonUtil.getMapToJson(map);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> mapStr1 = new HashMap<String, String>();
		mapStr1.put("aa", "11");
		mapStr1.put("bb", "22");
		Map<String,String> mapStr2 = new HashMap<String, String>();
		mapStr2.put("aa", "33");
		mapStr2.put("bb", "44");
		//
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(mapStr1);
		list.add(mapStr2);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("orders", list);
		System.out.println(getOrderListCreate(map));
	}

}
