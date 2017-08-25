package org.pub.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
  * @ClassName: PropsHandler
  * @author zhaotisheng	
  * @date 2017年3月17日 下午4:58:13
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public final class PropsHandler {
	

	private static Properties props = null;
	private static Properties props4Util = null;
	static {
		try {
			if(null == props){
				props = new Properties();
			}
			if(null == props4Util){
				props4Util = new Properties();
			}
			InputStream in = PropsHandler.class.getResourceAsStream("/application.properties");
			InputStream in4Util = PropsHandler.class.getResourceAsStream("/pub-util.properties");
			props.load(in);props4Util.load(in4Util);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		String property = PropsHandler.getProperty("cashier.url.root");
		System.out.println(property);
	}
	public final static String getProperty(String key) {
		return props.getProperty(key);
	}
	public final static String getProperty4Util(String key) {
		return props4Util.getProperty(key);
	}

	public final static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
}
