package com.ccg.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.ccg.io.excel.ExcelSheetPO;
import com.ccg.io.excel.ExcelVersion;
import com.ccg.io.excel.MyExcelUtils;
import com.ccg.io.file.MyFileUtils;

@WebServlet("/downExcel")
public class TestExcelServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* service1下载方式：工作簿写给输出流
		 * service2下载方式：从服务器路径下载MyFileUtils.downloadFile
		 * service3下载方式：从服务器路径下载MyExcelUtils.downLoadExcelFromPath
		 * */
		service2(req,resp);  
	}
	
	/**
	 * 测试MyExcelUtils.readExcel:excel文件读取,返回POJO
	 * 测试MyExcelUtils.downloadExcelFromWorkbook,将数据List<ExcelSheetPO>以excel文件下载
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void service1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//============================测试excel数据读取，返回PO数据==========================
		String fileName = "8月资金预算.xlsx";
		String realPath = req.getServletContext().getRealPath("/WEB-INF/download/"+fileName);
		List<ExcelSheetPO> excelSheets = MyExcelUtils.readExcel(new File(realPath), 3, 4);
		System.out.println(excelSheets);
		
		//================================测试excel下载，数据写入输出流返回给浏览器==========================
		ExcelVersion excelVersion = ExcelVersion.V2007; //用于下载文件的文件名后缀
		String outFileName = FilenameUtils.getBaseName(fileName) + "." + excelVersion.getSuffix();
		//resp必须先设置setContentType，setHeader。再写输出流
		MyFileUtils.setDownloadHead(req, resp, outFileName);

		//v2007方式;将数据excelSheets写到输出流outStream中;关闭输出流，直接响应给浏览器
		OutputStream outStream = resp.getOutputStream();
		excelSheets.get(0).setTitle("区县资金预算表");
		excelSheets.get(0).setHeaders(new String[]{"区县","预算","时间","小预算"});
		MyExcelUtils.downloadExcelFromWorkbook(excelVersion, excelSheets, outStream, true);
		
		
	}
	
	/**
	 * 测试MyFileUtils.downloadFile :从资源路径下载excel文件，同普通的文件下载MyFileUtils
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void service2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String fileName = "8月资金预算.xlsx";
		String realPath = req.getServletContext().getRealPath("/WEB-INF/download/"+fileName);
		try {
			MyFileUtils.setDownloadHead(req, resp, realPath);
			MyFileUtils.writeFileToResponse(realPath, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试MyExcelUtils.downLoadExcelFromPath：从路径下载excel文件，字节流的方式
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void service3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		String fileName = "8月资金预算.xlsx";
		String realPath = req.getServletContext().getRealPath("/WEB-INF/download/"+fileName);
		try {
			MyFileUtils.setDownloadHead(req, resp, realPath);
			MyExcelUtils.downLoadExcelFromPath(realPath, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
