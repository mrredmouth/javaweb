package com.ccg.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

/**
 * java基础：
 * 1、Variable 变量
 * @author Administrator
 */
@Data
public class JavaBasicVariable {
	private String userName;
	private String password;
	private int age = 18;
	private String[] params = new String[] { "2018", "09" };
	
	//声明并定义List变量
	private List<String> list1 = Arrays.asList("list1","list2","list3");
	private List<Student> list2 = new ArrayList<Student>() {
		private static final long serialVersionUID = 1L;
		{
			this.add(new Student(1, "ZhangSan", 23));
			this.add(new Student(2, "LiSi", 22));
			this.add(new Student(3, "WangWu", 21));
		}
	};
	
	//声明并定义HashMap变量
	private HashMap<String, Object> map = new HashMap<String,Object>(){
		private static final long serialVersionUID = 1L;
		{
			this.put("companyName","ccg");
			this.put("engineName","小马哥");
		}
	};
	
	//内部类
	@Data
    class Student {
    	private int id;
    	private String name;
    	private int age;
    	
    	// 构造函数
    	public Student(int id, String name, int age) {
    		super();
    		this.id = id;
    		this.name = name;
    		this.age = age;
    	}
    }
    
}



