package pers.chhuai.storm.service.bean.impl;

import org.springframework.stereotype.Service;
import pers.chhuai.storm.beans.*;
import pers.chhuai.storm.dao.impl.UserDaoImpl;
import pers.chhuai.storm.service.bean.UserService;
import pers.chhuai.storm.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name="userDao")
    private UserDaoImpl userDao;
    @Resource(name="txManager")
    private TransactionManager txManager;

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    @Override
    public List<All> getAll() {
        try {
            txManager.beginTransaction();
            List<All> all = userDao.getAll();
            txManager.commit();
            return all;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public User addUser(String id, String pwd, String name, String gender, String major,  String type) {
        try {
            txManager.beginTransaction();
            User user = userDao.addUser(id,  pwd,  name,  gender,  major,  type);
            txManager.commit();
            return user;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }
    @Override
    public List<Student> getStudents() {
        try {
            txManager.beginTransaction();
            List<Student> students = userDao.getStudents();
            txManager.commit();
            return students;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            txManager.beginTransaction();
            Student student = userDao.getStudentById(id);
            txManager.commit();
            return student;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        try {
            txManager.beginTransaction();
            List<Teacher> teachers = userDao.getTeachers();
            txManager.commit();
            return teachers;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public Teacher getTeacherById(String id) {
        try {
            txManager.beginTransaction();
            Teacher teacher = userDao.getTeacherById(id);
            txManager.commit();
            return teacher;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public List<Admin> getAdmins() {
        try {
            txManager.beginTransaction();
            List<Admin> admins = userDao.getAdmins();
            txManager.commit();
            return admins;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public Admin getAdminById(String id) {
        try {
            txManager.beginTransaction();
            Admin admin = userDao.getAdminById(id);
            txManager.commit();
            return admin;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public User checkLoginDao(String id, String pwd, String type) {
        try {
            txManager.beginTransaction();
            User user = userDao.checkLoginDao(id, pwd, type);
            txManager.commit();
            return user;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updatePwd(String id, String pwd) {
        try {
            txManager.beginTransaction();
            userDao.updatePwd(id, pwd);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void showSubmissions(String classNo) {

    }

    @Override
    public void showSubmissions() {

    }
}
