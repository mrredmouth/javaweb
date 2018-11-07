package com.ccg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccg.pojo.User;

/**
 * Servlet方式login到登录界面
 * @author Administrator
 *
 */
@WebServlet(value="/login",
	initParams = {
		@WebInitParam(name="userName",value="servletUserName"),
		@WebInitParam(name="password",value="123456")
	}
)
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		System.out.println("LoginServlet.init()");
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("user", 
				new User(super.getServletConfig().getInitParameter("userName"),
						super.getServletConfig().getInitParameter("password")));
		
		req.getRequestDispatcher("/pages/login/login.jsp").forward(req,resp);
		
	}

}
