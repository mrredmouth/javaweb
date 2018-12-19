package com.ccg.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class TestMyJsonUtils {

	/**
	 * 创建json字符串
	 * @throws JSONException
	 */
	@Test
	public void testJsonStr() throws JSONException {  
		System.out.println(MyJsonUtils.CreateJson());
    }  
	
	/**
	 * 解析json字符串->创建Json对象
	 * @throws JSONException 
	 */
	@Test
	public void testJsonObj() throws JSONException{
		String my_json_str = MyJsonUtils.CreateJson();
		
        JSONObject my_json=new JSONObject(my_json_str); //解析Json字符串  
        
        int total=my_json.getInt("total");  
        System.out.println("总数为："+total);       
        String class_name=my_json.getString("class");  
        System.out.println("班级为："+class_name);  
          
        JSONArray members=my_json.getJSONArray("members");  
        for(int i=0;i<members.length();i++)  
        {  
            JSONObject member=(JSONObject)members.get(i);  
            System.out.println("姓名："+member.getString("name")+" 年龄："+member.getInt("age")  
                    +" 成绩："+member.getInt("score"));  
        }  
	}
	
/*
	FeedbackMsg feedbackMsg= (FeedbackMsg)JSONObject.toBean(jasonObject,FeedbackMsg.class);
	System.out.println(feedbackMsg);*/
	
	
}
