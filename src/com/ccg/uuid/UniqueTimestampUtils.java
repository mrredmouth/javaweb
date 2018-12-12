package com.ccg.uuid;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

/**
 * Creating a unique timestamp in Java
 * @author Administrator
 *
 */
public class UniqueTimestampUtils {
	
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
	
	/**
	 * 避免每毫秒一个id的限制的一种方法是使用微秒时间戳currentTimeMillis。
	 * 即,将currentTimeMS乘以1000。这将允许每毫秒1000个id。
	 * (高并发很高很高时即使微毫秒也会有重复的，雪花算法一毫秒能产生4095个不重复ID)
	 * 示例：1544588758100
	 * @return
	 */
	public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	}
	
	/**
	 * 它是最精确的可用系统计时器，并将其除以百万以获得毫秒。虽然没有正式的保证它更新的频率，
	 * 但我认为合理地假设它更新的频率超过每毫秒一次（数量级）。
	 * 当然，如果创建小于毫秒间隔的整数时间戳，那么它们不可能都是唯一的。
	 * 示例：13314178754173
	 * @return
	 */
	public static long uniqueNanoTime() {
        return System.nanoTime();
    }
	
	
	/**
	 * synchronized + nanoTime 或者 currentTimeMillis ...
	 * 使用同步块，单线程得到唯一时间戳。
	 * 步骤：long类型的时间戳：12854263916111 
	 * 		-> 转成11位的16进制字符串：bb0ddd5a64f 
	 * 		-> 转成16位字符串，空位补0：00000BB0DDD5A64F
	 * 示例：00000BB0DDD5A64F
	 */
	private static Object SYNCHRONIZER = new Object();
	public static String getSynchronizedTime() {
		long ut = uniqueNanoTime();
		synchronized(SYNCHRONIZER) {
			return StringUtils.leftPad(Long.toHexString(ut).toUpperCase(), 16, '0');
		}
	}
	
}
