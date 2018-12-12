package com.ccg.uuid;

import org.junit.Test;

public class TestAllUniqueUtils {
	
	@Test
	public void testUniqueUUIDUtils(){
		System.out.println(UniqueUUIDUtils.getUUID());
		System.out.println(UniqueUUIDUtils.createUniqueId());
		
		//UUIDUtils.createUniqueId()：使用UUID+hashCode方式id，会出现重复的
		int hashCodeVa = "23c4bcec-c781-40ca-8d41-a1c498052c36".hashCode(); 
		int hashCodeVb = "3b0b731b-bad2-4bfd-b305-444fc482fa21".hashCode();
		System.out.println(hashCodeVa == hashCodeVb);
		
	}
	@Test
	public void testUniqueTimestampUtils(){
		System.out.println(UniqueTimestampUtils.uniqueCurrentTimeMS());
		System.out.println(UniqueTimestampUtils.uniqueCurrentTimeMS());
		
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
		System.out.println(UniqueTimestampUtils.uniqueNanoTime());
	}
	
	/**
	 * 用多线程测试，获取synchronized同步16位时间戳
	 */
	@Test
	public void testGetSynchronizedTime() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(UniqueTimestampUtils.getSynchronizedTime());
				}
			}).start();
		}
    }
}
