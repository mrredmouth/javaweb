package com.ccg.webservice.czservice.consumer2.invoke;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.ccg.webservice.czservice.consumer.wsdl.SendService;

/**
 * 用到wsdl解析下来的com.ccg.webservice.czservice.consumer.wsdl.SendService类
 * @author Administrator
 *
 */
public class TestInvokeNoWsdl {
	 public static void main(String[] args) throws MalformedURLException { 
		URL url = new URL("http://localhost:8080/czxt/webservice/sendServie?wsdl");  
		QName qName = new QName("http://czprovide.webservice.oapro.zjcw.com/", "SendServiceService");  
		Service service = Service.create(url, qName);  
		SendService sendService = service.getPort(SendService.class);  
		System.out.println(sendService.sendOA("1212")); 
		System.out.println(sendService.sendOA("zhoujian")); 
	} 
}
