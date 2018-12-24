package com.ccg.io.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class MyXmlUtils {
	public static String DEFAULT_ENCODING = "utf-8";

	// 生成xml形式的Document-------------------- load --------------------//
	/**
	 * 将xml文件转化为Xml Document
	 * @param file
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseDocument(File file) throws DocumentException {
		if (!file.exists()){
			return null;
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}
	/**
	 * 将xml文件转化为Xml Document
	 * @param filePath
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseDocumentFromFilePath(String filePath) throws DocumentException {
		return parseDocument(new File(filePath));
	}
	/**
	 * 将xml格式的字符串转化为XML Document
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseDocumentFromStr(String xmlStr) throws DocumentException {
		return StringUtils.isBlank(xmlStr) ? null : DocumentHelper.parseText(xmlStr);
	}

	/**
	 * 将xml形式的InputStream转化为XML Document
	 * @param stream
	 * @return
	 * @throws DocumentException 
	 */
	public Document parseDocument(InputStream stream) throws DocumentException{
		SAXReader reader=new SAXReader();
		Document document = reader.read(stream);
		return document;
	}
	
	public static Document parseDocument(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}
	// -------------------- create --------------------//
	/**
	 * 初始化Document
	 * @return
	 */
	public static Document createDoc() {
		Document document = DocumentHelper.createDocument();
		return document;
	}
	/**
	 * 生成根节点
	 * @param rootName
	 * @return
	 */
	public static Document createDoc(String rootName) {
		Document document = DocumentHelper.createDocument();
		return document;
	}
	/**
	 * 生成根节点
	 * @param document
	 * @param rootName
	 * @return
	 */
	public static Element addRoot(Document document, String rootName){
		Element root = document.addElement(rootName);
		return root;
	}

	// -------------------- write --------------------//
	/**
	 * Document写入文件
	 * 
	 * @param document
	 * @throws IOException
	 */
	public static void write(Document document, String fileName) throws IOException {
		write(document, fileName, DEFAULT_ENCODING);
	}

	/**
	 * 格式化XML文档,并按指定字符集输出
	 * 
	 * @param document
	 * @param fileName
	 * @param encoding
	 *            编码格式
	 * @return 返回操作结果, 0表失败, 1表成功
	 */
	public static int write(Document document, String fileName, String encoding)
			throws UnsupportedEncodingException, FileNotFoundException,
			IOException {
		int returnValue = 0;
		XMLWriter output = null;
		// 格式化输出,指定了格式化的方式为缩进式(非紧凑式)
		OutputFormat format = OutputFormat.createPrettyPrint();
		// format = OutputFormat.createCompactFormat();
		// 指定XML字符集编码
		encoding = StringUtils.isBlank(encoding) ? DEFAULT_ENCODING : encoding;
		format.setEncoding(encoding);
		output = new XMLWriter(new FileOutputStream(new File(fileName)), format);
		//writer = new XMLWriter(new FileWriter(new File(filename)), format);
		output.write(document);
		output.close();
		// 执行成功,需返回1
		returnValue = 1;
		return returnValue;
	}
	/**
	 * 生成节点
	 * @param element
	 * @param lable
	 * @return
	 */
	public Element addElement(Element element,String lable){
		Element node = element.addElement(lable);
		return node;
	}
	/**
	 * 为节点增加一个属性
	 * @param element
	 * @param name
	 * @param value
	 * @return
	 */
	public Element addAttribute(Element element, String name, String value){
		return element.addAttribute(name, value);
	}
	/** 
	 * 增加节点的值
	 * @param element 
	 * @param text 
	 */
	public Element addText(Element element, String text) {
		return element.addText(text);
	}
	
	public static String buildTag(String tagert, Object text) {
		return new StringBuffer("").append("<").append(tagert.trim()).append(
				">").append(text == null ? "" : text.toString().trim()).append(
				"</").append(tagert).append(">").toString();
	}
	// <![CDATA[text]]>
	public static String buildCDATATag(String tagert, Object text) {
		return new StringBuffer("").append("<").append(tagert.trim()).append("><![CDATA[").append(text == null ? "" : text.toString().trim()).append("]]></").append(tagert).append(">").toString();
	}

	// -------------------- read --------------------//
	public static String getXML(Document document) {
		return document == null ? "" : document.asXML();
	}
	
	public static String getXML(Element element) {
		return element == null ? "" : element.asXML();
	}
	
	public static Node selectNode(Document document, String xpath){		
		return document.selectSingleNode(xpath);
	}
	
	public static List<Node> selectNodes(Document document, String xpath){		
		return document.selectNodes(xpath);
	}

	// -------------------- tree --------------------//

	/**
	 * 如果你处理的XML文件很大，这个时候建议你最好使用dom4j提供的快速遍历方法，这个可以满足你对性能的需求，
	 * 因为dom4j快速遍历不会为每层循环都创建一个Iterator对象
	 * @param document
	 */
	public void treeWalk(Document document) {

		treeWalk(document.getRootElement());

	}

	/**
	 * Node node = element.node(i);关键是这一句，我们直接通过element下标就可以遍历节点，
	 * 而避免了为此创建Iterator对象，这就节省了大量遍历时间和资源
	 * @param element
	 */
	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			}
			else {
				// do something....
			}
		}
	}
	
	// Element-------------------- read --------------------//
	
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
	
	
}
