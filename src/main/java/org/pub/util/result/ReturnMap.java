package org.pub.util.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnMap {
	
	/*
	 * 以下为map返回值信息拼装
	 */
	protected Map<String, Object> getSuccessMap(Map<String, Object> returnMap, String msg){
        Map<String, Object> map = this.getMap(true, "1", msg);
        map.put("ReturnData", returnMap);
        return map;
    }

    protected Map<String, Object> getSuccessMap(List<Map<String, Object>> returnList, int total, String msg){
        Map<String, Object> map = this.getMap(true, "1", msg);
        map.put("total", total);
        map.put("ReturnData", returnList);
        return map;
    }

    protected Map<String, Object> getErrorMap(Map<String, Object> returnMap, String state, String msg){
        Map<String, Object> map = this.getMap(false, state, msg);
        map.put("ReturnData", returnMap);
        return map;
    }

    protected Map<String, Object> getErrorMap(List<Map<String, Object>> returnList, String state, String msg){
        Map<String, Object> map = this.getMap(false, state, msg);
        map.put("ReturnData", returnList);
        return map;
    }

    private Map<String, Object> getMap(boolean success, String state, String msg){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Success", success);
        map.put("state", state);
        map.put("Message", msg);
        return map;
    }

}
