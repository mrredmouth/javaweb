package com.ccg.common.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;


public class MyStringUtils {

	public static final String SPLIT_COMMA_STRING = "#%2C#";
	public static final String SPLIT_COMMA = ",";
	public static final String SPLIT_VERTICAL_STRING = "#%7C#";
	public static final String SPLIT_VERTICAL = "|";
	public static final String SPLIT_RETURNANDCHANGEROW = "\r\n";
	public static final String HTML_QUOT_STRING = "&quot;";
	public static final String HTML_QUOT = "\"";
	public static final String HTML_AMP_STRING = "&amp;";
	public static final String HTML_AMP = "&";
	public static final String HTML_LT_STRING = "&lt;";
	public static final String HTML_LT = "<";
	public static final String HTML_GT_STRING = "&gt;";
	public static final String HTML_GT = ">";
	private static final String[] hanzi = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖","拾"}; 
	public static final String HTML_SQUOT_STRING = "&#39;";
	public static final String HTML_SQUOT = "\'";
	public static final String HTML_QUOT_STRING_ZH_LEFT = "&ldquo;";
	public static final String HTML_QUOT_ZH_LEFT = "“";
	public static final String HTML_QUOT_STRING_ZH_RIGHT = "&rdquo;";
	public static final String HTML_QUOT_ZH_RIGHT = "”";
	
	
    /**
     * Description: 字符串按分隔符转换成数组
     * @param str 需要分割的字符串 如：1,2,3, 或者1,2,3
     * @param splitDot 分隔符 如：,
     */
    public static String[] strToArray(String str, String splitDot) {

        String[] result = null;
        if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(splitDot)) {
            if (str.endsWith(splitDot)) {
                str = str.substring(0, str.length() - splitDot.length());
            }
            result = str.split(splitDot);
        }
        return result;
    }
    
	public static String transform(String number){
		int value = 0; 
		int exponent = 0; 
		boolean hasZero = false; 

		StringBuffer chineseDigit = new StringBuffer(); 
		value = Integer.parseInt(number); 

		for(exponent = 4; exponent>=0; exponent--) { 
		String[] unit = {"", "拾", "佰", "仟"}; 
		int divisor = (int)Math.pow(10, exponent); 
		int result = value / divisor; 
		if(result != 0) { 
		chineseDigit.append(hanzi[result]); 
		if(number.length()<4){
		chineseDigit.append(unit[exponent]); 
		}
		
		
		hasZero = false; 
		} else if(result == 0 && !hasZero) { 
		chineseDigit.append(hanzi[0]); 
		hasZero = true; 
		} else 
		{ 

		} 
		value = value % divisor; 
		} 
		
		     if(chineseDigit.substring(0,1).equals("零")) { 
		    	// System.out.println(chineseDigit.deleteCharAt(0).toString());
		    	 String a=chineseDigit.deleteCharAt(0).toString(); 
		        if(a.length()==1)
		        	return "零"+a;
		        else
		        	return a;
		     } 
		     else{ 
		    	 
		    return chineseDigit.toString(); 
		     } 
       
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MyStringUtils.transform("2012")+"--"+MyStringUtils.transform("5")
				+"--"+MyStringUtils.transform("12"));
	
	}
	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(Object str) {
		return (str == null || str.toString().trim().length() <= 0);
	}
	
//	public static boolean isBrank(String str){ return isBlank(str);}
	
	public static boolean isBlank(String str){		
		return (isNull(str)) ? true : StringUtils.isBlank(str);
	}
	
	public static String filterStringForCell(String input){	
		if(isNull(input)) return "";
		return input.replaceAll(SPLIT_RETURNANDCHANGEROW, " ").replaceAll(HTML_QUOT,"“");
	}
	
	/**
	 * 使用Map中的value替换Input中的Key
	 * @param input
	 * @param m
	 * @return
	 */
	public static String filter(String input, Map<String, ?> m){	
		if(isNull(input)) return "";
		if(m==null) return input.replaceAll(SPLIT_COMMA_STRING, SPLIT_COMMA).replaceAll(SPLIT_VERTICAL_STRING, SPLIT_VERTICAL);
		Set<String> st = m.keySet();
		Iterator<String> iter = st.iterator();
		while(iter.hasNext()){
			String mk = iter.next();
			input = input.replaceAll(mk, (String) m.get(mk));
		}
		return input;
	}
	
	public static String filterHtml(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return filtered.toString();
	}
	private static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if (input != null && input.length() > 0) {
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				switch (c) {
				case '<':
					flag = true;
					break;
				case '>':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}  
	/**
	 * 判断两个字符是否相等
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isEqual(String s1, String s2){
		if(s1==null&&s2==null) return true;
		if(s1==null) return false;
		return (s1.compareTo(s2)==0) ? true : false;
	}
		
	/**
	 * 字符串是否全是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
	public static boolean isNum(char ch){
		return Character.isDigit(ch);
	}
	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符） 
	 * @param c 需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {  
		int k = 0x80;  
		return c / k == 0 ? true : false;  
	}  
	
	/**
	 * 字符串是否以数字结尾
	 * @param str
	 * @return
	 */
	public static boolean endWithNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+$");
		Matcher haveNum = pattern.matcher(str);
		return haveNum.find();
	}
	
	/**
	 * 将字符串中的表达式分割出来(表达式分隔符不能嵌套)
	 * @param str 字符串
	 * @param startFlag 开始标记
	 * @param endFlag 结束标记
	 * @return 表达式列
	 */
	public static Set<String> splitBySBrackets(String str, String startFlag, String endFlag){
		str = str.trim();
		Set<String> names = new TreeSet<String>();
		int sj = str.indexOf(startFlag);
		int sk = str.indexOf(endFlag, sj);
		while ((sj > -1) && (sk > sj)) {
			String tn = str.substring(sj, ++sk);
			names.add(tn);
			str = str.substring(sk);
			sj = str.indexOf(startFlag);
			sk = str.indexOf(endFlag, sj);			
		}
		return names;
	}
	/**
	 * 获得首字母
	 * @param s
	 * @return
	 */
	public static String getInitial(String s){
		return s.substring(0, 1);
	}
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1 
	 * @param s 需要得到长度的字符串
	 * @return 得到的字符串长度 
	 */
	public static int Len(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
//		return s.getBytes().length;
	}
	
     /** 
      * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位  
      * @param  origin 原始字符串 
      * @param len 截取长度(一个汉字长度按2算的) 
      * @param c 后缀            
      * @return 返回的字符串 
      */  
     public static String substring(String origin, int len, String c) {  
         if (origin == null || origin.equals("") || len < 1)  
             return "";  
         byte[] strByte = new byte[len];  
         if (len > Len(origin)) {  
             return origin+c;  
         }  
         try {  
             System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);  
             int count = 0;  
             for (int i = 0; i < len; i++) {  
                 int value = (int) strByte[i];  
                 if (value < 0) {  
                     count++;  
                 }  
             }  
             if (count % 2 != 0) {  
                 len = (len == 1) ? ++len : --len;  
             }  
             return new String(strByte, 0, len, "GBK")+c;  
         } catch (UnsupportedEncodingException e) {  
             throw new RuntimeException(e);  
         }  
     }  
     public static int f(int x) {
			int c = 0;
			while(x!=0) {
				System.out.print(x+"||"+(x-1)+" is");
				System.out.print(x+"->");
			x = x & (x-1);
			System.out.println(x);
			c++;
			}
			return c;
	}
     public static long get(){
    	  long c = 0;
    	 char a = 0x48;
    	 char b = 0x52;

    	 c = b<<8 | a;
    	 return c;
     }
	
	/**
	 * 替换字符
	 * @param parentStr
	 * @param ch 被替换字符
	 * @param rep 代替字符
	 * @return
	 */
	public static String   replaceStr(String   parentStr,String ch,String   rep)   {   
		int   i   =   parentStr.indexOf(ch);   
		StringBuffer   sb   =   new   StringBuffer();   
		if   (i   ==   -1)   
		return   parentStr;   
		sb.append(parentStr.substring(0,i)   +   rep);   
		if   (i+ch.length()   <   parentStr.length())   
		sb.append(replaceStr(parentStr.substring(i+ch.length(),parentStr.length()),ch,rep));   
		return   sb.toString();   
		}
	
	/**
	 * 还原被安全检查替换的字符
	 * @param input
	 * @return
	 */
	public static String filterXssParams(String param) {
		String result = param.replaceAll(HTML_LT_STRING,HTML_LT)
				.replaceAll(HTML_SQUOT_STRING, HTML_SQUOT)
				.replaceAll(HTML_AMP_STRING, HTML_AMP)
				.replaceAll(HTML_GT_STRING, HTML_GT)
				.replace(HTML_QUOT_STRING, HTML_QUOT)
				.replaceAll(HTML_QUOT_STRING_ZH_LEFT, HTML_QUOT_ZH_LEFT)
				.replaceAll(HTML_QUOT_STRING_ZH_RIGHT, HTML_QUOT_ZH_RIGHT);
		
		return result;
	}
	
	public static String splitFormula(String express){
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
		return resultStr;
	}
	
	public static Object jsEvalEngine(String str){

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = null;
		try {
			result = engine.eval(str);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
 
	}
	
    /**
     *  判断是字符串是否为数字
     * @return boolean true 数字； false 字符串
     */
    public static boolean isNumb(String str) {
        boolean isTrue = true;
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            isTrue = false;
        }
        return isTrue;
    }
    
    /**
     * TODO: 判断传入的字符是否为数字，下划线或者A-Z字母
     * @return boolean
     */
    public static boolean isNumOrStrOrBlan(char c) {

        if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || c == 91 || c == 93 || c == 95 || (c >= 97 && c <= 122)) {
            return true;
        }
        return false;
    }
    

    /**
     * 将字符串类型的clob字段，转为string
     * @param clob
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static String changeClobToString(Clob clob) throws IOException, SQLException {
        BufferedInputStream bi = new BufferedInputStream(clob.getAsciiStream());
        int len = (int) clob.length();
        byte[] by = new byte[len];
        int i;
        while (-1 != (i = bi.read(by, 0, by.length))) {
            bi.read(by, 0, i);
        }
        String clobValue = new String(by);
        bi.close();
        return clobValue;
    }

    /**
     * 将字符串类型的clob字段，转为string
     * @param clob
     * @return
     */
    public static String changeClobToStringGBK(Clob clob) throws UnsupportedEncodingException, IOException, SQLException {
    	return new String(changeClobToString(clob).getBytes("GBK"));
    }
    /**
     * 判断是否为数字
     * @param info
     * @return
     */
    public static boolean isNumber(String info) {

        Pattern numPattern = Pattern.compile("^[0-9]+(.[0-9]*)?$");
        if (StringUtils.isNotBlank(info) && numPattern.matcher(info).matches()) {
            return true;
        }
        return false;
    }
	/**
	 * 判断是否为整数
	 * @param info
	 * @return
	 */
    public static boolean isInteger(String info) {

        Pattern intPattern = Pattern.compile("^-?[1-9]\\d*$");
        if (StringUtils.isNotBlank(info) && intPattern.matcher(info).matches()) {
            return true;
        }
        return false;
    }
    
}
