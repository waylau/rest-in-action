package com.waylau.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 

/**
 * 测试用的 Resource
 * 
 * @author waylau.com
 * 2015年1月12日
 */
@Path("login")
public class LoginResource {

    /**
     * 方法处理 HTTP GET 请求. 返回给客户端的对象是
     * 转成了"text/plain" 媒体类型
     *
     * @return String ，转成了 text/plain 响应
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it! Welcome to waylau's REST world!";
    }
 
}
