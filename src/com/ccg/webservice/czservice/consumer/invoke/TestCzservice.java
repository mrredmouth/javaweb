package com.ccg.webservice.czservice.consumer.invoke;

import com.ccg.webservice.czservice.consumer.wsdl.SendService;
import com.ccg.webservice.czservice.consumer.wsdl.SendServiceService;

/**
 * 测试从czxt中得到的接口：http://localhost:8080/czxt/webservice/sendServie?wsdl
 * @author Administrator
 *
 */
public class TestCzservice {
	 public static void main(String[] args) { 
		 //调用webservice 
		 
		 SendService hello=new SendServiceService().getSendServicePort(); 
		 boolean sendOA = hello.sendOA("1212");
		 System.out.println(sendOA); 
		 System.out.println(hello.sendOA("zhoujian")); 
		 /*hello.sendOrg(new OaProject());*/
	}
}

