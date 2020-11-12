package pers.chhuai.storm.service.login.impl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.User;
import pers.chhuai.storm.service.bean.UserService;
import pers.chhuai.storm.service.login.LoginService;

public class LoginServiceImpl implements LoginService {
	public User checkLoginService(String id, String pwd, String type) {
		// 1.获取容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 2.获取业务层对象
		UserService us = ac.getBean("userService", UserService.class); //反射类
		return (User)us.checkLoginDao(id, pwd, type);
	}
}
