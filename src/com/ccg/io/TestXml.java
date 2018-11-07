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
		String filePath = "F:/projects/myit/WebRoot/pages/xml/tmp.xml";
		String xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
				"<table level=\"1\" key=\"ID\"><modifiedRow>" +
				"<row key=\"1112\" rowNumber=\"3\"><gk_zgqk origin=\"\">1q</gk_zgqk><gk_zgrq origin=\"\">q1q</gk_zgrq>" +
				"<gk_zgzrr origin=\"\">1q</gk_zgzrr></row>" +
				"<row key=\"1119\" rowNumber=\"4\"><gk_zgqk origin=\"\">2q</gk_zgqk><gk_zgrq origin=\"\">2q</gk_zgrq>" +
				"<gk_zgzrr origin=\"\">2q</gk_zgzrr></row></modifiedRow></table>";
		
		
		Document doc = MyXmlUtils.parse(new File(filePath));
		System.out.println(doc.asXML());//xml形式的字符串
		System.out.println(xmlStr);//xml形式的字符串
		

		Element rootEle = doc.getRootElement(); //根元素
		
		MyXmlUtils.listElements(rootEle);
	}
}
