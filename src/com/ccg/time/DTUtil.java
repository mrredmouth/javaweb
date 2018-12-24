/** 
* DTUtil.java Created on Dec 4, 2009
* Copyright 2009@JSHX. 
* All right reserved. 
*/
package com.ccg.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @Time 3:02:05 PM
 * @author mengxiankong
 */
public class DTUtil {

	private static Calendar calendar = null;
	private static final int[] weeks = {0,7,1,2,3,4,5,6};
	
	public final static char DATE_DIFF_YEAR = 'y';
	public final static char DATE_DIFF_MONTH = 'M';
	public final static char DATE_DIFF_DAY = 'd';
	public final static char DATE_DIFF_HOUR = 'h';
	public final static char DATE_DIFF_MINUTE = 'm';
	public final static char DATE_DIFF_SECOND = 's';
	
	static {
		init();
	}
	
	private DTUtil(){};
	
	public static Date getDate(){
		return new Date();
	}
	
	public static Date getDate(String dtStr, String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date dt = null;
		try {
			dt = sdf.parse(dtStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public static Date getDate(Date dt, int var){
		calendar.setTime(dt);		
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+var);
		return calendar.getTime();
	}
	
	public static int getYear(){
		init();
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getYear(Date dt){
		calendar.setTime(dt==null ? new Date() : dt);
		return calendar.get(Calendar.YEAR);
	}
	
	public static String getYearStr(){
		init();
		return String.valueOf(calendar.get(Calendar.YEAR));
	}
	
	public static String getYearStr(int var){	
		init();	
		return String.valueOf(calendar.get(Calendar.YEAR)+var);
	}
	
	public static int getMonth(){
		init();		
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static int getMonth(Date dt){
		calendar.setTime(dt==null ? new Date() : dt);		
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static String getMonthStr(){
		init();
		int month = calendar.get(Calendar.MONTH) + 1;		
		return (month > 9) ? String.valueOf(month) : "0" + String.valueOf(month);
	}
	
	public static String getMonthStr(int var){
		init();
		int month = calendar.get(Calendar.MONTH);
		month = (month + var%12 + 13)%12 ;
		month = (month==0) ? 12 : month; 
		return (month > 9) ? String.valueOf(month) : "0" + String.valueOf(month);
	}
	
	public static int getDay(){	
		init();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getDayOfWeek(){
		init();
		return weeks[calendar.get(Calendar.DAY_OF_WEEK)];
	}
	
	public static int getDayOfWeek(Date dt){
		calendar.setTime(dt==null ? new Date() : dt);	
		return weeks[calendar.get(Calendar.DAY_OF_WEEK)];
	}
	
	public static int countDaysOfMonth(Date dt){
		calendar.setTime(dt==null ? new Date() : dt);		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static int getDay(Date dt){	
		calendar.setTime(dt==null ? new Date() : dt);		
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(){
		init();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getHour(Date dt){
		calendar.setTime(dt==null ? new Date() : dt);		
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 根据给定的时间（默认当前时间）和时间格式(默认yyyy-MM-dd) 返回字符串
	 * @param aMask 时间格式
	 * @param aDate 时间参数
	 * @return 格式化后的字符串
	 *
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDate(Date aDate, String aMask) {
		return new SimpleDateFormat(aMask==null ? "yyyy-MM-dd" : aMask).format(aDate==null ? new Date() : aDate);
	}
	
	public static String getDate(String aMask){
		return getDate(new Date(), aMask);
	}
	
	/**
	 * 比较日期时间差
	 * @param dtStart
	 * @param dtEnd
	 * @param chInterval
	 * @return
	 */
	public static long dateDiff(Date dtStart, Date dtEnd, char chInterval) {
		long irt = -1;
		if (dtStart == null || dtEnd == null ) {
			return irt;
		}		
		switch (chInterval) {
		case DATE_DIFF_YEAR:
			irt = getYear(dtEnd) - getYear(dtStart);
			break;
		case DATE_DIFF_MONTH:
			irt = getMonth(dtEnd) + (getYear(dtEnd) - getYear(dtStart))*12 - getMonth(dtStart);
			break;
		case DATE_DIFF_DAY:
			irt = (dtEnd.getTime() - dtStart.getTime()) / 86400000;
			break;
		case DATE_DIFF_HOUR:
			irt = (dtEnd.getTime() - dtStart.getTime()) / 3600000;
			break;
		case DATE_DIFF_MINUTE:
			irt = (dtEnd.getTime() - dtStart.getTime()) / 60000;
			break;
		case DATE_DIFF_SECOND:
			irt = (dtEnd.getTime() - dtStart.getTime()) / 1000;
			break;
		default:
			irt = dtEnd.getTime() - dtStart.getTime();
			break;
		}
		return Math.abs(irt);
	}
	
	//-----------------------------------------//
	private static void l(Object o){
		System.out.println(o);
	}
	
	private static void init(){
		calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
	}
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
//		l(getYear());
//		l(getMonth());
//		l(getDay());
//		l(getMonthStr());
//		l(getMonthStr(-1));
//		l(getMonthStr(0));
//		l(getMonthStr(1));
//		l(getMonthStr(2));
//		l("getDate()");
//		l(getDate());
//		l(getDate(new Date(), null));
//		l(getDate(new Date(), ""));
//		l(getDate(null, "yyyy-MM-dd"));
//		l(getDate(null, "yyyy-MM"));
//		l(getDate(null, "yyyy年MM月"));
//		l(getDate(null, "yyyyMMddHHmmss"));
		DateFormat df = DateFormat.getDateInstance();
		Date d1 = df.parse("2010-05-12");
		Date d2 = df.parse("2011-02-21");
		l(dateDiff(d1, d2, 'y'));
		l(dateDiff(d1, d2, 'M'));
		l(dateDiff(d1, d2, 'd'));
		l(dateDiff(d1, d2, 'h'));
		l(dateDiff(d1, d2, 'm'));
		l(dateDiff(d1, d2, 's'));
		l(dateDiff(d1, d2, 'k'));
		l(getMonth(d1));
		l(countDaysOfMonth(d1));
		l(getDay(d1));
		long months = dateDiff(d1, d2, 'M');
		Calendar cur = Calendar.getInstance();
		cur.setTime(d1);
		int s_d = getDay(d1);
		int e_d = getDay(d2);
		l("------------------------------");
		for(int i=0; i<=months; i++){
			int ds = countDaysOfMonth(cur.getTime());
			if(dateDiff(d1, cur.getTime(), 'M') == 0){
				ds = ds - s_d + 1;
			} else if(dateDiff(d2, cur.getTime(), 'M') == 0){
				ds = e_d;
			}
			l("年度："+getYear(cur.getTime())+";月份："+getMonth(cur.getTime())+";天数："+ds);
			cur.add(Calendar.MONTH, 1);
		}
	}

}
