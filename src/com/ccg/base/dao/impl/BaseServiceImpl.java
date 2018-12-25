package com.ccg.base.dao.impl;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.ccg.base.dao.BaseService;
import com.ccg.jdbc.JdbcUtil;

/**
 * 用于给service层的类继承，直接继承此实现类
 * @author Administrator
 *
 */
public class BaseServiceImpl implements BaseService{

	/**
	 * 获取数据库连接对象，在jdbc.properties中配置连接方式：DBCP，DRUID，空（不使用连接池）
	 * @return
	 */
	protected Connection getConnection(){
		return JdbcUtil.getConnection();
	}
	
	/**
	 * 获取项目根路径,File.separator结尾
	 * @param req
	 * @return
	 */
	@Override
	public String getServletRootPath(HttpServletRequest req){

    	String rootPath = req.getServletContext().getRealPath(File.separator);
		if (!rootPath.endsWith(File.separator)) {
			rootPath = rootPath + File.separator;
		}
		return rootPath;
	}
}
