package com.ccg.io.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import com.ccg.io.file.MyFileUtils;

public class MyPropertyUtils {
	
	/**
	 * 获取properties文件的属性键值对。
	 * @param fileName 如：jdbc.properties
	 * @return
	 * @throws IOException
	 */
	public static Properties getPropertiesFromFile(String fileName) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			//三种方式获取流
			//方式一、
			/*URL url = MyPropertyUtils.class.getClassLoader().getResource(fileName);
			File file = new File(url.getFile().replace("%20", " "));
			is = new FileInputStream(file);*/
			//方式二、
			//is = MyPropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
			//方式三、
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is != null){
				props.load(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MyFileUtils.free(is);
		}
		return props;
	}
	

	
	
	/**
	 * 返回配置文件中key对应的value值
	 * @param fileName 必须在classpath路径下面
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public static String getPropertyValue(String fileName,String key) throws IOException {
		
		if(StringUtils.isBlank(fileName)){
			return null;
		}
		//加载资源文件
		Properties props = MyPropertyUtils.getPropertiesFromFile(fileName);
		
		String message = props.getProperty(key);
		if (StringUtils.isBlank(message)) {
			return null;
		} 
		return message;
	}
	
	/**
	 * 返回配置文件中key对应的value值，默认value值为defaultValue
	 * @param fileName properties文件，如：jdbc.properties
	 * @param key 键值对中的键的值
	 * @param defaultValue 没有此键值对的时候，取此默认值
	 * @return
	 * @throws IOException 
	 */
	public static String getPropertyValue(String fileName,String key,String defaultValue) throws IOException{
		String message = getPropertyValue(fileName,key);
		if(StringUtils.isBlank(message)){
			return defaultValue;
		}
		return message;
	}
}
