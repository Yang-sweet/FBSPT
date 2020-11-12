package pers.chhuai.storm.service.bean.impl;

import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.dao.impl.SubDaoImpl;
import pers.chhuai.storm.service.bean.SubmissionService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
@Service("submissionService")
public class SubmissionServiceImpl  implements SubmissionService {
    @Resource(name="subDao")
    private SubDaoImpl subDao;
    @Resource(name="txManager")
    private TransactionManager txManager;//事务控制变量


    @Override
    public List<Submission> getSubmissions() {
        try {
            txManager.beginTransaction();
            List<Submission> submissions = subDao.getSubmissions();
            txManager.commit();
            return submissions;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Submission> getSubmissions(String id) {
        try {
            txManager.beginTransaction();
            List<Submission> submissions = subDao.getSubmissions(id);
            txManager.commit();
            return submissions;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public Submission subTest(String id, String testId, String testName, String uRI, String state, String result,String jobname) {
        try {
            txManager.beginTransaction();
            Submission submission = subDao.subTest(id, testId, testName, uRI, state, result,jobname);
            txManager.commit();
            return submission;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updateState(Submission sub, String state, String result) {
        try {
            txManager.beginTransaction();
            subDao.updateState(sub, state, result);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updateState(Submission sub, String state) {
        try {
            txManager.beginTransaction();
            subDao.updateState(sub, state);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Statistics> getSubResults(String classNo) {
        try {
            txManager.beginTransaction();
            List<Statistics> stas = subDao.getTestResults(classNo);
            txManager.commit();
            return stas;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Statistics> getSubResults() {
        try {
            txManager.beginTransaction();
            List<Statistics> stas = subDao.getTestResults();
            txManager.commit();
            return stas;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
}
