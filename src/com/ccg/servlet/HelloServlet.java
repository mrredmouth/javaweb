package com.ccg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ccg.pojo.UserLogin;

/**
 * 原生的servlet创建，继承HttpServlet，覆盖init和service方法
 * @author Administrator
 *
 */
@WebServlet(value="/servlet/hello2",
	initParams = {
		@WebInitParam(name="encoding",value="UTF-8"),
		@WebInitParam(name="name",value="will")
	}
)
public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String encoding;
	public void init() throws ServletException {
		System.out.println("HelloServlet.init()");
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/**
		 * 内置属性配置对象ServletConfig
		 */
		//web.xml的初始化参数;或者注解WebService中初始化参数
		encoding = super.getServletConfig().getInitParameter("encoding");
		
		/**
		 * 三大作用域对象一：request
		 */
		//post方式中文乱码解决：在所有参数最前面添加setCharacterEncoding；get方式需要修改tomcat的server.xml文件的属性
		req.setCharacterEncoding(StringUtils.isNotBlank(encoding)?encoding:"utf-8");
		resp.setContentType("text/html;charset=utf-8");//响应给浏览器乱码解决：在所有使用resp最前面添加setContentType
		
		req.getMethod();//获取浏览器请求方式
		String requestURI = req.getRequestURI();//浏览器中请求行中的资源名称部分
		String requestURL = req.getRequestURL().toString();//浏览器地址栏中所有信息
		req.getContextPath();//上下文路径，context的path值
		
		/**
		 * 三大作用域对象二：session,是特殊的cookie
		 */
		//添加cookie,放到浏览器响应中，可供共享
		Cookie cookie = new Cookie("currentName","lucy");
		cookie.setMaxAge(60*60*24*3);//指定cookie的保存时间，单位秒，3天
		cookie.setPath("/");//cookie共享的作用域，在同一主机中共享，/表示所有路径资源都可以
		//cookie.setDomain(".baidu.com");//设置了domain，才可以实现跨域访问，如百度这么设置，所有二级域名都可以共享
		resp.addCookie(cookie);
		
		//session，是特殊的cookie
		HttpSession session = req.getSession();
		session.setAttribute("curSessionName", "Tom");
		session.setMaxInactiveInterval(60*10);//保存时间，单位秒，10分钟
		session.removeAttribute("currenName");//session销毁，只销毁一个属性
		resp.encodeURL(requestURL);//携带jsessionid。cookie可以被浏览器手动禁用，session实质也是cookie。encodeURL携带后可共享
		//session.invalidate();//session对象销毁
		
		/**
		 * 三大作用域对象三：servletContext
		 * 
		 */
		//获取servletContext三种方式：
		ServletContext servletContext = req.getServletContext();//方式一
		super.getServletContext();//方式二
		req.getSession().getServletContext();//方式三
		//根据资源相对路径获取绝对路径
		servletContext.getRealPath(requestURL);
		//servlet上下文路径，ContextPath
		servletContext.getContextPath();
		//获取web.xml全局参数，同super.getServletConfig().getInitParameter
		servletContext.getInitParameter("contextName");
		
		
		PrintWriter writer = resp.getWriter();
		if(requestURI.endsWith("1")){
			writer.append("创建HelloServlet方式一：web.xml配置方式<url-pattern>/servlet/hello1</url-pattern>");
		}else if(requestURI.endsWith("2")){
			writer.append("创建HelloServlet方式二：注解方式：@WebServlet(value=\"/servlet/hello2\"");
		}
		System.out.println(requestURI+"----HelloServlet.service()");
		
		//请求转发,共享req中的数据,有了请求转发，上面resp里的write数据无效，不会传过去
		UserLogin ul = new UserLogin();
		req.setAttribute("userLogin", ul);
		req.getRequestDispatcher("/pages/login/login.jsp").forward(req,resp);
		//重定向,重定向不可访问WEB-INF中的内容
		//resp.sendRedirect("/index.jsp");
		
	}

}
