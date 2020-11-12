package pers.chhuai.storm.dao;


import pers.chhuai.storm.beans.Application;

import java.util.List;
import java.util.Map;

public interface AppDao {

    public List<Application> getApplication();

    List<Application> getapp(String ApplicationId);

    public void updateAppllication(String  ApplicationId, String State, String FinalStatus, long FinishTime);
    public void updateAppllication(String  ApplicationId, String  Name, String ApplicationType, String State, String FinalStatus, long FinishTime, String URL);

    public void addApplication(String ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long StartTime,long FinishTime,String URL,String id);

    public void updateApplication();

    public  void deleteApplication();

    List<Application> selectTask(Map<String, String[]> conditions);



}
