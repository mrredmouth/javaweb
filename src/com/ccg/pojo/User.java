package com.ccg.pojo;

import lombok.Data;

@Data
public class User {

	private String userName;
	private String password;
	private Image headImage;
	
	public User(){
		
	}
	public User(String userName,String password){
		setUserName(userName);
		setPassword(password);
		setHeadImage(null);
	}
	
}
