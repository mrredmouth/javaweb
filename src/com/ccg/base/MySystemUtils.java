/** 
 * SystemTool.java Created on Nov 13, 2009
 * Copyright 2009@JSHX. 
 * All right reserved. 
 */
package com.ccg.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

/**
 * 与系统相关的一些常用工具方法
 * @Time 3:45:01 PM
 * @author mengxiankong
 */
public class MySystemUtils {

	/** 
	 * 获取当前操作系统名称. 
	 * return 操作系统名称 例如:windows xp,linux 等. 
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for"); //一般代理 eg.apache
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); //Squid反向代理
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 一个IP，是一个３２位无符号的二进制数。故用long的低32表示无符号32位二进制数。
	 * eg.	String s = ServletActionContext.getRequest().getRemoteAddr(); 
	 * long l = getIP(InetAddress.getByName(s)); 
	 * @param ip
	 * @return
	 */
	public long getIP(InetAddress ip) {
		byte[] b = ip.getAddress();
		long l = b[0] << 24L & 0xff000000L | b[1] << 16L & 0xff0000L
				| b[2] << 8L & 0xff00 | b[3] << 0L & 0xff;
		return l;
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
	 * 
	 * @return mac地址
	 */
	public static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息  
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]  
				if (index >= 0) {// 找到了  
					mac = line.substring(index + "hwaddr".length() + 1).trim();//  取出mac地址并去除2边空格  
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	/** 
	 * 获取widnows网卡的mac地址. 
	 * @return mac地址 
	 */
	public static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息  
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical address]  
				if (index >= 0) {// 找到了  
					index = line.indexOf(":");// 寻找":"的位置  
					if (index >= 0) {
						mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格  
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}
	
	public static String getMACAddress(String ip){
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String os = getOSName();
		System.out.println(os);
		if (os.startsWith("windows")) {
			//本地是windows  
			String mac = getWindowsMACAddress();
			System.out.println(mac);
		} else {
			//本地是非windows系统 一般就是unix  
			String mac = getUnixMACAddress();
			System.out.println(mac);
		}
//		String ip = "202.102.124.144";
		String ip = "132.228.43.238"; //00:21:70:A8:AD:C3
		String macAdd = getMACAddress(ip);
		System.out.println(macAdd);
	}

}
