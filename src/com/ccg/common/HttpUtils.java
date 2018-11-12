package com.ccg.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtils {

	/**
	 * 模拟发送http请求
	 * @param httpURL
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpRequest(String httpURL,Map<String,String> params) throws IOException{
		URL url = new URL(httpURL);
		//创建连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置请求方式，需要大写
		conn.setRequestMethod("POST");
		//设置需要输出
		conn.setDoOutput(true);
		//判断是否有参数
		if(params!=null && params.size()>0){
			StringBuilder sb = new StringBuilder();
			for(Entry<String,String> entry:params.entrySet()){
				sb.append("&").append(entry.getKey())
				.append("=").append(entry.getValue());
			}
			conn.getOutputStream().write(sb.substring(1).toString().getBytes("UTF-8"));
		}
		//发送请求到服务器
		conn.connect();
		
		//读取响应
		
		BufferedReader reader = null;
		String response="";
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			response += lines;
		}
		reader.close();
		
		// 断开连接
		conn.disconnect();
		return response;
	}
	/**
	 * 模拟发送http请求
	 * @param httpURL
	 * @throws IOException
	 */
	public static void sendHttpRequest(String httpURL,String jsessionId) throws IOException{
		URL url = new URL(httpURL);
		//创建连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置请求方式，需要大写
		conn.setRequestMethod("POST");
		//设置需要输出
		conn.setDoOutput(false);
		conn.addRequestProperty("Cookie", "JSESSIONID="+jsessionId);
		//发送请求到服务器
		conn.connect();
		conn.getInputStream();
		conn.disconnect();
	}
}
