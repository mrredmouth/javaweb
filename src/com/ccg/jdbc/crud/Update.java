package com.ccg.jdbc.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.ccg.jdbc.JdbcUtil;

public class Update {
	
	//事务性：update操作的，异常则回滚
	@SuppressWarnings("resource")
	@Test
	public void testUpdateTransaction() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);//设置为false，主动提交。默认是true，自动提交。
			
			//Lilly的年龄减2，送给Tom
			String updateSql = "update t_student set age = age - ? where name = ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1,2);
			pstmt.setString(2,"Lilly");
			pstmt.executeUpdate();
			
			//int ii = 1/0; //模拟异常，验证事务回滚
			
			//Tom的年龄增2
			updateSql = "update t_student set age = age + ? where name = ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1,2);
			pstmt.setString(2,"Tom");
			pstmt.executeUpdate();
			
			conn.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();//事务回滚
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			JdbcUtil.free(null, pstmt, conn);
		}
	}
}
