package pers.chhuai.storm.service.bean;

import pers.chhuai.storm.beans.Application;

import java.util.List;
import java.util.Map;

public interface AppService {

    public List<Application> getApplication();

    void updateAppllication(String ApplicationId, String State, String FinalStatus, long FinishTime);

    public void updateAppllication(String  ApplicationId, String  Name, String ApplicationType, String State, String FinalStatus, long FinishTime, String URL);


    public void addApplication(String ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long StartTime,long FinishTime,String URL,String id);

    public  void deleteApplication();
    public  void updateApplication();
    List<Application> getapp(String ApplicationId);


    List<Application> selectTask(Map<String, String[]> conditions);

}
