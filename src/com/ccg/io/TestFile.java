package com.ccg.io;

import org.junit.Test;

import com.ccg.io.file.MyFileUtils;

public class TestFile {
	
	@Test
	public void deleteMkdirFile(){
		MyFileUtils.deleteFiles("F:/testdelete/111");
	}
}
