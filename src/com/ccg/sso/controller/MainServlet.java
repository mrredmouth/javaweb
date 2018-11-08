package com.ccg.sso.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="mainServlet",urlPatterns="/main")
public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		System.out.println("MainServlet.init()");
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getRequestURI());	//浏览器访问的资源名
		System.out.println(req.getRequestURL().toString()); //浏览器中的访问的所有地址http://www.crm.com:8088/crm/main
		int number = req.getRequestURL().toString().indexOf(req.getRequestURI(),0);
		System.out.println(req.getRequestURI() + req.getRequestURL().toString().substring(0,number));  //资源名及往前的全地址
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req,resp);
		
	}

}
