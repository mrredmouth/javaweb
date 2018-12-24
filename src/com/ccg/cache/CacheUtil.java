/** 
* CacheUtil.java Created on Feb 3, 2010
* Copyright 2010@JSHX. 
* All right reserved. 
*/
package com.ccg.cache;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache 工具类
 * @Time 9:13:18 AM
 * @author mengxiankong
 */
public class CacheUtil {
	
	private static Cache cache;
	
	static {
//		CacheManager manager = new CacheManager();
//		CacheManager manager1 = new CacheManager("src/config/ehcache1.xml");
//		CacheManager manager = CacheManager.create();
		CacheManager manager = CacheManager.getInstance();
		//manager.addCache("test");
		//删除cache
		//manager.removeCache("glkj");  
		String[] cacheNames = manager.getCacheNames();
		for(int i=0; i<cacheNames.length; i++){
			System.out.println("Cache Name:"+cacheNames[i]);
		}	
		cache = manager.getCache("glkj");
	}
	
	public static Cache getCache(){
		return cache;
	}
	
	public static Object get(String key){

		//根据key取得对应element的序列化value值
//		Element element1 = cache.get("key1");
//		Serializable value = element1.getValue();	
		//根据key取得对应element的非序列化value值
		Element element = cache.get(key);
		Object value = element.getObjectValue();		
		return value;
	}
	
	public static Object put(String key, Object val){
		cache.put(new Element(key,val));
		return val;
	}
	
	public static void remove(String key){
		//从cache中移除key对应的element
		cache.remove(key);	
	}
	
	public static void shutdown(){
		//在使用ehcache后，需要关闭	 
		CacheManager.getInstance().shutdown(); 
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){		
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("mapk1", "map key one!");
		mp.put("mapk2", "map key two!");
		mp.put("mapk3", "map key three!");
		put("mp1",mp);		
		Map<String, Object> value3 = (Map<String, Object>) get("mp1");
		System.out.println(value3);
		
	}
}
