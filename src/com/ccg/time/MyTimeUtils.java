package com.ccg.time;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class MyTimeUtils {
	
	/**
	 * 获取上月;
	 * date 传入时间类型，返回时间类型
	 */
	@Test
	public static Date getLastMonth(Date date){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); // 设置为当前时间 
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月 
		Date lastDate = calendar.getTime(); 
		return lastDate;
	}
	
	/**
	 * 获取上月;
	 * dateStr 必须与parse的类型一致，也是yyyyMM
	 * @throws ParseException 
	 */
	@Test
	public static String getLastMonth(String dateStr) throws ParseException{
		Date date = MyDateFormatUtils.getDateFormatStr("yyyyMM", dateStr);
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // -1，设置为上一个月 
		Date lastDate = calendar.getTime(); 
		return MyDateFormatUtils.getDateFormatStr("yyyyMM", lastDate);
	}
}
