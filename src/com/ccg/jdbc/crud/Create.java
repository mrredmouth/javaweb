package com.ccg.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class Create {
	@Test
	public void testCreateTable() throws ClassNotFoundException, SQLException{
		String sql = "create table t_student (id number(6) primary key,name varchar2(20),age number(10))";
		//1、贾:加载驱动
		Class.forName("oracle.jdbc.OracleDriver");
		/*2、琏:获取连接对象。    thin：小型驱动，驱动方式;
							@localhost 本机ip地址 127.0.0.1
							orcl：数据库的名字 */
		Connection conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","test","123456");
		//3、欲:创建语句对象
		Statement stmt = conn.createStatement();
		//4、执:执行sql语句。查看api,返回0:不返回的SQL语句;非0：DML语句的行计数
		int row = stmt.executeUpdate(sql);
		//5、事:释放资源
		stmt.close();
		conn.close();
		System.out.println("OracleTest.testCreateTable()---row:"+row);
	}
}
