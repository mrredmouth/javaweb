package com.ccg.webservice.czservice.consumer.invoke;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import com.ccg.webservice.czservice.consumer.wsdl.SendCzService;
import com.ccg.webservice.czservice.consumer.wsdl.SendCzServiceService;

/**
 * 两种webservice调用方式。
 * 测试从czxt中得到的webservice接口：(生产环境)
 * http://134.96.245.118:7001/czxt/webservice/sendCzService?wsdl
 * @author Administrator
 *
 */
public class TestCzservice {

	private static String info="<?xml version=\'1.0\' encoding=\'gb2312\'?>"+
			   "<info>"+
			   "<documentID>CZ10312C3B67FB48E</documentID>"+
			   "<userLoginname>testdyf.zj</userLoginname>"+
			   "<status>办结</status>"+
			   "</info>";
	/**
	 * 调用方式一：（财智系统生产环境接口调用）
	 * http://134.96.245.118:7001/czxt/webservice/sendCzService?wsdl
	 */
	@Test
	 public void invokeDemo1() { 
		 SendCzService sendCzService=new SendCzServiceService().getSendCzServicePort(); 
		 System.out.println(sendCzService.ifService("OA_FEEDBACK", "$2018_oabj_WEBCLIENT$","ZJCZ_OABJ", "0", info));
	}
	 
	/**
	 * 调用方式二：（财智系统生产环境接口调用）
	 * http://134.96.245.118:7001/czxt/webservice/sendCzService?wsdl
	 */
	 @Test
	 public void invokeDemo2() throws MalformedURLException { 
		URL url = new URL("http://134.96.245.118:7001/czxt/webservice/sendCzService?wsdl");  
		QName qName = new QName("http://provider.czservice.webservice.oapro.zjcw.com/", "SendCzServiceService");  
		Service service = Service.create(url, qName);  
		SendCzService sendCzService = service.getPort(SendCzService.class);  
		 System.out.println(sendCzService.ifService("OA_FEEDBACK", "$2018_oabj_WEBCLIENT$","ZJCZ_OABJ", "0", info));
	} 
}

