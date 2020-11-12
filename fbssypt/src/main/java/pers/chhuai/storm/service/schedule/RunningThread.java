package pers.chhuai.storm.service.schedule;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.monitor.Yarn;
import pers.chhuai.storm.monitor.YarnMonitor;
import pers.chhuai.storm.service.bean.AppService;
import pers.chhuai.storm.service.bean.StaService;
import pers.chhuai.storm.service.hdfs.HdfsUtil;
import pers.chhuai.storm.service.judger.TestJudger;
import pers.chhuai.storm.service.remote.RemoteCommandExec;
import pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl;

import java.io.IOException;
import java.util.Date;

public class RunningThread implements Runnable{
	private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static RemoteCommandExecImpl rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);
	@Override
	public void run() {

		String ApplicationId=null;
		String Name=null;
		String ApplicationType=null;
		String State=null;
		String FinalStatus=null;
		long  StartTime= new Date().getTime();
		long FinishTime=0;
		String URL=null;
		String id=null;

		System.out.println("task runner...");
		while (true) {
			// 检测RunningList中是否有状态为SUBMITTE的任务
			Submission currentSub = null;
			// 同步锁
			synchronized (this) {
				for (Submission sub : TaskList.RunningList) {
					if (sub.getState().equals(TaskMonitor.SUBMITTED_STATE)) {
						currentSub = sub;
						break;
					}
				}
			}
			if (currentSub!=null) {
				currentSub.updateState(TaskMonitor.RUNNING_STATE, "运行中");
				Name=currentSub.getJobname();
				id=currentSub.getId();

				YarnThread yr = new YarnThread();//Runable
                yr.setId(id);
				Thread t = new Thread(yr);
				t.start();

				String submitResult = runCurrentSub(currentSub);
				System.out.println("run线程返回执行成功结果:"+submitResult);


				if (submitResult.equals(TaskMonitor.ERROR_STATE)) { // 错误
					currentSub.updateState(TaskMonitor.ERROR_STATE, submitResult);
				} else { //
					// 从HDFS读取结果并调用判决器
//					String output_result=rc.getResultFormHDFS(id,currentSub.getTestID());
//					System.out.println(output_result);

					String outputpath="/test"+currentSub.getTestID()+"/"+id+"/output-"+currentSub.getTestID()+"/part*";
					try {
						String output_result=HdfsUtil.cat(outputpath);
						System.out.println(output_result);
					} catch (IOException e) {
						e.printStackTrace();
					}

					currentSub.updateState(TaskMonitor.ACCEPTED_STATE, "正在判断");

				}

			}


			// 获得任务提交状态
//				if (submitResult.equals(TaskMonitor.ERROR_STATE)) { // 错误
//					currentSub.updateState(TaskMonitor.ERROR_STATE, submitResult);
//				} else { // 提交成功
//					// 从HDFS读取结果并调用判决器
//					if (TestJudger.isAccepted(currentSub)) {
//						currentSub.updateState(TaskMonitor.ACCEPTED_STATE, "结果正确");
//						StaService ss = ac.getBean("staService", StaService.class);
//						ss.updateSta(currentSub.getId(), currentSub.getTestID());
//					} else {
//						currentSub.updateState(TaskMonitor.ACCEPTED_STATE, "结果错误");
//					}
//				}
//
//			}
//
//
//				// 获得任务提交状态
//				if (subState.equals(TaskMonitor.ERROR_STATE)) { // 错误
//					currentSub.updateState(TaskMonitor.ERROR_STATE, submitResult);
//				} else if (subState.equals(TaskMonitor.EXCEPTION_STATE)) { // 异常
//					currentSub.updateState(TaskMonitor.EXCEPTION_STATE, submitResult);
//		    	} else { // 提交成功
//					// 从HDFS读取结果并调用判决器
//					if (TestJudger.isAccepted(currentSub)) {
//						currentSub.updateState(TaskMonitor.ACCEPTED_STATE, "结果正确");
//						StaService ss = ac.getBean("staService", StaService.class);
//						ss.updateSta(currentSub.getId(), currentSub.getTestID());
//					} else {
//						currentSub.updateState(TaskMonitor.ERROR_STATE, "结果错误");
//					}





			TimeDelay.delay(300);
		}
	}

	private String runCurrentSub(Submission currentSub) {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			RemoteCommandExec rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);
			return rc.runTopo(currentSub, 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}