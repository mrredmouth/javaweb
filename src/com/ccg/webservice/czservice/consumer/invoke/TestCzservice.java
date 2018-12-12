package com.ccg.webservice.czservice.consumer.invoke;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import com.ccg.webservice.czservice.consumer.wsdl.SendService;
import com.ccg.webservice.czservice.consumer.wsdl.SendServiceService;

/**
 * 测试从czxt中得到的接口：http://localhost:8080/czxt/webservice/sendServie?wsdl
 * @author Administrator
 *
 */
public class TestCzservice {
	
	@Test
	 public static void invokeDemo1(String[] args) { 
		 //调用webservice 
		 SendService hello=new SendServiceService().getSendServicePort(); 
		 boolean sendOA = hello.sendOA("1212");
		 System.out.println(sendOA); 
		 System.out.println(hello.sendOA("zhoujian")); 
		 /*hello.sendOrg(new OaProject());*/
	}
	 
	 @Test
	 public void invokeDemo2(String[] args) throws MalformedURLException { 
		URL url = new URL("http://localhost:8080/czxt/webservice/sendServie?wsdl");  
		QName qName = new QName("http://czprovide.webservice.oapro.zjcw.com/", "SendServiceService");  
		Service service = Service.create(url, qName);  
		SendService sendService = service.getPort(SendService.class);  
		System.out.println(sendService.sendOA("1212")); 
		System.out.println(sendService.sendOA("zhoujian")); 
	} 
}

