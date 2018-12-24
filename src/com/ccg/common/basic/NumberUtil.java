/** 
 * NumberUtil.java Created on Aug 13, 2009
 * Copyright 2009@JSHX. 
 * All right reserved. 
 */
package com.ccg.common.basic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import org.apache.commons.lang3.StringUtils;


/**
 * 数字工具类
 * 
 * @Time 5:49:45 PM
 * @author mengxiankong
 */
public class NumberUtil {

	// 默认运算精度 不能为 0
	private static final int DEF_DOUBLE_SCALE = 2;
	// 保留精度，
	// BigDecimal.ROUND_HALF_EVEN：财务常用的四舍六入五取偶，
	// BigDecimal.ROUND_HALF_UP 四舍五入
	private static int DEF_ROUND_MODE = BigDecimal.ROUND_HALF_UP;

	// 这个类不能实例化
	private NumberUtil() {
	}

	public static double getD(String s) {
		return StringUtils.isBlank(s) ? 0d : Double.parseDouble(s.trim()
				.replaceAll(",|，", ""));
	}

	public static long getL(String s) {
		return StringUtils.isBlank(s) ? 0l : Long.parseLong(s.trim().replaceAll(
				",|，", ""));
	}
	
	public static int getInt(String str){
		return (str == null || str.length() == 0) ? 0 : Integer.parseInt(str);
	}

	public static String formatDbl(double dbl, String aMask) {
		if (StringUtils.isBlank(aMask))
			aMask = "###############.##";
		DecimalFormat df = new DecimalFormat(aMask);
		// System.out.println(df.format(dbl));
		return df.format(dbl);
	}

	public static double formatDbl(String dbl, String aMask)
			throws ParseException {
		if (StringUtils.isBlank(aMask))
			aMask = "###############.##";
		DecimalFormat df = new DecimalFormat(aMask);
		return df.parse(dbl).doubleValue();
	}

	/**
	 * 格式化数字(自定义保留小数点)
	 * 
	 * @param value
	 *            需要格式化的数值
	 * @param digit
	 *            保留的位数 负数为默认位小数 可以自定义保留digit位小数
	 * @return
	 */
	public static String ConvertToString(Double value, int digit) {
		StringBuffer disb = new StringBuffer("####"); // 数字以[123456.0] 的格式输出
		if (digit == DEF_DOUBLE_SCALE || digit < 0) {
			disb.append(".00");
		} else if (digit > 0) {
			disb.append(".");
			for (int i = 0; i < digit; i++) {
				disb.append("0");
			}
		} else {

		}
		DecimalFormat df = new DecimalFormat(disb.toString());
		return df.format(value);
	}

	public static String ConvertToString(Double value) {
		return ConvertToString(value, DEF_DOUBLE_SCALE);
	}

	/**
	 * 取得浮点数
	 * 
	 * @param dbl
	 * @return
	 */
	public static double getDbl(Double dbl) {
		return (Double.isInfinite(dbl) || Double.isNaN(dbl)) ? 0.0 : dbl;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		return add(v1, v2, DEF_DOUBLE_SCALE);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		return sub(v1, v2, DEF_DOUBLE_SCALE);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		return mul(v1, v2, DEF_DOUBLE_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DOUBLE_SCALE);
	}

	/**
	 * 提供精确的乘法运算
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            :保留精度
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(ConvertToString(v1, scale));
		BigDecimal b2 = new BigDecimal(ConvertToString(v2, scale));
		return b1.multiply(b2).setScale(scale, DEF_ROUND_MODE).doubleValue();
	}

	/**
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            :保留精度
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(ConvertToString(v1, scale));
		BigDecimal b2 = new BigDecimal(ConvertToString(v2, scale));
		return b1.add(b2).setScale(scale, DEF_ROUND_MODE).doubleValue();
	}

	/**
	 * 提供精确的减法运算
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            :保留精度
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(ConvertToString(v1, scale));
		BigDecimal b2 = new BigDecimal(ConvertToString(v2, scale));
		return b1.subtract(b2).setScale(scale, DEF_ROUND_MODE).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(ConvertToString(v1, scale));
		BigDecimal b2 = new BigDecimal(ConvertToString(v2, scale));
		// return b1.divide(b2).setScale(scale, DEF_ROUND_MODE).doubleValue();
		return b1.divide(b2, scale, DEF_ROUND_MODE).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(ConvertToString(v, scale + 1));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, DEF_ROUND_MODE).doubleValue();
	}
	
	/**
	 * 对double数据进行取精度.
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * ret为100.3457 <br>
	 * @param value double数据.
	 * @param scale 精度位数(保留的小数位数).
	 * @param roundingMode 精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}    
	
	/**
	 * Java 小数精确计算问题 并不是所有的小数都可以用二进制浮点数精确表示。 二进制浮点对于货币计算是非常不适合的，因为它不可能将1.0 表示成10
	 * 的其他任何负次幂。
	 * 使用BigDecimal，但一定要用BigDecimal(String)构造器，而千万不要用BigDecimal(double)来构造
	 * （也不能将float或double型转换成String再来使用BigDecimal(String)来构造，
	 * 因为在将float或double转换成String时精度已丢失）。例如new BigDecimal(0.1)，它将返回一个BigDecimal，
	 * 也即0.1000000000000000055511151231257827021181583404541015625，正确使用BigDecimal，
	 * 程序就可以打印出我们所期望的结果0.9： System.out.println(new
	 * BigDecimal("2.0").subtract(new BigDecimal("1.10")));// 0.9
	 * 另外，如果要比较两个浮点数的大小，要使用BigDecimal的compareTo方法。
	 * http://hi.baidu.com/nova_xmu_fjut/blog/item/7ab0c832ba6ce845ac4b5f73.html
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// int i = 0x1b9;
		Double dd1 = 286403.04;
		Double dd2 = 226008.16;
		Double dd3 = 1244.61;
		Double d1 = dd1 * 100;
		Double d2 = dd2 * 100;
		Double d3 = dd3 * 100;
		System.out.println("Double1:" + round(d1, 2, BigDecimal.ROUND_HALF_UP));
		System.out.println("Double2:" + round(d2, 2, BigDecimal.ROUND_HALF_UP));
		System.out.println("Double3:" + round(d3, 2, BigDecimal.ROUND_HALF_UP));
		System.out.println(new Double(0.0001) > 0);
		System.out.println("S");
	}

}
