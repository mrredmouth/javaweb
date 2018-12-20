package com.ccg.common.utils;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtils {
	
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
    

	/**
	 * 判断某对象是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(Object str) {
		return (str == null || str.toString().trim().length() <= 0);
	}
}
