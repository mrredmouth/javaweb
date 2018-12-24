/**
 * BbsFileUtil.java Created on Feb 25, 2013
 * Copyright(c) 2011@JSHX
 * ALL Rights Reserved.
 */
package com.ccg.smb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.io.IOUtils;

import com.ccg.io.properties.MyPropertyUtils;


/**
 * 论坛文件处理
 * 
 * @time: 4:23:00 PM
 * @author mengxiankong
 */
public class BbsFileUtil {

	private static String remoteHostIp; //远程主机IP      
	private static String account; //登陆账户      
	private static String password; //登陆密码      
	private static String shareDocName; //共享文件夹名称 
	private static String uploadFilesPath; //上传文件夹路径 
	
	static {		 
		Properties props = MyPropertyUtils.getPropertiesFromFile("systemupload.properties");
        remoteHostIp = props.getProperty("REMOTE_HOST_IP");   
        account = props.getProperty("LOGIN_ACCOUNT");   
        password = props.getProperty("LOGIN_PASSWORD");   
        shareDocName = props.getProperty("SHARE_DOC_NAME");
        uploadFilesPath = props.getProperty("bbsuploadpath");
        StringBuffer bf = new StringBuffer("FCK UPLOAD CONFIG:");
        bf.append("smb://").append(account);
        bf.append(":").append(password);
        bf.append("@").append(remoteHostIp);
        bf.append("/").append(shareDocName);
        bf.append("/").append(uploadFilesPath).append("/");
        System.out.println(bf.toString());
	}
	//smb://smb:glkjsmb@132.228.226.13/smb/
	public static String getAbsoluteSharePath(){		
		return "smb://"+account+":"+password+"@"+remoteHostIp+"/"+shareDocName;
	}
	//smb://smb:glkjsmb@132.228.226.13/smb/userfiles/
	public static String getAbsoluteUploadPath(){		
		return "smb://"+account+":"+password+"@"+remoteHostIp+"/"+shareDocName+ "/" + uploadFilesPath;
	}
	
	public static String copyFiles(final String filePath, final String fileName, final InputStream inputStream) throws SmbException, UnknownHostException, IOException{
		String remotePath = getAbsoluteUploadPath();
		if(filePath!=null && filePath.startsWith("/")){
			remotePath = remotePath + filePath;
		} else {
			remotePath = remotePath + "/" + filePath;
		}
		SmbFile remoteDir = new SmbFile(remotePath);
		if(!remoteDir.exists()){
			remoteDir.mkdir();
		} else {
			remoteDir = null;
		}
		SmbFile remoteFile = new SmbFile(remotePath+"/"+fileName);
		IOUtils.copyLarge(inputStream, new SmbFileOutputStream(remoteFile));  
			
		return fileName;
	}
	
	
	public static String copyFiles_share(final String filePath, final String fileName, final InputStream inputStream) throws SmbException, UnknownHostException, IOException{
		String remotePath = getAbsoluteSharePath();
		if(filePath!=null && filePath.startsWith("/")){
			remotePath = remotePath + filePath;
		} else {
			remotePath = remotePath + "/" + filePath;
		}
		SmbFile remoteDir = new SmbFile(remotePath);
		if(!remoteDir.exists()){
			remoteDir.mkdir();
		} else {
			remoteDir = null;
		}
		SmbFile remoteFile = new SmbFile(remotePath+"/"+fileName);
		IOUtils.copyLarge(inputStream, new SmbFileOutputStream(remoteFile));  
			
		return remotePath+"/"+fileName;
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws SmbException 
	 */
	public static void main(String[] args) throws SmbException, UnknownHostException, IOException {
		System.out.println("---------------Start!");
		String loaclPath = "C:\\tmp\\xcopy.txt";
		InputStream inputStream = new FileInputStream(new File(loaclPath));
		String filePath = "/file";
		String fileName = "test.txt";
		copyFiles(filePath, fileName, inputStream);
		System.out.println("---------------End!");
	}

}
