package org.pub.util.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.pub.util.security.MapKeyComparator;

public class MapUtil {
	/** 
     * 使用 Map按key进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());  
        sortMap.putAll(map);  
        return sortMap;  
    } 
    
    /**
     * 将url参数转换成map
     * 
     * @param param
     *            aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 将map转换成url
     * 
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
}

