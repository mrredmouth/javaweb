package com.ccg.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class MyTimeUtils {
	
	/**
	 * 获取上月;
	 * date 传入时间类型，返回时间类型
	 */
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
	public static String getLastMonth(String dateStr) throws ParseException{
		Date date = MyDateFormatUtils.getDateFormatStr("yyyyMM", dateStr);
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // -1，设置为上一个月 
		Date lastDate = calendar.getTime(); 
		return MyDateFormatUtils.getDateFormatStr("yyyyMM", lastDate);
	}

    /**
     * 获取两个日期范围内所有的月份
     * 
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @param parse
     *            日期格式
     */
    public static String[] getBetweenTwoDate(Date startDate, Date endDate, String parse) {

        SimpleDateFormat sdf = new SimpleDateFormat(parse);
        Calendar dd = Calendar.getInstance();// 定义日期实例
        dd.setTime(startDate);// 设置日期起始时间
        String reStr = "";
        while (dd.getTime().before(endDate) || dd.getTime().equals(endDate)) {// 判断是否到结束日期
            String str = sdf.format(dd.getTime());
            reStr = reStr + str + ",";
            dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
        }
        String[] result = reStr.split(",");
        return result;
    }
    
	/**
	 * 根据开始时间startDate，进行年月日的加减
	 * @param startdate 起始时间yyyy-mm-dd
	 * @param y 年
	 * @param m 月
	 * @param d 日
	 * @return yy-mm-dd
	 * @throws ParseException 
	 */
	public static Date getDate(Date startDate, int y,int m,int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, y);
		calendar.add(Calendar.MONTH, m);
		calendar.add(Calendar.DATE, d);
		return calendar.getTime();
	}
	
    /**
	 * 根据时分秒，得到日期：默认当前时间为依据，进行时分秒加减
	 * getDate(14,30,0) -> 2018-12-25 14:30:00
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
     * @throws ParseException 
	 */
	public static Date getDate(int hour,int minute,int second){
		Date date = new Date();
		return MyTimeUtils.getDate(date,hour,minute,second);
	}

	/**
	 * 获取某月最后一天。
	 * 逻辑：月+1，天-1。
	 * @param startdatestring "-"分隔的年月字符串，如：2018-09
	 * @return
	 * @throws ParseException
	 */
	public static String lastDayOfDate(String startdatestring) throws ParseException {
		String[] date=startdatestring.split("-");
		String year=date[0];
		int month = Integer.parseInt(date[1])+1;
		String datebase="";
		if(month<10) {
			datebase=year+"-0"+month+"-01";
		} else {
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
	 * 获取今天时间,和上月的今天;
	 */
	@Test
	public void getLastMonthTime(){
		System.out.println(MyTimeUtils.getLastMonth(new Date()));
	}
	/**
	 * 获取上月;
	 * @throws ParseException 
	 */
	@Test
	public void getLastMonth() throws ParseException{
		System.out.println(MyTimeUtils.getLastMonth("201809"));
		System.out.println(MyTimeUtils.getLastMonth("201801"));
	}
}
