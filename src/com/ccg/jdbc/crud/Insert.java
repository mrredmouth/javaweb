package com.ccg.jdbc.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com.ccg.jdbc.JdbcUtil;

public class Insert {
	
	@Test
	public void testInsert() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String insertSql = "insert into t_student(id,name,age) values(?,?,?)";
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setInt(1,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(2,"Jack");
			pstmt.setInt(3,29);
			int row = pstmt.executeUpdate(); //用无参的方法，有参的是Statement的
			System.out.println("OracleTest.testInsert()---row:"+row);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(null, pstmt, conn);
		}
	}
	
	//PreparedStatement使用批处理插入3000条：1242ms
	//PreparedStatement不使用批处理时，耗时:4324ms
	@Test
	public void testPstmtInsertBatch() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		long beginTime = System.currentTimeMillis();
		try {
			conn = JdbcUtil.getConnection();//未使用连接池,释放时与数据库断开
			//conn = JdbcUtil.getDBCPDataSource().getConnection();//使用DBCP连接池,释放时将连接还给连接池
			
			String insertSql = "insert into t_student(id,name,age) values(?,?,?)";
			pstmt = conn.prepareStatement(insertSql);
			
			for(int i=0;i<3000;i++){
				pstmt.setInt(1,i+2);
				pstmt.setString(2,"Lilly");
				pstmt.setInt(3,24);
				pstmt.addBatch();
				if(i%200 == 0){
					pstmt.executeBatch();//200条执行批量操作
					pstmt.clearBatch();//清除缓存
					pstmt.clearParameters();//清除参数
				}
			}
			System.out.println("OracleTest.testPstmtInsertBatch()---耗时:"+(System.currentTimeMillis()-beginTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(null, pstmt, conn);
		}
	}
	
	
	//Statement批量插入3000条：4312ms
	//Statement不使用批处理：4558ms
	@Test
	public void testStmtInsertBatch() {
		Statement stmt = null;
		Connection conn = null;
		long beginTime = System.currentTimeMillis();
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			
			for(int i=0;i<3000;i++){
				String insertSql = "insert into t_student(id,name,age) values("+(i+3002)+",'Tonny',28)";
				stmt.addBatch(insertSql);
				if(i%200 == 0){
					stmt.executeBatch();//200条执行批量操作
					stmt.clearBatch();//清除缓存
				}
			}
			System.out.println("OracleTest.testStmtInsertBatch()---耗时:"+(System.currentTimeMillis()-beginTime));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(null, stmt, conn);
		}
	}
	
}
