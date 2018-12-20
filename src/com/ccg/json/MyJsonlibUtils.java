package com.ccg.json;

import java.util.Iterator;

import org.json.JSONException;
import org.junit.Test;

import com.ccg.base.MapBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json-lib-2.2.1-jdk15.jar 包下的json，有两个版本的实现，此处是jdk15的实现
 * 需要以下依赖包：
 * commons-logging-1.0.4.jar
 * commons-lang-2.3.jar
 * commons-collections-3.2.jar
 * commons-beanutils-1.7.0.jar
 * json-lib-2.2.1-jdk15.jar
 * ezmorph-1.0.4.jar
 * @author Administrator
 */
public class MyJsonlibUtils {
	
	private static String jsonStr;
	
	static {
		try {
			jsonStr = (new MyOrgJsonUtils()).createJson().toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 对象转json：
	 * 对象可以是：sjon字符串，MapBean对象等
	 */
	@Test
	public void jsonlibTest(){
		//自定义对象转为json对象
		MapBean map = new MapBean();
		map.put("total", 2);
		map.put("name", "lily");
		JSONObject myJson1 = JSONObject.fromObject(map);
		System.out.println(myJson1.getString("name"));
		
		//json字符串转为json对象
		System.out.println(jsonStr);
		JSONObject myJson2 = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = myJson2.getJSONArray("members");
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = jsonArray.iterator();
		while(iterator.hasNext()){
			JSONObject jo = iterator.next();
			System.out.println(jo.getString("name"));
			System.out.println(jo.getInt("age"));
		}
		
		
	}
}
