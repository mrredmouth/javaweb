package com.ccg.cache;

import java.io.Serializable;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheUtil {
	/** 同包内访问，缓存管理器 */
	protected static CacheManager manager=null;
    //private static String configfile="ehcache.xml";
    
    //EHCache初始化
    static{
        try {
        	//创建方式一：默认从classpath路径下寻找ehcache.xml文件,如果没有,则加载jar包中的ehcache-failsafe.xml文件
        	manager = CacheManager.getInstance();
        	//创建其他方式
            //manager = CacheManager.create(EHCacheUtil.class.getClassLoader().getResourceAsStream(configfile));
        	//manager = new CacheManager();
    		//manager = new CacheManager("src/config/ehcache1.xml");
    		//manager = CacheManager.create();
        	
    		
    		//增加、删除缓存
    		//manager.addCache("test");
    		//manager.removeCache("glkj");  
        	
    		String[] cacheNames = manager.getCacheNames();
    		for(int i=0; i<cacheNames.length; i++){
    			System.out.println("Cache Name:"+cacheNames[i]);
    		}
    		
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据存入Cache
     * @param cachename Cache名称
     * @param key 类似redis的Key
     * @param value 类似redis的value，value可以是任何对象、数据类型，比如person,map,list等
     */
    public static void put(String cachename,Serializable key,Serializable value){
        manager.getCache(cachename).put(new Element(key, value));
    }

    /**
     * 获取缓存cachename中key对应的value
     * @param cachename
     * @param key
     * @return
     */
    public static Serializable get(String cachename,Serializable key){
        try {
            Element e=manager.getCache(cachename).get(key);
            if(e==null){
                return null;
            }
            return e.getValue();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (CacheException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清除缓存名称为cachename的缓存
     * @param cachename
     */
    public static void clearCache(String cachename){
        try {
            manager.getCache(cachename).removeAll();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除缓存cachename中key对应的value
     * @param cachename
     * @param key
     */
    public static void remove(String cachename,Serializable key){
        manager.getCache(cachename).remove(key);
    }
}
