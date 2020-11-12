package pers.chhuai.storm.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.dao.JobDao;
import pers.chhuai.storm.service.schedule.TaskMonitor;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Repository("jobDao")
public class JobDaoImpl implements JobDao {


    @Resource(name="runner")
    private QueryRunner runner;
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;
    @Override
    public List<Jobinfo> getJobs() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from jobinfo",
                    new BeanListHandler<Jobinfo>(Jobinfo.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Jobinfo> getJobs(String id) {

        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from jobinfo where id = ? order by date desc",
                    new BeanListHandler<Jobinfo>(Jobinfo.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Jobinfo Jobs(String id, String testID, String testName, String jobname, String date, String state, String result) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
             date = df.format(new Date());
            runner.update(connectionUtils.getThreadConnection(), "insert into jobinfo values(?, ?, ?, ?, ?, ?, ?)",
                    testID, testName, jobname, date, state, result,id);
            return new Jobinfo(testID, testName, jobname, date, TaskMonitor.SUBMITTED_STATE, result,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(Jobinfo job, String state, String result) {
        try {
            String id = job.getId();
            String testId = job.getTestID();
            runner.update(connectionUtils.getThreadConnection(),"update submission set state = ? , result = ? where id = ? and testID = ? ",
                    state, result, id, testId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(Jobinfo job, String state) {
        try {
            String id = job.getId();
            String testId = job.getTestID();

            runner.update(connectionUtils.getThreadConnection(),"update submission set state = ? where id = ? and testID = ? ",
                    state, id, testId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
