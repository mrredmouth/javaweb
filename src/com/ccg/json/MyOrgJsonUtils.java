package com.ccg.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class MyOrgJsonUtils {
	
	/**
	 * 创建json对象
	 * @throws JSONException
	 */
	public JSONObject createJson() throws JSONException  {  
		JSONObject myJson=new JSONObject();  
		JSONArray members=new JSONArray();  
		myJson.put("class", "二年级");  
		myJson.put("total", 2);  
		myJson.put("members", members);
		
		//第一个同学  
		JSONObject member1=new JSONObject();  
		member1.put("name", "李小红");  
		member1.put("age", 18);  
		member1.put("score", 95);  
		members.put(member1);  
		//第二个同学  
		JSONObject member2=new JSONObject();  
		member2.put("name", "王尼玛");  
		member2.put("age", 28);  
		member2.put("score", 85);  
		members.put(member2);  
		
		return myJson;
	}  
	
	@Test
	public void createJsonTest() throws JSONException  {  
        System.out.println(createJson().toString());
    }  
	
	/**
	 * 解析json字符串->创建Json对象
	 * @throws JSONException 
	 */
	@Test
	public void testJsonObj() throws JSONException{
		String myJsonStr = createJson().toString();
		
        JSONObject myJson=new JSONObject(myJsonStr); //解析Json字符串  
        
        int total=myJson.getInt("total");  
        System.out.println("总数为："+total);       
        String class_name=myJson.getString("class");  
        System.out.println("班级为："+class_name);  
          
        JSONArray members=myJson.getJSONArray("members");  
        for(int i=0;i<members.length();i++) {  
            JSONObject member=(JSONObject)members.get(i);  
            System.out.println("姓名："+member.getString("name")+" 年龄："+member.getInt("age")  
                    +" 成绩："+member.getInt("score"));  
        }  
	}
}
