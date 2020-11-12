<%--
  Created by IntelliJ IDEA.
  User: 大娱乐家
  Date: 2020/4/19
  Time: 17:17
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
    SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>我的提交</title>
</head>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/submission.css"/>

    <style type="text/css">
        .sub_table{
            text-align: center;
            height:45px;
            color: white;
            background-color: #3d3b3bdb;}

    </style>

</head>
    <body>
    <!-- 仿真实验 test -->
    <div id="submission">
<%--        <table width = "80%" border="1px" cellpadding="0" cellspacing="0">--%>
    <form action='result.jsp' method='post' style="float:left; margin-left: 55px">
    <table class="table table-hover table-bordered" >
            <tr class="info">
                <th class="text-center">实验编号</th>
                <th class="text-center">实验名称</th>
                <th class="text-center">任务状态</th>
                <th class="text-center">实验结果</th>
                <th class="text-center">提交时间</th>
                <th class="text-center">操作</th>
            </tr><%
            List<Submission> subs = ss.getSubmissions(user.getId());
            for (Submission sub : subs) {
        %>

        <tr id="submission_list">
            <td id = "test_id"><%=sub.getTestID() %></td>
            <td id = "test_name"><%=sub.getTestName() %></td>
            <td id = "sub_state"><%=sub.getState() %></td>
            <td id = "sub_result" ><%=sub.getResult() %></td>
            <td id = "sub_time"><%=sub.getDate() %></td>
            <td id = "result">
                <button type="submit" class="btn btn-default">查看结果</button>
            </td>
        </tr>
        <%} %>
    </table>
    </form>
</div>


<%--    <nav aria-label="Page navigation">--%>
<%--        <ul class="pagination">--%>
<%--            <li>--%>
<%--                <a href="#" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            &lt;%&ndash;            <c:forEach begin="1" end="${pb.totalPage}" var="i">&ndash;%&gt;--%>
<%--            &lt;%&ndash;                <a href="#">i</a></li>&ndash;%&gt;--%>

<%--            &lt;%&ndash;            </c:forEach>>&ndash;%&gt;--%>
<%--            <li><a href="#">1</a></li>--%>
<%--            <li><a href="#">2</a></li>--%>
<%--            <li><a href="#">3</a></li>--%>
<%--            <li><a href="#">4</a></li>--%>
<%--            <li><a href="#">5</a></li>--%>
<%--            <li>--%>
<%--                <a href="#" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&raquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <span style="font-size: 20px;margin-left: 10px">--%>
<%--                共${pb.totalCount}条记录，共${pb.totalPage} 页--%>
<%--            </span>--%>
<%--        </ul>--%>
<%--    </nav>--%>
    </body>
</html>
