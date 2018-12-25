package com.ccg.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听seeion属性；
 * 监听session的某个属性,登录用户名的监听；
 * @author Administrator
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{


	/** 定义监听的session属性名  */
	public final static String LISTENER_NAME = "_login";
	/** 定义存储客户登录session的集合 */
	private static List<Object> sessions = new ArrayList<Object>();
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("属性添加："+se.getName()+","+se.getValue());
		
		if (LISTENER_NAME.equals(se.getName())) {
			sessions.add(se.getValue());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("属性删除："+se.getName()+","+se.getValue());

		if (LISTENER_NAME.equals(se.getName())) {
			sessions.remove((String)se.getValue());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("属性替换："+se.getName()+",旧值："+se.getValue()+",新值："+se.getSession().getAttribute(se.getName()));
	}


	/** 
	 * 返回客户登录session的集合. 
	 * @return 
	 */
	public static List<Object> getSessions() {
		return sessions;
	}
	
}
