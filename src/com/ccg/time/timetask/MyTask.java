/** 
* MyTask.java Created on Mar 29, 2010
* Copyright 2010@JSHX. 
* All right reserved. 
*/
package com.ccg.time.timetask;

import java.util.TimerTask;

/**
 * TODO 此处为类的功能性说明
 * @Time 4:31:15 PM
 * @author mengxiankong
 */
public class MyTask extends TimerTask{

	private String info = "^_^"; 
	
	@Override
	public void run() {
//		System.out.println("________");
		System.out.println(info);
	}

	/**
	 * @return Returns the info.
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info The info to set.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}
