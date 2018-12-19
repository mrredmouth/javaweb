package com.ccg.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * FilterConfig,Boolean.valueOf
 * @author Administrator
 *
 */
public class CharacterEncodingFilter implements javax.servlet.Filter{
	
	private String encoding;
	private boolean encodingForce = false;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
		//Boolean.valueOf方法，只有在值为true的时候返回true，其他任何情况(空，false，其他字符)都返回false
		this.encodingForce = Boolean.valueOf(filterConfig.getInitParameter("encodingForce"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		if(StringUtils.isNotBlank(encoding) && (req.getCharacterEncoding()==null || encodingForce)){
			req.setCharacterEncoding(encoding);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}

}
