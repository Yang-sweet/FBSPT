package pers.chhuai.storm.service.schedule;

public class TimeDelay {
	// 延时
	public static void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
