package com.ccg.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ccg.exception.MyFileException;
import com.ccg.io.image.MyImageUtils;
import com.ccg.pojo.Image;
import com.ccg.pojo.User;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//扫描器，扫描req的请求内容,打印到控制台
		Scanner sc = new Scanner(req.getInputStream());
		/*while(sc.hasNextLine()){
			System.out.println(sc.nextLine());
		}*/
		
		
		//看源码，判断请求方式是否post，请求编码是否multipart/form-data
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
		if(!isMultipartContent){
			sc.close();
			return;
		}
		
		try {
			Map<String, String> fieldMap = new HashMap<String,String>();//存放普通参数
			Map<String, Image> binaryMap = new HashMap<String, Image>();//存放图片参数
			
			//将req的参数封装到fieldMap中，文件需记录文件名和存储路径
			MyImageUtils.uploadImage(req,fieldMap,binaryMap);
			
			//将fieldMap数据封装到User对象中，并返回到req中
			User user = new User();
			user.setUserName(fieldMap.get("userName"));
			user.setHeadImage(binaryMap.get("headImage"));
			req.setAttribute("user", user);
			req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
			
		} catch (MyFileException e){ //catch自定义的异常，将错误信息返回给浏览器
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
		} catch (Exception e) {
		} finally {
			sc.close();
		}
	}
	public static void main(String[] args) {
		
		//输出某网页的源代码
		InputStream in = null;
		 try {
		   in = new URL( "http://commons.apache.org" ).openStream();
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		   String line;
		   while ( ( line = buf.readLine() ) != null ) {
		     System.out.println( line );
		   }
		   //直接用IOUtils方法替代上面一大段,commons-io包里
		   //System.out.println( IOUtils.toString( in ) );
		 } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 
	}
}
