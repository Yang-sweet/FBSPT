<%--
  Created by IntelliJ IDEA.
  User: chhua
  Date: 4/23/2020
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
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
    List<Application> appList;
    if(request.getAttribute("appList")==null){
        appList = ss.getApplication();
    }else {
        appList=(List)request.getAttribute("appList");

    }
    session.setAttribute("appList", appList);

%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pers.chhuai.storm.service.schedule.TaskList" %>
<%@ page import="pers.chhuai.storm.beans.Application" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.chhuai.storm.service.bean.AppService" %>
<%@ page import="pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
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

    <title>分布式虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/monitor.css"/>
    <style type="text/css">
        .sub_table{
            text-align: center;
            height:45px;
            color: white;
            background-color: #3d3b3bdb;}

        #submission{margin:0 auto; margin-top: 50px; width: 90%; height:550px; border: 1px white;}

    </style>

    <script>
        function datetimeFormat(longTypeDate){
            var datetimeType = "";
            var date = new Date();
            date.setTime(longTypeDate);
            datetimeType = date.getFullYear()+"-"+getMonth(date)+"-"+getDay(date)+"&nbsp;"+getHours(date)+":"+getMinutes(date)+":"+getSeconds(date);//yyyy-MM-dd 00:00:00格式日期
            return datetimeType;
        }


            var myselect=document.getElementById("status");

            var index = myselect.selectedIndex;

            var value=myselect.options[index].text;

            document.getElementById("sevalue").innerHTML=value;



    </script>

</head>
<body>


<div id="test">

    <div id="submission">
        <form class="navbar-form navbar-left" action="SelectTaskServlet" method="post" style=" position: absolute;margin-top:-30px;right:50px;">
            <div class="form-group">

                <input type="text" name="id" class="form-control" placeholder="提交人">
                <select id="status" name="FinalStatus" class="form-control" style="width: 200px">
                    <option>SUCCEEDED</option>
                    <option>FAILD</option>
                </select>
            </div>


            <button type="submit" class="btn btn-default">查询</button>
        </form>

        <form action="KillTask" method="post">
            <table class="table table-hover table-bordered">
                <tr class="info">
                    <th class="text-center">任务ID</th>
                    <th class="text-center">任务名称</th>
                    <th class="text-center">任务类型</th>
                    <th class="text-center">任务状态</th>
                    <th class="text-center">最终状态</th>
                    <th class="text-center">开始时间</th>
                    <th class="text-center">结束时间</th>
                    <th class="text-center">Progress</th>
                    <th class="text-center">历史日志</th>
                    <th class="text-center">提交人</th>
                    <th class="text-center">操作 </th>

                        <% for (Application app : appList) { %>
                <tr >
                    <td id = "ApplicationId"><%=app.getApplicationId() %></td>
                    <td id = "Name"><%=app.getName() %></td>
                    <td id = "ApplicationType"><%=app.getApplicationType() %></td>
                    <td id = "State"><%=app.getState() %></td>
                    <td id = "FinalStatus" name="FinalSatatus"><%=app.getFinalStatus() %></td>

                <%
                    Date stime = new Date(app.getStartTime());
                    Date ftime = new Date(app.getFinishTime());
                    SimpleDateFormat ft =new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                %>>
<%--                    <td id = "StartTime"><%=new Date(app.getStartTime()) %></td>--%>
                    <td id = "StartTime"><%=ft.format(stime)%></td>
<%--                <td><fmt:formatDate value="${Date(app.getStartTime())}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                    <td id = "FinishTime" ><%=ft.format(ftime)%></td>
                    <td id = "FinishTime" >
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
                                <span class="sr-only">40% 完成</span>
                            </div>
                        </div>
                    </td>
                    <%--            <td id = "URL"><%=app.getURL()%></td>--%>
                    <td id = "URL"> <a href="<%=app.getURL()%>" style="color: black;text-decoration:underline">history</a></td>
                    <td id = "id" name="id"><%=app.getId()%></td>
                    <%--            <input type='submit' name = 'delete' value=删除>--%>
                    <%--            <td><button onclick="Delete()" >kill</button></td>--%>
<%--                    <td><input type="submit" class="btn btn-default" value="kill"></td>--%>
                <td><button type="submit" class="btn btn-danger">kill</button></td>
                </tr>
                <%} %>

            </table>
        </form>
    </div>


    <div style="position: absolute; margin-top: 500px; right: 92px">
        <nav aria-label="Page navigation-lg">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
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
    </div>


<%--    <div id="test">--%>

<%--        <div id="submission">--%>

<%--            <form name="form1" id="form1" method="post" action="myTest.jsp">--%>
<%--                搜索:  任务状态:&nbsp;&nbsp;--%>
<%--                <select>--%>
<%--                    <option value ="succeeded">SUCCEEDED</option>--%>
<%--                    <option value ="failed">FAILED</option>--%>

<%--                </select>&nbsp;&nbsp;&nbsp;--%>
<%--                <input type="submit" name="Submit" value="查询" style='border:solid 1px #000000; color:#666666' />--%>
<%--            </form>--%>
<%--            <br>--%>
<%--            <form action="KillTask" method="post">--%>
<%--                <table width = "100%" border="1px" cellpadding="0" cellspacing="0">--%>
<%--                    <tr class="sub_table">--%>
<%--                        <td>任务ID</td>--%>
<%--                        <td>任务名称</td>--%>
<%--                        <td>任务类型</td>--%>
<%--                        <td>任务状态</td>--%>
<%--                        <td>最终状态</td>--%>
<%--                        <td>开始时间</td>--%>
<%--                        <td>结束时间</td>--%>
<%--                        <td>历史日志</td>--%>
<%--                        <td>提交人</td>--%>
<%--                        <td>操作 </td>--%>

<%--                            <%--%>
<%--            List<Application> apps= ss.getApplication();--%>
<%--            for (Application app :apps) {--%>
<%--        %>--%>
<%--                    <tr id="submission_list">--%>
<%--                        <td id = "ApplicationId"><%=app.getApplicationId() %></td>--%>
<%--                        <td id = "Name"><%=app.getName() %></td>--%>
<%--                        <td id = "ApplicationType"><%=app.getApplicationType() %></td>--%>
<%--                        <td id = "State"><%=app.getState() %></td>--%>
<%--                        <td id = "FinalStatus"><%=app.getFinalStatus() %></td>--%>
<%--                        <td id = "StartTime"><%=new Date(app.getStartTime()) %></td>--%>
<%--                        <td id = "FinishTime"><%=new Date(app.getFinishTime()) %></td>--%>
<%--                        &lt;%&ndash;            <td id = "URL"><%=app.getURL()%></td>&ndash;%&gt;--%>
<%--                        <td id = "URL"> <a href="<%=app.getURL()%>" style="color: black;text-decoration:underline">history</a></td>--%>
<%--                        <td id = "id"><%=app.getId()%></td>--%>
<%--                        &lt;%&ndash;            <input type='submit' name = 'delete' value=删除>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;            <td><button onclick="Delete()" >kill</button></td>&ndash;%&gt;--%>
<%--                        <td><input type="submit" value="kill"></td>--%>
<%--                    </tr>--%>
<%--                    <%} %>--%>
<%--                    --%>
<%--                </table>--%>
<%--            </form>--%>
<%--        </div>--%>











<%--    --%>

<%--<div id="my_monitor">--%>
<%--    <div id="monitor_title">--%>
<%--        <font size="5px" color="#B0252A">平台当前负载</font><br><br>--%>
<%--    </div>--%>
<%--    <div id="line">--%>
<%--        <hr size="2">--%>
<%--    </div>--%>
<%--    <div class="task">--%>
<%--        等待中任务数：<%=TaskList.SubmittedQueue.size()%>--%>
<%--    </div>--%>
<%--    <div id="line">--%>
<%--        <hr size="1">--%>
<%--    </div>--%>
<%--    <div class="task">--%>
<%--        运行中任务数：<%=TaskList.RunningList.size()%>--%>
<%--    </div>--%>
<%--    <div id="line">--%>
<%--        <hr size="1">--%>
<%--    </div>--%>
<%--    <div class="task">--%>
<%--        通过任务数：<%=TaskList.FinishedList.size()%>--%>
<%--    </div>--%>
<%--    <div id="line">--%>
<%--        <hr size="1">--%>
<%--    </div>--%>
<%--    <div class="task">--%>
<%--        异常/未通过任务数：<%=TaskList.ErrorList.size()%>--%>
<%--    </div>--%>
<%--    <div id="line">--%>
<%--        <hr size="1">--%>
<%--    </div>--%>
<%--    <div id="monitor_title">--%>
<%--        <font size="5px" color="#B0252A">平台操作面板</font><br><br>--%>
<%--        <iframe src="http://192.168.8.114:8888/cyybt" width="100%" height="1000px"></iframe>--%>
<%--        &lt;%&ndash;--%>
<%--            uname : aliyun123--%>
<%--            password : xidian123--%>
<%--        &ndash;%&gt;--%>
<%--    </div>--%>
<%--</div>--%>

</body>
</html>
