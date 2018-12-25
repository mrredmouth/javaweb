package com.ccg.cache;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MyEhcacheTest {

	private static MyEhcache myEhcache;
	
	static{
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("mapk1", "map key one!");
		mp.put("mapk2", "map key two!");
		mp.put("mapk3", "map key three!");
		//想缓存中放内容
		myEhcache = new MyEhcache("glkj");
		myEhcache.put("mp1", mp);
	}
	
	@Test
	public void testMyEhcacheUtils(){		
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("mapk1", "map key one!");
		mp.put("mapk2", "map key two!");
		mp.put("mapk3", "map key three!");
		//想缓存中放内容
		myEhcache.put("mp2",mp);		
		@SuppressWarnings("unchecked")
		Map<String, Object> value3 = (Map<String, Object>) myEhcache.get("mp2");
		System.out.println(value3);
	}
	
	@Test
	public void testMyEhcacheUtils2(){		
		System.out.println(myEhcache.get("mp1"));
	}
}
