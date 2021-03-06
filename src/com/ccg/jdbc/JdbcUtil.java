package com.ccg.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ccg.base.MapBean;
import com.ccg.io.properties.MyPropertyUtils;

public class JdbcUtil {
	private static String driverClassName;
	private static String url;
	private static String username;
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
		props = MyPropertyUtils.getPropertiesFromFile("jdbc.properties");
		driverClassName = props.getProperty("oracle.driverClassName");
		url = props.getProperty("url");
		username = props.getProperty("username");
		password = props.getProperty("password");
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
				conn = DriverManager.getConnection(url, username, password);
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

	/**
	 * 调用存储过程
	 * @param procedure_name	存储过程名
	 * @param args 存储过程的参数
	 */
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
	
	/**
	 * 查询并封装结果为MapBean
	 * @param sql SQL语句
	 * @param obj 一个或多个查询参数
	 * @return List<MapBean> 返回一条或多条数据
	 */
	public static List<MapBean> queryRsMapBean(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MapBean> retMapList = new ArrayList<MapBean>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				MapBean rowMap = new MapBean();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					rowMap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
				}
				retMapList.add(rowMap);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}finally{
			free(rs, pstmt, conn);
		}
		return retMapList;
	}

	/**
	 * 查询并封装结果为MapBean
	 * @param conn 自己传入conn
	 * @param sql SQL语句
	 * @param obj 查询参数
	 * @return ResultSet
	 */
	public static List<MapBean> queryRsMapBeanByConn(Connection conn, String sql, Object... obj) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MapBean> retMapList = new ArrayList<MapBean>();
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				MapBean rowMap = new MapBean();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					rowMap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
				}
				retMapList.add(rowMap);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally{
			free(rs, pstmt, null);
		}
		return retMapList;
	}
	

	/**
	 * 保存和更新
	 * @param dbModule DBModule枚举
	 * @param sql SQL语句
	 * @param obj 查询参数
	 * @return int
	 */
	public static int update(String sql, Object... obj) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
			free(null, pstmt, conn);
		} finally {
			free(null, pstmt, conn);
		}
		return result;

	}
	
}
