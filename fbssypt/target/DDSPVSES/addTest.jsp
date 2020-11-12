<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/22
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>

<%@page import="pers.chhuai.storm.beans.Test"%>
<%@page import="pers.chhuai.storm.beans.Submission"%>
<%@page import="pers.chhuai.storm.beans.Student"%>
<%@page import="pers.chhuai.storm.beans.Teacher"%>
<%@page import="pers.chhuai.storm.beans.Admin"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/testInfo.css"/>
    <style type="text/css">
        .des_table{
            text-align: center;
            height:45px; color: #808080;
            background-color: #b85a34;
            border: solid 1px;}
        table{
            margin: auto;
            width:80% ;
            align:"center";
            cellpadding:1px;
            cellspacing:1px;
            bordercolor:#00FFFF;
        }
        #addTest{margin:0 auto; margin-top: 50px; width: 50%; height:550px; border: 1px white;}

    </style>


</head>
<body>
<br>
<%--<table class="table table-hover table-bordered">--%>


<%--<form class="form-horizontal">--%>
<%--    <div class="form-group">--%>

<%--        <label for="input1" class="col-sm-2 control-label">编号</label>--%>
<%--        <div class="input-group ">--%>
<%--            <input type="text" class="form-control" id="input1" placeholder="no">--%>
<%--        </div>--%>
<%--        <label for="input2" class="col-sm-2 control-label">实验名称</label>--%>
<%--        <div class="input-group ">--%>
<%--            <input type="text" class="form-control" id="input2" placeholder="name">--%>
<%--        </div>--%>
<%--        <label for="input3" class="col-sm-2 control-label">实验内容</label>--%>
<%--        <div class="input-group ">--%>
<%--            <input type="text" class="form-control" id="input3" placeholder="content">--%>
<%--        </div>--%>
<%--        <label for="input4" class="col-sm-2 control-label">输入示例</label>--%>
<%--        <div class="input-group ">--%>
<%--            <input type="text" class="form-control" id="input4" placeholder="input">--%>
<%--        </div>--%>
<%--        <label for="input5" class="col-sm-2 control-label">输出示例</label>--%>
<%--        <div class="input-group ">--%>
<%--            <input type="text" class="form-control" id="input5" placeholder="output">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <label for="input6" class="col-sm-2 control-label">发布人</label>--%>
<%--        <div class="col-sm-10">--%>
<%--            <input type="text" class="form-control" id="input6" placeholder="user">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <div class="col-sm-offset-2 col-sm-10">--%>
<%--            <div class="checkbox">--%>
<%--                <label>--%>
<%--                    <input type="checkbox"> Remember me--%>
<%--                </label>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="form-group">--%>
<%--        <div class="col-sm-offset-2 col-sm-10">--%>
<%--            <button type="submit" class="btn btn-default">Sign in</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</form>--%>

<%--    <form action="KillTask" method="post">--%>
<%--        <table class="table table-hover table-bordered">--%>
<%--            <tr>--%>
<%--                <th>编号</th>--%>
<%--                <td>--%>
<%--                    <div class="input-group ">--%>
<%--                        <input type="text" class="form-control" id="input1" placeholder="no">--%>
<%--                    </div>--%>
<%--                </td>--%>

<%--            </tr>--%>

<%--        </table>--%>
<%--    </form>--%>

<%--</table>--%>

<div id="addTest">
<form method="post" action="AddTestServlet"   >

    <table class="table  table-bordered" >
        <tr>
            <td  width="100">编号：</td>
            <td><input name='testID' type='text' class="form-control"  /></td></tr>
        <tr>
            <td >名称：</td>
            <td><input name='title' type='text'class="form-control"  /></td></tr>
        <tr>
            <td  width="200">内容：</td>
<%--            <td><textarea name='introduction' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
            <td>
                <textarea  name='introduction' class="form-control" rows="3"></textarea>
            </td></tr>
        <tr>
            <td  width="200">输入示例：</td>
<%--            <td><textarea name='input' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
        <td>
            <textarea name='input' class="form-control" rows="3"></textarea>

        </td></tr>
        <tr>
            <td  width="200">输出示例：</td>
<%--            <td><textarea name='output' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
        <td>
            <textarea name='output' class="form-control" rows="3"></textarea>
        </td></tr>


        <!--<tr>
            <td  width="200">文件：</td>
            <td>
                <input type="file" name="uploadFile" />
        <tr>-->
            <td  width="200">发布人：</td>
            <td><input name='faburen' type='text' class="form-control" value='<%=user.getName()%>' readonly="readonly" /></td></tr>
<%--            <td><input name='faburen' type='text'  value='<%=user.getName()%>' readonly="readonly" /></td></tr>--%>

        <tr>
            <td>&nbsp;</td>
<%--            <td><input type="submit" name="Submit" value="提交"  style='border:solid 1px #000000; color:#666666' />--%>
<%--                <input type="reset" name="Submit2" value="重置" style='border:solid 1px #000000; color:#666666' /></td>--%>
            <td><button type="submit" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
        </tr>
    </table>
</form>
</div>


</body>
</html>

