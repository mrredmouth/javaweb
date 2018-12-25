package com.ccg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ccg.io.file.MyFileUtils;

@WebServlet("/down")
public class FileDownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String fileName = req.getParameter("fileName");
		//a链接直接？参数，get方式请求，不能采用req.set...。解决：先解码ISO-8859-1，再编码utf-8
		if(StringUtils.isNotBlank(fileName)){
			fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		}
		//获取绝对路径
		String realPath = req.getServletContext().getRealPath("/WEB-INF/download/"+fileName);
		
		MyFileUtils.setDownloadHead(req, resp, realPath);
		MyFileUtils.writeFileToResponse(realPath, resp);
		
	}
}
