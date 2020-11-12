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
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    User user = (User)session.getAttribute("User");
    // 通过会话接收测试列表
    List<Test> testList = (List<Test>)request.getSession().getAttribute("testList");
    String testID = request.getParameter("testID"); // 选择的测试题编号
    String testName = request.getParameter("testName");// 选择的测试题名称
    String introduction = request.getParameter("introduction");
    String input = request.getParameter("input");
    String output = request.getParameter("output");

//    int test_index = Integer.parseInt(testID)-1;
//    // 测试题号
//    Test current_test = testList.get(test_index);
    // 访问信息
    System.out.println(user.getId() + " - " + user.getName() + "访问测试" + testID);
%>
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
            background: white;
            border: solid 1px;}
        #addTest{margin:0 auto; margin-top: 50px; width: 50%; height:550px; border: 1px white;}
    </style>


</head>
<body>


<div id="addTest">
<%--    <form method="post" action="AddTestServlet"   >--%>

        <table class="table  table-bordered" >
            <tr>
                <td  width="100">编号：</td>
                <td><input name='testID' type='text' class="form-control" value="<%=testID%>" readonly="readonly"  /></td></tr>
            <tr>
                <td >名称：</td>
                <td><input name='title' type='text'class="form-control" value="<%=testName%>" readonly="readonly" /></td></tr>
            <tr>
                <td  width="200">内容：</td>
                <%--            <td><textarea name='introduction' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
                <td>
                    <textarea  name='introduction' class="form-control" rows="3" readonly="readonly" ><%=introduction%></textarea>
                </td></tr>
            <tr>
                <td  width="200">输入示例：</td>
                <%--            <td><textarea name='input' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
                <td>
                    <textarea name='input' class="form-control" rows="3" readonly="readonly"><%=input%></textarea>

                </td></tr>
            <tr>
                <td  width="200">输出示例：</td>
                <%--            <td><textarea name='output' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>--%>
                <td>
                    <textarea name='output' class="form-control" rows="3" readonly="readonly" > <%=output%></textarea>
                </td></tr>


            <!--<tr>
                <td  width="200">文件：</td>
                <td>
                    <input type="file" name="uploadFile" />
            <tr>-->

            <td  width="200">发布人：</td>
            <td><input name='faburen' type='text' class="form-control" value='<%=user.getName()%>' readonly="readonly" /></td></tr>
            <tr>
                <td  width="200">上传jar包</td>
                <td align="left">
                    <form method="post" action="UploadServlet"  enctype="multipart/form-data">
                        <input type="file" name="uploadFile" style="float:left;"/>
                        <input type="hidden" name="id"  value=<%=user.getId() %>>
                        <input type="hidden" name="testID"  value=<%=testID %>>
                        <input type="hidden" name="testName"  value=<%=testName %>>
                        <%--                        <button type="submit" class="btn btn-default">上传</button>--%>
                        <input type="submit" value="上传" />
<%--                        <input type="submit" class="btn btn-default" value="上传" style="float: right;"/>--%>
                    </form>
                </td>

                </td></tr>
            <tr>
<%--            <tr>--%>
<%--                <td>&nbsp;</td>--%>
<%--                &lt;%&ndash;            <td><input type="submit" name="Submit" value="提交"  style='border:solid 1px #000000; color:#666666' />&ndash;%&gt;--%>
<%--                &lt;%&ndash;                <input type="reset" name="Submit2" value="重置" style='border:solid 1px #000000; color:#666666' /></td>&ndash;%&gt;--%>
<%--                <td><button type="submit" class="btn btn-default">提交</button>--%>
<%--                    <button type="reset" class="btn btn-default">重置</button>--%>
<%--            </tr>--%>
        </table>
<%--    </form>--%>
</div>


<%--<div id="addTest">--%>
<%--    <form method="post" action="AddTestServlet"   >--%>

<%--        <table class="table  table-bordered" >--%>
<%--            <tr>--%>
<%--                <td  width="100">编号：</td>--%>
<%--                <td><input name='testID' type='text' class="form-control" value="<%=current_test.getTestId() %>" readonly="readonly"  /></td></tr>--%>
<%--            <tr>--%>
<%--                <td >名称：</td>--%>
<%--                <td><input name='title' type='text'class="form-control" value="<%=current_test.getTitle() %>" readonly="readonly" /></td></tr>--%>
<%--            <tr>--%>
<%--                <td  width="200">内容：</td>--%>
<%--                &lt;%&ndash;            <td><textarea name='introduction' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>&ndash;%&gt;--%>
<%--                <td>--%>
<%--                    <textarea  name='introduction' class="form-control" rows="3" value="<%=current_test.getIntroduction() %>" readonly="readonly" ></textarea>--%>
<%--                </td></tr>--%>
<%--            <tr>--%>
<%--                <td  width="200">输入示例：</td>--%>
<%--                &lt;%&ndash;            <td><textarea name='input' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>&ndash;%&gt;--%>
<%--                <td>--%>
<%--                    <textarea name='input' class="form-control" rows="3" value=<%=current_test.getInput() %> readonly="readonly" ></textarea>--%>

<%--                </td></tr>--%>
<%--            <tr>--%>
<%--                <td  width="200">输出示例：</td>--%>
<%--                &lt;%&ndash;            <td><textarea name='output' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>&ndash;%&gt;--%>
<%--                <td>--%>
<%--                    <textarea name='output' class="form-control" rows="3" value="<%=current_test.getOutput() %>" readonly="readonly" ></textarea>--%>
<%--                </td></tr>--%>


<%--            <!--<tr>--%>
<%--                <td  width="200">文件：</td>--%>
<%--                <td>--%>
<%--                    <input type="file" name="uploadFile" />--%>
<%--            <tr>-->--%>
<%--            <td  width="200">发布人：</td>--%>
<%--            <td><input name='faburen' type='text' class="form-control" value='<%=user.getName()%>' readonly="readonly" /></td></tr>--%>
<%--            &lt;%&ndash;            <td><input name='faburen' type='text'  value='<%=user.getName()%>' readonly="readonly" /></td></tr>&ndash;%&gt;--%>
<%--            <tr>--%>
<%--                <td  width="200">上传jar包</td>--%>
<%--                &lt;%&ndash;            <td><textarea name='input' cols='50' rows='5'  style='border:solid 0.5px #000000; color:#666666' ></textarea></td></tr>&ndash;%&gt;--%>
<%--                <td align="left">--%>
<%--                    <form method="post" action="UploadServlet"  enctype="multipart/form-data">--%>
<%--                        <input type="file" name="uploadFile" style="float:left;"/>--%>
<%--                        <input type="hidden" name="id"  value=<%=user.getId() %>>--%>
<%--                        <input type="hidden" name="testID"  value=<%=testID %>>--%>
<%--                        <input type="hidden" name="testName"  value=<%=testName %>>--%>
<%--&lt;%&ndash;                        <button type="submit" class="btn btn-default">上传</button>&ndash;%&gt;--%>
<%--                        <input type="submit" class="btn btn-default" value="上传" style="float: right;"/>--%>
<%--                    </form>--%>
<%--                </td>--%>

<%--                </td></tr>--%>
<%--            <tr>--%>
<%--            <tr>--%>
<%--                <td>&nbsp;</td>--%>
<%--                &lt;%&ndash;            <td><input type="submit" name="Submit" value="提交"  style='border:solid 1px #000000; color:#666666' />&ndash;%&gt;--%>
<%--                &lt;%&ndash;                <input type="reset" name="Submit2" value="重置" style='border:solid 1px #000000; color:#666666' /></td>&ndash;%&gt;--%>
<%--                <td><button type="submit" class="btn btn-default">提交</button>--%>
<%--                    <button type="reset" class="btn btn-default">重置</button>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>
<%--</div>--%>









<%--<div id="test_info">--%>
<%--    <!-- 问题描述 -->--%>

<%--    <div class="des_table">--%>
<%--        <table  class="table  table-bordered" >--%>
<%--            <tr class="des_table">--%>
<%--                <td width="20%" height="30px" >--%>
<%--                    <font size="3px" color="black" >实验信息</font></td>--%>
<%--                <td></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">测试编号</td>--%>
<%--                <td align="left"><%=current_test.getTestId() %></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">测试名称</td>--%>
<%--                <td align="left"><%=current_test.getTitle() %></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">问题描述</td>--%>
<%--                <td align="left">--%>
<%--                    <%=current_test.getIntroduction() %>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">输入</td>--%>
<%--                <td align="left">--%>
<%--                    <%=current_test.getInput() %>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td align="center">输出样例</td>--%>
<%--                <td align="left">--%>
<%--                    <%=current_test.getOutput() %>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <!-- 提交你的jar包 -->--%>
<%--        <div id="submit_jar">--%>
<%--            <table width = "100%" border="1px" cellpadding="0" cellspacing="0">--%>
<%--                <tr class="des_table">--%>
<%--                    <td width="20%" height="30px" >--%>
<%--                        <font size="3px" color="black">上传jar</font></td>--%>
<%--                    <td></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td align="center" height="40px">请选择文件</td>--%>
<%--                    <td align="left">--%>
<%--                        <form method="post" action="UploadServlet"  enctype="multipart/form-data">--%>
<%--                            选择jar包:--%>
<%--                            <input type="file" name="uploadFile" />--%>
<%--                            <input type="hidden" name="id"  value=<%=user.getId() %>>--%>
<%--                            <input type="hidden" name="testID"  value=<%=testID %>>--%>
<%--                            <input type="hidden" name="testName"  value=<%=testName %>>--%>
<%--                            <input type="submit" value="上传" />--%>
<%--                        </form>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>

