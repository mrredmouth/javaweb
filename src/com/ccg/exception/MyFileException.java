package com.ccg.exception;

public class MyFileException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	//快捷键alt+shift+s,从父类创建构造器
	/**
	 * 
	 * @param message	异常信息
	 * @param cause		异常的根本原因
	 */
	public MyFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyFileException(String message) {
		super(message);
	}
	
}
