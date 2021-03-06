package com.ccg.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ccg.pojo.User;

/**
 * Arrays.asList(),FilenameUtils,StringUtils
 * @author Administrator
 */
public class OrgUtils {

	private static final Log logger = LogFactory.getLog(OrgUtils.class);
	
	private String filePath = "/czxtdemo3/WebRoot/WEB-INF/download/01_HTML-JS-新.xlsx";
	private String strToSplit = "png;gif;jpg;jpeg";
	private List<String> strList = Arrays.asList("list1","list2","list3");
	private List<Object> objList = Arrays.asList("list1",10,new User("Lilly","123456"));
	
	
	/**
	 * 测试官方的工具类：
	 * FilenameUtils，
	 * CollectionUtils，
	 * StringUtils
	 */
	@Test
	public void testOrgUtils(){
		
		//1、FilenameUtils==================================================
		System.out.println(FilenameUtils.getName(filePath));//获取文件名：XXX.xlsx
		System.out.println(FilenameUtils.getExtension(filePath));//获取文件拓展名：xlsx
		System.out.println(FilenameUtils.getBaseName(filePath));//获取文件前缀名：01_HTML-JS-新
		
		//2、CollectionUtils==================================================
		System.out.println(Arrays.asList(strToSplit.split(";")));//数组转集合
		System.out.println(CollectionUtils.isNotEmpty(objList));//判断集合是否为空，不管里面存的什么对象

		//3、StringUtils==================================================
		System.out.println(StringUtils.join(strList, ","));
		//blank:空格表示空；empty:空格表示非空。其他两者一致
		System.out.println(StringUtils.isNotBlank(null)+","+StringUtils.isNotBlank("")+","+StringUtils.isNotBlank(" "));
		System.out.println(StringUtils.isNotEmpty(null)+","+StringUtils.isNotEmpty("")+","+StringUtils.isNotEmpty(" "));
		/**
		 * StringUtils.leftPad(String str, int size, char padChar)
		 * 返回长度为size或者str的字符串：
		 * 如果str长度小于5，则左边用padChar补充		StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
		 * 如果str长度大于5，则返回str	StringUtils.leftPad("battteeeeet", 5, 'z')  = "battteeeeet"
		 */
		System.out.println(StringUtils.leftPad("bat", 5, 'z'));
	}
	
	/**
	 * DateUtils
	 */
	@Test
	public void testDateUtils(){
		
		//1、DateUtils==================================================
		//测试时间点：11点35分
		//返回当前时间的小时：11
		System.out.println(DateUtils.getFragmentInHours(new Date(), Calendar.DATE));
		//获取当前时间的分钟：11*60+35
		System.out.println(DateUtils.getFragmentInMinutes(new Date(), Calendar.DATE));
		//获取当前时间的秒：(11*60+35)*60
		System.out.println(DateUtils.getFragmentInSeconds(new Date(), Calendar.DATE));
		//获取当前时间的毫秒：(11*60+35)*60*1000
		System.out.println(DateUtils.getFragmentInMilliseconds(new Date(), Calendar.DATE));
	}
	
	
	/**
	 * 测试官方的静态方法，
	 * Arrays，URLEncoder，URLDecoder，
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@Test
	public void testOrgStaticMethod() throws UnsupportedEncodingException{
		
		//Arrays====================================================================
		System.out.println(Arrays.asList(strToSplit.split(";")));//数组转集合
		
		//URLEncoder，URLDecoder，==============================================================
		String orignalStr = "fatherid=2240&fatherdir=BT1261&fathername=2018年久其月报&global_flow_code=421";
		String encoderStr = URLEncoder.encode(orignalStr,"UTF-8");
		System.out.println("URLEncoder:  "+encoderStr);
		String decoderStr = URLDecoder.decode(encoderStr,"UTF-8");
		System.out.println("URLDecoder:  "+decoderStr);
	}
	

	/**
	 * TimeUnit的静态属性
	 */
	@Test
	public void testTimeUnit() {
		logger.info(TimeUnit.DAYS); //天
		logger.info(TimeUnit.HOURS);  //小时
		logger.info(TimeUnit.MINUTES); //分钟
		logger.info(TimeUnit.SECONDS);  //秒
		logger.info(TimeUnit.MILLISECONDS); //毫秒
		logger.info(TimeUnit.MICROSECONDS); //微妙
		logger.info(TimeUnit.NANOSECONDS); //纳秒
	}
}
