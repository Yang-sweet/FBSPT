package pers.chhuai.storm.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;
import pers.chhuai.storm.beans.*;
import pers.chhuai.storm.dao.UserDao;
import pers.chhuai.storm.utils.ConnectionUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Resource(name="runner")
	private QueryRunner runner;
	@Resource(name="connectionUtils")
	private ConnectionUtils connectionUtils;


	public List<All> getAll(){
		try {
			return runner.query(connectionUtils.getThreadConnection(), "select * from user",
					new BeanListHandler<All>(All.class)); //将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Student> getStudents(){
		try {
			return runner.query(connectionUtils.getThreadConnection(), "select * from user where type = 1",
					new BeanListHandler<Student>(Student.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Student getStudentById(String id) {
		try {
			return runner.query(connectionUtils.getThreadConnection(),"select * from user where id = ?",
					new BeanHandler<Student>(Student.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Teacher> getTeachers(){
		try {
			return runner.query(connectionUtils.getThreadConnection(),"select * from user where type = 2",
					new BeanListHandler<Teacher>(Teacher.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Teacher getTeacherById(String id) {
		try {
			return runner.query(connectionUtils.getThreadConnection(),"select * from user where type = 2 and id = ?",
					new BeanHandler<Teacher>(Teacher.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Admin> getAdmins(){
		try {
			return runner.query(connectionUtils.getThreadConnection(),"select * from user where type = 3",
					new BeanListHandler<Admin>(Admin.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Admin getAdminById(String id) {
		try {
			return runner.query(connectionUtils.getThreadConnection(),"select * from user where type = 3 and id = ?",
					new BeanHandler<Admin>(Admin.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 用户登录
	 * @param id
	 * @param pwd
	 * @param type
	 * @return
	 */
	public User checkLoginDao(String id, String pwd, String type){
		try {
			if (type.equals("1")) {
				return runner.query(connectionUtils.getThreadConnection(),"select * from user where id = ? and pwd = ? and type = ?",
						new BeanHandler<Student>(Student.class),  id, pwd, type);
			} else if (type.equals("2")) {
				return runner.query(connectionUtils.getThreadConnection(),"select * from user where id = ? and pwd = ? and type = ?",
						new BeanHandler<Teacher>(Teacher.class),  id, pwd, type);
			} else {
				return runner.query(connectionUtils.getThreadConnection(),"select * from user where id = ? and pwd = ? and type = ?",
						new BeanHandler<Admin>(Admin.class),  id, pwd, type);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User addUser(String id, String pwd, String name, String gender, String major,  String type) {
		try {

			runner.update(connectionUtils.getThreadConnection(), "insert into user values(?, ?, ?, ?, ?, ?)",
					id,  pwd,  name,  gender,  major,  type);
			return new User(id,  pwd,  name,  gender,  major,  type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 更新密码
	 * @param id
	 * @param pwd
	 */
	public void updatePwd(String id, String pwd) {
		try {
			runner.update(connectionUtils.getThreadConnection(),"update user set pwd = ? where id = ?", pwd, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

