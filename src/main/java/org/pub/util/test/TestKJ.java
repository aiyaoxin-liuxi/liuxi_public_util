package org.pub.util.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.pub.util.https.HttpClientUtil;
import org.pub.util.json.JsonUtil;
import org.pub.util.security.MessageSecurity;
/**
 *  模拟方法
 *
 * <strong>TestKJ</strong>. <br> 
 * <strong>Description : TODO...</strong> <br>
 * <strong>Create on : 2017年1月5日 下午4:52:25</strong>. <br>
 * <p>
 * <strong>Copyright (C) zhl Co.,Ltd.</strong> <br>
 * </p>
 * @author zts zhaotisheng@qq.com <br>
 * @version <strong>zhl-0.1.0</strong> <br>
 * <br>
 * <strong>修改历史: .</strong> <br>
 * 修改人 修改日期 修改描述<br>
 * Copyright ©  zhl by zts Inc. All Rights Reserved
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class TestKJ {

	public static final String enkey="cashier";
	
	public static void main(String[] args) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {
		
//		test_step1();//发送验证码(安农快捷)
		
//		String smsCode="601424";
//		String orderid="17010515177991661483";
//		test_step2(smsCode,orderid);//确认支付(安农快捷)
//		
		
		
		
//		testWX_step1();
		
		testWX_step1_outer();//模拟以通道形式提供接口 外放
		//		
//		String orderTranOrderId="16121610388670523574";
//		testQueryDetail(orderTranOrderId);
	}
	
    private static void testWX_step1_outer() throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {
    	Map<String, Object> map = new HashMap<String,Object>();
    	Random random = new Random();
    	map.put("oriordercore", "merorderid2275448773"+random.nextInt(100));//上送商户号 1
    	map.put("amount", "1");//分  2
    	map.put("merchno", "merchno459789");// 3
    	map.put("platcode", "99");// 4
    	map.put("ordertype", "0");//被扫  5
    	map.put("channelid", "WECHAT");//M
    	map.put("currency", "CNY");//MMM
    	map.put("nfc_type", "nfc_passive");//被扫 MMM
    	map.put("webyurl", "http://localhost/ppayTestMer/wallet/new/payResSYNC");//异步通知地址
    	map.put("merchname", "merchname459789");//被
    	String sign = MessageSecurity.getMapObjMessageSecurity(map,enkey);
		map.put("sign", sign);
		
    	String json = JsonUtil.getMapToJson(map);
		String o = HttpClientUtil.httpPost("http://localhost:7020/cashier-site/service/ttransactionCreate", null, json);//.doHttpClient("http://localhost:8081/cashier-site/service/ttransactionCreate", "POST", "utf-8", json.toString(), "60000","application/json","");
		System.err.println("Not yet xxxxxxxxxxxxx  end \t\r\n "+o);
		
		Map<String, Object> hashMap2 = JsonUtil.getJsonToMap(new String(o));
//		 hashMap2 = JsonUtil.toMap(new StringBuilder(o));
			boolean res = MessageSecurity.checkMapObjMessageSecurity(hashMap2,enkey);
			if(!res){
				System.out.println("验签失败iffail");
			}else{
				System.out.println("验签成功succ。。。。。。。。。。。。 ");
				
			}
    }

	
}
