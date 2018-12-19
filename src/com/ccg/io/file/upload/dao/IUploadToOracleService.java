package com.ccg.io.file.upload.dao;

public interface IUploadToOracleService {

	/**
	 * 将服务器上的文件（临时文件），上传到数据库
	 * @param newFilePath 已经临时存在服务器上后的新的文件全路径
	 * @return
	 */
	public String uploadOaFile(String newFilePath);
}
