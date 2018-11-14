package com.ccg.io;

import org.junit.Test;

import com.ccg.io.excel.MyExcelUtils;

public class TestMyExcelUtils {
	
	@Test
	public void testMyExcelUtils(){
		System.out.println(MyExcelUtils.getColumnIndexFromStr("A"));
		System.out.println(MyExcelUtils.getColumnIndexFromStr("Z"));
		System.out.println(MyExcelUtils.getColumnIndexFromStr("AH"));
		System.out.println(MyExcelUtils.getColumnStrFromIndex(2));
		System.out.println(MyExcelUtils.getColumnStrFromIndex(27));
		System.out.println(MyExcelUtils.getColumnStrFromIndex(35));
	}
}
