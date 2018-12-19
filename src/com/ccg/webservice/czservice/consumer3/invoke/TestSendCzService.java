package com.ccg.webservice.czservice.consumer3.invoke;

import org.junit.Test;

import com.ccg.webservice.czservice.consumer3.wsdl.SendCzService;
import com.ccg.webservice.czservice.consumer3.wsdl.SendCzServiceService;

/**
 * 测试本地调用财智的接口
 * @author Administrator
 *
 */
public class TestSendCzService {
	private static String info="<?xml version=\'1.0\' encoding=\'gb2312\'?>"+
			   "<info>"+
			   "<documentID>CZ00000068111</documentID>"+
			   "<userLoginname>testdyf.zj</userLoginname>"+
			   "<status>办结</status>"+
			   "</info>";
	/**
	 * http://134.98.100.70:8080/czxt/webservice/sendCzService?wsdl
	 */
	@Test
	public void testSendCzService1() { 
		 //调用webservice 
		 SendCzService hello=new SendCzServiceService().getSendCzServicePort(); 
		 System.out.println(hello.ifService("OA_FEEDBACK", "$2018_oabj_WEBCLIENT$","ZJCZ_OABJ", "0", info));
	}
	
	/*@Test
	public void testSendCzService2(){
		Client client;
		try {
			 client = new Client(new URL("http://134.98.100.70:8080/czxt/webservice/sendCzService?wsdl"));
			 Object[] obj = {"OA_FEEDBACK","$2018_oabj_WEBCLIENT$","ZJCZ_OABJ","0",info};
			 Object[] results = client.invoke("IfService", obj);
			 System.out.println("=========================="+results[0]);
			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	
	public void testSendOaService(){
		Client client;
		try {
			client = new Client(new URL("http://134.96.40.120/zjdxoaqy/services/ZjoaService?wsdl"));
			Object[] obj = {"czpd","$2018czpdwebclient4OA$","zjoa_czpd","0",info};
			Object[] results = client.invoke("IfService", obj);
			System.out.println("=========================="+results[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
