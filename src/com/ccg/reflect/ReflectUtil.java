/** 
* ReflectUtil.java Created on Sep 7, 2009
* Copyright 2009@JSHX. 
* All right reserved. 
*/
package com.ccg.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 反射 工具类
 * @Time 4:34:00 PM
 * @author mengxiankong
 */
public class ReflectUtil {
	
	
	/**
	 * 传入对象和属性名称 获取对应的属性值
	 */
	public static Object getValueByObjAName(Object obj, String name) {
		Method method = null;
		Object result = null;
		if(obj == null || name==null || name.length() == 0) return null;
		try {
			if(obj instanceof Map){
				Map<?, ?> objMap = (Map<?, ?>) obj;
				// result = objMap.get(name)!=null ? objMap.get(name) : (objMap.get(name.toUpperCase())!=null ? objMap.get(name.toUpperCase()) : (objMap.get(name.toLowerCase())) );
				
				if(objMap.get(name)!=null){
					return objMap.get(name);
				} else if(objMap.get(name.toUpperCase())!=null){
					return objMap.get(name.toUpperCase());
				} else {
					return objMap.get(name.toLowerCase());
				}
			} else {
				method = obj.getClass().getMethod(getGetStr(name),
						new java.lang.Class[] {});
				result = method.invoke(obj, new Object[] {});
			}
		} catch (SecurityException e) {			
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	/**
	 * 根据属性名称 获取属性的Get方法
	 * @param str
	 * @return
	 */
	public static String getGetStr(String property) {
//		char ch[];
//		ch = property.toCharArray();
//		if (ch[0] >= 'a' && ch[0] < 'z') {
//			ch[0] = (char) (ch[0] - 32);
//		}
//		String s = new String(ch);
//		return "get" + s;
		return buildGetter(property);
	}
	
	public static String buildGetter(String property){
		String proHead = property.substring(0, 1);
		proHead = proHead.toUpperCase();
		return "get" + proHead + property.substring(1, property.length());
	}
	
	public static String buildSetter(String property){
		String proHead = property.substring(0, 1);
		proHead = proHead.toUpperCase();
		return "set" + proHead + property.substring(1, property.length());
	}
	
	public static void main(String[] args){
		Person p = new Person();
		p.setCode("code");
		Object o1 = getValueByObjAName(p, "code");
		Object o2 = getValueByObjAName(p, "id");
		Object o3 = getValueByObjAName(p, "name");
		System.out.println(o1);
		System.out.println(o2);	
		System.out.println(o3);		
//		Parameter pmt = Parameter.createParameter("abc", "abc");
//		pmt.addParameter("ABC", "ABC");
//		System.out.println(pmt);	
//		System.out.println("aBc" + getValueByObjAName(pmt, "aBc"));	
//		System.out.println("abc" + getValueByObjAName(pmt, "abc"));	
//		System.out.println("ABC" + getValueByObjAName(pmt, "ABC"));	
//		System.out.println("ABCD" + getValueByObjAName(pmt, "ABCD"));	
	}
	
	static class Person{
		private String code;
		private String name;
		
		/**
		 * @return Returns the code.
		 */
		public String getCode() {
			return code;
		}
		/**
		 * @param code The code to set.
		 */
		public void setCode(String code) {
			this.code = code;
		}
		/**
		 * @return Returns the name.
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name The name to set.
		 */
		public void setName(String name) {
			this.name = name;
		}
	}
	
}
