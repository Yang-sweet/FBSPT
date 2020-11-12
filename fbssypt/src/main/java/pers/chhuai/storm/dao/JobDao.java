package pers.chhuai.storm.dao;

import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;

import java.util.List;

public interface JobDao {
    /**
     * 获取所有任务信息
     * @return
     */
    public List<Jobinfo> getJobs();

    public List<Jobinfo> getJobs(String id);

    /**
     * 上传信息
     * @param id
     * @param testID
     * @param testName
     * @param state
     */
    public Jobinfo Jobs(String id, String testID, String testName, String jobname, String date, String state, String result);

    /**
     * 更新任务状态
     * @param job
     * @param state
     * @param result
     */
    public void updateState(Jobinfo job, String state, String result);

    /**
     * 更新任务状态
     * @param job
     * @param state
     */
    public void updateState(Jobinfo job, String state);

    /**
     * 根据班级显统计学生作业完成情况
     * 教师使用
     *
     * @param classNo
     */
    public List<Statistics> getTestResults(String classNo);


    /**
     * 统计所有学生所有测试的完成情况
     * 管理员使用
     */
    public List<Statistics> getTestResults();
}
