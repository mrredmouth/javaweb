package com.ccg.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter2 implements javax.servlet.Filter{
	
	public LoginFilter2() {
		System.out.println("创建LoginFilter2");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoginFilter2.doFilter()--放行之前");
		chain.doFilter(req, resp);
		System.out.println("LoginFilter2.doFilter()--放行之后");
	}

	@Override
	public void destroy() {
		
	}

}
