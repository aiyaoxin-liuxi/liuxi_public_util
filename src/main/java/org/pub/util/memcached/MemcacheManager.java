package org.pub.util.memcached;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.pub.util.properties.PropertiesUtil;

public class MemcacheManager {  
  
    // 构建缓存客户端  
    private static MemcachedClient cachedClient;  
    // 构建缓存客户端  
    private static String memcache_head="";  
    // 单例模式实现客户端管理类  
    private static MemcacheManager INSTANCE = new MemcacheManager();  

    private MemcacheManager(){  
    	String memcache_server=null;
		try {
	    	Properties p = PropertiesUtil.loadProperties("pub-util.properties");
	    	memcache_server = p.getProperty("memcache_server");
	    	memcache_head = p.getProperty("memcache_head");
    	
	        cachedClient = new MemcachedClient();  
	          
	        // 初始化SockIOPool，管理memcached的连接池  
	        SockIOPool pool = SockIOPool.getInstance();  
	  
	        // 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器。（这里应该设置为多个不同的服务器）  
	        // 也可以使用域名 "server3.mydomain.com:1624"  
	        String[] servers = {memcache_server};  
	  
	        pool.setServers(servers);  
	        pool.setFailover(true);  
	        pool.setInitConn(10); // 设置初始连接  
	        pool.setMinConn(5);// 设置最小连接  
	        pool.setMaxConn(250); // 设置最大连接  
	        pool.setMaxIdle(1000 * 60 * 60 * 3); // 设置每个连接最大空闲时间3个小时  
	        pool.setMaintSleep(30);  
	        pool.setNagle(false);  
	        pool.setSocketTO(3000);  
	        pool.setAliveCheck(true);  
	        pool.initialize();  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
  
    /** 
     * 获取缓存管理器唯一实例 
     *  
     * @return 
     */  
    public static MemcacheManager getInstance() {  
        return INSTANCE;  
    }  
  
    public void add(String key, Object value) { 
        cachedClient.set(memcache_head+key, value);  
    }  
  
    public void add(String key, Object value, int milliseconds) {  
        cachedClient.set(memcache_head+key, value, milliseconds);  
    }  
  
    public void remove(String key) {  
        cachedClient.delete(memcache_head+key);  
    }  
  
    public void remove(String key, int milliseconds) {  
        cachedClient.delete(memcache_head+key, milliseconds, new Date());  
    }  
  
    public void update(String key, Object value, int milliseconds) {  
        cachedClient.replace(memcache_head+key, value, milliseconds);  
    }  
  
    public void update(String key, Object value) {  
        cachedClient.replace(memcache_head+key, value);  
    }  
  
    public Object get(String key) {  
        return cachedClient.get(memcache_head+key);  
    }  
    
    public static void main(String[] args) {  
        //将对象加入到memcached缓存  
        //MemcacheManager.getInstance().add("gtest", "This is a test String"); 
        //从memcached缓存中按key值取对象  
        String result  = (String) MemcacheManager.getInstance().get("keke");  
        System.out.println(result);  

        String result2  = (String) MemcacheManager.getInstance().get("gtest");  
        System.out.println(result2);  
    }  
      
}  