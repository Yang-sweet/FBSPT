package pers.chhuai.storm.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.dao.SubDao;
import pers.chhuai.storm.service.schedule.TaskMonitor;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Repository("subDao")
public class SubDaoImpl implements SubDao {
    @Resource(name="runner")
    private QueryRunner runner;
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;

    @Override
    public List<Submission> getSubmissions() {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from submission",
                    new BeanListHandler<Submission>(Submission.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Submission> getSubmissions(String id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(), "select * from submission where id = ? order by date desc",
                    new BeanListHandler<Submission>(Submission.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Submission subTest(String id, String testId, String testName, String uri, String state, String result,String jobname) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            long timestamp = System.currentTimeMillis();
            runner.update(connectionUtils.getThreadConnection(), "insert into submission values(?, ?, ?, ?, ?, ?, ?, ?,?)",
                    id, testId, testName, uri, timestamp, date, state, result,jobname);
            return new Submission(id, testId, testName, uri, timestamp, date, TaskMonitor.SUBMITTED_STATE, result,jobname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(Submission sub, String state, String result) {
        try {
            String id = sub.getId();
            String testId = sub.getTestID();
            long timestamp = sub.getTimestamp();
            runner.update(connectionUtils.getThreadConnection(),"update submission set state = ? , result = ? where id = ? and testID = ? and timestamp = ?",
                   state, result, id, testId, timestamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(Submission sub, String state) {
        try {
            String id = sub.getId();
            String testId = sub.getTestID();
            long timestamp = sub.getTimestamp();
            runner.update(connectionUtils.getThreadConnection(),"update submission set state = ? where id = ? and testID = ? and timestamp = ?",
                    state, id, testId, timestamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Statistics> getTestResults(String classNo) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select submission.id, name, testID, result from submission, user where " +
                            "user.id=submission.id and substring(submission.id, 1, 7) = ?",
                    new BeanListHandler<Statistics>(Statistics.class), classNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Statistics> getTestResults() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select submission.id, name, testID, result from submission, user where " +
                "user.id=submission.id",
                    new BeanListHandler<Statistics>(Statistics.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
