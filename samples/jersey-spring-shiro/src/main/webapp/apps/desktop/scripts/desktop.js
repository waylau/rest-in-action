/**
 * Created by waylau.com on 2014/9/30.
 */

var initAppItem = function () {


    $.ajax({
        //请求方式为get
        type: "GET",
        //json文件位置
        url: "data/app.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function (result) {
            if (result) {
                createDeskTopApp (result);
            }

        },
        error: function () {
            alert('失败');
        }
    });
};

var createDeskTopApp = function (data) {
    $(".desktop").empty();

    var win_h = $(".desktop").height();
    var cur_top = 10;
    var cur_left = 10;
    for (var idx = 0; idx < data.length; idx++) {
        var deskicon = data[idx];

        //组合桌面图标
        var html ='<div style="left:'+ cur_left + 'px;top:' + cur_top + 'px;" class="deskicon" ondblclick="javascript:addTabHref(\''+deskicon.title+'\',\''+deskicon.url+'\');return false;">';
        html += '<div>';
        html += '<p><img src="' + deskicon.icon + '" /><p>';
        html += '<em>' + deskicon.title + '</em>';
        html += '</div></div>';

        $(".desktop").append(html);
        //计算左边距和顶边距
        if ((cur_top + 110) > (win_h - 140)) {
            cur_top = 10;
            cur_left = cur_left + 110;
        } else {
            cur_top = cur_top + 110;
        }

    }
};
