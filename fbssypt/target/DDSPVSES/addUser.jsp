<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/26
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.TestService" %>
<%@ page import="pers.chhuai.storm.service.bean.UserService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.beans.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <title>用户管理</title>
    <style type="text/css">
        p{
            align: center;
        }
        #user_add{margin:0 auto; margin-top: 50px; width:40%; height:550px; border: 1px white;}

    </style>
</head>
<script language="javascript">
    function check()
    {
        if(document.form1.id.value=="") {
            alert("请输入ID");
        document.form1.id.focus();return false;}
        if(document.form1.pwd.value=="") {alert("请输入密码");
        document.form1.pwd.focus();return false;}
        if(document.form1.name.value==""){alert("请输入姓名");
        document.form1.name.focus();return false;}
        alert("添加成功")
    }

</script>


<body>
<div id="user_add" >
    <form  action="allUser.jsp"  method="post" name="form1" onsubmit="check()">
    <table class="table  table-bordered" width="60%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="black" style="border-collapse:collapse">
        <tr><td  width="200">ID：</td>
            <td><input name='id' type='text' class="form-control" /></td></tr>
<%--            <td><input name='id' type='text'  style='border:solid 1px #000000; color:#666666' />&nbsp;</td></tr>--%>
        <tr><td  width="200">密码：</td>
            <td><input name='pwd' type='text' class="form-control"  />&nbsp;</td></tr>
        <tr><td  width="200">姓名：</td>
            <td><input name='name' type='text' class="form-control"/>&nbsp;</td></tr>
        <tr><td>性别：</td>
            <td><select name='gender' class="form-control" style="width: 200px">
                <option value="男">男</option>
                <option value="女">女</option>
            </select></td></tr>
        <tr><td  width="200">专业：</td>
            <td><input name='major' type='text'  class="form-control" /></td></tr>
        <tr><td>权限：</td>
            <td>
                <select  name='type' id='type' class="form-control" style="width: 200px">
                    <option value="学生">学生</option>
                    <option value="教师">教师</option>
                    <option value="教师">管理员</option>
                </select>

            </td></tr>
        <tr>
            <td>&nbsp;</td>
<%--            <td><input type="submit" name="Submit" value="提交" onsubmit="return check()" style='border:solid 1px #000000; color:#666666' />--%>
<%--                <input type="reset" name="Submit2" value="重置" style='border:solid 1px #000000; color:#666666' /></td>--%>
            <td><button type="submit" name="Submit" class="btn btn-default" onsubmit="return check()">提交</button>
                <button type="reset"  name="Submit2" class="btn btn-default">重置</button>
        </tr>
    </table>
</form>
</div>
</body>


</html>
