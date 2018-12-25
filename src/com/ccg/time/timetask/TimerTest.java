/** 
* TimerTest.java Created on Mar 29, 2010
* Copyright 2010@JSHX. 
* All right reserved. 
*/
package com.ccg.time.timetask;

import java.io.IOException;
import java.util.Timer;

/**
 * Timer类是用来执行任务的类，它接受一个TimerTask做参数 Timer有两种执行任务的模式,最常用的是schedule,
 * 它可以以两种方式执行任务:1:在某个时间(Data)，2:在某个固定的时间之后(int delay). 这两种方式都可以指定任务执行的频率
 * 
 * @Time 4:29:49 PM
 * @author mengxiankong
 */
public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 1000, 2000);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
		while(true){// 这个是用来停止此任务的,否则就一直循环执行此任务了
			try {
				int ch = System.in.read();
				if(ch-'c'==0){
					timer.cancel();// 使用这个方法退出任务
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
