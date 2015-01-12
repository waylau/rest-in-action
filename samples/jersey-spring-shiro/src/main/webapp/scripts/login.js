/**
 * Created by waylau.com on 2014/9/30.
 */

var loginFormSubmit = function () {


    $.ajax({
        //请求方式为get
        type: "POST",
        //json文件位置
        url: "rest/login",
        //返回数据格式为json
        dataType: "json",
        //提交的数据
        data: $('#loginForm').serialize(),
        //请求成功完成后要执行的方法
        success: function (result) {
            if (result) {
                alert('成功');
            }

        },
        error: function () {
            alert('失败');
        }
    });
};

