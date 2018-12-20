package com.ccg.io.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ccg.io.file.MyFileUtils;

public class MyPropertyUtils {
	
	/**
	 * 获取properties文件的属性键值对。
	 * @param fileName 如：jdbc.properties
	 * @return
	 * @throws IOException
	 */
	public static Properties getPropertiesFromFile(String fileName) throws IOException{
		Properties props = new Properties();
		InputStream is = null;
		try {
			//两种获取流的方式都可以
			//is = CommonUtils.class.getClassLoader().getResourceAsStream(fileName);
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyFileUtils.free(is);
		}
		return props;
	}
}
