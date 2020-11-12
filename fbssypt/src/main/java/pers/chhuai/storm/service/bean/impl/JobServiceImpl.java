package pers.chhuai.storm.service.bean.impl;

import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.dao.JobDao;
import pers.chhuai.storm.dao.impl.SubDaoImpl;
import pers.chhuai.storm.service.bean.JobService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
@Service("jobService")
public class JobServiceImpl implements JobService {
    @Resource(name="subDao")
    private SubDaoImpl subDao;
    @Resource(name="txManager")
    private TransactionManager txManager;//事务控制变量

    @Override
    public List<Jobinfo> getJobs() {
//        try {
//            txManager.beginTransaction();
//            List<Jobinfo> jobs = JobDao.getJobs();
//            txManager.commit();
//            return jobs;
//        } catch (Exception e) {
//            txManager.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            txManager.release();
//        }
        return null;
    }

    @Override
    public List<Jobinfo> getJobs(String id) {
//        try {
//            txManager.beginTransaction();
//            List<Jobinfo> jobs = JobDao.getJobs(id);
//            txManager.commit();
//            return jobs;
//        } catch (Exception e) {
//            txManager.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            txManager.release();
//        }
        return null;
    }

    @Override
    public Jobinfo Jobs(String id, String testID, String testName, String jobname, String date, String state, String result) {
//        try {
//            txManager.beginTransaction();
//            Jobinfo job = JobDao.Jobs(id, testID, testName, jobname, date, state, result);
//            txManager.commit();
//            return job;
//        } catch (Exception e) {
//            txManager.rollback();
//            throw new RuntimeException(e);
//        } finally {
//            txManager.release();
//        }
        return null;
    }

    @Override
    public void updateState(Jobinfo job, String state, String result) {

    }

    @Override
    public void updateState(Jobinfo job, String state) {

    }

    @Override
    public List<Statistics> getTestResults(String classNo) {
        return null;
    }

    @Override
    public List<Statistics> getTestResults() {
        return null;
    }
}
