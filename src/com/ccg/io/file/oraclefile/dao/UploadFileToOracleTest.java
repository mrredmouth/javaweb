package com.ccg.io.file.oraclefile.dao;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.ccg.io.file.MyFileUtils;
import com.ccg.io.file.oraclefile.dao.impl.OracleFileServiceImpl;
import com.ccg.io.file.oraclefile.model.OracleFile;

public class UploadFileToOracleTest {

	String newFilePath = "F:/projects/javaweb/WebRoot/WEB-INF/download/excelToHtmlDemo.xls";
	
	@Test
	public void uploadFileToOracle(){
		IOracleFileService aa = new OracleFileServiceImpl();
		aa.uploadOaFile(newFilePath);
	}
	
	/**
	 * 测试下载文件到指定目录
	 */
	@Test
	public void downloadOracleFile(){
		IOracleFileService aa = new OracleFileServiceImpl();
		OracleFile oracleFile = aa.getOaProFileById("16");
		String fileRootPath = "F:/projects/javaweb/WebRoot/WEB-INF/download/oraclefile/";
		MyFileUtils.createFileDir(fileRootPath);
		String filePath = fileRootPath + oracleFile.getName();
		OutputStream os = null;
		try {
			os = new FileOutputStream(filePath);
			aa.downloadOracleFileToOs(oracleFile, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MyFileUtils.free(os);
		}
	}
}
