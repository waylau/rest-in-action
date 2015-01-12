'use strict';
/**
 * 消息类
 *
 * Created by waylau.com on 2014/8/21.
 */
/**
 * 弹窗
 */
var msgShow = function (title, msg,showType,timeout,showSpeed,width,height) {
    $.messager.show({
        title:title,
        msg:msg,
        timeout:timeout,
        showSpeed:showSpeed,
        showType:showType,
        width:width,
        heigh:height
    });
};

/**
 * 警告窗 e=error, q=question,i=info,w=warning.
 */
var msgAlert = function (title, msg, icon, fn) {

    if ( /^e/.test(icon)) {
        icon = 'error';
    }else if ( /^q/.test(icon)) {
        icon = 'question';
    }else if ( /^i/.test(icon)) {
        icon = 'info';
    }else if ( /^w/.test(icon)) {
        icon = 'warning';
    }

    $.messager.alert(
        title,
        msg,
        icon,
        fn
    );
};

/**
 * 确认窗
 */
var msgConfirm = function (title, msg, fn) {
    $.messager.confirm(
        title,
        msg,
        fn
    );
};

/**
 * 提示窗
 */
var msgPrompt = function (title, msg, fn) {
    $.messager.prompt(
        title,
        msg,
        fn
    );
};

/**
 * 进度条窗
 */
var msgProgress = function (isShow, title, msg, text) {
    if (!isShow) {
        $.messager.progress('close');
        return;
    }
    var win = $.messager.progress({
        title: title,
        msg: msg,
        text:text
    });
};
