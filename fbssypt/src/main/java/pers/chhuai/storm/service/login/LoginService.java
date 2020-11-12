package pers.chhuai.storm.service.login;

import pers.chhuai.storm.beans.User;

public interface LoginService {
	// 登录
	User checkLoginService(String id, String pwd, String type);
}
