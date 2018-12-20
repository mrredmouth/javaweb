package com.ccg.common.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ccg.pojo.User;

/**
 * Arrays.asList(),FilenameUtils,StringUtils
 * @author Administrator
 */
public class OrgUtils {
	private String filePath = "/czxtdemo3/WebRoot/WEB-INF/download/01_HTML-JS-新.xlsx";
	private String strToSplit = "png;gif;jpg;jpeg";
	private List<String> strList = Arrays.asList("list1","list2","list3");
	private List<Object> objList = Arrays.asList("list1",10,new User("Lilly","123456"));
	
	
	/**
	 * 测试官方的工具类
	 */
	@Test
	public void testOrgUtils(){
		
		//1、FilenameUtils
		System.out.println(FilenameUtils.getName(filePath));//获取文件名：XXX.xlsx
		System.out.println(FilenameUtils.getExtension(filePath));//获取文件拓展名：xlsx
		System.out.println(FilenameUtils.getBaseName(filePath));//获取文件前缀名：01_HTML-JS-新
		
		//2、CollectionUtils
		System.out.println(Arrays.asList(strToSplit.split(";")));//数组转集合
		System.out.println(CollectionUtils.isNotEmpty(objList));//判断集合是否为空，不管里面存的什么对象

		//3、StringUtils
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
	 * 测试官方的静态方法
	 */
	@Test
	public void testOrgStaticMethod(){
		
		//========================Arrays===============================
		System.out.println(Arrays.asList(strToSplit.split(";")));//数组转集合
	}
}