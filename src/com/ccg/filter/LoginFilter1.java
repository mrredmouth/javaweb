package com.ccg.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter1 implements javax.servlet.Filter{
	
	public LoginFilter1() {
		System.out.println("创建LoginFilter1");
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 安装web.xml中的filter-mapping顺序执行过滤器
	 * 放行之前的执行顺序：1，3，2
	 * 放行之后的执行顺序：2，3，1
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoginFilter1.doFilter()--放行之前");
		chain.doFilter(req, resp);
		System.out.println("LoginFilter1.doFilter()--放行之后");
	}

	@Override
	public void destroy() {
		
	}

}
