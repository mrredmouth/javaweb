package com.ccg.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("监听会话开始");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//session销毁的时候调用
		System.out.println("监听会话结束");
	}

}
