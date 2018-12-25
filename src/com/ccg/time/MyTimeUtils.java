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

}
