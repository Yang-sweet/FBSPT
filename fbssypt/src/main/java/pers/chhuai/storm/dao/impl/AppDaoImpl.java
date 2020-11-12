package pers.chhuai.storm.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.dao.AppDao;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository("appDao")
public class AppDaoImpl implements AppDao {
    @Resource(name="runner")
    private QueryRunner runner;
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Application> getApplication() {

        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from application",
                    new BeanListHandler<Application>(Application.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Application> getapp(String ApplicationId) {

        try {
            return runner.query(connectionUtils.getThreadConnection(),"select count(*) from application where ApplicationId=?",
                    new BeanListHandler<Application>(Application.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateAppllication(String ApplicationId,  String State, String FinalStatus, long FinishTime) {
        try {

            runner.update(connectionUtils.getThreadConnection(), "update application set State=?,FinalStatus=?,FinishTime=? where ApplicationId=? ",
                    State,FinalStatus,FinishTime,ApplicationId
            );
            //return new Application(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAppllication(String ApplicationId, String Name, String ApplicationType, String State, String FinalStatus, long FinishTime,String URL) {
        try {

            runner.update(connectionUtils.getThreadConnection(), "update application set ApplicationType=?,State=?,FinalStatus=?,FinishTime=?,URL=? where ApplicationId=? and Name=? ",
                    ApplicationType,State,FinalStatus,FinishTime,URL,ApplicationId,Name
            );
            //return new Application(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void updateAppllication() {
//
//    }

    @Override
    public void addApplication(String ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long StartTime,long FinishTime,String URL,String id) {
        try {

            runner.update(connectionUtils.getThreadConnection(), "insert into application values(?, ?, ?, ?, ?, ?, ?,?,?)",
                    ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime,URL,id
            );
//            return new Application(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateApplication() {

    }

//    @Override
//    public void updateApplication(String  ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long FinishTime) {
//        try {
//
//            runner.update(connectionUtils.getThreadConnection(), "update application set ApplicationType=?,State=?,FinalStatus=?,FinishTime=? where ApplicationId=? and Name=? ",
//                    ApplicationType,State,FinalStatus,FinishTime,ApplicationId,Name
//            );
//           //return new Application(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }


    @Override
    public void deleteApplication() {

    }

    @Override
    public List<Application> selectTask(Map<String, String[]> conditions) {
        try {
            String sql="select * from application where 1=1";
            StringBuilder sb=new StringBuilder(sql);
            Set<String> keySet=conditions.keySet();
            List<Object> params=new ArrayList<Object>();
            for (String key:keySet){
                String value=conditions.get(key)[0];
                System.out.println("value="+value);
                if(value!=null&&!"".equals(value)){
                    sb.append(" and "+key+" = ? ");
                    params.add(value);
                }
            }
            System.out.println(sql);
            return runner.query(connectionUtils.getThreadConnection(),sb.toString(),
                    new BeanListHandler<Application>(Application.class),params.toArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
