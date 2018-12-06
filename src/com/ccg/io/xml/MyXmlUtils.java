package com.ccg.io.xml;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MyXmlUtils {

	// -------------------- load --------------------//
	/**
	 * 将xml文件转化为Xml Document
	 * @param file
	 * @return
	 * @throws DocumentException
	 */
	public static Document parse(File file) throws DocumentException {
		if (!file.exists())
			return null;
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}
	public static Document parse(String filePath) throws DocumentException {
		return parse(new File(filePath));
	}
	/**
	 * 将xml格式的字符串转化为XML Document
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseXml(String xmlStr) throws DocumentException {
		return StringUtils.isBlank(xmlStr) ? null : DocumentHelper.parseText(xmlStr);
	}
	
	// -------------------- create --------------------//
	
	
	
	
	// -------------------- read --------------------//
	
	/**
	 * 展示某个元素的内容：元素名，元素的属性(属性名，属性值)，元素内容
	 * @param ele
	 */
	public static void listElements(Element ele) {  
        System.out.println("【name】：" + ele.getName());  // 元素名
        List<Attribute> list = ele.attributes();  // 元素的属性
        List<String> attrStr = new ArrayList<String>();
        for (Attribute attr : list) {  
        	attrStr.add(attr.getName() + "=" + attr.getValue());  
        }  
        System.out.println("【attributes】：" + StringUtils.join(attrStr, ","));
        System.out.println("【text】：" + ele.getTextTrim());  // 元素值

        // 遍历ele的子元素 
        Iterator<Element> it = ele.elementIterator();  
        while (it.hasNext()) {  
            Element e = it.next();  
            listElements(e);  //递归展示子元素的内容
        }  
    }  
	public static void listElements2(Element ele) {  
		Iterator<Element> eleIterator = (Iterator<Element>)ele.elementIterator(); 
		while (eleIterator.hasNext()) {  
			//元素名
			String eleName = ele.getName();		
			// 元素的属性：属性名，属性值
			List<Attribute> eleAttributes = (List<Attribute>)ele.attributes();  
			//元素值
			String eleText = ele.getTextTrim(); 
			
			System.out.println("【name】：" + eleName +",【text】:" + eleText +"，【attributes】：" + eleAttributes.toString());
			ele = eleIterator.next();  
		}
    } 
	
	
	//文件流InputStream转Document,转Element
	public Element parseXml(InputStream stream){
		Element root = null;
		SAXReader reader=new SAXReader();
		Document document;
		try {
			document = reader.read(stream);
			root = document.getRootElement();//Element为根结点
		} catch (DocumentException e) {
	      e.printStackTrace();
		}
		return root;
	}
}
