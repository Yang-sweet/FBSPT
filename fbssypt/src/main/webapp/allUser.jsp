<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/28
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.TestService" %>
<%@ page import="pers.chhuai.storm.service.bean.UserService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.beans.*" %>
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
    List<Student> userlist = ss.getStudents();
    session.setAttribute("userlist", userlist);
    List<Admin> admin_list = ss.getAdmins();
    session.setAttribute("admin_list", admin_list);
    List<Teacher> tea_list = ss.getTeachers();
    session.setAttribute("tea_list", tea_list);
if(request.getParameter("id")!=null){
    String user_ID = request.getParameter("id");
    String user_pwd = request.getParameter("pwd");
    String user_name = request.getParameter("name");
    String user_gender= request.getParameter("gender");
    String user_major = request.getParameter("major");
    String  user_type= request.getParameter("type");
    ss.addUser(user_ID,user_pwd,user_name,user_gender,user_major,"1");}
%>
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

    <title>所有用户</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/test.css"/>

    <style type="text/css">

        input[type=submit]{
            text-decoration: underline;
            text-align: center;
            margin: 0 auto;
            margin-top: 7px;
            background-color:white;
            color: #985f0d;
            border: #706969;
            cursor: pointer;
            size: 10px}
        input[type=text]{
            border:solid 1px #000000;
            color:#666666;
            size: 12px;
            height: 20px;
            width: 100px;
        }
        #operate{

        }
        .test_table{
            height: 30px;
            text-align: center;
        }
        #click{
            text-align: center;
            margin-top: 5px;
            margin-left: 60px;
            width: 60%; height: 30px;
            line-height: 10px;
            background-color: white;
            color: white;
            border-radius: 5%;
        }
        #user_list{
            text-align: center;
        }
        #enter{
            text-align: left;
            width: 30%}
    </style>
</head>
<body>
<div id="test">
<%--    <form class="form-inline">--%>

<%--        <div class="form-group">--%>
<%--            <label for="input" class="sr-only">Password</label>--%>
<%--            <input type="text" class="form-control" id="input" placeholder="id">--%>
<%--        </div>--%>
<%--        <button type="submit" class="btn btn-default">查询</button>--%>
<%--    </form>--%>

<%--    <p>所有用户信息：</p><br>--%>
<%--    <form name="form1" id="form1" method="post" action="">--%>
<%--        搜索:  id：<input name="bianhao" type="text" id="bianhao" />  姓名：<input name="timu" type="text" id="timu" />--%>
<%--        <input type="submit" name="Submit" value="查找" style='border:solid 1px #000000; color:#666666' />--%>
<%--    </form><br>--%>
    <table class="table table-hover table-bordered" >
        <tr class="text-center">
            <th class="text-center">id</th>
            <th class="text-center">密码</th>
            <th class="text-center">姓名</th>
            <th class="text-center">性别</th>
            <th class="text-center">专业</th>
            <th class="text-center">权限</th>
            <th class="text-center">操作</th>
        </tr>
        <% for (User user1 : userlist) { %>
        <tr id="user_list">
            <td id = "userID" style="text-align: center"><%=user1.getId() %></td>
            <td id = "user_pwd" ><%=user1.getPwd() %></td>
            <td id = "user_name" ><%=user1.getName() %></td>
            <td id = "user_gender" ><%=user1.getGender() %></td>
            <td id = "user_major" ><%=user1.getMajor() %></td>
            <td id = "user_type" ><%=user1.getType() %></td>
            <td id = "enter">
                <div id="click">
                    <form action='testInfo.jsp' method='post'>
<%--                        <input type='submit' name = 'testID' value=修改>--%>
<%--                        <input type='submit' name = 'delete' value=删除>--%>

                        <button type="submit"  class="btn btn-primary">修改</button>
                        <button type="submit" class="btn btn-danger">删除</button>
                    </form>
                </div>


            </td>


        </tr>
        <%}
        %>
        <% for (User user1 : admin_list) { %>
        <tr id="user_list">
            <td id = "adminID" style="text-align: center"><%=user1.getId() %></td>
            <td id = "admin_pwd" ><%=user1.getPwd() %></td>
            <td id = "admin_name" ><%=user1.getName() %></td>
            <td id = "admin_gender" ><%=user1.getGender() %></td>
            <td id = "admin_major" ><%=user1.getMajor() %></td>
            <td id = "admin_type" ><%=user1.getType() %></td>
            <td id = "adminenter">
                <div id="adminclick">
                    <form action='testInfo.jsp' method='post'>
                        <input type='submit' name = 'testID' value=修改>
                        <input type='submit' name = 'delete' value=删除>
                    </form>
                </div>


            </td>


        </tr>
        <%}
        %>
        <% for (User user1 : tea_list) { %>
        <tr id="user_list">
            <td id = "teaID" style="text-align: center"><%=user1.getId() %></td>
            <td id = "tea_pwd" ><%=user1.getPwd() %></td>
            <td id = "tea_name" ><%=user1.getName() %></td>
            <td id = "tea_gender" ><%=user1.getGender() %></td>
            <td id = "tea_major" ><%=user1.getMajor() %></td>
            <td id = "tea_type" ><%=user1.getType() %></td>
            <td id = "teaenter">
                <div id="teaclick">
                    <form action='testInfo.jsp' method='post'>
                        <input type='submit' name = 'testID' value=修改>
                        <input type='submit' name = 'delete' value=删除>
                    </form>
                </div>


            </td>


        </tr>
        <%}
        %>
    </table>
</div>
</body>
</html>
