package com.ccg.pojo;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
@Data
public class UserLogin {
	
	private String userName;
	private String password;
	private int age = 18;
	//声明List变量的时候定义
	private List<String> list = Arrays.asList("list1","list2","list3");
	//声明HashMap变量的时候定义
	private HashMap<String, Object> map = new HashMap<String,Object>(){
		private static final long serialVersionUID = 1L;
		{
			this.put("companyName","ccg");
			this.put("engineName","小马哥");
		}
	};
}
