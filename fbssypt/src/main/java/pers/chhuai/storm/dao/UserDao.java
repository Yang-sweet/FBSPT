package pers.chhuai.storm.dao;


import pers.chhuai.storm.beans.*;

import java.util.List;

public interface UserDao {
	/**
	 * 获取所有学生信息
	 * @return
	 */
	public List<All> getAll();
	public List<Student> getStudents();
	public User addUser(String id, String pwd, String name, String gender, String major,  String type);
	/**
	 * 根据学号获取学生信息
	 * @param id
	 * @return
	 */
	public Student getStudentById(String id);
	/**
	 * 获取所有教师信息
	 * @return
	 */
	public List<Teacher> getTeachers();

	/**
	 * 根据工号获取教师信息
	 * @param id
	 * @return
	 */
	public Teacher getTeacherById(String id);
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	public List<Admin> getAdmins();

	/**
	 * 根据工号获得管理员信息
	 * @param id
	 * @return
	 */
	public Admin getAdminById(String id);

	/**
	 * 用户登录
	 * @param id
	 * @param pwd
	 * @param type
	 * @return
	 */
	public User checkLoginDao(String id, String pwd, String type);

	/**
	 * 更新密码
	 * @param id
	 * @param pwd
	 */
	public void updatePwd(String id, String pwd);



}
