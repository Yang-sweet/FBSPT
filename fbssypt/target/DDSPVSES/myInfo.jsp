<%--
  Created by IntelliJ IDEA.
  User: 大娱乐家
  Date: 2020/4/19
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pers.chhuai.storm.beans.Student"%>
<%@page import="pers.chhuai.storm.beans.Teacher"%>
<%@page import="pers.chhuai.storm.beans.Admin"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    // 从session中获取登录对象
    String type = (String)request.getSession().getAttribute("type");
    User user = null;
    // 从session中获取登录对象
    if (type.equals("1")) {
        user = (Student)request.getSession().getAttribute("User");
    } else if (type.equals("2")) {
        user = (Teacher)request.getSession().getAttribute("User");
    } else {
        user = (Admin)request.getSession().getAttribute("User");
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title>分布式虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/information.css"/>
</head>
<body>
<div id="my_info">
    <div id="info_title">
        <font size="5px" color="#B0252A">用户信息</font><br><br>
        <hr width="80%">
    </div>
    <div class="info">
        用户：<%=user.getId() %>
    </div>
    <div id="line">
        <hr size="1">
    </div>
    <div class="info">
        姓名：<%=user.getName() %>
    </div>
    <div id="line">
        <hr size="1">
    </div>
    <div class="info">
        性别：<%=user.getGender() %>
    </div>
    <div id="line">
        <hr size="1">
    </div>
    <div class="info">
        专业：<%=user.getMajor() %>
    </div>
    <div id="line">
        <hr size="1">
    </div>
    <div class="info">
        类型：
        <%
            if (type.equals("1")) {%>
        本科生
        <%}
            else if (type.equals("2")) {%>
        教师
        <%} else if (type.equals("3")) {%>
        管理员
        <%}%>
    </div>
    <div id="line">
        <hr size="1">
    </div>
</div>
</body>
</html>
