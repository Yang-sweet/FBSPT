<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/23
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.TestService" %>
<%@ page import="pers.chhuai.storm.service.bean.UserService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.beans.*" %>
<%@page import="pers.chhuai.storm.beans.User"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    User user = (User)session.getAttribute("User");

    // 1.获取容器
    ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
    // 2.获取业务层对象
    UserService ss = ac.getBean("userService", UserService.class);
    //User user = (User)session.getAttribute("User");
   // List<User> userList = (List<User>)request.getSession().getAttribute("user_add");
    String user_ID = request.getParameter("id");
    String user_pwd = request.getParameter("pwd");
    String user_name = request.getParameter("name");
    String user_gender= request.getParameter("gender");
    String user_major = request.getParameter("major");
    String  user_type= request.getParameter("type");
    ss.addUser(user_ID,user_pwd,user_name,user_gender,user_major,"1");

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<p>添加成功</p>

</body>
</html>
