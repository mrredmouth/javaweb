package com.ccg.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.ccg.io.file.MyFileUtils;

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
		conn.setDoOutput(true);
		conn.addRequestProperty("Cookie", "JSESSIONID="+jsessionId);
		//发送请求到服务器
		conn.connect();
		conn.getInputStream();
		conn.disconnect();
	}
	

	/**
	  * 通过拼接URL地址的方式获取短信接口信息
	  * @param url
	  * @param params
	  * @return String  
	  * @throws
	 */
	public static String callUrl3(String url,Map<String,String> params){
		
		StringBuffer urlBuffer = new StringBuffer(url);
		
		if(params != null){
			Set<String> list = params.keySet();
			
			urlBuffer.append("?");
			
			for(String key:list){
				urlBuffer.append("&");
				urlBuffer.append(key);
				urlBuffer.append("=");
				urlBuffer.append(params.get(key));
			}
			
		}
		
		url = urlBuffer.toString();
		
		URL u = null;
		HttpURLConnection httpConn = null;
		
		try {
			u = new URL(url);
			httpConn = (HttpURLConnection)u.openConnection();
			
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			
			httpConn.connect();
			
			// 写出参数到输出流
			httpConn.getOutputStream().flush();
			httpConn.getOutputStream().close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
			
			String readLine = "";
			StringBuffer buffer = new StringBuffer();
			while((readLine = reader.readLine()) != null){
				buffer.append(readLine);
			}
			reader.close();
			return buffer.toString();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e){
			e.printStackTrace();
			return "";
		} finally{
			if(httpConn!= null){
				httpConn.disconnect();
			}
			
		}	
	}
	
	/**
	  * 根据网络地址判断图片或者文件是否存在
	  * @param imgUrl	  图片网络地址
	  * @return boolean  true:图片存在；false图片不存在
	  * @throws
	 */
	public static boolean isImgExist(String fileUrl) {
		boolean isExist = true;
		URL url = null;
		HttpURLConnection urlConn = null;
		InputStream is = null;
		try{
			url = new URL(fileUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			is = urlConn.getInputStream();
		} catch(Exception e) {
			System.out.println(("\n图片或文件为非正常文件"));
			e.printStackTrace();
			isExist = false;
		} finally{
			MyFileUtils.free(is);
		}
		return isExist;
	}

	/**
	 * 车点点接口调用（只是用于车点点，不能通用，因为系统参数只有车点点才有）
	 * HttpClient实现get请求
	 * @param url
	 * @param params
	 * @param sign
	 * @param timestamp  时间戳,格式：yyyyMMddHHmmss
	 * @param channelid  渠道编号，由车点点分配
	 * @param user  用户手机号码（固话号码），调用与用户相关接口时，此参数必传
	 * @return
	 */
	public static String goGet(String url, Map<String, String> params,String sign,String timestamp,String channelid,String user) {
	StringBuffer urlBuffer = new StringBuffer(url);
		
		if(params != null){
			Set<String> list = params.keySet();
			
			urlBuffer.append("?");
			
			for(String key:list){
				urlBuffer.append("&");
				urlBuffer.append(key);
				urlBuffer.append("=");
				urlBuffer.append(params.get(key));
			}
			
		}
		url = urlBuffer.toString();
		URL u = null;
		HttpURLConnection httpConn = null;
		try {
			u = new URL(url);
			httpConn = (HttpURLConnection)u.openConnection();
			httpConn.setRequestProperty("sign", sign);
			httpConn.setRequestProperty("timestamp", timestamp);
			httpConn.setRequestProperty("channelid", channelid);
			httpConn.setRequestProperty("user", user);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			httpConn.connect();
			
			// 写出参数到输出流
			httpConn.getOutputStream().flush();
			httpConn.getOutputStream().close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
			
			String readLine = "";
			StringBuffer buffer = new StringBuffer();
			while((readLine = reader.readLine()) != null){
				buffer.append(readLine);
			}
			reader.close();
			return buffer.toString();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e){
			e.printStackTrace();
			return "";
		} finally{
			if(httpConn!= null){
				httpConn.disconnect();
			}
			
		}	
	}
	
	/**
	 * 使用apache 的http组件，发送url，并返回json字符串
	 * @param url
	 * @param sendContent
	 * @return 返回json字符串
	 * @throws Exception
	 */
	public static String send(String url, String sendContent) throws Exception {
		String result = "";
		HttpClientBuilder create = HttpClientBuilder.create();
		CloseableHttpClient httpclient = create.build();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		String returnJsonStr = null;
		httpPost.setEntity(new StringEntity(sendContent, "UTF-8"));// 设置节点
		HttpResponse response = httpclient.execute(httpPost);// 发出请求
		HttpEntity entity = response.getEntity();// 响应
		returnJsonStr = EntityUtils.toString(entity, "UTF-8");
		System.out.println("返回JSON数据为：" + returnJsonStr + "============");
		return result;
	}
}
