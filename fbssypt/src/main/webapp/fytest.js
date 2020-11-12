//注意：若将代码写到$(function(){})中，则里面的所有方法都不是全局的，故此a标签中的onclick调用不到里面的方法！！！。
//总记录数
var totalNum = 0;

//总页数
var totalPage = 0;

//默认每页显示数
var avageNum = 1;

//默认显示第一页
var currentPage = 1;


getUserList(currentPage,avageNum);
initPagination(totalNum,totalPage);

//与后台交互获取数据，异步加载到页面上
function getUserList(pageNum,pageSize){
    currentPage = pageNum;
    $(".panel-body table tbody").html(" ");
    $.ajax({
        url:"../../user/userList.do",
        method:"get",
        data:{"pageNum":pageNum,"pageSize":pageSize},
        async:false,
        success:function(data){
            if (data.success){
                var length = data.data.list.length;
                totalNum = data.data.total;
                totalPage = data.data.pages;
                for(var  i = 0;i<length;i++){
                    var userId = data.data.list[i].userId;
                    var userName = data.data.list[i].userName;
                    var roleLength = data.data.list[i].roleList.length;
                    var roleName = " ";
                    for(var j=0;j<roleLength;j++){
                        roleName = data.data.list[i].roleList[j].roleName + " " + roleName;
                    }
                    var email = data.data.list[i].userEmail;
                    var phone = data.data.list[i].userPhone;
                    var status = data.data.list[i].userStatus;
                    $(".userListTable tbody").append(
                        '<tr>'+
                        '<td>'+userId+'</td>'+
                        '<td>'+userName+'</td>'+
                        '<td>'+roleName+'</td>'+
                        '<td>'+email+'</td>'+
                        '<td>'+phone+'</td>'+
                        '<td><button type="button" class="btn btn-success btn-default">角色分配</button><button type="button" class="btn btn-default btn-danger">禁止登陆</button></td>'+
                        '</tr>'
                    )
                }
            }
        }
    });
}

//初始化分页栏
function initPagination(totalPage,totalNum) {
    $('#pagination').html(" ");
    $('#pagination').append(
        '<ul class="pagination" style="display:inline;">' +
        '<span style="float: right;">每页显示' +
        '<select id="dataNum">' +
        '<option value="1">1</option>' +
        '<option value="2">2</option>' +
        '<option value="3">3</option>' +
        '</select>条记录,总共有' + totalPage + '页，总共' + totalNum + '条记录</span>' +
        '</ul>'
    )
    // onclick="getUserList('+ (currentPage - 1) + ','+ avageNum + ')"
    $("#pagination ul").append(
        '<li><a href="javascript:void(0);" id="prev">上一页</a>'
    )
    for (var i = 1; i <= totalPage; i++) {
        $("#pagination ul").append(
            '<li id="page'+i+'"><a href="javascript:void(0);"  onclick="getUserList(' + i + ',' + avageNum + ')">' + i + '</a>'
        )
    }
    $("#pagination ul").append(
        '<li><a href="javascript:void(0);"  id="next">下一页</a>'
    )
    $("select option[value=" + avageNum + "]").attr('selected', true)
    $("#page1").addClass("active");
    checkA();
}

//很关键，因为执行initPagination方法时，将select删除再重新添加，所以需要先将select上的结点移除off
//然后再绑定结点on，如果不这么做，会出现change事件只被触发一次。
$(document).off('change','#dataNum').on('change','#dataNum',function(){
    avageNum = $(this).children('option:selected').val();
    currentPage = 1;
    getUserList(currentPage,avageNum);
    initPagination(totalPage,totalNum);
});

//设置分页栏点击时候的高亮
$("#pagination").on("click","li",function(){
    var aText = $(this).find('a').html();
    checkA();
    if (aText == "上一页"){
        $(".pagination > li").removeClass("active");
        $("#page"+(currentPage -1)).addClass("active");
        getUserList(currentPage - 1,avageNum);
        checkA();
    }else if(aText == "下一页"){
        $(".pagination > li").removeClass("active");
        $("#page"+(currentPage + 1)).addClass("active");
        getUserList(currentPage + 1,avageNum);
        checkA();
    }else{
        $(".pagination > li").removeClass("active");
        $(this).addClass("active");
    }
})

//因为其他地方都需要用到这两句，所以封装出来
//主要是用于检测当前页如果为首页，或者尾页时，上一页和下一页设置为不可选中状态
function checkA() {
    currentPage == 1 ? $("#prev").addClass("btn btn-default disabled") : $("#prev").removeClass("btn btn-default disabled");
    currentPage == totalPage ? $("#next").addClass("btn btn-default disabled") : $("#next").removeClass("btn btn-default disabled");
}