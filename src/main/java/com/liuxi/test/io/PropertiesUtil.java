package com.liuxi.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
	
	private static Properties p = null;
	
	static 
	{
		if(p == null){
			p = new Properties();
		}
		InputStream in = PropertiesUtil.class.getResourceAsStream("/pub-util.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final static String getProperty(String key) {
		return p.getProperty(key);
	}
	
	public static void main(String[] args) {
		String property = PropertiesUtil.getProperty("inputUrl");
		System.out.println(property);
	}

}
