/** 
 * CoderUtil.java Created on Feb 24, 2010
 * Copyright 2010@JSHX. 
 * All right reserved. 
 */
package com.ccg.secret.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 编码工具类
 * 
 * @Time 4:31:35 PM
 * @author mengxiankong
 */
public abstract class CoderUtil {

	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decoderBASE64(String key) throws Exception {
		return Base64.decodeBase64(key);
	}
	
	public static String decoderBASE64ToString(String key) throws Exception {
		return new String(decoderBASE64(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encoderBASE64(byte[] key) throws Exception {
		return new String(Base64.encodeBase64(key));
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encoderMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encoderSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
	
	public static byte[] shortToByte(short pShort)
	{
		byte bb[] = new byte[2];
		bb[0] = (byte)((pShort & 0xff00) >>> 8);
		bb[1] = (byte)(pShort & 0xff);
		return bb;
	}

	public static byte[] intToByte(int pInt)
	{
		byte bi[] = {
			0, 0, 6, 5
		};
		bi[0] = (byte)((pInt & 0xff000000) >>> 24);
		bi[1] = (byte)((pInt & 0xff0000) >>> 16);
		bi[2] = (byte)((pInt & 0xff00) >>> 8);
		bi[3] = (byte)(pInt & 0xff);
		return bi;
	}

	public static int byteToInt(byte pByte[], int pbegin, int len)
	{
		int ret = 0;
		for (int i = pbegin; i < pbegin + len; i++)
			ret = ret << 8 & 0xffffff00 | pByte[i] & 0xff;

		return ret;
	}

	public static short byteToShort(byte pByte[], int pbegin, int len)
	{
		short ret = 0;
		for (int i = pbegin; i < pbegin + len; i++)
			ret = (short)((short)(ret << 8) & 0xff00 | (short)(pByte[i] & 0xff));

		return ret;
	}

	public static String byteToString(byte pByte[], int pbegin, int len)
		throws UnsupportedEncodingException
	{
		return new String(pByte, pbegin, len, "gb2312");
	}

	public static String HextoASCII(byte byteSrc[], int start, int length)
	{
		if (start < 0 || length < 0)
			throw new ArrayIndexOutOfBoundsException();
		if (start + length > byteSrc.length)
			throw new ArrayIndexOutOfBoundsException();
		StringBuffer hexTableBuffer = new StringBuffer(2 * length);
		for (int i = start; i < start + length; i++)
		{
			String num = Integer.toHexString(byteSrc[i] & 0xff).toUpperCase();
			if (num.length() < 2)
				hexTableBuffer.append('0');
			hexTableBuffer.append(num);
		}

		return hexTableBuffer.toString();
	}

	public static String HextoASCII(byte byteSrc[])
	{
		return HextoASCII(byteSrc, 0, byteSrc.length);
	}

	public static byte[] ASCIItoHex(String asciistring)
	{
		if (asciistring.length() % 2 != 0)
			throw new IllegalArgumentException("string length error");
		byte coverdata[] = new byte[asciistring.length() / 2];
		for (int i = 0; i < coverdata.length; i++)
			coverdata[i] = (byte)Integer.parseInt(asciistring.substring(i * 2, (i + 1) * 2), 16);

		return coverdata;
	}

	public static byte[] readFile(String pathandname)
		throws IOException
	{
		FileInputStream fs = new FileInputStream(pathandname);
		byte temp[] = new byte[fs.available()];
		fs.read(temp);
		fs.close();
		return temp;
	}

	@SuppressWarnings("resource")
	public static byte[] readFile(String pathandname, int start)
		throws IOException
	{
		FileInputStream fs = new FileInputStream(pathandname);
		if (start < 0 || start > fs.available()) {
			throw new IOException();
		} else {
			byte temp[] = new byte[fs.available() - start];
			fs.skip(start);
			fs.read(temp);
			fs.close();
			return temp;
		}
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String test = "C466E6F746";
		byte a[] = test.getBytes();
		a = ASCIItoHex(test);
		System.out.println(new String(a, "gb2312"));
		String text = "this is a test message to be test";
		String encodeed = encoderBASE64(text.getBytes());
		byte[] decodeed = decoderBASE64(encodeed);
		System.out.println(encodeed);
		System.out.println(new String(decodeed));
		StringBuffer sf = new StringBuffer(16);
		sf.append("PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCMjMxMiIgPz4KPENNUz4KPGViPgo8cHVi\r\n" + 
				"Pgo8VHJhbnNDb2RlPlFQQVlFTlQ8L1RyYW5zQ29kZT4KPENJUz40MzAxOTAwMDAwMTM0NDE8L0NJ\r\n" + 
				"Uz4KPElEPkpTRFgxLnkuNDMwMTwvSUQ+CjxCYW5rQ29kZT4xMDI8L0JhbmtDb2RlPgo8VHJhbkRh\r\n" + 
				"dGU+PC9UcmFuRGF0ZT4KPFRyYW5UaW1lPjwvVHJhblRpbWU+CjxmU2Vxbm8+UVBBWUVOVDIwMTAw\r\n" + 
				"NjIwMDEyNzIzMDkzODI5PC9mU2Vxbm8+CjxSZXRDb2RlPkIwMTE2PC9SZXRDb2RlPgo8UmV0TXNn\r\n" + 
				"PsO709C3+7rPzPW8/rXEvMfCvDwvUmV0TXNnPgo8L3B1Yj4KPG91dD4KPFFyeWZTZXFubz5QQVlF\r\n" + 
				"TlQyMDEwMDYxODE2MjUwNTQyMTk1MjwvUXJ5ZlNlcW5vPgo8UXJ5U2VyaWFsTm8+Q01NMzAyNTkz\r\n" + 
				"NDYxPC9RcnlTZXJpYWxObz4KPE9ubEJhdEY+PC9PbmxCYXRGPgo8U2V0dGxlTW9kZT48L1NldHRs\r\n" + 
				"ZU1vZGU+CjxCdXNUeXBlPjwvQnVzVHlwZT4KPFJlcFJlc2VydmVkMT48L1JlcFJlc2VydmVkMT4K\r\n" + 
				"PFJlcFJlc2VydmVkMj48L1JlcFJlc2VydmVkMj4KPHJkPgo8aVNlcW5vPjwvaVNlcW5vPgo8UXJ5\r\n" + 
				"aVNlcW5vPjwvUXJ5aVNlcW5vPgo8UXJ5T3JkZXJObz48L1FyeU9yZGVyTm8+CjxSZWltYnVyc2VO\r\n" + 
				"bz48L1JlaW1idXJzZU5vPgo8UmVpbWJ1cnNlTnVtPjwvUmVpbWJ1cnNlTnVtPgo8U3RhcnREYXRl\r\n" + 
				"PjwvU3RhcnREYXRlPgo8U3RhcnRUaW1lPjwvU3RhcnRUaW1lPgo8UGF5VHlwZT48L1BheVR5cGU+\r\n" + 
				"CjxQYXlBY2NObz48L1BheUFjY05vPgo8UGF5QWNjTmFtZUNOPjwvUGF5QWNjTmFtZUNOPgo8UGF5\r\n" + 
				"QWNjTmFtZUVOPjwvUGF5QWNjTmFtZUVOPgo8UmVjQWNjTm8+PC9SZWNBY2NObz4KPFJlY0FjY05h\r\n" + 
				"bWVDTj48L1JlY0FjY05hbWVDTj4KPFJlY0FjY05hbWVFTj48L1JlY0FjY05hbWVFTj4KPFN5c0lP\r\n" + 
				"RmxnPjwvU3lzSU9GbGc+CjxJc1NhbWVDaXR5PjwvSXNTYW1lQ2l0eT4KPFJlY0lDQkNDb2RlPjwv\r\n" + 
				"UmVjSUNCQ0NvZGU+CjxSZWNDaXR5TmFtZT48L1JlY0NpdHlOYW1lPgo8UmVjQmFua05vPjwvUmVj\r\n" + 
				"QmFua05vPgo8UmVjQmFua05hbWU+PC9SZWNCYW5rTmFtZT4KPEN1cnJUeXBlPjwvQ3VyclR5cGU+\r\n" + 
				"CjxQYXlBbXQ+PC9QYXlBbXQ+CjxVc2VDb2RlPjwvVXNlQ29kZT4KPFVzZUNOPjwvVXNlQ04+CjxF\r\n" + 
				"blN1bW1hcnk+PC9FblN1bW1hcnk+CjxQb3N0U2NyaXB0PjwvUG9zdFNjcmlwdD4KPFN1bW1hcnk+\r\n" + 
				"PC9TdW1tYXJ5Pgo8UmVmPjwvUmVmPgo8T3JlZj48L09yZWY+CjxFUlBTcW4+PC9FUlBTcW4+CjxC\r\n" + 
				"dXNDb2RlPjwvQnVzQ29kZT4KPEVSUGNoZWNrbm8+PC9FUlBjaGVja25vPgo8Q3J2b3VoVHlwZT48\r\n" + 
				"L0Nydm91aFR5cGU+CjxDcnZvdWhOYW1lPjwvQ3J2b3VoTmFtZT4KPENydm91aE5vPjwvQ3J2b3Vo\r\n" + 
				"Tm8+CjxpUmV0Q29kZT48L2lSZXRDb2RlPgo8aVJldE1zZz48L2lSZXRNc2c+CjxSZXN1bHQ+PC9S\r\n" + 
				"ZXN1bHQ+CjxpbnN0clJldENvZGU+PC9pbnN0clJldENvZGU+CjxpbnN0clJldE1zZz48L2luc3Ry\r\n" + 
				"UmV0TXNnPgo8QmFua1JldFRpbWU+PC9CYW5rUmV0VGltZT4KPFJlcFJlc2VydmVkMz48L1JlcFJl\r\n" + 
				"c2VydmVkMz4KPFJlcFJlc2VydmVkND48L1JlcFJlc2VydmVkND4KPC9yZD4KPC9vdXQ+CjwvZWI+\r\n" + 
				"CjwvQ01TPgo=");
		
		byte[] cd = decoderBASE64(sf.toString());
		System.out.println(new String(cd));

	}

}
