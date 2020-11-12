package pers.chhuai.storm.servlet;


import pers.chhuai.storm.service.schedule.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class TaskMonitor
 */
@WebServlet(name = "TaskScheduler", urlPatterns = "/TaskScheduler", loadOnStartup=1)
public class TaskScheduler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		// 任务监视器
		Thread taskMonitor = new Thread(new TaskMonitor(), "taskMonitor");
		taskMonitor.start();

		// 任务监听器
		Thread taskListener = new Thread(new SocketForword(), "taskListener");
		taskListener.start();

		// 任务提交器
		Thread taskSubmitter = new Thread(new SubmitThread(), "taskSubmitter");
		taskSubmitter.start();

		// 任务调度器
		Thread taskScheduler = new Thread(new SchedulerThread(), "taskScheduler");
		taskScheduler.start();
				
		// 任务判决器
		Thread taskJudger = new Thread(new JudgerThread(), "taskJudger");
		taskJudger.start();

		// 任务提交器
		Thread YarnMonitor = new Thread(new YarnThread(), "taskSubmitter");
		YarnMonitor.start();
	}
}
