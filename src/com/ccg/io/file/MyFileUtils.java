package com.ccg.io.file;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.ccg.exception.MyFileException;
import com.ccg.pojo.Image;

public class MyFileUtils {
	private static final String ALLOW_IMAGE_TYPE = "png;gif;jpg;jpeg";
	
	/**
	 * 上传图片。形参传值的方式返回两种封装：普通表单和文件表单。
	 * @param req
	 * @param fieldMap
	 * @param binaryMap
	 */
	public static void uploadImage(HttpServletRequest req,Map<String,String> fieldMap,Map<String, Image> binaryMap){
		
		try {
			//创建FileItemFactory对象，用于创建FileItem对象，FileItem是浏览器form表单中的表单控件的封装
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload  fileUpload = new ServletFileUpload(fileItemFactory);
			
			fileUpload.setFileSizeMax(2*1024*1024);//设置单个请求单个文件上传的大小限制,2M;对应异常需手动catch
			fileUpload.setSizeMax(3*1024*1024);//设置单个请求所有文件大小限制,3M;对应异常需手动catch
			
			List<FileItem> fileItems = fileUpload.parseRequest(req);
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    String fieldName = item.getFieldName();//参数名称，普通和文件的都有名称
			    if (item.isFormField()) {//普通表单控件
			        String value = item.getString("utf-8");//获取表单参数value值，中文乱码处理
			        System.out.println(fieldName + ":" + value);
			        fieldMap.put(fieldName,value);
			    } else {//文件上传控件
			    	//item.getName(),获取文件的名称，IE6浏览器获取的是全路径，其他浏览器都获取的XXX.png等。用FilenameUtils.getName()解决
			    	String fileName = FilenameUtils.getName(item.getName());
			    	String ext = FilenameUtils.getExtension(fileName);
			        System.out.println(fieldName + "--" + fileName);
			        
			    	//将二进制文件输入到某个文件中，不是目录。文件名相同时，会覆盖，起唯一名：UUID.拓展名
			    	String newFileName = UUID.randomUUID().toString() + "." + ext;
			    	ResourceBundle bundle = ResourceBundle.getBundle("app",Locale.CHINA);//寻找app_zh_CN.properties资源文件
			    	String allowImageTypeStr = bundle.containsKey("allowImageType")?bundle.getString("allowImageType"):ALLOW_IMAGE_TYPE;
			    	String[] allowImageType = allowImageTypeStr.split(";");
			    	if(!Arrays.asList(allowImageType).contains(ext.toLowerCase())){
			    		//throw异常，相当于抛出一个异常，并return。还要经过下面的catch，继续抛出，必须在大的Exception之前catch
			    		throw new MyFileException("请上传正确的图片格式");
			    	}
			        //item.write(new File("F:\\javaLocal"+fileName));
			    	//讲图片存到服务器的upload中
			    	String dir = req.getServletContext().getRealPath("/upload");
			    	item.write(new File(dir,newFileName));
			    	
			    	/*图片缓存：图片小于缓存限制，则放到缓存中；超过缓存限制，则放到tomcat的临时目录F:\apache-tomcat-7.0.90-demo\temp*/
			    	fileItemFactory.setSizeThreshold(20*1024);//设置缓存大小，20KB
			    	//fileItemFactory.setRepository(repository);//设置临时目录，一般在temp下，不建议修改
			    	System.out.println(item.isInMemory());//查看是否在缓存中
			    	
			    	Image image = new Image();
			    	image.setImageName(fileName);
			    	image.setImageUrl("/upload/" + newFileName);
			    	binaryMap.put(fieldName,image);
			    	
			    }
			}
		} catch(MissingResourceException e){
			e.printStackTrace();
		} catch(FileSizeLimitExceededException e){	//将文件大小的两个异常跑给自定义异常，然后返回给调用者
			throw new MyFileException("单个上传文件大小异常，超过限制2M",e);
		} catch(SizeLimitExceededException e){
			throw new MyFileException("所有上传文件大小异常，超过限制3M",e);
		} catch (MyFileException e){
			throw e;//继续抛出给调用者，UploadServlet
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从路径下载文件
	 * @param realPath
	 * @param outputStream
	 * @param closeStream 是否关闭输出流
	 * @throws IOException
	 */
	public static void downloadFile(String realPath, ServletOutputStream outputStream, boolean closeStream) throws IOException{
		
		//文件拷贝，从拷贝到输出流。resp的getWriter字节流和getOutputStream字符流区别，文件下载是二进制，必须字符流
		Files.copy(Paths.get(realPath), outputStream);
		if(closeStream){
			outputStream.close();
		}
	}
	
	/**
	 * 设置下载文件的请求头，响应头;同时给浏览器下载的文件名编码兼容
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
    	resp.setContentType("application/x-msdownload");
    	//设置响应头，文件名称
    	resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
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
    
}
