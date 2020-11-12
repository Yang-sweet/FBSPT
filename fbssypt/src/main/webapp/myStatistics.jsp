<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.StaService" %>
<%@ page import="pers.chhuai.storm.beans.Statistics" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: chhua
  Date: 4/24/2020
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>分布式数据流处理虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/statistics.css"/>

    <style type="text/css">
        .sta_table{
            text-align: center;
            height:45px;
            color: white;
            background-color: #3d3b3bdb;}
        #my_statistics{margin:0 auto; margin-top: 50px; width: 60%; height:550px; border: 1px white;}

    </style>

</head>
<body>
<div id="my_statistics">
    <form class="navbar-form navbar-left" style=" position: absolute;margin-top:-30px;right:200px;">
        <%--    <p class="navbar-text" style="left: 100px;">搜索</p>--%>

        <div class="form-group">

            <input type="text" class="form-control" placeholder="姓名">
            <select class="form-control" style="width: 200px">
                <option>admin</option>
                <option>teacher</option>
                <option>student</option>
            </select>
        </div>

        <button type="submit" class="btn btn-default">查询</button>
    </form>


    <br>

    <table class="table table-hover table-bordered" >
        <tr class="info">
            <th></th>
            <th class="text-center">姓名</th>
            <th class="text-center">完成情况</th>
            <th class="text-center">成绩</th>
        </tr>
        <%
            // 1.获取容器
            ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
            // 2.获取业务层对象
            StaService ss = ac.getBean("staService", StaService.class);
            List<Statistics> stas = ss.getAllSta();
        for (Statistics sta : stas) {%>
        <tr id = "statistics_list" >
            <td id = "user_id" ><%=sta.getId()%></td >
            <td id = "user_name" ><%=sta.getName()%></td >
            <td id = "user_finished" ><%=sta.getFinished()%> / 4</td >
            <td id = "user_score" ><%=100*sta.getFinished()/4%></td >
        </tr >
        <%} %>
    </table>
</div>
</body>
</html>




