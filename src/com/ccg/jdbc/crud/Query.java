package com.ccg.jdbc.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.ccg.jdbc.JdbcUtil;
import com.ccg.pojo.Student;

public class Query {
	@Test
	public void testQuery() {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String querySql = "select * from t_student";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySql);
			Student stu = new Student();
			if(rs.next()){
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
			}
			System.out.println("OracleTest.testQuery()---stu:"+stu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(null, stmt, conn);
		}
	}
}
