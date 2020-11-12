<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/28
  Time: 19:17
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
    TestService ss = ac.getBean("testService", TestService.class);
    String testID=request.getParameter("testID");
    String title=request.getParameter("title");
    String introduction=request.getParameter("introduction");
    String input=request.getParameter("input");
    String output=request.getParameter("output");
    //String url=request.getParameter("url");
    //String regex=request.getParameter("regex");
    ss.addTest(testID,title,introduction,input,output,"hdfs://localhost:9000","[\\w]*:[\\d]*");

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
aoifoisgsiobnb
</body>
</html>
