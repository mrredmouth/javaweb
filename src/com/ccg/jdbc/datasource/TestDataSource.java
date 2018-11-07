package com.ccg.jdbc.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.ccg.jdbc.JdbcUtil;

public class TestDataSource {
	
	/**
	 * 	连接、断开		100次	1000次
	 * 不使用连接池：	5612ms	45384ms
	 * DBCP连接池：		1574ms	3204ms
	 * Druid连接池：		1765ms	2981ms
	 */
	@Test
	public void testDataSourceInsert() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		long beginTime = System.currentTimeMillis();
		try {
			for(int i=0;i<1000;i++){
				conn = JdbcUtil.getConnection();
				
				String insertSql = "insert into t_student(id,name,age) values(?,?,?)";
				pstmt = conn.prepareStatement(insertSql);
				pstmt.setString(1,String.valueOf(i));
				pstmt.setString(2,"Lilly");
				pstmt.setInt(3,24);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
			System.out.println("OracleTest.testDataSourceInsert()---耗时:"+(System.currentTimeMillis()-beginTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(null, pstmt, conn);
		}
	}
}
