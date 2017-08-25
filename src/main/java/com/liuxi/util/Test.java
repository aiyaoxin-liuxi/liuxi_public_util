package com.liuxi.util;

import java.util.HashMap;
import java.util.Map;

import org.pub.util.security.MessageSecurity;

public class Test {
	
public static void main(String[] args) {
		
		System.out.println(MessageSecurity.getMessageSecurity(null, "123456","123"));
		System.out.println(MessageSecurity.getMessageSecurity("", "123456","123"));
		System.out.println(MessageSecurity.getMessageSecurity("123", "","123"));
		System.out.println(MessageSecurity.checkMessageSecurityIgnoreCase("123", "123123",MessageSecurity.getMessageSecurity("123", "123123","123").toLowerCase(),"123"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", "123");
		map.put("test1", "t1");
		map.put("test2", "t2");
		map.put("test3", "t3");
		map.put("test5", "t5");
		map.put("test6", "t6");
		map.put("test4", "t4");
		
		System.out.println(MessageSecurity.getMapMessageSecurity(map,"123"));
		String sign = MessageSecurity.getMapMessageSecurity(map,"123");
		map.put("sign", sign);
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("sign", sign);
		map1.put("userId", "123");
		map1.put("test1", "t1");
		map1.put("test2", "t2");
		map1.put("test3", "t3");
		map1.put("test5", "t5");
		map1.put("test6", "t6");
		map1.put("test4", "t4");
		System.out.println(MessageSecurity.checkMapMessageSecurityIgnoreCase(map,"123"));
		System.out.println(MessageSecurity.checkMapMessageSecurity(map1,"123"));
		
	
	}


}
