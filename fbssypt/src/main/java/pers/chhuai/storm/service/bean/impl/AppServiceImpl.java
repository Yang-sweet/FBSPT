package pers.chhuai.storm.service.bean.impl;

import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.dao.AppDao;
import pers.chhuai.storm.dao.impl.AppDaoImpl;
import pers.chhuai.storm.service.bean.AppService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("appService")

public class AppServiceImpl implements AppService {
    @Resource(name="appDao")
    private AppDaoImpl appDao;
    @Resource(name="txManager")
    private TransactionManager txManager;

    @Override
    public List<Application> getApplication() {
        try {
            txManager.beginTransaction();
            List<Application> applications = appDao.getApplication();
            txManager.commit();
            return applications;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }


    }
    @Override
    public List<Application> getapp(String ApplicationId) {
        try {
            txManager.beginTransaction();
            List<Application> applications = appDao.getapp(ApplicationId);
            txManager.commit();
            return applications;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }


    }
    @Override
    public void updateAppllication(String ApplicationId,  String State, String FinalStatus, long FinishTime) {
        try {
            txManager.beginTransaction();
            appDao.updateAppllication(ApplicationId,State,FinalStatus,FinishTime);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public void updateAppllication(String ApplicationId, String Name, String ApplicationType, String State, String FinalStatus, long FinishTime,String URL) {
        try {
            txManager.beginTransaction();
            appDao.updateAppllication(ApplicationId, Name,ApplicationType,State,FinalStatus,FinishTime,URL);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

//    @Override
//    public void updateAppllication() {
//
//    }

//    @Override
//    public void updateAppllication(String  Name,String ApplicationType,String State,String FinalStatus,String FinishTime) {
//        try {
//            txManager.beginTransaction();
//            appDao.updateAppllication(ApplicationType,State,FinalStatus,FinishTime,Name);
//            txManager.commit();
//        } catch (Exception e) {
//            txManager.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            txManager.release();
//        }
//    }


    @Override
    public void addApplication(String ApplicationId,String  Name,String ApplicationType,String State,String FinalStatus,long StartTime,long FinishTime,String URL,String id) {

        try {
            txManager.beginTransaction();
            appDao.addApplication(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime,URL,id);
            txManager.commit();
//            return test;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void deleteApplication() {

    }

    @Override
    public void updateApplication() {

    }

    @Override
    public List<Application> selectTask(Map<String, String[]> conditions) {
        try {
            txManager.beginTransaction();
            List<Application> tests = appDao.selectTask(conditions);
            txManager.commit();
            return tests;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }

    }


}
