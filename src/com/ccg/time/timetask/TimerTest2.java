package com.ccg.time.timetask;

import java.io.IOException;
import java.util.Timer;

/**
 * 本类给出了使用Timer和TimerTaske的主要方法,其中包括定制任务,添加任务退出任务,退出定时器.
 * 因为TimerTask的status域是包级可访问的,所以没有办法在java.util.包外得到其状态,这对编程造成一些不便.
 * 我们不能判断某个Task的状态了
 * 
 * @Time 4:36:30 PM
 * @author mengxiankong
 */
public class TimerTest2 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		MyTask myTask1 = new MyTask();
		MyTask myTask2 = new MyTask();
		myTask2.setInfo("myTask-2");
		timer.schedule(myTask1, 1000, 2000);
		/**
		 * 如果你使用的是JDK 5+,还有一个scheduleAtFixedRate模式可以用,
		 * 在这个模式下,Timer会尽量的让任务在一个固定的频率下运行,
		 * 举例说明:在上面的例子中,我们想让MyTask在1秒钟后,每两秒钟执行一次,
		 * 但是因为java不是实时的(其实java实时性很差.....),
		 * 所以,我们在上个程序中表达的原义并不能够严格执行.
		 * 如果我们调用的是scheduleAtFixedRate,那么,Timer会尽量让你的Task执行的频率保持在2秒一次.
		 * 运行上面的程序,假设使用的是scheduleAtFixedRate,那么下面的场景就是可能的:
		 * 1秒钟后,MyTask 执行一次,因为系统繁忙,之后的2.5秒后MyTask 才得以执行第二次,
		 * 然后,Timer记下了这个延迟,并尝试在下一个任务的时候弥补这个延迟,那么,1.5秒后,
		 * MyTask 将执行的三次."以固定的频率而不是固定的延迟时间去执行一个任务"
		 */
		timer.scheduleAtFixedRate(myTask2, 2000, 3000);
		while (true) {
			try {
				byte[] info = new byte[1024];
				int len = System.in.read(info);
				String strInfo = new String(info, 0, len, "GBK");// 从控制台读出信息
				if (strInfo.charAt(strInfo.length() - 1) == ' ') {
					strInfo = strInfo.substring(0, strInfo.length() - 2);
				}
				if (strInfo.startsWith("Cancel-1")) {
					myTask1.cancel();// 退出单个任务
					// 其实应该在这里判断myTask2是否也退出了,是的话就应该break.但是因为无法在包外得到
					// myTask2的状态,所以,这里不能做出是否退出循环的判断.
				} else if (strInfo.startsWith("Cancel-2")) {
					myTask2.cancel();
				} else if (strInfo.startsWith("Cancel-All")) {
					timer.cancel();// 退出Timer
					break;
				} else {
					// 只对myTask1作出判断,偷个懒^_^
					myTask1.setInfo(strInfo);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
