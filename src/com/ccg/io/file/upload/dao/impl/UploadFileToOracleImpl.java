package com.ccg.io.file.upload.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.io.FilenameUtils;
import com.ccg.base.dao.impl.BaseServiceImpl;
import com.ccg.io.file.MyFileUtils;
import com.ccg.io.file.upload.dao.IUploadFileToOracle;
import com.ccg.jdbc.JdbcUtil;
import oracle.sql.BLOB;

public class UploadFileToOracleImpl extends BaseServiceImpl implements IUploadFileToOracle{


	@SuppressWarnings("resource")
	@Override
	public String uploadOaFile(String newFilePath) {
		Connection conn = getConnection();
		Statement stmt1 = null;
		Statement stmt2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		FileInputStream fis = null;
		OutputStream os = null;
		try{
			//1、用于事务回滚，false：主动提交事务
			conn.setAutoCommit(false);
			//2、先获取主键值
			int fileId = 0;
			String querySql = "select OA_PROJECT_FILE_SEQ.Nextval as file_id from OA_PROJECT_FILE";
			stmt1 = conn.createStatement();
			rs = stmt1.executeQuery(querySql);
			if(rs.next()){
				fileId = rs.getInt("file_id");
			}
			
			//2、插入一条记录，blob字段为空
			String insertSql = "insert into OA_PROJECT_FILE(file_id,file_name,file_date,file_size,file_type,file_content) "
					+ "values(?,?,sysdate,?,?,empty_blob())";
			pstmt1 = conn.prepareStatement(insertSql);
			String fileName = FilenameUtils.getName(newFilePath);
			String fileType = FilenameUtils.getExtension(newFilePath);
			pstmt1.setInt(1,fileId);
			pstmt1.setString(2,fileName);
			pstmt1.setInt(3,29);
			pstmt1.setString(4,fileType);
			int row = pstmt1.executeUpdate(); //用无参的方法，有参的是Statement的
			System.out.println("insert into OA_PROJECT_FILE ---rows:"+row);
			
			//3、更新刚插入的记录的blob字段，将文件流读进数据库
			stmt2 = conn.createStatement();
			rs = stmt2.executeQuery("select file_content from OA_PROJECT_FILE where file_id='"+fileId+"' for update");     
			
			fis = new FileInputStream(new File(newFilePath));
			if(fis!=null){
				int size = 0;
				if (rs.next()) {  
					BLOB blob = (BLOB) rs.getBlob(1);  
					//outStream = blob.getBinaryOutputStream(); //此方法不再用
					os = blob.setBinaryStream(0);  //从0开始重写 原本blob就为空
					
					byte[] b2 = new byte[blob.getBufferSize()];
					int len = 0;
					while ( (len = fis.read(b2)) != -1) {
						os.write(b2, 0, len);
						size += len;
					}
					
					fis.close();
					os.flush();
					os.close();
				} 
				//更新文件大小
				String updateSql = "update OA_PROJECT_FILE set file_size = ? where file_id = ?";
				pstmt2 = conn.prepareStatement(updateSql);
				pstmt2.setInt(1,size);
				pstmt2.setInt(2,fileId);
				pstmt2.executeUpdate();
				pstmt2.close();
			}
			//4、提交事务
			conn.commit();
			
		}catch (Exception e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JdbcUtil.free(rs, pstmt1, conn);
			JdbcUtil.free(pstmt2);
			JdbcUtil.free(stmt1);
			JdbcUtil.free(stmt2);
			MyFileUtils.free(fis);
			MyFileUtils.free(os);
		}
		return null;
	}

}
