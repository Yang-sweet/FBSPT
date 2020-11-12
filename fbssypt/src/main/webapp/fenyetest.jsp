<%--
  Created by IntelliJ IDEA.
  User: 12195
  Date: 2020/10/24
  Time: 16:58
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
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover userListTable">
                        <thead>
                        <tr>
                            <th>用户id</th>
                            <th>用户名</th>
                            <th>角色名称</th>
                            <th>邮箱</th>
                            <th>手机号</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>

                </div>
            </div>
            <!--分页栏-->
            <div id="pagination">

            </div>
        </div>
    </div>
</div>

</body>
</html>
