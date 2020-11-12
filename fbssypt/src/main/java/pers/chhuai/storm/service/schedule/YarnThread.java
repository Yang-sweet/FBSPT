package pers.chhuai.storm.service.schedule;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.monitor.Yarn;
import pers.chhuai.storm.monitor.YarnMonitor;
import pers.chhuai.storm.service.bean.AppService;
import java.io.IOException;
import java.util.*;


public class YarnThread implements Runnable {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    private String id;
    public void setId(String id){
        this.id=id;
    }

    @Override
    public void run() {
        String ApplicationId = null;
        String Name = null;
        String ApplicationType = null;
        String State = null;
        String FinalStatus = null;
        long StartTime = new Date().getTime();
        long FinishTime = new Date().getTime();
        String URL = null;
        String Progress = null;
        //String id = null;

        System.out.println("YarnThreadruning......");
        HashSet<String> set=new HashSet<>();

        AppService as = ac.getBean("appService", AppService.class);

        while (true) {
            Configuration conf = new YarnConfiguration();
            YarnClient yarnClient = YarnClient.createYarnClient();
            yarnClient.init(conf);
            yarnClient.start();
            List<ApplicationReport> applications = new ArrayList<ApplicationReport>();
            ApplicationReport currentApp = null;

           EnumSet<YarnApplicationState> appStates = EnumSet.noneOf(YarnApplicationState.class);
//           if (appStates.isEmpty()) {

               appStates.add(YarnApplicationState.ACCEPTED);

//           }

            synchronized (this) {
                try {
//                applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.ACCEPTED));
                    applications = yarnClient.getApplications(appStates);

                    if (applications.isEmpty()) {
                    } else {
                        for (ApplicationReport application : applications) {
                            currentApp = application;

                            ApplicationId = application.getApplicationId().toString();
                            Name = application.getName();
                            ApplicationType = application.getApplicationType();
                            State = application.getYarnApplicationState().toString();
                            FinalStatus = application.getFinalApplicationStatus().toString();
                            StartTime = application.getStartTime();
                            FinishTime = application.getFinishTime();
                            URL = application.getOriginalTrackingUrl();

                            if (set.contains(ApplicationId)) {

                                String state=YarnMonitor.getappInfo(ApplicationId).getYarnApplicationState().toString();
                                String finalstatus=YarnMonitor.getappInfo(ApplicationId).getFinalApplicationStatus().toString();
                                long finishtime=YarnMonitor.getappInfo(ApplicationId).getFinishTime();

                                as.updateAppllication(ApplicationId, state, finalstatus, finishtime);
                                
                                continue;
                            } else {
                                set.add(ApplicationId);
                                System.out.println(set + ApplicationId.toString() + id);
                                System.out.println("加入");
                                as.addApplication(ApplicationId, Name, ApplicationType, State, FinalStatus, StartTime, FinishTime, URL, id);
                            }
                            Thread.sleep(5000);
                        }
                    }
                } catch (YarnException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }


//    public static boolean yarnNotNull() {
//
//        Configuration conf = new YarnConfiguration();
//        YarnClient yarnClient = YarnClient.createYarnClient();
//        yarnClient.init(conf);
//        yarnClient.start();
//        boolean isContains = false;
//        List<ApplicationReport> applications = new ArrayList<ApplicationReport>();
//
//        try {
//            //applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.RUNNING, YarnApplicationState.FINISHED));
//            applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.ACCEPTED));
//            if (applications.isEmpty()) {
//                return false;
//            } else {
//                return true;
//            }
//
//        } catch (YarnException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}