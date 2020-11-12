package pers.chhuai.storm.dao.impl;

import com.sun.xml.internal.xsom.impl.scd.Step;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.dao.TestDao;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    @Resource(name="runner")
    private QueryRunner runner;
    @Resource(name="connectionUtils")
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }
    @Override
    public List<Test> getTests() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from test",
                    new BeanListHandler<Test>(Test.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void getTestResults() {

    }
    @Override
    public Test  addTest(String testId, String title, String introduction, String input, String output, String url, String regex) {
        try {

            runner.update(connectionUtils.getThreadConnection(), "insert into test values(?, ?, ?, ?, ?, ?, ?)",
                    testId,title,introduction,input,output,url,regex);
            return new Test(testId, title,introduction,input,output,url,regex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTest(String testId) {

    }

    @Override
    public void deleteTest(String testId) {
        try {

            runner.update(connectionUtils.getThreadConnection(), "delete from test where testId=?",
                    testId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void createTest() {

    }

    @Override
    public int findTotalCount() {
        try {
            //return runner.query(connectionUtils.getThreadConnection(),"select cout* from test",new ScalarHandler());
            return ((Long)runner.query(connectionUtils.getThreadConnection(),"select cout(*) from test",new ScalarHandler())).intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Test> findByPage(int start, int rows) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from test limit ? , ?",
                    new BeanListHandler<Test>(Test.class),start,rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Test> selectTest(String testId, String testName) {
        return null;
    }

    @Override
    public List<Test> selectTest(Map<String, String[]> conditions) {
        try {
        String sql="select * from test where 1=1";
        StringBuilder sb=new StringBuilder(sql);
        Set<String> keySet=conditions.keySet();
        List<Object> params=new ArrayList<Object>();
        for (String key:keySet){
            String value=conditions.get(key)[0];
            System.out.println("value="+value);
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" = ? ");
                params.add(value);
                System.out.println("params="+params);
            }
        }
            System.out.println(sql);
            return runner.query(connectionUtils.getThreadConnection(),sb.toString(),
                    new BeanListHandler<Test>(Test.class),params.toArray());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




//    @Override
//    public List<Test> selectTest(String testId, String testName) {
//        try {
//            return runner.query(connectionUtils.getThreadConnection(),"select * from test where testId=? and title=?",
//                    new BeanListHandler<Test>(Test.class),testId,testName);
//
//         } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }


}
