package pers.chhuai.storm.service.bean.impl;

import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Page;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.dao.TestDao;
import pers.chhuai.storm.dao.impl.TestDaoImpl;
import pers.chhuai.storm.service.bean.TestService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Resource(name="testDao")
    private TestDaoImpl testDao;
    @Resource(name="txManager")
    private TransactionManager txManager;

    @Override
    public List<Test> getTests() {
        try {
            txManager.beginTransaction();
            List<Test> tests = testDao.getTests();
            txManager.commit();
            return tests;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public Test addTest(String testId, String title, String introduction, String input, String output, String url, String regex) {
        //testDao.updateTest(testId);
        try {
            txManager.beginTransaction();
            Test test = testDao.addTest(testId,title,introduction,input,output,url,regex);
            txManager.commit();
            return test;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updateTest(String testId) {
        //testDao.updateTest(testId);
    }

    @Override
    public void deleteTest(String testId) {
        //testDao.deleteTest(testId);

        try {
            txManager.beginTransaction();
            testDao.deleteTest(testId);
            txManager.commit();

        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public List<Test> selectTest(String testId,String testName) {
        //testDao.deleteTest(testId);

        try {
            txManager.beginTransaction();
            List<Test> tests = testDao.selectTest(testId,testName);
            txManager.commit();
            return tests;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public void createTest() {
        //testDao.createTest();
    }

    @Override
    public void deleteSelectTest(String[] seids) {
        //遍历数组
        for(String seid:seids){
            testDao.deleteTest(seid);
        }
    }

    @Override
    public Page<Test> findTestByPage(String _currentPge, String _rows) {
        int currentPage=Integer.parseInt(_currentPge);
        int rows=Integer.parseInt(_rows);
        Page<Test> pb=new Page<Test>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount=testDao.findTotalCount();
        pb.setTotalCount(totalCount);
        int start=(currentPage-1)*rows;

        List<Test> list=testDao.findByPage(start,rows);
        pb.setList(list);

        int totalPage=totalCount%rows == 0 ? totalCount%rows :totalCount%rows+1;
        pb.setTotalPage(totalPage);
        return null;
    }

    @Override
    public List<Test> selectTest(Map<String, String[]> conditions) {
        try {
            txManager.beginTransaction();
            List<Test> tests = testDao.selectTest(conditions);
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
