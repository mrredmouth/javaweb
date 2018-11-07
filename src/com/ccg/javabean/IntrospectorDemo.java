package com.ccg.javabean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import com.ccg.pojo.UserLogin;

/**
 * javaBean的内省机制，通过反射，获取某个bean的属性和方法等
 * @author Administrator
 *
 */
public class IntrospectorDemo {

	public static void main(String[] args) throws IntrospectionException {
		
		BeanInfo beanInfo = Introspector.getBeanInfo(UserLogin.class, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd:pds){
			String name = pd.getName();//属性名
			Class<? extends PropertyDescriptor> type = pd.getClass();//属性类型
			Method getter = pd.getReadMethod();//获取getter方法
			Method setter = pd.getWriteMethod();//获取setter方法
			System.out.println(name+","+type+","+getter+","+setter);
		}
		
	}
}
