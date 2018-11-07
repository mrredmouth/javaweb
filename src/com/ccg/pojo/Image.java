package com.ccg.pojo;

import lombok.Data;

@Data
public class Image {

	private String imageUrl; //头像图片存储在相对路径  /upload/...
	private String imageName; //头像图片的原始名称
}
