/** 
* SystemConfig.java Created on Apr 16, 2010
* Copyright 2010@JSHX. 
* All right reserved. 
*/
package com.ccg.base;

import java.io.File;

/**
 * 系统配置
 * @Time 1:00:53 PM
 * @author mengxiankong
 */
public final class SystemConfig {
	
	public static String webRootPath = null;
	public static String classRootPath = null;
	public static String webInfRootPath = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SystemConfig.webRootPath);
		System.out.println(SystemConfig.classRootPath);
		System.out.println(SystemConfig.webInfRootPath);
		SystemConfig.webRootPath = "/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/cwconNode01Cell/glkj_war.ear/glkj.war/";
		SystemConfig.classRootPath = SystemConfig.webRootPath + "WEB-INF" + File.separator + "classes" + File.separator;
		SystemConfig.webInfRootPath = SystemConfig.webRootPath + "WEB-INF" + File.separator;
		System.out.println(SystemConfig.webRootPath);
		System.out.println(SystemConfig.classRootPath);
		System.out.println(SystemConfig.webInfRootPath);
	}

}
