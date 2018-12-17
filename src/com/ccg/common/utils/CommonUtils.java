package com.ccg.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.dom4j.DocumentException;

public class CommonUtils {
	
	/**
	 * 获取配置文件的properties键值对
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String fileName) throws IOException{
		//InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		//两种获取流的方式都可以
		InputStream is = CommonUtils.class.getClassLoader().getResourceAsStream(fileName);
		Properties props = new Properties();
		props.load(is);
		return props;
	}
	
	/**
	 * 计算js表达式结果：逻辑表达式、算术表达式等
	 * 	"(1+2)*3+9/5-5+8*3.9+7/3"
	 *  "(false || true ) && true"
	 * @param str
	 * @return 返回Boolean,Long等类型
	 * @throws ScriptException
	 */
	public static Object evalScriptEngine(String str) throws DocumentException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = null;
		try {
			result = engine.eval(str);
			System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将表达式拆分成逻辑表达式
	 * a>(b+3)* 0.3 || (((a+3)*2) && c<5)
	 * @param express
	 * @return
	 */
	public static String getLogicalExpression(String express) {
		List<String> symbolList = Arrays.asList("||","&&");
		String splitStr = ",";
		String newExpress = express;
		for(String symbol:symbolList){
			newExpress = newExpress.replace(symbol, splitStr);
		}
		newExpress = newExpress.replace(" ", "");
		while(newExpress.contains(",,")){
			newExpress = newExpress.replace(",,", splitStr);
		}
		String resultStr = "";
		for(String tempStr : newExpress.split(splitStr)){
			int leftBrackets = tempStr.length() - tempStr.replace("(", "").length();
			int rightBrackets = tempStr.length() - tempStr.replace(")", "").length();
			if(leftBrackets > rightBrackets){
				tempStr = tempStr.substring(1);
			}else if(leftBrackets < rightBrackets){
				tempStr = tempStr.substring(0,tempStr.length()-1);
			}
			resultStr = resultStr + tempStr + ",";
		}
		resultStr = resultStr.substring(0, resultStr.length()-1);
		System.out.println(resultStr);
		return resultStr;
	}
}











