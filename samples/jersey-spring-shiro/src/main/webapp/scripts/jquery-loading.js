/**
 * 加载进度条
 *
 * Created by waylau.com on 2014/8/21.
 */

/**
 * beforeSend:ajaxLoading 发送请求前打开进度条 环。
 */
var ajaxLoading = function ( text ){
    'use strict';
    if('undefined' === typeof arguments[0])
    {
        text = '正在处理，请稍候。。。';
    }

    $('<div class=\'datagrid-mask\'></div>').css({display:'block',width:'100%',height:$(window).height()}).appendTo('body');
    $('<div class=\'datagrid-mask-msg\'></div>').html(text).appendTo('body').css({display:'block',left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
};
/**
 * ajaxLoadEnd(); 任务执行成功，关闭进度条
 */
var ajaxLoadEnd = function (){
    'use strict';
    $('.datagrid-mask').remove();
    $('.datagrid-mask-msg').remove();
};
