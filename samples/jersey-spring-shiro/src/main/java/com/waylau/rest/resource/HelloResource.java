package com.waylau.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
 

/**
 * 测试用的 Resource
 * 
 * @author waylau.com
 * 2015年1月12日
 */
@Path("hello")
public class HelloResource {

    /**
     * 方法处理 HTTP GET 请求. 返回给客户端的对象是
     * 转成了"text/plain" 媒体类型
     *
     * @return String ，转成了 text/plain 响应
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	System.out.println("helloword!");
        return "Got it! Welcome to waylau's REST world!";
    }
    
    @POST
	@Path("/formuser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String formUser(@FormParam("username") String username,
	        @FormParam("password") String password) {
    	System.out.println("helloword!"+username+password);
        return "helloword!"+username;
    }
    
    @POST
	@Path("/queryuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String queryUser(@QueryParam("username") String username,
	        @QueryParam("password") String password) {
    	System.out.println("helloword!"+username+password);
        return "helloword!"+username;
    }
}
