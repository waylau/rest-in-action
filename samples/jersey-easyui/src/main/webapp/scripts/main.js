'use strict';
/**
 * 主应用启动所需方法
 *
 * Created by waylau.com on 2014/8/21.
 */

/**
 * 初始化导航菜单
 */

var url;
var type;
function newUser(){
    $('#dlg').dialog('open').dialog('setTitle','New User');
    $('#fm').form('clear');
    url = 'rest/hello/users';
    type = 'POST';
}
function editUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','Edit User');
        $('#fm').form('load',row);
        url = 'rest/hello/users/id='+row.id;
        type = 'PUT';
    }
}
function saveUser(){
    $.ajax({
        //请求方式为get
        type:type,
        //json文件位置
        url:url,
        //返回数据格式为json
        dataType: "json",
        contentType:"application/json",
        //请求成功完成后要执行的方法
        data:JSON.stringify($("#fm").serializeObject()) ,
        success: function( data ) {
        	getUsers();
        },
        error: function() {
            alert.show('失败');
        }
    });
}
function destroyUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
                $.post('destroy_user.php',{id:row.id},function(result){
                    if (result.success){
                        $('#dg').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
    }
}

function getUsers(){
	 $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"rest/hello/users",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function( data ) {
            if( data ){
            	 $('#dg').datagrid('loadData',data);
            }
        },
        error: function() {
            alert.show('失败');
        }
    });
}

$(function(){
	getUsers();
});

 