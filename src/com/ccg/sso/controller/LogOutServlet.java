package com.ccg.sso.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="LogOutServlet.java",urlPatterns="/logOut")
public class LogOutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("LogOutServlet.init()");
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().invalidate();
	}
}
