package com.ccg.uuid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * 
 * @author Administrator
 *
 */
public class AllUniqueUtilsTest {
	
	@Test
	public void testUniqueUUIDUtils(){
		System.out.println(UniqueUUIDUtils.getUUID());
		System.out.println(UniqueUUIDUtils.createUniqueId());
		
		//UUIDUtils.createUniqueId()：使用UUID+hashCode方式id，会出现重复的
		int hashCodeVa = "23c4bcec-c781-40ca-8d41-a1c498052c36".hashCode(); 
		int hashCodeVb = "3b0b731b-bad2-4bfd-b305-444fc482fa21".hashCode();
		System.out.println(hashCodeVa == hashCodeVb);
		
	}
	@Test
	public void testUniqueTimestampUtils(){
		System.out.println(UniqueTimestampUtils.uniqueCurrentTimeMS());
		System.out.println(UniqueTimestampUtils.uniqueCurrentTimeMS());
		
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
	}
	
	/**
	 * 用多线程测试，获取synchronized同步16位时间戳
	 */
	@Test
	public void testGetSynchronizedTime() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(UniqueTimestampUtils.getSynchronizedTime());
				}
			}).start();
		}
    }
	@Test
	public void testGetSynchronizedTime1() {
		String jsonstr = "{documentID:CZ00000071, dep1id:0, dep2id:1, dep3id:358, dep4id:, loginame:testdyf.zj, usrname:测试账号 ,telephone:13112121212}";

		String jsonQuoteStr = JSONObject.quote(jsonstr);
		System.out.println(jsonQuoteStr);
		
	}
	
	@Test
    public void createJson() throws JSONException  
    {  
        JSONObject myJson=new JSONObject();  
        myJson.put("class", "二年级");  
        myJson.put("total", 2);  
      
        JSONArray members=new JSONArray();  
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
        myJson.put("members", members);  
         
        System.out.println(myJson.toString());  
    }  
	@Test
	public void createJsonTest() throws JSONException  
	{  
		JSONObject myJson=new JSONObject();  
		myJson.put("class", "二年级");  
		myJson.put("total", 2);  
		System.out.println(myJson.toString());  
		
		JSONObject myJson2 = new JSONObject(myJson.toString());  
		System.out.println(myJson2.getInt("total"));
		System.out.println(myJson2.getString("class"));
	}  
}
