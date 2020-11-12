package pers.chhuai.storm.service.bean;

import pers.chhuai.storm.beans.Page;
import pers.chhuai.storm.beans.Test;

import java.util.List;
import java.util.Map;

public interface TestService {
    // 获取所有测试信息
    public List<Test> getTests();

    public Test addTest(String testId, String title, String introduction, String input, String output, String url, String regex);
    /**
     * 修改测试题
     * 教师、管理员使用
     * @param testId
     */
    public void updateTest(String testId);

    /**
     * 删除测试题
     * 教师、管理员使用
     * @param testId
     */
    public void deleteTest(String testId);

    public List<Test> selectTest(String testId,String testName);

    /**
     * 添加测试题
     */
    public void createTest();

    /**
     * 批量删除
     * @param seids
     */
    void deleteSelectTest(String[] seids);

    /**
     * 分页查询
     * @param currentPge
     * @param rows
     * @return
     */
    Page<Test> findTestByPage(String currentPge, String rows);

    List<Test> selectTest(Map<String, String[]> conditions);
}
