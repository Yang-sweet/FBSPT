package pers.chhuai.storm.service.schedule;


import pers.chhuai.storm.beans.Submission;

public class SchedulerThread implements Runnable {
	// 调度任务到运行池
	public void run() {
		try {
			System.out.println("task scheduler started");
			while (true) {     
				TimeDelay.delay(200);
        		if (TaskList.RunningList.size()<TaskMonitor.MAX_RUNNING_TASK&&TaskList.SubmittedQueue.size()!=0) {  
        			// 取阻塞队列对头
        			Submission currentSub = TaskList.SubmittedQueue.peek();//取头元素

					// 同步锁，提交队列--->运行池
        			synchronized (this) {
        				TaskList.RunningList.add(currentSub);
	        			TaskList.SubmittedQueue.poll();//删除头
					} 
        			// 任务执行器
        			Thread taskExecutor = new Thread(new RunningThread(), "taskExecutor");
        			taskExecutor.start();
        		}
			}
		}
		 catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}				
	}
}
