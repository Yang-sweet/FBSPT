package pers.chhuai.storm.dao;

import pers.chhuai.storm.beans.Statistics;
import pers.chhuai.storm.beans.Submission;

import java.util.List;



public interface SubDao {
	/**
	 * 获取所有任务信息
	 * @return
	 */
	public List<Submission> getSubmissions();
	
	public List<Submission> getSubmissions(String Sno);
	
	/**
	 * 上传信息
	 * @param Sno
	 * @param title
	 * @param url
	 * @param state
	 */
	public Submission subTest(String sno, String testID, String testName, String uRI, String state,
                              String result,String jobname);

	/**
	 * 更新任务状态
	 * @param sub
	 * @param state
	 * @param result
	 */
	public void updateState(Submission sub, String state, String result);

	/**
	 * 更新任务状态
	 * @param sub
	 * @param state
	 */
	public void updateState(Submission sub, String state);

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
