package com.ccg.secret;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;



public class MyAESUtil {

	private final static String sKey = "2afc0d92fdb74463";
		
	/**
	 * AES加密
	 * @param content 待加密内容
	 * @param key 加密的密钥
	 * @return
	 */
	public static String aesEncrypt(String sSrc, String sKey) {
		try {
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			byte[] resultArray = Base64.encodeBase64(encrypted); //加密的字符串不带\r\n，没有换行
			//String resultStr = Base64.encodeBase64String(encrypted); //加密的字符串带\r\n，有换行
			return new String(resultArray);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * AES解密字符串
	 * @param sSrc
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static String aesEncrypt(String sSrc) throws IllegalBlockSizeException,Exception{
		return aesEncrypt(sSrc,sKey);
	}
	
	/**
	 * AES解密
	 * @param content 待解密内容
	 * @param key 解密的密钥
	 * @return
	 */
	public static String aesDecrypt(String sSrc, String sKey) {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			//byte[] encrypted1 = new Base64().decode(sSrc.getBytes());// 先用base64解密
			byte[] encrypted1 = Base64.decodeBase64(sSrc.getBytes());// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密字符串
	 * @param sSrc
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public static String aesDecrypt(String sSrc) throws IllegalBlockSizeException,Exception{
		return aesDecrypt(sSrc,sKey);
	}

	
	
	
	/**
	 * @throws Exception 
	 * @throws IllegalBlockSizeException 
	 */
	@Test
	public void testAESUtil() throws IllegalBlockSizeException, Exception {
		String param = "{'documentID':'CZ8719392C83283C', 'dep1id':0 ,'dep2id':'','dep3id':'','dep4id':'','loginame':'testdyf.zj','usrname':'测试账号' ,'telephone':'13112121212'}";
		
		//1、AES加密
		String encryptStr = MyAESUtil.aesEncrypt(param);
		System.out.println("AESEncrypt:  "+encryptStr);
		//2、URL编码
		String uRLEncoderStr = URLEncoder.encode(encryptStr,"UTF-8");
		System.out.println("URLEncoder:  "+uRLEncoderStr);
		//3、URL解码
		String uRLDecoderStr = URLDecoder.decode(uRLEncoderStr,"UTF-8");
		System.out.println("URLDecoder:  "+uRLDecoderStr);
		//4、AES解密
		String decryptStr = MyAESUtil.aesDecrypt(uRLDecoderStr, sKey);
		System.out.println("AESDecrypt:  "+decryptStr);
	}
	@Test
	public void testURLEncoderDecode() throws UnsupportedEncodingException{

		String encryptStr = "fatherid=2240&fatherdir=BT1261&fathername=2018年久其月报&global_flow_code=421";
		String uRLEncoderStr = URLEncoder.encode(encryptStr,"UTF-8");
		System.out.println("uRLEncoderStr: " + uRLEncoderStr);
		String uRLDecoderStr = URLDecoder.decode(uRLEncoderStr,"UTF-8");
		System.out.println("uRLDecoderStr: " + uRLDecoderStr);
	}
}
