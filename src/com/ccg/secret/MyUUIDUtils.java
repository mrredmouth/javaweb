package com.ccg.secret;

import java.util.UUID;

public class MyUUIDUtils {
	/**
	 * 生成随机字符串:UUID 
	 * 示例：35c18972c0a7417b96ccec603ebc669e
	 * @return
	 */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 创建唯一编号:UUID + hashCode
	 * 疑问：跑十万个左右会出现重复的，UUID不会重复，但是hsshcode重复了
	 * 示例：1000001430529527
	 * @return
	 */
	public static String createUniqueId() {  
	    int machineId = 1;//最大支持1-9个集群机器部署  
	    int hashCodeV = UUID.randomUUID().toString().hashCode();  
	    if(hashCodeV < 0) {//有可能是负数  
	        hashCodeV = - hashCodeV;  
	    }  
	    // 0 代表前面补充0       
	    // 4 代表长度为4       
	    // d 代表参数为正数型  
	    return machineId+String.format("%015d", hashCodeV);  
	}
	
	
}
