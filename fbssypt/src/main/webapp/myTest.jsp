<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@page import="pers.chhuai.storm.beans.Test"%>
<%@page import="pers.chhuai.storm.beans.User"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="pers.chhuai.storm.service.bean.TestService" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="pers.chhuai.storm.beans.Page" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    User user = (User)session.getAttribute("User");
    String type = (String)request.getSession().getAttribute("type");
    // 1.获取容器
    ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
    // 2.获取业务层对象
    TestService ss = ac.getBean("testService", TestService.class);

//    List<Test> testList = ss.getTests();
//    session.setAttribute("testList", testList);

//    List<Test> testList=(List)request.getAttribute("testList");
    List<Test> testList;
    if(request.getAttribute("testList")==null){
        testList = ss.getTests();
    }else {
        testList=(List)request.getAttribute("testList");

    }
    session.setAttribute("testList", testList);
//    List<Test> pb = (List<Test>)request.getSession().getAttribute("pb");
//    Page<Test> pb=new Page<Test>();
   Page<Test> pb = (Page<Test>)request.getSession().getAttribute("pb");



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
        #delete{
            text-align: center;
            margin-top: 5px;
            margin-left: 50px;
            width: 30%; height: 10px;
            line-height: 10px;
            background-color: #b85a34;
            color: #FFFFFF;
            border-radius: 5%;}
        /*#test_list{*/
        /*    height: 30px;}*/
        #test_name{
            text-align: center;
            width: 50%; height: 10px;
            color: #3d3b3b;
            font-weight: normal;
            }
        #enter{
            text-align: center;
            width: 30%}
    </style>

    <script>
        function Delete()
        {
            alert("确定要删除吗？")

        }

        window.onload=function () {
            //给删除选中按钮添加单机事件
            document.getElementById("DeSelected").onclick=function () {
                document.getElementById("form").submit();
            }

        }
    </script>
<%--    <%--%>
<%--        ss.deleteTest(request.getParameter("testID"));--%>
<%--    %>--%>

</head>
<body>


<div id="test">
    <div style="float:left;">
        <form class="form-inline" action='SelectTestServlet' method="post">
            <div class="form-group">
                <label for="exampleInputName2">实验编号</label>
                <input class="form-control" name="testID" id="exampleInputName2" style="width: 100px">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">实验名称</label>
                <input class="form-control" name="testName" id="exampleInputEmail2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px;">
        <a type="submit" class="btn btn-primary" href="${pageContext.request.contextPath}/addTest.jsp">添加实验</a>
        <a type="submit" class="btn btn-danger" href="javascript:void(0)";id="DelSelected">删除选中</a>
    </div>
<%--    <p>已有实验列表：</p><br>--%>
<%--    <form name="form1" id="form1" method="post" action="myTest.jsp">--%>
<%--        搜索:  编号：<input name="bianhao" type="text" id="bianhao" />  名称：<input name="timu" type="text" id="timu" />--%>
<%--        <input type="submit" name="Submit" value="查找" style='border:solid 1px #000000; color:#666666' />--%>
<%--    </form><br>--%>
<%--    <form id="form" action="${pageContext.request.contextPath}/DelSelectServlet" method="post">--%>
        <table class="table table-hover table-bordered" >
<%--        <tr class="test_table" style="text-align: center">--%>
        <tr class="info" >
            <th class="text-center"> </th>
            <th class="text-center">实验编号</th>
            <th class="text-center">实验名称</th>
            <th class="text-center">操作</th>
        </tr>
<%--        <% for (Test test : testList) { %>--%>
    <% for (Test test : testList) { %>
<%--    <% for (Test test : testList) { %>--%>
<%--    <% for (Test test : pb.getList()) { %>--%>
        <tr >
            <th class="text-center"><input type="checkbox" name="seid" value="<%=test.getTestId() %>"></th>
            <td id = "testID" style="text-align: center"><%=test.getTestId() %></td>
            <td id = "test_name" ><%=test.getTitle() %></td>
            <td id = "enter">
                <form action='testInfo.jsp' method='post' style="float:left; margin-left: 55px">
                    <input type="hidden" name="id"  value=<%=user.getId() %>>
                    <input type="hidden" name="name"  value=<%=user.getName()%>>
                    <input type="hidden" name="testID"  value=<%=test.getTestId() %>>
                    <input type="hidden" name="testName"  value=<%=test.getTitle() %>>
                    <input type="hidden" name="introduction"  value=<%=test.getIntroduction() %>>
                    <input type="hidden" name="input"  value=<%=test.getInput() %>>
                    <input type="hidden" name="output"  value=<%=test.getOutput() %>>
                    <button type="submit" class="btn btn-default">详情</button>
<%--                    <% if (type.equals("2") || type.equals("3")) {%>--%>
<%--                    <button type="submit" class="btn btn-default">修改</button>--%>
<%--                    <%}%>--%>
<%--                    <% if (type.equals("1") ) {%>--%>
<%--                    <button type="submit" class="btn btn-default">详情</button>--%>
<%--                    <%}%>--%>
                </form>
                <% if (type.equals("2") || type.equals("3")) {%>
                    <form action='DelTestServlet' method='post' style="margin-right: 25px;">
                        <input type="hidden" name="testID"  value=<%=test.getTestId() %>>
                        <button type="submit" class="btn btn-default">删除</button>
                    </form>
                <%}%>
            </td>
        </tr>
        <%}
        %>
        </table>
<%--</form>--%>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
<%--            <c:forEach begin="1" end="${pb.totalPage}" var="i">--%>
<%--                <a href="#">i</a></li>--%>

<%--            </c:forEach>>--%>
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
            <span style="font-size: 20px;margin-left: 10px">
                共${pb.totalCount}条记录，共${pb.totalPage} 页
            </span>
        </ul>
    </nav>
</div>
</body>
</html>
