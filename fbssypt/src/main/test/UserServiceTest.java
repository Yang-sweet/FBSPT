import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Admin;
import pers.chhuai.storm.beans.Student;
import pers.chhuai.storm.beans.Teacher;
import pers.chhuai.storm.beans.User;
import pers.chhuai.storm.service.bean.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceTest {
    @Test
    public void findAllStudents() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println();
        System.out.println("获取容器前："+df.format(new Date()));
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("获取业务层前："+df.format(new Date()));
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        System.out.println("查询前："+df.format(new Date()));
        List<Student> students = us.getStudents();
        System.out.println("查询后"+df.format(new Date()));
        for(Student stu : students) {
            System.out.println(stu);
        }
    }
    @Test
    public void findStudentById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        Student student = us.getStudentById("16030140021");
        System.out.println(student);
    }
    @Test
    public void findAllTeachers() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        List<Teacher> teachers = us.getTeachers();
        for(Teacher tch : teachers) {
            System.out.println(tch);
        }
    }
    @Test
    public void findTeacherById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        Teacher teacher = us.getTeacherById("1234");
        System.out.println(teacher);
    }
    @Test
    public void findAllAdmins() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        List<Admin> admins = us.getAdmins();
        for(Admin admin : admins) {
            System.out.println(admin);
        }
    }
    @Test
    public void findAdminById() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        Admin admin = us.getAdminById("4427");
        System.out.println(admin);
    }
    @Test
    public void checkLogin() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        Student stu = (Student)us.checkLoginDao("16030140021", "140021", "1");
        System.out.println(stu);
    }
    @Test
    public void updatePwd() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2.获取业务层对象
        UserService us = ac.getBean("userService", UserService.class);
        us.updatePwd("16030140021", "123456");
    }
}
