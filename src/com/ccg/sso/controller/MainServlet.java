package com.ccg.sso.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccg.common.utils.CommonUtils;

/**
 * 测试方案：
 * 模拟客户端： http://www.crm.com:8088/crm/main
 * 			   http://www.wms.com:8088/wms/main
 * 对应服务端(统一认证中心),模拟在springmvc项目中：
 * 			   http://www.sso.com:8088/sso/checkLogin
 * @author Administrator
 *
 */
@WebServlet(name="mainServlet",urlPatterns="/main")
public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		System.out.println("MainServlet.init()");
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getContextPath();   		//浏览器访问的上下文路径 /crm
		req.getRequestURI();			//浏览器访问的资源名  /crm/main
		req.getRequestURL().toString(); //浏览器中的访问的所有地址  http://www.crm.com:8088/crm/main?...
		int number = req.getRequestURL().toString().indexOf(req.getContextPath(),0);
		System.out.println(req.getRequestURL().toString().substring(0,number) + req.getContextPath());  //上下文路径地址
		
		//调用sso的登出方法
		req.setAttribute("serverLogOutUrl", CommonUtils.getProperties("sso.properties").get("server-url-prefix").toString() + "/logOut");
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,resp);
		
	}

}
