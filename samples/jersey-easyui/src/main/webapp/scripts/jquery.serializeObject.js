'use strict';
/**
 * 基于jquery 扩展的 json 转换器
 *
 * 用法：JSON.stringify($("#form").serializeObject());
 *
 * Created by waylau.com on 2014/8/21.
 * update by waylau.com on 2014-11-7.
 */

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
;