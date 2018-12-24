/** 
* ContextUtil.java Created on Jun 10, 2009
* Copyright 2009@JSHX. 
* All right reserved. 
*/
package com.ccg.common.basic;

import java.io.File;

import javax.servlet.ServletContext;

import com.ccg.base.SystemConfig;



/**
 * ServletContext 上下文
 * @Time 10:25:22 AM
 * @author mengxiankong
 */
public class ContextUtil {


	private ServletContext context = null;// Servlet 上下文
	private static ContextUtil instances = null;
	
	private ContextUtil() {
	}
	
	public static ContextUtil getInstance() {
		return instances==null ? new ContextUtil() : instances;
	}
	
	public void init(ServletContext context) {
		if(this.context == null) {
			this.context = context;
		}
		// 初始化系统根目录
		String webRootPath = this.context.getRealPath("/");
		if (!webRootPath.endsWith("/") && !webRootPath.endsWith("\\"))
			webRootPath = webRootPath + File.separator;	
		String webInfRootPath = null;
		if (webInfRootPath == null || "".equals(webInfRootPath))
			webInfRootPath = "/WEB-INF";	
		webInfRootPath = this.context.getRealPath(webInfRootPath);
		if (!webInfRootPath.endsWith("/") && !webInfRootPath.endsWith("\\"))
			webInfRootPath = webInfRootPath + File.separator;
		SystemConfig.webRootPath = webRootPath;
		SystemConfig.webInfRootPath = webInfRootPath;
		SystemConfig.classRootPath = webRootPath + "WEB-INF" + File.separator + "classes" + File.separator;
	}
	
	public Object getAttribute(String attributeName) {
		return context.getAttribute(attributeName);
	}
	
	public void setAttribute(String name, Object object) {
		context.setAttribute(name, object);
	}
}
