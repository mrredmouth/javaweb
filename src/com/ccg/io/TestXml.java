package com.ccg.io;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;

import com.ccg.io.xml.MyXmlUtils;


public class TestXml {
	
	@Test
	public void testXml() throws DocumentException{
		//========读xml文件
		String filePath = "F:\\projects\\javaweb\\WebRoot\\pages\\xml/tmp.xml";
		Document doc = MyXmlUtils.parse(new File(filePath));
		System.out.println(doc.asXML());//xml形式的字符串
		Element rootEle = doc.getRootElement(); //根元素
		MyXmlUtils.listElements(rootEle);
		
		System.out.println("=============================================================");
		
		//============读xml形式的字符串
		String xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
				"<table level=\"1\" key=\"ID\"><modifiedRow>" +
				"<row key=\"1112\" rowNumber=\"3\"><gk_zgqk origin=\"\">1q</gk_zgqk><gk_zgrq origin=\"\">q1q</gk_zgrq>" +
				"<gk_zgzrr origin=\"\">1q</gk_zgzrr></row>" +
				"<row key=\"1119\" rowNumber=\"4\"><gk_zgqk origin=\"\">2q</gk_zgqk><gk_zgrq origin=\"\">2q</gk_zgrq>" +
				"<gk_zgzrr origin=\"\">2q</gk_zgzrr></row></modifiedRow></table>";
		Document doc2 = MyXmlUtils.parseXml(xmlStr);
		Element rootEle2 = doc2.getRootElement(); //根元素
		MyXmlUtils.listElements(rootEle2);

	}
}
