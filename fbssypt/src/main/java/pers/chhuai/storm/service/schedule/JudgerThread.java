package pers.chhuai.storm.service.schedule;
import pers.chhuai.storm.beans.Submission;
public class JudgerThread implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {			
			// 同步锁
			synchronized (this) {
				Submission removeSub = null;
				// 检测RunningList中的任务状态
				for (Submission currentSub : TaskList.RunningList) {
					// 执行完后移出RunningList		
					if (currentSub.getState().equals(TaskMonitor.ACCEPTED_STATE)) {		
		        		TaskList.FinishedList.add(currentSub);
		        		removeSub = currentSub;
		        		break;
					} else if (currentSub.getState().equals(TaskMonitor.ERROR_STATE)){
		    			TaskList.ErrorList.add(currentSub);	
		    			removeSub = currentSub;
		    			break;
					}  						
				}
				if (removeSub!=null) {
					TaskList.RunningList.remove(removeSub);
					//return;
				}	
			}	
			TimeDelay.delay(300);
		}
	}		
}
