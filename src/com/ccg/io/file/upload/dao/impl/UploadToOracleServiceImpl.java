package com.ccg.io.file.upload.dao.impl;

import java.sql.Connection;

import com.ccg.base.dao.impl.BaseServiceImpl;
import com.ccg.io.file.upload.dao.IUploadToOracleService;

public class UploadToOracleServiceImpl extends BaseServiceImpl implements IUploadToOracleService{

	@Override
	public String uploadOaFile(String newFilePath) {
		Connection connection = getConnection();
		
		return null;
	}

}
