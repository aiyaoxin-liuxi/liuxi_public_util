package com.liuxi.action.abs;

import java.util.HashMap;
import java.util.Map;

import org.pub.util.json.JsonUtil;
import org.pub.util.map.MapUtil;
import org.pub.util.security.MessageSecurity;

/**
 * 开发使用，可以删除
 * @author 刘熙
 *
 */
public class Business_sms {
	
	/**
	 * 短信余额查询
	 * @return
	 */
	public Map<String, Object> getUserInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("merchantNo", "1");
		map.put("type", "01");
		return map;
	}
		/**
		 * 发短信
		 * @return
		 */
		public Map<String, Object> sendSms(){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("applyNo", "0000003");// 商户编号
			map.put("mobile", "13810193325");// 接收手机号
			map.put("outSmsNo", "0000001");// 下游消息编号
	//		map.put("content", "您的短信验证码是：213425");// 发送内容（验证码）01
			map.put("content", "您的短信验证码是：213411");// 发送内容（验证码）01
	//		map.put("content", "您好，您有一笔收款成功，金额：20元，消费时间：2017-03-21 10:00:00。【中互联】");// 发送内容（通知）02
	//		map.put("content", "营销类短信,退订回T【中互联】");// 发送内容（营销）03
			map.put("type", "01");
			
			map = MapUtil.sortMapByKey(map);// 排序
			String json = JsonUtil.getMapToJson(map);
			String sign = MessageSecurity.getMessageSecurity("", json, "YIMAFU");
			map.put("sign", sign);
			System.out.println(map.toString());
			return map;
		}
	
	/**
	 * 批量发短信
	 * @return
	 */
	public Map<String, Object> batchSendSms(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("merchantNo", "1");// 商户编号
		map.put("mobile", "13810193325,17600076643");// 接收手机号
		map.put("outSmsNo", "0000001");// 下游消息编号
//		map.put("content", "验证码：111111【中互联】");// 发送内容（验证码）01
		map.put("content", "通知类短信【中互联】");// 发送内容（通知）02
//		map.put("content", "营销类短信,退订回T【中互联】");// 发送内容（营销）03
		map.put("type", "04");
		return map;
	}
	
	
	private Map<String, Object> addSign(Map<String, Object> map){
		String json = JsonUtil.getMapToJson(map);
		String sign = MessageSecurity.getMessageSecurity("", json, "test123");
		map.put("sign", sign);
		return map;
	}

}
