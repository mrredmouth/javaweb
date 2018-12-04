package com.ccg.webservice.consumer.invoke;

import com.ccg.webservice.consumer.JwsServiceHello;
import com.ccg.webservice.consumer.JwsServiceHelloService;

/**
 * 客户端调用服务端的接口
 * 测试本项目中的提供服务者：/javaweb/src/com/ccg/webservice/provider/JwsServiceHello.java
 * 接口地址：http://134.98.100.70:8080/Service/ServiceHello?wsdl
 * @author Administrator
 *
 */
public class JwsClientHello {

	 public static void main(String[] args) { 
		 //调用webservice 
		 JwsServiceHello hello=new JwsServiceHelloService().getJwsServiceHelloPort(); 
		 String name=hello.getValue("panchengming");
		 System.out.println(name); 
	}
}
