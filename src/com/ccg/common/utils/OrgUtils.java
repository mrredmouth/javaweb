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
	private String fileName = "/czxtdemo3/WebRoot/WEB-INF/download/01_HTML-JS-新.xlsx";
	private String strToSplit = "png;gif;jpg;jpeg";
	private List<String> strList = Arrays.asList("list1","list2","list3");
	private List<Object> objList = Arrays.asList("list1",10,new User("Lilly","123456"));
	
	
	/**
	 * 测试官方的工具类
	 */
	@Test
	public void testOrgUtils(){
		
		//========================FilenameUtils===============================
		System.out.println(FilenameUtils.getName(fileName));//获取文件名：XXX.xlsx
		System.out.println(FilenameUtils.getExtension(fileName));//获取文件拓展名：xlsx
		System.out.println(FilenameUtils.getBaseName(fileName));//获取文件前缀名：01_HTML-JS-新
		
		//========================CollectionUtils===============================
		System.out.println(Arrays.asList(strToSplit.split(";")));//数组转集合
		System.out.println(CollectionUtils.isNotEmpty(objList));//判断集合是否为空，不管里面存的什么对象

		//========================StringUtils===============================
		System.out.println(StringUtils.join(strList, ","));
		//blank:空格表示空；empty:空格表示非空。其他两者一致
		System.out.println(StringUtils.isNotBlank(null)+","+StringUtils.isNotBlank("")+","+StringUtils.isNotBlank(" "));
		System.out.println(StringUtils.isNotEmpty(null)+","+StringUtils.isNotEmpty("")+","+StringUtils.isNotEmpty(" "));
		
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
