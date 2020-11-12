package pers.chhuai.storm.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.bean.JobService;
import pers.chhuai.storm.service.bean.SubmissionService;

public class Jobinfo {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static JobService ss = ac.getBean("jobService", JobService.class);

    private String testID;
    private String testName;
    private String jobid;
    private String jobname;
    private String id;
    private String date;
    private String state;
    private String result;

    public Jobinfo(){}

    public Jobinfo(String id, String testID, String testName,String jobname, String date, String state, String result) {
        this.id = id;
        this.testID = testID;
        this.testName = testName;
        this.jobname = jobname;
        this.date = date;
        this.state = state;
        this.result = result;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Jobinfo{" +
                "testID='" + testID + '\'' +
                ", testName='" + testName + '\'' +
                ", jobid='" + jobid + '\'' +
                ", jobname='" + jobname + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public void updateState(String state, String result) {
        this.setState(state);
        ss.updateState(this, state, result);
    }

    public void updateState(String state) {
        this.setState(state);
        ss.updateState(this, state);
    }
}
