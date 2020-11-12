package pers.chhuai.storm.monitor;


import javafx.scene.effect.FloatMapBuilder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.ipc.YarnRPC;
import org.junit.jupiter.api.Test;
import org.apache.hadoop.util.FastNumberFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.bean.AppService;
import pers.chhuai.storm.service.bean.SubmissionService;
import pers.chhuai.storm.service.schedule.TaskMonitor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;



public class YarnMonitor {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static YarnClient client = YarnClient.createYarnClient();
    static{
        Configuration conf = new Configuration();
        client.init(conf);
        client.start();
    }
    public static String getInfo(String jobName){

        String ApplicationId=null;
        String Name=null;
        String ApplicationType=null;
        String State=null;
        String FinalStatus=null;
        long  StartTime=0;
        long FinishTime=0;
        String URL=null;

        YarnClient client = YarnClient.createYarnClient();
        Configuration conf = new Configuration();
        client.init(conf);
        client.start();
        EnumSet<YarnApplicationState> appStates = EnumSet.noneOf(YarnApplicationState.class);
        if (appStates.isEmpty()) {
            appStates.add(YarnApplicationState.NEW);
            appStates.add(YarnApplicationState.NEW_SAVING);
            appStates.add(YarnApplicationState.SUBMITTED);
            appStates.add(YarnApplicationState.ACCEPTED);
            appStates.add(YarnApplicationState.RUNNING);
            appStates.add(YarnApplicationState.FINISHED);
            appStates.add(YarnApplicationState.FAILED);
            appStates.add(YarnApplicationState.KILLED);
        }
        List<ApplicationReport> appsReport = null;
        //返回EnumSet<YarnApplicationState>中个人任务状态的所有任务
        try {
            appsReport = client.getApplications(appStates);
            //System.out.println(appsReport);
        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert appsReport != null;  //asert断言
        for (ApplicationReport appReport : appsReport) {
            //获取任务名
            String jn = appReport.getName();
            System.out.println(jn);
            String applicationType = appReport.getApplicationType();
            System.out.println(applicationType);


            if (jn.equals(jobName) && "MAPREDUCE".equals(applicationType)) {
                try {

                    ApplicationId=appReport.getApplicationId().toString();
                    Name=appReport.getName();
                    ApplicationType=appReport.getApplicationType();
                    StartTime=appReport.getStartTime();
                    FinishTime=appReport.getFinishTime();
                    State=appReport.getYarnApplicationState().toString();
                    FinalStatus=appReport.getFinalApplicationStatus().toString();
                    URL=appReport.getOriginalTrackingUrl();
                    System.out.println(URL);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String finishTime = format.format(FinishTime);
                    System.out.println(finishTime);

                    AppService as = ac.getBean("appService", AppService.class);
                    as.updateAppllication(ApplicationId, Name,ApplicationType,State,FinalStatus,FinishTime,URL);
                    //as.updateAppllication(ApplicationId, Name,ApplicationType,State,FinalStatus,FinishTime);

                    client.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return appReport.getApplicationId().toString();
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }



    /**
     * 获取任务的applicationId
     * @return String
     * @param jobName
     * @return
     */
    public static String getAppId(String jobName) {
        YarnClient client = YarnClient.createYarnClient();
        Configuration conf = new Configuration();
        client.init(conf);
        client.start();
        EnumSet<YarnApplicationState> appStates = EnumSet.noneOf(YarnApplicationState.class);
        if (appStates.isEmpty()) {

            appStates.add(YarnApplicationState.RUNNING);
            appStates.add(YarnApplicationState.ACCEPTED);
            appStates.add(YarnApplicationState.SUBMITTED);
            appStates.add(YarnApplicationState.FINISHED);
        }

        List<ApplicationReport> appsReport = null;

        //返回EnumSet<YarnApplicationState>中个人任务状态的所有任务
        try {
            appsReport = client.getApplications(appStates);
            //System.out.println(appsReport);
        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert appsReport != null;  //asert断言
        for (ApplicationReport appReport : appsReport) {
            //获取任务名
            String jn = appReport.getName();
            //System.out.println(jn);
            String applicationType = appReport.getApplicationType();
            //System.out.println(applicationType);


            if (jn.equals(jobName) && "MAPREDUCE".equals(applicationType)) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return appReport.getApplicationId().toString();
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


//    @Test
//    public static ArrayList getappInfo(String appId) {
//        YarnClient client = YarnClient.createYarnClient();
//        Configuration conf = new Configuration();
//        client.init(conf);
//        client.start();
//        ApplicationId applicationId = ApplicationId.fromString(appId);
//        ArrayList appinfo=new ArrayList();
//
//        String State ;
//        String FinalStatus;
//        Long FinishTime;
//
//
//        ApplicationReport applicationReport = null;
//        try {
//            applicationReport = client.getApplicationReport(applicationId);
//            State = applicationReport.getYarnApplicationState().toString();
//            FinalStatus=applicationReport.getFinalApplicationStatus().toString();
//            FinishTime=applicationReport.getFinishTime();
//            appinfo.add(State);
//            appinfo.add(FinalStatus);
//            appinfo.add(FinishTime);
//
//            System.out.println("Progress ============> "+applicationReport.getProgress());
//
//        } catch (YarnException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return appinfo;
//    }
@Test
public static ApplicationReport getappInfo(String appId) {

    ApplicationId applicationId = ApplicationId.fromString(appId);
    ArrayList appinfo=new ArrayList();

    String State ;
    String FinalStatus;
    Long FinishTime;


    ApplicationReport applicationReport = null;
    try {
        applicationReport = client.getApplicationReport(applicationId);

    } catch (YarnException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        client.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return applicationReport;
}


    /**
     * 根据任务的applicationId去获取任务的状态
     * @return YarnApplicationState
     * @param appId
     * @return
     */
    @Test
    public static YarnApplicationState getState(String appId) {
        YarnClient client = YarnClient.createYarnClient();
        Configuration conf = new Configuration();
        client.init(conf);
        client.start();
        ApplicationId applicationId = ApplicationId.fromString(appId);

        YarnApplicationState yarnApplicationState = null;
        FinalApplicationStatus yarnfinalStatus;


        ApplicationReport applicationReport = null;
        try {
            applicationReport = client.getApplicationReport(applicationId);

        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        yarnApplicationState = applicationReport.getYarnApplicationState();

        yarnfinalStatus =applicationReport.getFinalApplicationStatus();
        String jn =applicationReport.getName();

        System.out.println("ApplicationId ============> "+appId);
        System.out.println("name ============> "+applicationReport.getName());
        System.out.println("Queue ============> "+applicationReport.getQueue());
        System.out.println("User ============> "+applicationReport.getUser());
        System.out.println("ApplicationType ============> "+applicationReport.getApplicationType());
        System.out.println("getAmNodeLabelExpression ============> "+applicationReport.getAmNodeLabelExpression());
        System.out.println("AppNodeLabelExpression ============> "+applicationReport.getAppNodeLabelExpression());
        System.out.println("Host ============> "+applicationReport.getHost());
        System.out.println("OriginalTrackingUrl ============> "+applicationReport.getOriginalTrackingUrl());
        System.out.println("Progress ============> "+applicationReport.getProgress());
        System.out.println("StartTime ============> "+applicationReport.getStartTime());
        System.out.println("FinishTime ============> "+applicationReport.getFinishTime());
        System.out.println("FinalApplicationStatus ============> "+applicationReport.getFinalApplicationStatus());

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yarnApplicationState;
    }

    public static void main(String[] args) throws IOException, InterruptedException {


        YarnMonitor.getInfo("mr_wordcount");


    }


//    private void killApplication(String applicationId) throws YarnException, IOException{
//        ApplicationId appId = ConverterUtils.toApplicationId(applicationId);
//        ApplicationReport  appReport = null;
//        try {
//            appReport = client.getApplicationReport(appId);
//        } catch (ApplicationNotFoundException e) {
//            sysout.println("Application with id '" + applicationId +
//                    "' doesn't exist in RM.");
//            throw e;
//        }
//
//        if (appReport.getYarnApplicationState() == YarnApplicationState.FINISHED
//                || appReport.getYarnApplicationState() == YarnApplicationState.KILLED
//                || appReport.getYarnApplicationState() == YarnApplicationState.FAILED) {
//            sysout.println("Application " + applicationId + " has already finished ");
//        } else {
//            sysout.println("Killing application " + applicationId);
//            client.killApplication(appId);
//        }
//    }




    /**
     * 判断任务名为appName的任务，是否在yarn中运行，状态为RUNNING
     * @return boolean
     * @param appName
     * @return
     */
    public static boolean yarnIsContains(String appName) {
        Configuration conf = new YarnConfiguration();
        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(conf);
        yarnClient.start();
        boolean isContains = false;
        List<ApplicationReport> applications = new ArrayList<ApplicationReport>();
        try {
            //applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.RUNNING, YarnApplicationState.FINISHED));
            applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.FINISHED));
            for(ApplicationReport application:applications) {
                String name = application.getName();
                if(name.equals(appName)) {
                    System.out.println("ApplicationId ============> "+application.getApplicationId());
                    System.out.println("name ============> "+application.getName());
                    System.out.println("queue ============> "+application.getQueue());
                    System.out.println("queue ============> "+application.getUser());
                    System.out.println(applications);
                    isContains = true;
                }
            }

        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            yarnClient.stop();
        }
        return isContains;
    }
}