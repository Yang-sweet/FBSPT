package pers.chhuai.storm.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.dao.StaDao;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.util.List;
@Repository("staDao")
public class StaDaoImpl implements StaDao {
    @Resource(name="runner")
    private QueryRunner runner;
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;

    @Override
    public List<Statistics> getAllSta() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select statistics.id, name, count(statistics.id) finished from statistics, user\n" +
                            "where statistics.id = user.id group by id",
                    new BeanListHandler<Statistics>(Statistics.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Statistics> getStaById(String id) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select statistics.id, name, count(statistics.id) finished from statistics, user\n" +
                            "where statistics.id = user.id and user.id = ? group by id",
                    new BeanListHandler<Statistics>(Statistics.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Statistics> getStaByClsId(String classId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from (select statistics.id, name, count(statistics.id) finished from statistics, user " +
                            "where statistics.id = user.id group by id) as a where substring(id, 1, 7)= ?",
                    new BeanListHandler<Statistics>(Statistics.class), classId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSta(String id, String testId) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"insert into statistics values(?, ?)",
                   id, testId);
        } catch (Exception e) {
            throw new RuntimeException("已通过测试，更新数据库失败");
        }
    }
}
