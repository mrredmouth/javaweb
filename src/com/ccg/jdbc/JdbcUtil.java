package com.ccg.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JdbcUtil {
	private static String driverClassName;
	private static String url;
	private static String userName;
	private static String password;
	private static Properties props = new Properties();
	private static DataSource ds = null;
	
	//静态块，当JdbcUtil的字节码被加载近JVM的时候立刻执行。项目启动的时候执行，只执行一次
	static{
		init();
		System.out.println(props);
		
		if(StringUtils.isNotBlank(props.getProperty("dataSourceType")) 
				&& "DBCP".equals(props.getProperty("dataSourceType"))){
			//使用DBCP创建连接池DataSource,释放时将连接还给连接池
			try {
				ds = getDBCPDataSource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(StringUtils.isNotBlank(props.getProperty("dataSourceType")) 
				&& "DRUID".equals(props.getProperty("dataSourceType"))){
			//使用druid创建连接池DataSource,释放时将连接还给连接池
			try {
				ds = getDruidDataSource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//不使用连接池,释放后和数据库断开连接
			try{
				Class.forName(driverClassName);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static DataSource getDruidDataSource() throws Exception{
		return DruidDataSourceFactory.createDataSource(props);
	}
	public static DataSource getDBCPDataSource() throws Exception{
		return BasicDataSourceFactory.createDataSource(props);
	}
	
	public static void init() {
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
			props.load(is);
			driverClassName = props.getProperty("oracle.driverClassName");
			url = props.getProperty("oracle.url");
			userName = props.getProperty("oracle.username");
			password = props.getProperty("oracle.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接：不使用连接池，DBCP,Druid
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			if(ds == null){
				//未使用连接池,JDBC最底层，获取连接
				conn = DriverManager.getConnection(url, userName, password);
			}else{
				//使用连接池ds:DBCP、Druid
				conn = ds.getConnection();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void free(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	public static void free(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	public static void free(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}
	/**
	 * 释放所有资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement stmt, Connection conn) {
		try {
			free(rs);
	    } finally {
	        try {
	        	free(stmt);
	        } finally {
	        	free(conn);
	        }
	    }
	}
	/**
	 * 调用存储过程
	 * @param conn	连接,哪里创建在哪里关闭
	 * @param procedure_name	存储过程名
	 * @param args 存储过程的参数
	 */
	public static void callProcedure(Connection conn, String procedure_name,Object ... args) {
		CallableStatement stmt = null; 
		try {
			String markStr = "";
			if(args.length > 0){
				for(int i=0;i<args.length;i++){
					if(i == 0){
						markStr = "(?";
					}else{
						markStr = markStr + ",?";
					}
				}
				markStr = markStr + ")";
			}
			stmt = conn.prepareCall("{call "+procedure_name+markStr+"}");
			for(int i=0;i<args.length;i++){
				stmt.setObject(i+1, args[i]);  
			}
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			free(stmt);
		}
	}

	public static void callProcedure(String procedure_name,Object ... args) {
		Connection conn = null;
		try {
			conn = getConnection();
			callProcedure(conn,procedure_name,args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(conn);
		}
	}
}
