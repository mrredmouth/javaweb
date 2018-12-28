package com.ccg.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * @author Administrator
 */
public class MyDateFormatUtils {

	/** SimpleDateFormat是一个非线程安全的类，一般不定义为static,如果定义为static，必须加锁，或者使用DateUtils工具类。*/
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将日期格式化成字符串，
	 * SimpleDateFormat没有定义为static，所以不需要加同步块
	 * @param formatStr "yyyyMMdd HH:mm:ss"
	 * @param date new Date()
	 * @return
	 */
    public static String getDateFormatStr(String formatStr , Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }   
    /**
     * 将字符串转为日期，formatStr与dateStr格式必须一致
     * @param formatStr "yyyyMM"
     * @param dateStr "201809"
     * @return
     * @throws ParseException 
     */
    public static Date getDateFormatStr(String formatStr , String dateStr) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
    	return sdf.parse(dateStr);
    }   
    
    /**
     * 使用默认格式化："yyyy-MM-dd HH:mm:ss"
     * SimpleDateFormat是一个非线程安全的类，一般不定义为static,如果定义为static，必须加锁，或者使用DateUtils工具类。
     * @param date
     * @return
     */
    public static String getDateFormatStr(Date date){
    	String result = "";
    	synchronized (SIMPLE_DATE_FORMAT){
    		result = SIMPLE_DATE_FORMAT.format(date);
    	}
    	return result;
    }   
    

	@Test
	public void testGetDate(){
		System.out.println(MyDateFormatUtils.getDateFormatStr(MyTimeUtils.getDate(14,30,0)));
	}
}
