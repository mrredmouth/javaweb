package com.ccg.jdbc.procedure;

import java.sql.Connection;

import org.junit.Test;

import com.ccg.jdbc.JdbcUtil;

public class TestProcedure {
	/**
	 * 测试存储过程调用，JdbcUtil.callSelfProcedure
	 * 创建连接，自己创建自己释放
	 */
	@Test
	public void testCallSelfProcedure(){
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			JdbcUtil.callProcedure(conn, "t_student_insert", "Honny",46);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(conn);
		}
	}
	/**
	 * 测试存储过程调用，里面创建连接，里面释放
	 */
	@Test
	public void testCallSelfProcedure2(){
		JdbcUtil.callProcedure("t_student_insert", "Honny",99);
	}
}
