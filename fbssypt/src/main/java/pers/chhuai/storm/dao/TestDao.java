package pers.chhuai.storm.dao;

import pers.chhuai.storm.beans.Test;

import java.util.List;
import java.util.Map;


public interface TestDao {
	// 获取所有测试信息
	public List<Test> getTests();


	/**
	 * 统计所有学生所有测试的完成情况
	 * 管理员使用
	 */
	public void getTestResults();

	/**
	 * 修改测试题
	 * 教师、管理员使用
	 * @param testId
	 * @return
	 */

	public Test addTest(String testId, String title, String introduction, String input, String output, String url, String regex);


	public void updateTest(String testId);


	/**
	 * 删除测试题
	 * 教师、管理员使用
	 * @param testId
	 */
	public void deleteTest(String testId);

	/**
	 * 添加测试题
	 */
	public void createTest();

    int findTotalCount();

	List<Test> findByPage(int start, int rows);

	List<Test> selectTest(String testId,String testName);

	List<Test> selectTest(Map<String, String[]> conditions);

}
