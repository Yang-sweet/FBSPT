<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/6/24
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
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

    <title>修改密码</title>

    <style type="text/css">

        #up_pwd{margin:0 auto; margin-top: 50px; width: 40%; height:550px; border: 1px white;}

    </style>


</head>
<body>
<div id="up_pwd">
<form action="jspmkczytjyglxthsg3423B6?ac=uppass" name="form1" method="post">
    <table class="table  table-bordered">
        <tr>
            <th colspan="2"><div align="center">修改密码</div></th>
        </tr>
        <tr>
            <td>原密码：</td>
            <td><input name="ymm" type="password" class="form-control"  id="ymm" /></td>
        </tr>
        <tr>
            <td>新密码：</td>
            <td><input name="xmm1" type="password" class="form-control" id="xmm1" /></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input name="xmm2" type="password" class="form-control" id="xmm2" /></td>
        </tr>
        <tr>
            <td> </td>
<%--            <td colspan="2" align="center"><input name="Submit" type="submit"  onClick="return check()" value="确定" />--%>
<%--                <input name="Submit2" type="reset" value="重置" /></td>--%>
            <td><button type="submit" class="btn btn-default">提交</button>
                <button type="reset" class="btn btn-default">重置</button>
        </tr>
    </table>
</form>
</div>
</body>
</html>
