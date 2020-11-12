package pers.chhuai.storm.beans;

import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

public class Application {
    private String ApplicationId;
    private String  Name;
    private String ApplicationType;
    private String State;
    private String FinalStatus;
    private long StartTime;
    private long FinishTime;
    private String URL;
    private String id;

    public Application(String ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long StartTime,long FinishTime,String URL,String id){
        this.ApplicationId= ApplicationId;
        this.Name=Name;
        this.ApplicationType=ApplicationType;
        this.State=State;
        this.FinalStatus=FinalStatus;
        this.StartTime=StartTime;
        this.FinishTime=FinishTime;
        this.URL=URL;
        this.id=id;
    }
    public Application(){}
    @Override
    public String toString() {
        return "Application{" +
                "ApplicationId='" + ApplicationId + '\'' +
                ", Name='" + Name + '\'' +
                ", ApplicationType='" + ApplicationType + '\'' +
                ", State='" + State + '\'' +
                ", FinalStatus='" + FinalStatus + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", FinishTime='" + FinishTime + '\'' +
                ", URL='" + URL + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getURL() {

        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(String applicationType) {
        ApplicationType = applicationType;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFinalStatus() {
        return FinalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        FinalStatus = finalStatus;
    }

    public long getStartTime() {
        return StartTime;
    }

    public void setStartTime(long startTime) {
        StartTime = startTime;
    }

    public long getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(long finishTime) {
        FinishTime = finishTime;
    }
}
