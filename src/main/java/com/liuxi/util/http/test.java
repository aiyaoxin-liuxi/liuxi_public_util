package com.liuxi.util.http;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pub.util.json.JsonUtil;

import com.google.common.collect.Maps;




public class test {
	
	/**
	 * 参数规则排序
	 * @param map
	 */
	public static String getBuildPayParams(Map<String,Object> map){
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String s = "";
		for(String key : keys){
			if(map.get(key) != null && !map.get(key).equals("")){
				//if(!key.equals("sign")&&!key.equals("serverCert")&&!key.equals("serverSign")){
					s += key + "=" + map.get(key) + "&";
				//}
			}
        }
		return s.substring(0, s.length()-1);
	}

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		/**
		 * 注册(手机号无密注册，同时生成多电子账户)
		 * 手机有密注册
		 * （手机号是否重复，手机验证码是否失效）
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("mobile", "13888888888");//用户注册手机号
//		map.put("recommendPhone", "15010002222");//推荐人手机号
//		map.put("msgCode", "545791");//手机验证码
//		map.put("registerType", "02");//01手机有密注册，02手机无密注册
//		map.put("password", "123456");//注册密码
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://localhost:8098/dbank-site/service/register");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://localhost:8098/dbank-site/service/register");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		
		/**
		 * 发送短信验证码
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("mobile", "13888888888");//手机号
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8090/dbank-site/service/sendMsgCode");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://localhost:8098/dbank-site/service/sendMsgCode");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		/**
		 * 登陆（手机无密登陆，手机有密登陆，QQ登陆，WECHAT登陆）
		 * QQ&&WECHAT如果没有用户，自动注册跳转登陆	
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("loginType", "05");//登陆类型01手机有密登陆,02手机无密登陆,03QQ登陆,04微信登陆,05商户号授权登陆
//		map.put("loginMedium","62220000000000000000");//登陆媒介（mobile||QQ||WECHAT||MERCHNO）
//		map.put("password", "");//登陆密码
//		map.put("msgCode", "");//手机验证码
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8098/dbank-site/service/userLogin");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/userLogin");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		/**
		 * 电子账户转账（账户金额增减，交易流水）
		 * 个人对个人
		 * 注意  接口对外需验证  业务类型和转出的电子账户号是否匹配
		 * 转账加如冻结金额 （余额=转账金额-冻结金额）
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("tradeChannel", "01");//交易渠道号
//		map.put("userId", "257477");//用户id
//		map.put("businessType", "05");//业务类型01（转账）授信业务，05（转账）余额业务
		/**
		 * 注：授信自动还款和授信自动放款 不验证支付密码
		 */
//		map.put("subBusinessType", "01");//子业务类型01授信放款，02授信自动放款，03授信还款，04授信自动还款
//		map.put("outAccountNo", "1500000000121692");//转出电子账户号
//		map.put("inMobile", "13211111111");//转入手机号
//		//map.put("checkType", "01");//验证类型（01支付密码，02手机验证码&&支付密码）---去掉此参数
//		map.put("payPass", "123456");//支付密码
//		//map.put("msgCode", "523659");//手机验证码
//		map.put("amount", "9");//金额
//		map.put("freezeAmount", "4.5");//冻结金额(非必填项，如果传值将对转入的余额和冻结金额进行操作)
//		map.put("remark", "个人对个人转账");//交易备注
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://localhost:8090/dbank-site/service/accTransPersonToPerson");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/accTransPersonToPerson");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/		
		/**
		 * 电子账户转账（账户金额增减，交易流水）
		 * 银行卡对个人
		 * userId和转出电子账户来验证是否真实（放款除外）
		 * 增加异步通知地址参数 
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("tradeChannel", "01");//交易渠道号
//		map.put("userId", "1417");//用户id
//		map.put("businessType", "05");//业务类型01（转账）授信业务，05（转账）余额业务
		/**
		 * 注：授信自动还款和授信自动放款 不验证支付密码
		 */
//		map.put("subBusinessType", "01");//子业务类型01授信放款，02授信自动放款，03授信还款，04授信自动还款
//		map.put("outBankCardNo", "9400301100000748266");//转出银行卡
//		map.put("inMobile", "15010001182");//转入手机号
//		map.put("amount", "0.01");//金额
//		map.put("notifyUrl", "http://www.baidu.com");//银行卡转账的异步通知地址
//		map.put("remark", "银行卡对个人转账");//交易备注
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8098/dbank-site/service/accTransBankCardToPerson");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/accTransBankCardToPerson");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		/**
		 * 电子账户充值请求（普通充值）
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("tradeChannel", "01");//交易渠道号
//		map.put("userId", "257477");//用户id
//		map.put("businessType", "01");//业务类型01（转账）授信业务，05（转账）余额业务
//		map.put("outBankCardNo", "6222021919009098887");//转出银行卡
//		map.put("amount", "0.06");//金额
//		map.put("notifyUrl", "http://www.baidu.com");//银行卡转账的异步通知地址
//		map.put("remark", "普通充值");//交易备注
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8098/dbank-site/service/accRecharge");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String orderId = map.get("orderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", orderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/accRecharge");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		/**
		 * 
		 * 电子账户充值确认
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "170104212439");//商户订单号
//		map.put("userId", "262393");
//		map.put("msgCode", "184344");
//		map.put("payPass", "999999");
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8090/dbank-site/service/accRechargeOk");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**
		 * 模拟充值时通道方调用我的异步通知
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "panTest_1483587715715");//收银台订单号（外部通道订单号）
//		map.put("oriordercore", "170105114227");//我方调用通道传过去的订单号（下游系统上送的订单号）
//		map.put("paystatus", "000000");//支付结果
//		map.put("amount", "5500");//支付金额，单位为分
//		map.put("plattime", "");//交易时间）
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8090/dbank-site/service/accRechargeNotify");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String outOrderId = map.get("outOrderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", outOrderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/accRechargeNotify");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
		
		/**
		 * 模拟内管系统调用充值流水查询接口
		 * 直接调用EAMS（备用） 
		 */
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String startDate = "2015-02-03 15:30:00";
//		String endDate = "2016-12-29 21:54:34";
//		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//		xml += "<eams>";
//		xml += "<serviceCode>accRechargeWaterList</serviceCode>";
//		xml += "<orderId>pan_1481959565002</orderId>";
//		xml += "<status>00</status>";
//		xml += "<rechargeMobile>15010001111</rechargeMobile>";
//		xml += "<pageNumber>1</pageNumber>";
//		xml += "<pageSize>25</pageSize>";
//		xml += "<startDate>"+startDate+"</startDate>";
//		xml += "<endDate>"+endDate+"</endDate>";
//		xml += "</eams>";
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:9080/ifp-eams-provider/service/accRechargeWaterList?format=xml");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/xml;charset=UTF-8");
//		http.setContext(xml);
//		System.out.println("订单号："+"111111111111111"+"发送商户的报文："+xml);
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+"111111111111111"+"发送商户结果："+resp.getContent());
		/**
		 * 查询EAMS充值交易流水（含分页）
		 */
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("orderId", "pan_"+System.currentTimeMillis());//商户订单号
//		map.put("mobile", "15010001161");//手机号
//		map.put("accountNo", "1500000000106635");//电子账户号
//		map.put("startTransDate", "2016-12-03 00:00:00");//开始交易时间
//		map.put("endTransDate", "2016-12-23 15:17:34");//结束交易时间
//		HttpRequestParam http = new HttpRequestParam();
//		http.setUrl("http://127.0.0.1:8098/dbank-site/service/accRechargeWaterList");
//		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
//		http.setContext(JsonUtil.getMapToJsonStr(map));
//		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
//		http.setHeads(heads);
//		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
//		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		
		
		
		/**
		 * liuxi--test
		 */
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "lx");
		HttpRequestParam http = new HttpRequestParam();
		http.setUrl("http://192.168.0.172:8070/SMSPlatform/users/register");
		Map<String,String> heads = Maps.newHashMap();
//		heads.put("Content-Type", "application/json;charset=UTF-8");
		heads.put("Content-Type", "text/xml;charset=UTF-8");
		http.setContext(JsonUtil.getMapToJsonStr(map));
		System.out.println("订单号："+map.get("orderId")+"发送商户的报文："+JsonUtil.getMapToJsonStr(map));
		http.setHeads(heads);
		HttpResponser resp=HttpHelp.postParamByHttpClient(http);
		System.out.println("订单号："+map.get("orderId")+"发送商户结果："+resp.getContent());
		/**组装调用dbank-site的数据报文
		String outOrderId = map.get("outOrderId").toString();//订单号
		String resData = JsonUtil.getMapToJson(map);//明文
		String md5Str = MD5Encrypt.MD5(resData);//密文，暂时跟KEY无关
		Map<String,Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("md5Str", md5Str);
		map2.put("resData", resData);
		map2.put("orderId", outOrderId);
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("url", "http://127.0.0.1:8098/dbank-site/service/accRechargeNotify");
		parameters.put("parameters", map2);
		String ret = NotifyService.executeNotice(parameters);
		System.out.println("通知结果："+ret);**/
	}
}
