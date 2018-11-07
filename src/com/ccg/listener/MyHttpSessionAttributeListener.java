package com.ccg.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听seeion属性
 * @author Administrator
 *
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("属性添加："+se.getName()+","+se.getValue());
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("属性删除："+se.getName()+","+se.getValue());
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("属性替换："+se.getName()+",旧值："+se.getValue()+",新值："+se.getSession().getAttribute(se.getName()));
	}

}
