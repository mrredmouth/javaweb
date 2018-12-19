package com.ccg.io.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;

/**
 * 
 * @author Administrator
 */
public class PoiExcelToHtml {
    final static String PATH = "F:/projects/javaweb/WebRoot/WEB-INF/download/";
	final static String FILE = "excelToHtmlDemo.xls";
	
	/**
	 * 将2003的excel文件，转变为静态html文件
	 * @param args
	 * @throws Exception
	 */
 public static void main(String args[]) throws Exception {

     InputStream input=new FileInputStream(PATH+FILE);
     HSSFWorkbook excelBook=new HSSFWorkbook(input);
     ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
     excelToHtmlConverter.processWorkbook(excelBook);
     @SuppressWarnings("rawtypes")
	 List pics = excelBook.getAllPictures();
     if (pics != null) {
         for (int i = 0; i < pics.size(); i++) {
             Picture pic = (Picture) pics.get (i);
             try {
                 pic.writeImageContent (new FileOutputStream (PATH + pic.suggestFullFileName() ) );
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
     }
     Document htmlDocument =excelToHtmlConverter.getDocument();
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     DOMSource domSource = new DOMSource (htmlDocument);
     StreamResult streamResult = new StreamResult (outStream);
     TransformerFactory tf = TransformerFactory.newInstance();
     Transformer serializer = tf.newTransformer();
     serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
     serializer.setOutputProperty (OutputKeys.INDENT, "yes");
     serializer.setOutputProperty (OutputKeys.METHOD, "html");
     serializer.transform (domSource, streamResult);
     outStream.close();

     String content = new String (outStream.toByteArray() );

     FileUtils.writeStringToFile(new File (PATH, "exportExcel.html"), content, "utf-8");
 }
}
