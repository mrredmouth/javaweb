package com.ccg.sso.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ccg.http.HttpUtils;
import com.ccg.utils.CommonUtils;

/**
 * 单点登录过滤器：
  <filter-mapping>
    <filter-name>SSOClientFilter</filter-name>
    <url-pattern>/main</url-pattern>
  </filter-mapping>
 * @author Administrator
 *
 */
public class SSOClintFilter implements Filter{
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("SSOClintFilter.init()");
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SSOClintFilter.doFilter()");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;

		Properties props = CommonUtils.getProperties("sso.properties");
		
		//1、判断是否有局部会话。
		HttpSession session = req.getSession();
		Boolean isLogin = (Boolean)session.getAttribute("isLogin");//因为可能为空，不能用boolean接收，要用其包装类型Boolean
		
		//有局部会话，直接放行
		if(isLogin != null && isLogin){
			chain.doFilter(request, response);
			return;
		}
		
		//没有局部会话，首先确认是否是从认证中心过来的，是否有token令牌信息
		String token = req.getParameter("token");
		if(StringUtils.isNotBlank(token)){
			//不为空，判断令牌是否是统一认证中心发过来的
			Map<String,String> params = new HashMap<String,String>();
			params.put("token", token);
			//其他系统使用统一认证中心已有的token时，需要将登出url和jsessionid带过去，便于销毁session
			int number = req.getRequestURL().toString().indexOf(req.getContextPath(),0);
			//获取到上下文路径的地址 http://www.crm.com:8088/crm
			String clientHostUri =  req.getRequestURL().toString().substring(0,number) + req.getContextPath();
			params.put("clientLogOutUrl", clientHostUri+"/logOut"); //http://www.crm.com:8088/crm/logOut
			params.put("jsessionId", req.getSession().getId());
			

			String httpUrl = (String) props.get("server-url-prefix") + "/verify";
			String isVerify = HttpUtils.sendHttpRequest(httpUrl, params);
			if("true".equals(isVerify)){
				//统一认证中心验证通过，则创建局部会话
				session.setAttribute("isLogin", true);
				//并放行
				chain.doFilter(req, resp);
				return;
			}
		}
		
		//没有局部会话，且没有令牌token，则重定向到统一认证中心，检查是否有其他系统登录过
		String clientHostUrl = req.getRequestURL().toString();  //http://www.crm.com:8088/crm/main
		String serverUrlPrefix = (String) props.get("server-url-prefix");
		StringBuilder url = new StringBuilder(50)
				.append(serverUrlPrefix)
				.append("/checkLogin?redirectUrl=")
				.append(clientHostUrl);
		resp.sendRedirect(url.toString());
		
	}

}
