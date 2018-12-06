package com.ccg.webservice.oaservice.consumer.invoke;

import java.net.MalformedURLException;

import org.junit.Test;

import com.ccg.webservice.oaservice.consumer.wsdl.ZjoaService;
import com.ccg.webservice.oaservice.consumer.wsdl.ZjoaServicePortType;

public class TestOaService {

	private static String str="<?xml version=\'1.0\' encoding=\'gb2312\'?>"+
		   "<info>"+
		   "<documentID>czpd1号</documentID>"+
		   "<userLoginname>xgx.zj</userLoginname>"+
		   "<subject>czpd测试</subject>"+
		   "<businessType>物联网</businessType>"+
		   "<queryFeedbackUrl>http://www.baidu.com</queryFeedbackUrl>"+
		   "<toDealCompanys>杭州市分公司</toDealCompanys>"+
		   "</info>";
	/**
	 * http://134.96.42.120/zjdxoaqy/services/ZjoaService?wsdl
	 * @param args
	 * @throws Exception 
	 * @throws MalformedURLException 
	 */
	 public static void main(String[] args) throws MalformedURLException, Exception { 
		 //调用webservice 

		/* Client client = new Client(new URL("http://134.96.40.120/zjdxoaqy/services/ZjoaService?wsdl"));
		 Object[] obj = {"czpd","$2018czpdwebclient4OA$","zjoa_czpd","0",str};
		 Object[] results = client.invoke("IfService", obj);
		 System.out.println("=========================="+results[0]);*/
	 }
	 
	 @Test 
	 public void testOaServiceWsdl(){
		 ZjoaServicePortType zjoaService = new ZjoaService().getZjoaServiceHttpPort(); 
		 String result = zjoaService.ifService("czpd", "$2018czpdwebclient4OA$", "zjoa_czpd", "0", str);
		 System.out.println(result); 
	 }
}
