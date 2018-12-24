/** 
 * RandomNumberUtil.java Created on Jan 26, 2010
 * Copyright 2010@JSHX. 
 * All right reserved. 
 */
package com.ccg.secret;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 * 在使用commons-lang的RandomStringUtils类时，有一个方法引起了我的注意RandomStringUtils.randomNumeric(int
 * count), 该方法返回指定数目的随机数字串，如果把返回的数字串解析成数字，不就可以产生随机数了么
 * 
 * @Time 7:14:55 PM
 * @author mengxiankong
 */
public class RandomNumberUtil {

	private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static final String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private static final String[] tchars = new String[] { "~", "!", "@", "#", "$", "%", "^", "&", "*" };
	

	/**
	 * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
	 * 
	 * @param digit
	 *            用户指定随机数据的位数
	 */
	public static long randomLong(int digit) {
		if (digit >= 19 || digit <= 0)
			throw new IllegalArgumentException(
					"digit should between 1 and 18(1<=digit<=18)");
		String s = RandomStringUtils.randomNumeric(digit - 1);
		return Long.parseLong(getPrefix() + s);
	}

	/**
	 * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit
	 * 
	 * @param minDigit
	 *            用户指定随机数据的最小位数 minDigit>=1
	 * @param maxDigit
	 *            用户指定随机数据的最大位数 maxDigit<=18
	 */
	public static long randomLong(int minDigit, int maxDigit) {
		if (minDigit > maxDigit) {
			throw new IllegalArgumentException("minDigit > maxDigit");
		}
		if (minDigit <= 0 || maxDigit >= 19) {
			throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
		}
		return randomLong(minDigit + getDigit(maxDigit - minDigit));
	}

	private static int getDigit(int max) {
		return RandomUtils.nextInt(max + 1);
	}
	/**
	 * 根据密级生成密码
	 * 1：一般，2：中等强度，3高强度；
	 * 高强度表示必须含特殊字符且必须是字符和数字混合,中等强度表示必须是字符和数字混合
	 * 
	 * @param grade
	 * @return
	 */
	public static String getDefaultPwd(int len, int grade){	
		String pwd = String.valueOf(randomLong(len));
		if(1==grade){
			
		} else if(2==grade){			
			String newc = chars[(new Random().nextInt(26)%26)];
			String oldc = pwd.substring(len/2, len/2+1);
			pwd = pwd.replace(oldc, newc);
		} else {			
			String newt = tchars[(new Random().nextInt(9)%9)];
			String oldt = pwd.substring(len/2, len/2+1);
			pwd = pwd.replace(oldt, newt);			
		}
		return pwd;
	}

	/**
	 * 保证第一位不是零
	 * 
	 * @return
	 */
	private static String getPrefix() {
		return prefix[RandomUtils.nextInt(9)] + "";
	}
	
	public void test(){
		long startTime;
		long endTime;
		int times = 8000000;
		Random rand = new Random();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			int a = rand.nextInt(5);
			System.out.println(a);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Random.nextInt(): " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			int a = (int)(Math.random() * 5);
			System.out.println(a);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Math.random(): " + (endTime - startTime));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(randomLong(18));
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
		System.out.println(System.currentTimeMillis());
		Random rand = new Random();
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(rand.nextInt(9));
		System.out.println(getDefaultPwd(6, 2));
		System.out.println(getDefaultPwd(6, 1));
		System.out.println(getDefaultPwd(6, 3));
		System.out.println(getDefaultPwd(6, 4));
		System.out.println(getDefaultPwd(6, 0));
	}

}
