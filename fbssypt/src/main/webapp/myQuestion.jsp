<%--
  Created by IntelliJ IDEA.
  User: 大娱乐家
  Date: 2020/4/19
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pers.chhuai.storm.beans.Student"%>
<%@page import="pers.chhuai.storm.beans.Teacher"%>
<%@page import="pers.chhuai.storm.beans.Admin"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String qx;
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //从session中获取登录对象
    String type = (String)request.getSession().getAttribute("type");
    User user = null;
    // 从session中获取登录对象
    if (type.equals("1")) {
        user = (Student)request.getSession().getAttribute("User");
        qx="学生";

    } else if (type.equals("2")) {
        user = (Teacher)request.getSession().getAttribute("User");
        qx="教师";
    } else {
        user = (Admin)request.getSession().getAttribute("User");
        qx="管理员";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>分布式数据流处理虚拟仿真实验系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/question.css"/>
	<style type="text/css">

		table{margin: auto;
			width: 80%;
			height: 210px;
			align:center;
			cellpadding:3px;
			cellspacing:1px;

			bordercolor:#D9E9FF ;

		}

	</style>

</head>

<body>
<table border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#D9E9FF" style="margin-top: 50px; border-collapse:collapse">
	<TBODY>
	<TR
			align=middle bgColor=#ffffff>
		<td colspan="4" bgColor=#CADCEA><strong>系统基本信息</strong></td>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD width="14%" align="left" valign="bottom" >当前用户：</TD>
		<TD width="37%"  align="left" valign="bottom" ><font class="t4"><%=user.getName()%></font></TD>
		<TD width="9%"  align="left" valign="bottom" >您的权限：</TD>
		<TD width="40%"  align="left" valign="bottom" ><font class="t4"><%=qx%></font></TD>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD height="38" align="left" valign="bottom"  >当前日期：</TD>
		<TD  align="left" valign="bottom" ><%java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);

			String result = format.format(date);
			out.print(result);%></TD>
		<TD align="left" valign="bottom" >您的IP：</TD>
		<TD  align="left" valign="bottom" ><%=request.getRemoteAddr() %></TD>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD align="left" valign="bottom"  >浏览器版本：</TD>
		<TD  align="left" valign="bottom" ><font class="t41"><%=request.getHeader("User-Agent")%></font></TD>
		<TD align="left" valign="bottom" >操作系统：</TD>
		<TD  align="left" valign="bottom" ><font class="t41"><%=System.getProperty("os.name")%> </font></TD>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD height="43" align="left" valign="bottom"  >服务器端口：</TD>
		<TD  align="left" valign="bottom" ><font class="t41"><%=request.getServerPort()%></font></TD>
		<TD align="left" valign="bottom" >开发日期：</TD>
		<TD  align="left" valign="bottom" ><%
			out.print(result);%></TD>
	</TR>
	</TBODY>
</TABLE>
<p>&nbsp;</p>
<table width="80%" height="193" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#D9E9FF" style="border-collapse:collapse">
	<TBODY>
	<TR
			align=middle bgColor=#ffffff>
		<td colspan="2" bgColor=#CADCEA><strong>分布式虚拟仿真实验系统</strong></td>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD width="10%" align="left" >系统作者：</TD>
		<TD width="41%"  align="left" ><font class="t4">xxxxxx</font></TD>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD align="left"  >指导老师：</TD>
		<TD  align="left" >xxxxxxx</TD>
	</TR>
	<TR   align=middle
		  bgColor=#ffffff>
		<TD align="left"  >联系方式：</TD>
		<TD  align="left" ><font class="t41">xxxxxxxxxxxxxxx</font></TD>
	</TR>
	</TBODY>
</TABLE>

</body>
</html>
