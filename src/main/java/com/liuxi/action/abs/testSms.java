package com.liuxi.action.abs;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

import org.pub.util.https.HttpClientUtil;
import org.pub.util.json.JsonUtil;

public class testSms {

	public static void main(String[] args) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Business_sms bc = new Business_sms();
		String service = "";

		// 短信余额查询
//		map = bc.getUserInfo();
//		service = "getUserInfo";
		
		// 发送短信
		map = bc.sendSms();
		service = "sendSms";
		
		// 批量发送短信
//		map = bc.batchSendSms();
//		service = "batchSendSms";
		
		String dbankUrl = null;
		
//		map.put("username", "lx");
		
		/** 开发环境地址 */
//		dbankUrl = "http://119.90.53.150:8070/creditCard-butler/userInfo/upLoadHeadImg";
//		dbankUrl = "http://127.0.0.1:8070/SMSPlatform/sms/"+service;
//		dbankUrl = "http://218.240.148.254/SMSPlatform/sms/"+service;
		
//		dbankUrl = "http://192.168.0.172:8070/SMSPlatform/users/register";
		 
		String json = JsonUtil.getMapToJson(map);
		System.out.println(json);
		json = HttpClientUtil.httpPost(dbankUrl, "", map);
//		json = HttpClientUtil.httpGet(dbankUrl, "", map);
//		json = HttpClientUtil.httpPost(dbankUrl, "", mapStr);
		
		System.out.println("通知结果:"+json);
	}

}
