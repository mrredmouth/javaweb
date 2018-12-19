package com.ccg.io.file.upload.dao;

import org.junit.Test;

import com.ccg.io.file.upload.dao.impl.UploadFileToOracleImpl;

public class UploadFileToOracleTest {

	String newFilePath = "F:/projects/javaweb/WebRoot/WEB-INF/download/excelToHtmlDemo.xls";
	
	@Test
	public void uploadFileToOracleServiceTest(){
		IUploadFileToOracle aa = new UploadFileToOracleImpl();
		aa.uploadOaFile(newFilePath);
	}
}
