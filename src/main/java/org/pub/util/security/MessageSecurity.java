package org.pub.util.security;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.pub.util.json.JsonUtil;


public class MessageSecurity {

	static Logger logger = Logger.getLogger(MessageSecurity.class);
	
	/**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static boolean checkMapMessageSecurity(Map<String, String> map,String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
		if(key==null || key.trim().equals("")){
			return false;
		}
        //移除密文属性
        String sign = map.get("sign");
        map.remove("sign");
        //获取map加密串
        String sign_n = getMapMessageSecurity(map,key);

        logger.info("sign :"+sign);
        logger.info("sign_n :"+sign_n);
        if(sign==null || sign.trim().equals("")){
        	return false;
        }else if(sign_n == null || sign_n.trim().equals("")){
        	return false;
        }else if(sign.equals(sign_n)){
        	return true;
        }
		return false;
    }


	/**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static boolean checkMapMessageSecurityIgnoreCase(Map<String, String> map,String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
		if(key==null || key.trim().equals("")){
			return false;
		}
        //移除密文属性
        String sign = map.get("sign");
        map.remove("sign");
        //获取map加密串
        String sign_n = getMapMessageSecurity(map,key);
        logger.info("sign :"+sign);
        logger.info("sign_n :"+sign_n);
        if(sign==null || sign.trim().equals("")){
        	return false;
        }else if(sign_n == null || sign_n.trim().equals("")){
        	return false;
        }else if(sign.equalsIgnoreCase(sign_n)){
        	return true;
        }
		return false;
    }


    /**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static String getMapMessageSecurity(Map<String, String> map,String key) {
        if (map == null || map.isEmpty()) {
            return null;
        }
		if(key==null || key.trim().equals("")){
			return null;
		}
        //移除密文属性
        map.remove("sign");
        //获取用户ID
        String userId = map.get("userId");
        //map进行排序
        Map<String, String> sortMap = sortMapByKey(map);
        logger.debug("=======sortMap:"+sortMap);
        //map转换成json
        String json = JsonUtil.getMapToJsonStr(sortMap);
        logger.debug("get json:"+json);
        //加密
        return getMessageSecurity(userId, json,key);
    }
    
	/**
	 * 获取信息密文
	 * @param persion
	 * @param message not null
	 * @param key not null
	 * @return
	 */
	public static String getMessageSecurity(String persion,String message,String key){
		if(message==null || message.trim().equals("")){
			return null;
		}
		if(key==null || key.trim().equals("")){
			return null;
		}
		if(persion == null){
			persion  = "";
		}
		return Md5.getDigestString(Md5.getDigestString(message)+persion+key);
	}

	/**
	 * 验证信息密文
	 * @param persion
	 * @param message not null
	 * @param securityMessage not null
	 * @param key not null
	 * @return
	 */
	public static boolean checkMessageSecurity(String persion,String message,String securityMessage,String key){
		if(message==null || message.trim().equals("")){
			return false;
		}
		if(key==null || key.trim().equals("")){
			return false;
		}
		if(persion == null){
			persion  = "";
		}
		String checkMessage = getMessageSecurity(persion, message, key);
		
		if(checkMessage.equals(securityMessage)){
			return true;
		}
		return false;
	}

	/**
	 * 验证信息密文 忽略大小写
	 * @param persion
	 * @param message not null
	 * @param key not null
	 * @return
	 */
	public static boolean checkMessageSecurityIgnoreCase(String persion,String message,String securityMessage,String key){
		if(message==null || message.trim().equals("")){
			return false;
		}
		if(key==null || key.trim().equals("")){
			return false;
		}
		if(persion == null){
			persion  = "";
		}
		String checkMessage = getMessageSecurity(persion, message, key);
		
		if(checkMessage.equalsIgnoreCase(securityMessage)){
			return true;
		}
		return false;
	}
	
	
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
	//===========================================

	
	/**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static boolean checkMapObjMessageSecurity(Map<String, Object> map,String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
		if(key==null || key.trim().equals("")){
			return false;
		}
        //移除密文属性
        String sign = (String)map.get("sign");
        map.remove("sign");
        //获取map加密串
        String sign_n = getMapObjMessageSecurity(map,key);

        logger.info("sign :"+sign);
        logger.info("sign_n :"+sign_n);
        if(sign==null || sign.trim().equals("")){
        	return false;
        }else if(sign_n == null || sign_n.trim().equals("")){
        	return false;
        }else if(sign.equals(sign_n)){
        	return true;
        }
		return false;
    }


	/**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static boolean checkMapObjMessageSecurityIgnoreCase(Map<String, Object> map,String key) {
        if (map == null || map.isEmpty()) {
            return false;
        }
		if(key==null || key.trim().equals("")){
			return false;
		}
        //移除密文属性
        String sign = (String)map.get("sign");
        map.remove("sign");
        //获取map加密串
        String sign_n = getMapObjMessageSecurity(map,key);
        logger.info("sign :"+sign);
        logger.info("sign_n :"+sign_n);
        if(sign==null || sign.trim().equals("")){
        	return false;
        }else if(sign_n == null || sign_n.trim().equals("")){
        	return false;
        }else if(sign.equalsIgnoreCase(sign_n)){
        	return true;
        }
		return false;
    }


    /**
     * 使用 Map 获取加密sign
     * @param string sign
	 * @param key not null
     * @return
     */
    public static String getMapObjMessageSecurity(Map<String, Object> map,String key) {
        if (map == null || map.isEmpty()) {
            return null;
        }
		if(key==null || key.trim().equals("")){
			return null;
		}
        //移除密文属性
        map.remove("sign");
        //获取用户ID
        String userId = (String)map.get("userId");
        //map进行排序
        Map<String, Object> sortMap = sortMapObjByKey(map);
        logger.debug("============"+sortMap);
        //map转换成json
        String json = JsonUtil.getMapToJson(sortMap);
        logger.debug("get json:"+json);
        //加密
        return getMessageSecurity(userId, json,key);
    }
	
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    private static Map<String, Object> sortMapObjByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        //移除map中的value空值
        MapRemoveNullUtil.removeNullValue(map); 
        
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
	
    /**
	public static void main(String[] args) throws Exception {
		
//		System.out.println(MessageSecurity.getMessageSecurity(null, "123456","123"));
//		System.out.println(MessageSecurity.getMessageSecurity("", "123456","123"));
//		System.out.println(MessageSecurity.getMessageSecurity("123", "","123"));
//		System.out.println(MessageSecurity.checkMessageSecurityIgnoreCase("123", "123123",MessageSecurity.getMessageSecurity("123", "123123","123").toLowerCase(),"123"));
//		
		
		String t2 = RSAUtil.encrypt("d://public_key.dat","我问问的sd实打d实的","UTF-8");
				System.out.println("getEptStr:" +t2.length()+"\n"+ t2);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", "123");
		map.put("test1", "t1");
		map.put("test2", t2);
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
		map1.put("test2", t2);
		map1.put("test3", "t3");
		map1.put("test5", "t5");
		map1.put("test6", "t6");
		map1.put("test4", "t4");

		System.out.println("======="+map1.get("test2"));
		

		String drpStr = RSAUtil.decrypt("d://private_key.dat",map1.get("test2"),"UTF-8");
		System.out.println("drpStr:" + drpStr);
		
		System.out.println(MessageSecurity.checkMapMessageSecurityIgnoreCase(map,"123"));
		System.out.println(MessageSecurity.checkMapMessageSecurity(map1,"123"));
		
	
	}
	
	**/

	public static void main(String[] args) throws Exception {
		
//		System.out.println(MessageSecurity.getMessageSecurity(null, "123456","123"));
//		System.out.println(MessageSecurity.getMessageSecurity("", "123456","123"));
//		System.out.println(MessageSecurity.getMessageSecurity("123", "","123"));
//		System.out.println(MessageSecurity.checkMessageSecurityIgnoreCase("123", "123123",MessageSecurity.getMessageSecurity("123", "123123","123").toLowerCase(),"123"));
//		
		
		String t2 = RSAUtil.encrypt("d://public_key.dat","我问问的sd实打d实的","UTF-8");
				System.out.println("getEptStr:" +t2.length()+"\n"+ t2);
				Map<String, Object> m1 = new HashMap<String, Object>();
				m1.put("userId", "123");
				m1.put("test1", "t1");
				m1.put("test3", "t3");
				m1.put("test5", "");
				m1.put("test6", "");
				m1.put("test4", "t4");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", "123");
		map.put("test1", "t1");
		map.put("test2", t2);
		map.put("test3", "t3");
		map.put("test5", "啧啧啧");
		map.put("test6", "");
		map.put("test4", "t4");
		map.put("m", m1);
		
		System.out.println("------"+MessageSecurity.getMapObjMessageSecurity(map,"123"));
		String sign = MessageSecurity.getMapObjMessageSecurity(map,"123");
		map.put("sign", sign);
		
		
		
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("sign", sign);
		map1.put("userId", "123");
		map1.put("test1", "t1");
		map1.put("test2", t2);
		map1.put("test3", "t3");
		map1.put("test5", "啧啧啧");
		map1.put("test6", "");
		map1.put("test4", "t4");
		map1.put("m", m1);

		System.out.println("======="+map1.get("test2"));
		

		String drpStr = RSAUtil.decrypt("d://private_key.dat",(String)map1.get("test2"),"UTF-8");
		System.out.println("drpStr:" + drpStr);
		
		System.out.println(MessageSecurity.checkMapObjMessageSecurityIgnoreCase(map,"123"));
		System.out.println(MessageSecurity.checkMapObjMessageSecurity(map1,"123"));
		
	
	}


}
