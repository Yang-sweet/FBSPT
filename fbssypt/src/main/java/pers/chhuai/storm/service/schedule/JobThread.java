package pers.chhuai.storm.service.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.remote.RemoteCommandExec;
import pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl;

public class JobThread implements Runnable{



    Jobinfo current_job = null;
    String job = null;
//    private int taskNum;

//    public JobThread(int num) {
//        this.taskNum = num;
//    }
public JobThread(Jobinfo current_job) {
    this.current_job = current_job;
    job = current_job.toString();
}



    @Override
    public void run() {
//        try {
//            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//            RemoteCommandExec rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);
//            return rc.runTopo(current_job, 5);
//            //return rc.runTopo(currentSub);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;


//        System.out.println("正在执行task ");
//        try {
//            Thread.currentThread().sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("task "+taskNum+"执行完毕");
    }
}
