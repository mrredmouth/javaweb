package com.ccg.common;

import org.dom4j.DocumentException;
import org.junit.Test;

public class TestCommonUtils {
	
	@Test
	public void testEvalJsStr() throws DocumentException{
		CommonUtils.evalScriptEngine("(1+2)*3+9/5-5+8*3.9+7/3");
		CommonUtils.evalScriptEngine("(false || true ) && true");
		CommonUtils.evalScriptEngine("false || false");
		CommonUtils.evalScriptEngine("true || true");
		CommonUtils.evalScriptEngine("false || true");
		
		CommonUtils.getLogicalExpression("a>(b+3)* 0.3 || (((a+3)*2) && c<5)");
	}
}
