package com.ccg.http;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * TODO				操作工具类
 * @author 			ywl
 * @version 		1.0
 * @since 			JDK 1.5
 * @Date 			2016-3-24 下午4:48:52
 */
public class OperateResult<T> implements Serializable{

	/**
	 * serialVersionUID:TODO(序列化唯一标示).
	 * @since JDK 1.5
	 */
	private static final long serialVersionUID = -5824105662361364331L;
	
	private boolean isSucc;
	private int code;
	private String error;
	private T result;
	private Date date;
	
	public boolean isSucc() {
		return isSucc;
	}
	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
