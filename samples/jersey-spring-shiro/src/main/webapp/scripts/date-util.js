/**
 * 时间对象的格式化
 * 用法 ：
 * var time = new Date().format('yyyy-MM-dd hh:mm:ss');
 * 或者
 * var time = new Date().format('yyyy年MM月dd日 hh时mm分ss秒');
 *
 * Created by waylau.com on 2014/8/21.
 * update by waylau.com on 2014-11-7.
 */

Date.prototype.format = function (format) {
    'use strict';
    /*
     * format='yyyy-MM-dd hh:mm:ss';
     */
    var o = {
        'y+': this.getFullYear(),
        'M+': this.getMonth() + 1,
        'd+': this.getDate(),
        'h+': this.getHours(),
        'm+': this.getMinutes(),
        's+': this.getSeconds(),

        'q+': Math.floor((this.getMonth() + 3) / 3),
        'S': this.getMilliseconds()
    };


    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }


    for (var k in o) {
        if (new RegExp('(' + k + ')').test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
        }
    }
    return format;
};

