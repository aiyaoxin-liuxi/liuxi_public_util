package com.liuxi.util.notify;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "md5Str=ac6bbdd0253696cdfee67b2f0de88085&resData=mobile%3D15010001173%26msgCode%3D777777%26orderId%3Dpan_112233445566778899%26recommendPhone%3D15010001161%26registerType%3D00&keyStr=112233";
		//转换成map
		String[] aOne = a.split("&");
		String[] value =null;
		Map<String,String> map = new LinkedHashMap<String,String>();
		for(int i= 0;i<aOne.length; i++){
			value = aOne[i].split("=");
			map.put(value[0], value[1]);
		 }
		System.out.println(map.get("resData"));
	}

}
