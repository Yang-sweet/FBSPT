<%@ page language="java" pageEncoding="utf-8" import="java.util.*" %>
<%@page import="pers.chhuai.storm.beans.Student"%>
<%@page import="pers.chhuai.storm.beans.Teacher"%>
<%@page import="pers.chhuai.storm.beans.Admin"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.setContentType("text/html;charset=utf-8");
    request.setCharacterEncoding("utf-8");
    //从session中获取登录对象
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
    ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>分布式虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/testInfo.css"/>

    <style type="text/css">
        #header{
            width: 100%;
            height: 80px;
            background-image: url("./img/bg3.jpg");
            background-size: 100% 100%;
            background-repeat: no-repeat;

        }
        #header_logo{
            float:left;
            margin-top: 20px;
            margin-left: 70px;
            margin-bottom: 30px;}

        #nav{
            height: 50px;
            background-color: #3d3b3b;}

        #header_user{
            position: absolute;
            margin-top: 50px;
            right: 50px;}


        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #f1f1f1}

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }

    </style>


</head>
<body>


<div id="header">
    <div id="header_logo">
        <h1><font color="white">分布式虚拟仿真实验系统</font></h1>
    </div>
    <div id="header_user">
        <!-- 此处显示用户名 -->
        <font color="white"; size="3">当前用户：</font>
        <font color="white"; size="3"><%=user.getName()%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="loginPage.jsp"  >退出</a>
    </div>
</div>
<div id="nav">
    <div id="nav_test" class="dropdown">
                <a href="myTest.jsp" target="myIframe">仿真实验</a>
<%--    <a href="${pageContext.request.contextPath}/FindTestByPageServlet" target="myIframe">仿真实验</a>--%>
    <div class="dropdown-content">
            <div>
                <a href="myTest.jsp" target="myIframe">查询</a>
            </div>
            <div>
                <a href="addTest.jsp" target="myIframe">添加</a>
            </div>

        </div>
    </div>
    <div id="nav_sub" class="dropdown">
        <a href="mySubmission.jsp" target="myIframe">提交记录</a>
        <div class="dropdown-content">
            <div>
                <a href="myTest.jsp" target="myIframe">查询</a>
            </div>
            <div>
                <a href="addTest.jsp" target="myIframe">添加</a>
            </div>

        </div>
    </div>


    <% if (type.equals("2") || type.equals("3")) {%>
    <div id="nav_statistics">
        <a href="myStatistics.jsp" target="myIframe">成绩统计</a>
    </div>
    <%}%>
    <% if (type.equals("2") || type.equals("3")) {%>
    <div id="nav_monitor">
        <a href="myMonitor.jsp" target="myIframe">任务监控</a>

    </div>
    <%}%>
    <div id="nav_info" class="dropdown">
        <a href="myInfo.jsp" target="myIframe">用户管理</a>
        <div class="dropdown-content">
            <div>
                <a href="addUser.jsp" target="myIframe">添加用户</a>
            </div>
            <div>
                <a href="allUser.jsp" target="myIframe">所有用户</a>
            </div>
        </div>
    </div>
    <div id="nav_help" class="dropdown">
        <a href="myQuestion.jsp" target="myIframe">系统管理</a>
        <div class="dropdown-content">
            <div>
                <a href="myInfo.jsp" target="myIframe">个人信息</a>
            </div>
            <div>
                <a href="up_pwd.jsp" target="myIframe">修改密码</a>
            </div>
            <div>
                <a href="loginPage.jsp" target="myIframe">退出</a>
            </div>
        </div>
    </div>
</div>
<iframe name="myIframe" src="myQuestion.jsp" width="100%" height="800px"></iframe>
</body>
</html>
