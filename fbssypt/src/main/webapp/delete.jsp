<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/27
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pers.chhuai.storm.beans.Test"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.TestService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>

<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    User user = (User)session.getAttribute("User");
    // 1.获取容器
    ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
    // 2.获取业务层对象
    TestService ss = ac.getBean("testService", TestService.class);
    List<Test> testList = ss.getTests();
    session.setAttribute("testList", testList);
    String id = request.getParameter("id");
    conDB db = new conDB();
    db.connectDB();
    Statement state = db.getConnection().createStatement();
    String sql = "delete from personinfo where id = "+id;
    int flag= state.executeUpdate(sql);
    if(flag > 0)
    {
        out.println("<script>alert('删除成功！');history.go(-1)</script>");
    }
    else
    {
        out.println("<script>alert('删除失败！');history.go(-1)</script>");
    }
    state.close();
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
