package com.ccg.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;


/**
 * 
 * @author Administrator
 */
public class MyFileUtils {
	private static Logger logger = Logger.getLogger(MyFileUtils.class);
	
	/**
	 * 设置下载文件的请求头，响应头;
	 * 同时给浏览器下载的文件名编码兼容
	 * 可设置文件类型已测：图片、word、excel
	 * @param req
	 * @param resp
	 * @param realPath
	 * @throws IOException
	 */
	public static void setDownloadHead(HttpServletRequest req, HttpServletResponse resp,String realPath) throws IOException{

		String fileName = FilenameUtils.getName(realPath);
		resp.reset(); // 非常重要
		//设置文件保存名称。区分IE浏览器，中文乱码解决
		String userAgent = req.getHeader("User-Agent");
		if(userAgent.contains("MSIE")){		//IE，采用URLEncoder编码方式
			fileName = URLEncoder.encode(fileName,"UTF-8");
		}else{			//非IE,默认的编码方式是"ISO-8859-1"，用utf-8编码的数据会有乱码。解决：先解码utf-8，再编码ISO-8859-1
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//设置内容类型。IE会直接打开，设置让浏览器不直接打开，而是弹出下载框，保存文件
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
    	resp.setContentType(getMineType(fileType) + "; charset=GBK");
    	//设置响应头，文件名称
    	resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	}
	
	/**
	 * 获取resp.setContentType中的mineType类型
	 * @param ftype
	 * @return
	 */
	public static String getMineType(String ftype){
		String mineType = "application/octet-stream";
		if("doc".equals(ftype.toLowerCase())){
			mineType="application/msword";
		}if("docx".equals(ftype.toLowerCase())){
			mineType="application/msword";
		}else if("ppt".equals(ftype.toLowerCase())){
			mineType="application/vnd.ms-powerpoint";
		}else if("pdf".equals(ftype.toLowerCase())){
			mineType="application/pdf";
		}else if("xls".equals(ftype.toLowerCase())||"xlsx".equals(ftype.toLowerCase())){
			mineType="application/vnd.ms-excel";
		}else if("bmp".equals(ftype.toLowerCase())){
			mineType="image/bmp";
		}else if("jpeg".equals(ftype.toLowerCase())||"jpg".equals(ftype.toLowerCase())){
			mineType="image/jpeg";
		}else if("gz".equals(ftype.toLowerCase())){
			mineType="application/x-gzip";
		}else if("zip".equals(ftype.toLowerCase())){
			mineType="application/zip";
		}else if("txt".equals(ftype.toLowerCase())){
			mineType="text/plain";
		}
		return mineType;	
	}
	
	/**
	 * 创建文件目录 filePath = fileRootPath + fileName
	 * @param fileRootPath
	 */
	public static void createFileDir(String fileRootPath) {
		File mkdir = new File(fileRootPath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
	}
	
    /**
     * 清空文件夹里的内容
     */
    public static void clearFiles(String filePath) {
        File file = new File(filePath);
        clearFiles(file);
    }
    /**
     * 清空文件夹里的内容
     * @param file
     */
    public static void clearFiles(File file) {
    	deleteFiles(file,false);
    }
    /**
     * 删除文件夹及其内容
     * @param filePath
     */
    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        deleteFiles(file);
    }
    /**
     * 删除文件夹及其内容
     * @param filePath
     */
    public static void deleteFiles(File file) {
    	deleteFiles(file,true);
    }
    /**
     * 删除或清空文件夹
     * @param file
     * @param flag true:删除文件夹;false:清空文件夹里的内容（默认）
     */
    public static void deleteFiles(File file,boolean flag) {

    	if(file.exists()){	//目录是否存在
	    	if(file.isFile()){	//如果是文件，则删除文件
	    		//System.out.println(file.getAbsoluteFile());
	    		file.delete(); //删除文件
	    	}
	    	
	        if (file.isDirectory()) {	//不是文件，则为文件夹
	            File[] childFiles = file.listFiles();
	            for (int i = 0; i < childFiles.length; i++) {
	            	deleteFiles(childFiles[i],true); //递归删除，从最里面的开始删除
	            }
	            if(flag){
	            	//System.out.println(file.getAbsoluteFile());
	            	file.delete(); //删除文件夹
	            }
	        }
    	}
    }
    
	/**
	 * 将暂存在服务器上的文件转为字节数组
	 * @param sourcePath 暂存到服务器上的文件地址
	 * @return
	 * @throws ServletException
	 */
	public static byte[] readBytesFromFile(String sourcePath) throws ServletException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			FileInputStream fin = new FileInputStream(new File(sourcePath));
			byte buf[] = new byte[fin.available()];
			
			@SuppressWarnings("unused")
			int len = 0;
			for (len = -1; (len = fin.read(buf)) != -1;){
				bos.write(buf);
			}
			byte res[] = bos.toByteArray();
			bos.close();
			fin.close();
			return res;
		} catch (Exception e) {
			throw new ServletException(e.toString());
		} 
	}

	/**
	 * 读取url网页的源代码，string输出
	 */
	public static String readStringFromUrl(URL url){

		String result = "";
		//输出某网页的源代码
		InputStream in = null;
		 try {
		   in = url.openStream();
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		   String line;
		   StringBuffer sb = new StringBuffer();
		   while ( ( line = buf.readLine() ) != null ) {
			   sb.append(line);
			   sb.append("\n");
		   }
		   result = sb.toString();
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
		return result;
	}
	
	/**
	 * 将字符串写到输出流
	 * @param filePath 要写出的文件
	 * @param fos OutputStream 输出流，可以是文件，可以是response等
	 * @throws IOException
	 */
	public static void writeStringToOutputStream(List<String> writeStrList,OutputStream os) throws IOException{
		
		//OutputStream是顶级接口，FileOutputStream是其实现类
		PrintStream ps = new PrintStream(os);
        for(String writeStr:writeStrList){
        	ps.println(writeStr);
        }
        ps.close();
	}

	/**
     * 将字符串换行写到文件里
     * @param writeStr 要写出的字符串
     * @param FilePath 输出文件的全路径，例："E:\\all_tables.dat"
     * @param appendFlag true:表示从文件末尾追加写入。false:清空原有的文件内容，从开头写入
     * @throws IOException 
     */
    public static void writeStringToFile(List<String> writeStrList,String filePath,Boolean appendFlag) throws IOException{
    	
    	// FileOutputStream 第二个参数：是否追加。true:字节写入文件的末尾 
    	OutputStream fos = new FileOutputStream(filePath,appendFlag);
    	writeStringToOutputStream(writeStrList,fos);
        fos.flush();
        fos.close();
    }

	/**
	 * 将文件写到输出流
	 * @param filePath 要写出的文件
	 * @param outputStream 输出流，可以是文件，可以是response等
	 * @throws IOException
	 */
	public static void writeFileToOutputStream(String filePath,OutputStream outputStream) throws IOException{
		InputStream is = new FileInputStream(new File(filePath));
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buf = new byte[4096]; //缓冲区
        int len = 0;
        while ((len = bis.read(buf)) > 0) {
        	outputStream.write(buf, 0, len);
        }
        outputStream.flush();
        bis.close();
	}
	/**
	 * 将文件写到response中，即浏览器下载文件
	 * @param filePath
	 * @param response
	 * @throws IOException
	 */
	public static void writeFileToResponse(String filePath,HttpServletResponse response) throws IOException{
		OutputStream outputStream = response.getOutputStream();
		MyFileUtils.writeFileToOutputStream(filePath,outputStream);
        response.flushBuffer();
        outputStream.close();
	}

	/**
	 * 从路径下载文件
	 * @param realPath 服务器文件所在的路径
	 * @param outputStream 输出流，将文件输出到浏览器的输出流，实现下载
	 * @param closeStream 是否关闭输出流
	 * @throws IOException
	 */
	public static void writeFileToResponse(String realPath, ServletOutputStream outputStream, boolean closeStream) throws IOException{
		
		//文件拷贝，从拷贝到输出流。resp的getWriter字节流和getOutputStream字符流区别，
		//文件下载是二进制，必须字符流
		Files.copy(Paths.get(realPath), outputStream);
		if(closeStream){
			outputStream.close();
		}
	}
	
	/**
	 * 关闭输输入流
	 * @param is
	 */
	public static void free(InputStream is){
		try{ 
			if(is != null){ 
				is.close(); 
			} 
		}catch(Exception e){ 
			logger.error("关闭输入流错误！", e); 
		}
	}
	
	/**
	 * 关闭输出流
	 * @param os
	 */
	public static void free(OutputStream os){
		try{
			if(os != null){
				os.close();
			}
		}catch(Exception e){
			logger.error("关闭输出流错误！", e);
		}
	}
}
