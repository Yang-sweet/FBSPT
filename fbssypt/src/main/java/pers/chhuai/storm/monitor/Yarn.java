package pers.chhuai.storm.monitor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Yarn {

    private static YarnClient client = YarnClient.createYarnClient();
    static{
        Configuration conf = new Configuration();
        client.init(conf);
        client.start();
    }

    /**
     * 获取任务的applicationId
     * @return String
     * @param jobName
     * @return
     */
    public static String getAppId(String jobName) {

        List<ApplicationReport> appsReport = null;
        try {
            appsReport = client.getApplications();
            assert appsReport != null;
            for (ApplicationReport appReport : appsReport) {

                if (appReport.getName().equals(jobName)) {
                    return appReport.getApplicationId().toString();
                }
            }

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
        return null;
    }



    /**
     * 根据任务的applicationId去获取任务的状态
     * @return YarnApplicationState
     * @param appId
     * @return
     */
    @Test
    public static YarnApplicationState getState(String appId) {

        ApplicationId applicationId = ApplicationId.fromString(appId);
        System.out.println(applicationId);
        //System.out.println(applicationId);
        //	        ApplicationId appId = ConverterUtils.toApplicationId(appId);
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

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yarnApplicationState;
    }

    /**
     * 杀死任务
     * @param applicationId
     * @return
     * @throws IOException
     * @throws YarnException
     */
    public static boolean killApplication(ApplicationId applicationId) throws IOException, YarnException {

        try {
            client.killApplication(applicationId);
        } catch (YarnException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        Boolean result=yarnIsContains("mr_wordcount");


    }

    /**
     * 判断任务名为appName的任务，是否在yarn中运行，状态为RUNNING
     * @return boolean
     * @param appName
     * @return
     */
    public static boolean yarnIsContains(String appName) {

        boolean isContains = false;
        List<ApplicationReport> applications = new ArrayList<ApplicationReport>();
        try {
            //applications = yarnClient.getApplications(EnumSet.of(YarnApplicationState.RUNNING, YarnApplicationState.FINISHED));
            applications = client.getApplications(EnumSet.of(YarnApplicationState.KILLED));
            for(ApplicationReport application:applications) {
                String name = application.getName();
                if(name.equals(appName)) {
                    System.out.println("ApplicationId ============> "+application.getApplicationId());
                    System.out.println("name ============> "+application.getName());

                    isContains = true;
                }
            }

        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.stop();
        }
        return isContains;
    }
}