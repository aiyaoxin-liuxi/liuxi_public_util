package com.liuxi.util.notify;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;



/**
 * 异步通知商户服务
 * @author nii
 *
 */
public class NotifyService {
	
	static Logger logger = Logger.getLogger(NotifyService.class);
	
	
	/**
	 * 手动通知
	 * nii
	 * @param url
	 * @param dsybdata
	 * @param dsybdatasign
	 * @return
	 */
//	public static String manuallyNotify(String url,Map<String,String> parameter){
//		String ret = "" ;
//		String uuid = UUIDGenerator.getUUID();	//自动生成UUID
//		
//		if(url==null||url.trim().length() ==0){
//			ret = buildReturnJson("01",uuid,"","通知地址(dsyburl)为空！");
//			return ret;
//		}
//		
//		Map<String,Object> parameters = new HashMap<String, Object>();
//		parameters.put(Constants.PARAMETER_NAME_UUID, uuid);
//		parameters.put(Constants.PARAMETER_NAME_ORDERSN, uuid);
//		parameters.put(Constants.PARAMETER_NAME_BEGIN, System.currentTimeMillis()+"");
//		parameters.put(Constants.PARAMETER_NAME_URL, url);
//		parameters.put(Constants.PARAMETER_NAME_PARAMETERS, parameter);
//		ret = executeNotice(parameters);
//		if(ret==null||!ret.trim().equals("00")){	//第一次通知失败
//			boolean b = JobManager.addJobFile(parameters);
//			if(!b){
//				logger.error("添加通知任务失败：ordersn="+uuid+"-->uuid="+uuid);
//				ret = buildReturnJson("02",uuid,"","添加任务(ordersn="+uuid+")失败，请联系管理员!");
//				return ret;
//			}else{
//				logger.error("添加通知任务成功：ordersn="+uuid+"-->uuid="+uuid);
//			}
//		}
//		
//		return buildReturnJson("00",uuid,ret,"") ;
//	}
	
	/**
	 * 自动通知， 提供给 POSP调用。
	 * nii
	 * @param transcode
	 * @param obj
	 * @return
	 */
//	public static String notify(String transcode ,RequestObj obj){
//		String uuid = UUIDGenerator.getUUID();
//		logger.info("处理通知请求："+uuid+"-->"+obj.to_json());
//		String ret = null;
//		String url = obj.get("dsyburl");
//		String ordersn = obj.get("ordersn");
//		String data = obj.get("dsybdata");
//		String sign = obj.get("dsybdatasign");
//		if(url==null||url.trim().length() ==0){
//			ret = buildReturnJson("01",uuid,"","通知地址(dsyburl)为空！");
//			return ret;
//		}
//		if(ordersn == null || ordersn.trim().length() == 0 ){
//			ret = buildReturnJson("01",uuid,"","订单号(ordersn)为空！");
//			return ret;
//		}
//		if(data == null || data.trim().length() == 0){
//			logger.info("注意：dsybdata没有传递！");
//		}
//		if(sign == null || data.trim().length() == 0){
//			logger.info("注意：dsybdatasign没有传递！");
//		}
//		
//		Map<String,String> parameters = new HashMap<String, String>();
//		parameters.put("uuid", uuid);
//		parameters.put("ordersn", ordersn);
//		parameters.put("dsyburl", url);
//		parameters.put("dsybdata", data);
//		parameters.put("dsybdatasign", sign);
//		
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.SECOND, 1);
//		//1秒之后 开始通知
//		String cron = c.get(Calendar.SECOND)+" 0/5 * * * ?";
//		System.out.println("本次定时："+cron);
//        boolean b = QuartzManager.addJob(uuid,parameters, NotifyJob.class, cron);
////		QuartzManager.addJob(uuid,parameters,NotifyJob.class, "0/5,10,15 * * * * ?");
//		
//        if(!b){
//        	logger.error("添加通知任务失败：ordersn="+ordersn+"-->uuid="+uuid);
//        	ret = buildReturnJson("02",uuid,"","添加任务(ordersn="+ordersn+")失败，请联系管理员!");
//        	return ret;
//        }
//		//这里是否做这里是否做异步通知
//		logger.info("添加通知任务结果："+ordersn+"-->"+uuid+"="+b);
//		String result = buildReturnJson("00",uuid,ret,"");
//		return result;
//	}
	
//	public static String notify(String transcode ,RequestObj obj){
//		String uuid = UUIDGenerator.getUUID();
//		logger.info("do notify "+uuid+"-->"+obj.to_json());
//		String ret = null;
//		String url = obj.get(Constants.PARAMETER_NAME_URL);
//		String ordersn = obj.get(Constants.PARAMETER_NAME_ORDERSN);
//		Object data = obj.get_obj(Constants.PARAMETER_NAME_PARAMETERS);
//		if(url==null||url.trim().length() ==0){
//			ret = buildReturnJson("01",uuid,"","通知地址(dsyburl)为空！");
//			return ret;
//		}
//		if(ordersn == null || ordersn.trim().length() == 0 ){
//			ret = buildReturnJson("01",uuid,"","订单号(ordersn)为空！");
//			return ret;
//		}
//		if(data == null){
//			logger.info("注意："+ordersn+" 本次通知，参数为空！");
//		}
//		Map<String,Object> parameters = new HashMap<String, Object>();
//		parameters.put(Constants.PARAMETER_NAME_UUID, uuid);
//		parameters.put(Constants.PARAMETER_NAME_BEGIN, System.currentTimeMillis()+"");
//		parameters.put(Constants.PARAMETER_NAME_ORDERSN, ordersn);
//		parameters.put(Constants.PARAMETER_NAME_URL, url);
//		parameters.put(Constants.PARAMETER_NAME_PARAMETERS, data);
//		ret = executeNotice(parameters);
//		if(ret==null||!ret.trim().equals("00")){	//第一次通知失败
//			boolean b = JobManager.addJobFile(parameters);
//			if(!b){
//				logger.error("Add Job failure ordersn="+ordersn+"-->uuid="+uuid);
//				ret = buildReturnJson("02",uuid,"","添加任务(ordersn="+ordersn+")失败，请联系管理员!");
//				return ret;
//			}else{
//				logger.error("Add Job Success: ordersn="+ordersn+"-->uuid="+uuid);
//			}
//		}
//		//这里是否做这里是否做异步通知
//		String result = buildReturnJson("00",uuid,ret,"");
//		return result;
//	}
	
//	public static String buildReturnJson(String code,String uuid,String notifyRet,String errorMsg){
//		Map<String,String> map = new HashMap<String,String>(); 
//		map.put("op_ret_code", code.equals("00")?"000":"601");	//消息头
//		map.put("returncode", code);	//
//		map.put("seqno", uuid);
//		map.put("errtext", errorMsg);	//
//		return JsonUtil.to_json(map);
//	}
	
	public static String executeNotice(Map<String,Object> parameters){
		String ret = "";
		String url = parameters.get(Constants.PARAMETER_NAME_URL).toString();
		//String ordersn = parameters.get(Constants.PARAMETER_NAME_ORDERSN).toString();
		//String uuid = parameters.get(Constants.PARAMETER_NAME_UUID).toString();
		
		if(url.startsWith("https://")){	//调用Https
			ret = HttpClientUtil.httpsPost(url,null,null, (Map<String,Object>)(parameters.get(Constants.PARAMETER_NAME_PARAMETERS)));
		}else if(url.startsWith("http://")){	//调用http
			ret = HttpClientUtil.httpPost(url,null,null, (Map<String,Object>)(parameters.get(Constants.PARAMETER_NAME_PARAMETERS)));
			System.out.println((Map<String,Object>)(parameters.get(Constants.PARAMETER_NAME_PARAMETERS)));
		}
		return ret;
	}
}
