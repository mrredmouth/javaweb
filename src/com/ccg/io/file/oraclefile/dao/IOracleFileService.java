package com.ccg.io.file.oraclefile.dao;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.ccg.base.MapBean;
import com.ccg.io.file.oraclefile.model.OracleFile;

public interface IOracleFileService {

	/**
	 * 文件以二进制形式上传到数据库
	 * @param newFilePath 已经临时存在服务器上后的新的文件全路径。如："F:/projects/javaweb/WebRoot/WEB-INF/download/excelToHtmlDemo.xls"
	 * @return
	 */
	public String uploadOaFile(String newFilePath);
	
	/**
	 * 获取oracle中的blob二进制文件流
	 * @param map
	 * @return
	 */
	public OracleFile getOaProFile(MapBean map);
	
	/**
	 * 根据主键id获取oracle中的blob二进制文件流
	 * @param id
	 * @return
	 */
	public OracleFile getOaProFileById(String id);
	
	/**
	 * 下载数据库文件到浏览器
	 * @param oracleFile
	 * @param resp
	 */
	public void downloadOracleFileToResp(OracleFile oracleFile,HttpServletResponse resp);

	/**
	 * 下载数据库文件到输出流，可以到指定文件，可以到浏览器
	 * @param oracleFile
	 * @param os
	 */
	public void downloadOracleFileToOs(OracleFile oracleFile,OutputStream os);
}
