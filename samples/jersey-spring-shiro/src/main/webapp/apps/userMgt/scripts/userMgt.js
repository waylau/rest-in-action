/**
 * Created by waylau.com on 2014/9/30.
 */

var getUsers = function () {


    $.ajax({
        //请求方式为get
        type: "GET",
        //json文件位置
        url: "rest/users",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function (result) {
            if(result){
                $('#dataTable').datagrid('loadData',result);
            }else{
                alert(result.message);
            }

        },
        error: function () {
            alert('失败');
        }
    });
};
$(document).ready(function () {
    $(function(){
        $('.dataTable').css({'height':($(window).height())});
        $('#dataTable').datagrid('resize',{
            width:($(window).width()),
            height:($(window).height())
        });

        $(window).resize(function(){
            $('.dataTable').css({'height':($(window).height())});
            var aa=($(window).width());
            $('#dataTable').datagrid('resize',{
                width:($(window).width()),
                height:($(window).height())
            });
        })
    });

    getUsers();
});