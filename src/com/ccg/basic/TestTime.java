package com.ccg.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * java基础：
 * 1、Time时间相关
 * @author Administrator
 *
 */
public class TestTime {

	/**
	 * 获取今天时间,和上月的今天;
	 */
	@Test
	public void getTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日"); 
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd"); 
		Date nowDate = new Date(); 
		Date date2 = null;
		try {
			date2 = dateFormat2.parse("20181031");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date2);
		System.out.println("当前时间是：" + dateFormat.format(nowDate)); 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(nowDate); // 设置为当前时间 
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月 
		Date lastDate = calendar.getTime(); 
		System.out.println("上个月的时间： " + dateFormat.format(lastDate));
	}
	/**
	 * 获取上月;
	 */
	@Test
	public void getMonth(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM"); 
		Date nowDate =  null;
		try {
			nowDate = dateFormat.parse("201801");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("当前月：" + dateFormat.format(nowDate)); 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(nowDate); // 设置为当前时间 
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月 
		Date lastDate = calendar.getTime(); 
		System.out.println("上个月： " + dateFormat.format(lastDate));
	}
}
