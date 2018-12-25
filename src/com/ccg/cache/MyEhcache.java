package com.ccg.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * @author 
 */
public class MyEhcache{
	
	private static CacheManager manager;
	private static Cache cache;
	
	public MyEhcache(){
		manager = EHCacheUtil.manager;
	}
	
	public MyEhcache(String cacheName) {
		manager = EHCacheUtil.manager;
		cache = manager.getCache(cacheName);
	}
	
	public Cache getCache(){
		return cache;
	}
	/**
	 * 获取缓存中，key对应element的序列化value值
	 * @param key
	 * @return
	 */
	public Serializable get(Serializable key){
		Element e = cache.get(key);
        if(e==null){
            return null;
        }
		return e.getValue();
	}
	/**
	 * 获取缓存中，key对应element的非序列化value值
	 * @param key
	 * @return
	 */
	public Object get(String key){
		Element element = cache.get(key);
		Object value = element.getObjectValue();		
		return value;
	}
	/**
	 * 向缓存中存放数据
	 * @param key
	 * @param val
	 * @return
	 */
	public Object put(String key, Object val){
		cache.put(new Element(key,val));
		return val;
	}

	/**
	 * 移除缓存中的某个key键值对
	 * @param key
	 */
	public void remove(String key){
		//从cache中移除key对应的element
		cache.remove(key);	
	}
	
	/**
	 * 关闭所有的缓存
	 */
	public static void shutdown(){
		//在使用ehcache后，需要关闭	 
		manager.shutdown(); 
	}
}
