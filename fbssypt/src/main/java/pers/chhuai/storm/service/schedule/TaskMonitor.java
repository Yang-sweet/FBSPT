package pers.chhuai.storm.service.schedule;


import pers.chhuai.storm.beans.Submission;

public class TaskMonitor implements Runnable {
	public static int MAX_RUNNING_TASK = 2;
	public static String SUBMITTED_STATE = "已提交";
	public static String RUNNING_STATE = "运行中";
	public static String ERROR_STATE = "错误";
	public static String EXCEPTION_STATE = "异常";
	public static String ACCEPTED_STATE = "已完成";
	
	
	  public void showTaskInfo() {
				//System.out.println("阻塞中:"+TaskList.SubmittedQueue.size());
				for (Submission sub : TaskList.SubmittedQueue) {
					System.out.println(sub);
				}
	
				//System.out.println("运行中:"+TaskList.RunningList.size());
				for (Submission sub : TaskList.RunningList) {
					System.out.println(sub);
				}
				//System.out.println("已完成:"+TaskList.FinishedList.size());
				for (Submission sub : TaskList.FinishedList) {
					System.out.println(sub);
				}
	
				//System.out.println("异常/错误:"+TaskList.ErrorList.size());
				for (Submission sub : TaskList.ErrorList) {
					System.out.println(sub);
				}
				//System.out.println("==========================================================");
		}
		
		@Override
		public void run() {
			// 打印队列信息
			while (true) {
				TimeDelay.delay(1000);
				//showTaskInfo();
			}
		}
}
