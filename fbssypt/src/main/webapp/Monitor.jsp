<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/10/23
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pers.chhuai.storm.beans.Submission"%>
<%@page import="pers.chhuai.storm.beans.Student"%>
<%@page import="pers.chhuai.storm.beans.Teacher"%>
<%@page import="pers.chhuai.storm.beans.Admin"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%@ page import="org.springframework.context.ApplicationContext" %>

<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.SubmissionService" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

    // 1.获取容器
    ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
    // 2.获取业务层对象
//    AppService ss = ac.getBean("appService2", AppService.class);
    AppService ss = ac.getBean("appService", AppService.class);
//    ss.addApplication("test","test","test","test","test","2020-06-03 01:08:23","2020-06-03 01:08:23");

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pers.chhuai.storm.service.schedule.TaskList" %>
<%@ page import="pers.chhuai.storm.beans.Application" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.chhuai.storm.service.bean.AppService" %>
<%@ page import="pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-inverse" >
    <div class="container-fluid"  >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"  style="text-align: center;">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">仿真实验 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">查询</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">添加</a></li>
                    </ul>
                </li>
                <li><a href="#">提交记录</a></li>
                <li><a href="#">任务监控</a></li>
                <li><a href="#">成绩统计</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">添加用户</a></li>

                        <li><a href="#">所有用户</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人信息</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">退出系统</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">当前用户：李老师</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<form class="navbar-form navbar-left">
    搜索:  任务状态:&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
    </div>
    <button type="submit" class="btn btn-default">查询</button>
</form>

<div id="test">
    <div id="submission">
        <form name="form1" id="form1" method="post" action="myTest.jsp">

            <select>
                <option value ="succeeded">SUCCEEDED</option>
                <option value ="failed">FAILED</option>

            </select>&nbsp;&nbsp;&nbsp;
            <input type="submit" name="Submit" value="查询" style='border:solid 1px #000000; color:#666666' />
        </form>
        <br>
        <form action="KillTask" method="post">
            <table class="table table-hover">
                <tr>
                    <th>任务ID</th>
                    <th>任务名称</th>
                    <th>任务类型</th>
                    <th>任务状态</th>
                    <th>最终状态</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>历史日志</th>
                    <th>提交人</th>
                    <th>操作 </th>

                        <%
            List<Application> apps= ss.getApplication();
            for (Application app :apps) {
        %>
                <tr >
                    <td id = "ApplicationId"><%=app.getApplicationId() %></td>
                    <td id = "Name"><%=app.getName() %></td>
                    <td id = "ApplicationType"><%=app.getApplicationType() %></td>
                    <td id = "State"><%=app.getState() %></td>
                    <td id = "FinalStatus"><%=app.getFinalStatus() %></td>
                    <td id = "StartTime"><%=new Date(app.getStartTime()) %></td>
                    <td id = "FinishTime"><%=new Date(app.getFinishTime()) %></td>
                    <%--            <td id = "URL"><%=app.getURL()%></td>--%>
                    <td id = "URL"> <a href="<%=app.getURL()%>" style="color: black;text-decoration:underline">history</a></td>
                    <td id = "id"><%=app.getId()%></td>
                    <%--            <input type='submit' name = 'delete' value=删除>--%>
                    <%--            <td><button onclick="Delete()" >kill</button></td>--%>
                    <td><input type="submit" value="kill"></td>
                </tr>
                <%} %>

            </table>
        </form>
    </div>




    <form>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="exampleInputFile">File input</label>
            <input type="file" id="exampleInputFile">
            <p class="help-block">Example block-level help text here.</p>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox"> Check me out
            </label>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

<a class="btn btn-default" href="#" role="button">Link</a>
<button class="btn btn-default" type="submit">Button</button>
<input class="btn btn-default" type="button" value="Input">
<input class="btn btn-default" type="submit" value="Submit">




<!--分页-->
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li class="active"><a href="#">1</a></li>    <!--active 激活 disable禁用-->
        <li class="disabled"><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>


<!--轮播图-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="img/01.jpeg" alt="...">
        </div>
        <div class="item">
            <img src="img/02.png" alt="...">
        </div>
        ...
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>






</body>
</html>
