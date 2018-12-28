package com.ccg.time;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class TimeUtilsTest {

	
	/**
	 * DateUtils
	 */
	@Test
	public void testDateUtils(){
		//测试时间点：11点35分
		//返回当前时间的小时：11
		System.out.println(DateUtils.getFragmentInHours(new Date(), Calendar.DATE)); //11点
		//获取当前时间的分钟：11*60+35
		System.out.println(DateUtils.getFragmentInMinutes(new Date(), Calendar.DATE)); //11点28分
		//获取当前时间的秒：(11*60+35)*60
		System.out.println(DateUtils.getFragmentInSeconds(new Date(), Calendar.DATE));
		//获取当前时间的毫秒：(11*60+35)*60*1000
		System.out.println(DateUtils.getFragmentInMilliseconds(new Date(), Calendar.DATE));
	}
	
}
