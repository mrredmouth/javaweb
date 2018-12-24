/** 
 * TimeFormatUtil.java Created on May 21, 2009
 * Copyright 2009@JSHX. 
 * All right reserved. 
 */
package com.ccg.time;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间格式工具类
 * @Time 5:14:11 PM
 * @author mengxiankong
 */
public class DateTimeUtil {
	
	/**
	 * 取得日期datestring 格式yyyymmdd
	 * @param masktype "yyyy-MM-dd" or "yyyyMMdd"
	 * @throws ParseException 
	 * */
	public static Date getDateFromDateString(String datestring,String masktype) throws ParseException{
		
		SimpleDateFormat sdf=new SimpleDateFormat(masktype);
		
			
		return sdf.parse(datestring);
	}
	/**
	 * 取得系统当前时间
	 * @return 系统当前时间
	 */
	public static Date getSystemDateTime() {
		return new Date();
	}
	/**
	 * 根据年度和期间得到某期第一天或最后一天
	 * @param type 'first'or'last'
	 * @param year 
	 * @param qj 
	 * @return 系统当前时间 如 2010 3 first 返回2010-03-01的date形式
	 * @throws ParseException 
	 */
	public static String getDateTimeInACurtainYearAndMonth(String year,String qj,String type) throws ParseException {
		if(new Integer(qj)<10)
		{
			qj="0"+qj;
		}
		if("first".equals(type))
		{
			return year+"-"+qj+"-01";
		}
		if("last".equals(type))
		{
			return lastDayOfDate(year+"-"+qj);
		}
			return null;
	}
	
	/**
	 * 取得系统当前日期组成的字符串
	 * 默认格式为：yyyy-MM-dd
	 * @return 系统当前日期组成的字符串
	 */
	public static String getSystemDateString() {
		return getDateTime(getSystemDateTime(), "yyyy-MM-dd");
	}

	/**
	 * 取得系统当前日期＋时间组成的字符串
	 * 默认格式为：yyyy-MM-dd HH:mm:ss
	 * @return 系统当前日期＋时间组成的字符串
	 */
	public static String getSystemDateTimeString() {
		return getDateTime(getSystemDateTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据给定的时间（为空时默认使用当前时间）和时间格式 返回时间字符串
	 *
	 * @param aMask 时间格式
	 * @param aDate 时间参数
	 * @return 格式化后的时间字符串
	 *
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(Date aDate, String aMask) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (StringUtils.isBlank(aMask)) {
			return "";
		} else if(aDate == null){
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(getSystemDateTime());
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 返回时间的字符串
	 * @param dt
	 * @return
	 */
	public static String getLongStringByDate(Date aDate) {
		if (aDate == null)
			return "";
		return String.valueOf(aDate.getTime());
	}

	/**
	 * 根据 时间格式 时间Long字符串 转换时间格式
	 * @param aMask
	 * @param aDateLong
	 * @return
	 */
	public static String getDateStrByLongStr(String aDateLong, String aMask) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDateLong != null && !"".equals(aDateLong)) {
			if (aMask == null || "".equals(aMask))
				aMask = "yyyy-MM-dd HH:mm:ss";
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(new Date(Long.parseLong(aDateLong)));
		}
		return returnValue;
	}

	/**
	 * 获取上一个月字符串
	 * @param aDate 2010-06-01
	 * @return  05
	 */
	public static String getPreMonthStr(Date aDate) {
		return getMonthStr(aDate, -1);
	}

	/**
	 * 根据当前时间和参数 获取月份字符
	 * @param aDate
	 * @param m
	 * @return
	 */
	public static String getMonthStr(Date aDate, int m) {
		Calendar calendar = initCalendar(aDate);
		int month = calendar.get(Calendar.MONTH);
		month = (month + m%12 + 13)%12 ;
		month = (month==0) ? 12 : month; 
		return (month > 9) ? String.valueOf(month) : "0"
				+ String.valueOf(month);
	}
	
	
	/**
	 * 根据当前时间和参数 获取年份字符
	 * @param aDate
	 * @param y
	 * @return
	 */
	public static String getYearStr(Date aDate, int y) {
		Calendar calendar = initCalendar(aDate);
		int year = calendar.get(Calendar.YEAR);
		year = year + y;
		return String.valueOf(year);
	}
	
	/**
	 * 根据当前时间和参数 获取日期字符
	 * @param aDate
	 * @param d
	 * @return
	 */
	public static String getDayStr(Date aDate, int d) {
		Calendar calendar = initCalendar(aDate);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		day = day + d;
		calendar.set(Calendar.DAY_OF_YEAR, day);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		return (day > 9) ? String.valueOf(day) : "0"
				+ String.valueOf(day);
	}
	/**
	 *  根据日历的规则，为给定的时间字段添加或减去指定的月数
	 * @param 时间字段 （默认为当前时间）
	 * @param 为字段添加的月数
	 * @return
	 */
	public static Date getMonth(Date aDate, int amount){
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.MONTH, amount);		
		return calendar.getTime();
	}

	/**
	 *  根据日历的规则，为给定的时间字段添加或减去指定的年数
	 * @param 时间字段 （默认为当前时间）
	 * @param 为字段添加的年数
	 * @return
	 */
	public static Date getYear(Date aDate, int amount){
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.YEAR, amount);		
		return calendar.getTime();
	}

	/**
	 *  根据日历的规则，为给定的时间字段添加或减去指定的天数
	 * @param 时间字段 （默认为当前时间）
	 * @param 为字段添加的天数
	 * @return
	 */	
	public static Date getDay(Date aDate, int amount){
		Calendar calendar = initCalendar(aDate);
		calendar.add(Calendar.DAY_OF_MONTH, amount);		
		return calendar.getTime();
	}
	
	/**
	 * 设置日期
	 * @param aDate
	 * @param field
	 * @param value
	 * @return
	 */
	public static Date set(Date aDate, int field, int value) {
		Calendar c = initCalendar(aDate);
		c.set(field, value);
		return c.getTime();
	}
	
	public static Calendar initCalendar(Date aDate){
		Calendar calendar = null;
		if(aDate==null){
			calendar = Calendar.getInstance();
		} else {
			calendar = new GregorianCalendar();
			calendar.setTime(aDate);
		}
		return calendar;
	}
	
	
	

	 /**
     * 取得2个日期之间的天
     */
	public static List<String> getDayBetweenTwoDate(Date startdate,Date enddate){
		List<String> list=new ArrayList<String>();
		
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		//cal_end.set(cal_end.DAY_OF_YEAR, cal_end.get(Calendar.DAY_OF_YEAR)+1);
		for(;cal_start.compareTo(cal_end)<=0;cal_start.set(Calendar.DAY_OF_MONTH, cal_start.get(Calendar.DAY_OF_MONTH)+1))
		{
			String dd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
			String util[]=dd.split("-");
			//System.out.println(util[0]+"年"+util[1]+"月"+util[2]+"日"+"  ;");
			list.add(util[0]+"-"+util[1]+"-"+util[2]);
		}

		return list;
	}
	
	 /**
     * 取得2个日期之间的月份
     */
	public static List<String> getMonthBetweenTwoDate(Date startdate,Date enddate){
		List<String> list=new ArrayList<String>();
	
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_start.set(Calendar.DATE, 1);
		
		@SuppressWarnings("unused")
		String ddd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
		cal_end.setTime(enddate);
		//cal_end.set(cal_end.MONTH, cal_end.get(Calendar.MONTH)+1);
		for(;cal_start.compareTo(cal_end)<=0;cal_start.set(Calendar.MONTH, cal_start.get(Calendar.MONTH)+1))
		{
			String dd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
			//System.out.println(dd);
			String util[]=dd.split("-");
		    list.add(util[0]+"-"+util[1]);
		}

		return list;
	}
	/**
	 * 根据当前时间和参数 获取某个的日期，正数表示加，负数表示倒退
	 * @param startdate 起始时间yyyy-mm-dd
	 * @param y 年
	 * @param m 月
	 * @param d 日
	 * @return yy-mm-dd
	 * @throws ParseException 
	 */
	public static String calDate(String startdatestring, int y,int m,int d) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startdate=sdf.parse(startdatestring);
		Calendar cal_start = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_start.add(Calendar.YEAR, y);
			cal_start.add(Calendar.MONTH, m);
			cal_start.add(Calendar.DATE, d);
		String dd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
		//System.out.println(dd);
		return dd;
	}

	/*
	 * 传月2009－09返回本月最后一天
	 * */
	public static String lastDayOfDate(String startdatestring) throws ParseException {
		String[] date=startdatestring.split("-");
		String year=date[0];
		int month = Integer.parseInt(date[1])+1;
		String datebase="";
		if(month<10)
		{
			datebase=year+"-0"+month+"-01";
		}
		else
		{
			datebase=year+"-"+month+"-01";
		}
		
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startdate=sdf.parse(datebase);
		Calendar cal_start = Calendar.getInstance();
		cal_start.setTime(startdate);
	    cal_start.add(Calendar.DATE, -1);
	    String dd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
		
		return dd;
	}
	

	 /**
     * 取得2个日期之间的年
     */
	public static List<String> getYearBetweenTwoDate(Date startdate,Date enddate){
		List<String> list=new ArrayList<String>();
	
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		//cal_end.set(cal_end.YEAR, cal_end.get(Calendar.YEAR)+1);
		for(;cal_start.compareTo(cal_end)<=0;cal_start.set(Calendar.YEAR, cal_start.get(Calendar.YEAR)+1))
		{
			String dd=new SimpleDateFormat("yyyy-MM-dd").format(cal_start.getTime());
			String util[]=dd.split("-");
			list.add(util[0]);
		}
        return list;
	}
	
	 /**
     * 取得2个日期之间的年月
     */
	public static List<String> getYearMonthBetweenTwoDate(Date startdate,Date enddate){
		List<String> list=new ArrayList<String>();
	
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_end = Calendar.getInstance();
		cal_start.setTime(startdate);
		cal_end.setTime(enddate);
		//cal_end.set(cal_end.YEAR, cal_end.get(Calendar.YEAR)+1);
		for(;cal_start.compareTo(cal_end)<=0;cal_start.set(Calendar.MONTH, cal_start.get(Calendar.MONTH)+1))
		{
			String dd=new SimpleDateFormat("yyyyMM").format(cal_start.getTime());
			
			list.add(dd);
		}
        return list;
	}
	/**
	 * @param args
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		getYearMonthBetweenTwoDate(sdf.parse("201005"),
				sdf.parse("201006"));*/
//	System.out.println(URLEncoder.encode("%E2%88%9A","utf-8"));
	//System.out.println(URLDecoder.decode("%25E2%2588%259A","utf-8"));
	//URLDecoder.decode(s, enc)
		/*Date dt = new SimpleDateFormat("yyyy-MM-dd").parse("2009-3-3");	
		Date dt1 = new SimpleDateFormat().parse("2009-3");	
		String s = new SimpleDateFormat("yyyy-MM-dd").format(getDay(dt, -6));
		System.out.println(s);
		System.out.println(dt1);*/
		//System.out.println(DateTimeUtil.getReturnDate(new Date())+"-28");		
		//System.out.println(d);
		//System.out.println(getDayStr(new Date(), 0));
		//getDateList("201005","201009");\
	/*System.out.println(DateTimeUtil.getDateTime(new Date(), "yyyyMMdd")+
			DateTimeUtil.getLongStringByDate(new Date()));*/
		/*Set<String> pzidset=new HashSet<String>();
		pzidset.add("aaa");
		pzidset.add("bbb");
		pzidset.add("ccc");
		
		String pzids="";
		 Iterator<String> it=pzidset.iterator();
		    for(;it.hasNext();){
		    	pzids=pzids+" '"+it.next()+"' ,";
		    }
		    System.out.println("in ("+pzids.substring(0, pzids.lastIndexOf(","))+")");
		  */
		//System.out.println(getDateTimeInACurtainYearAndMonth("2010", "2", "first"));
		System.out.println("CWGLS3201A"+DateTimeUtil.getDateTime(new Date(), "yyyyMMddhhmmss"));
	}
	 /**
     * 取得当前年的前一年的最后一天,或前一月的最后一天,或前一天
     * @param flag
     * y,m,d
     */
	public static String getDateForLastYearOrMonthOrDay(String flag){
		
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String returnResults="";
		
		if("y".equals(flag))
		{
			cal.set(Calendar.MONTH,0);
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.DATE, -1);
			returnResults=sdf.format(cal.getTime());
		}
		else if("m".equals(flag))
		{
			//cal.set(cal.MONTH,-1);
			cal.set(Calendar.DATE, 1);
			cal.add(Calendar.DATE, -1);
			returnResults=sdf.format(cal.getTime());
		}
		else if("t".equals(flag))
		{
			cal.add(Calendar.MONTH,-2);
			cal.set(Calendar.DATE, 1);
			returnResults=sdf.format(cal.getTime());
		}
		else
		{
			cal.add(Calendar.DATE, -1);
			returnResults=sdf.format(cal.getTime());
		}
		return returnResults;
}

}
