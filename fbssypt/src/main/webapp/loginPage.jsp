
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    // 提示用户名或密码错误
    String str = (String)request.getAttribute("str")==null?"":(String)request.getAttribute("str");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title>分布式虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
    <style type="text/css">
        body{
            background-image: url("./img/bg4.jpg");
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }
        #login{
            position: absolute;
            text-align: center;
            margin-top: 200px;
            margin-left: 30%;
            width: 350px;
            height: 250px;
            border: solid 3px;
            border-radius: 1%;
            color: #f7eeee;
            padding: 20px;
            z-index: 20;
            background: #35495b;

        }
        input[type=submit]{
            width: 150px;
            height: 30px;
            background-color: #e8a474b0;
            color: #FFFFFF;
            border: solid 1px #e8a474b0;}

    </style>

</head>
<body>
<%--<div id="logo">
    <font size="7px" color="#FFFFFF" >分布式虚拟仿真实验系统</font>
</div>--%>

<div id="login" >

    <font size="6px" color="#f7eeee" >Login</font>
    <hr size="2px" color="#f7eeee" width="250px">
    <div id="login_div">
        <form id="login_form" action="Login" method="get">
            <div id="uname">
                用户名：<input type="text" name="uname" id=" " value="" />
            </div>
            <div id="pwd">
                密码：<input type="password" name="pwd" id="" value="" />
            </div>
            <%=str %>
            <div id="user_type">
                学生<input type="radio" name="type" value="1" checked="checked"/>
                教师<input type="radio" name="type" value="2" />
                管理员<input type="radio" name="type" value="3" />
            </div>
            <input type="submit" value="登录"/>
        </form>
    </div>
</div>
</body>
</html>
