package com.ccg.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonUtils {
	
	
	public static String CreateJson() throws JSONException  {  
        JSONObject my_json=new JSONObject();  
        JSONArray members=new JSONArray();  
        my_json.put("class", "二年级");  
        my_json.put("total", 2);  
        my_json.put("members", members);
      
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
        
        return my_json.toString();
    }  
}
