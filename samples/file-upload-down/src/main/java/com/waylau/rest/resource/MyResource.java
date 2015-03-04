package com.waylau.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.waylau.rest.bean.MyBean;

/**
 * 根资源 (暴露在"myresource"路径)
 * 
 * @author waylau.com 
 * 2015-3-1
 */
@Path("myresource")
public class MyResource {

    /**
     * 方法处理 HTTP GET 请求。返回的对象以"text/plain"媒体类型
	 * 给客户端
     *
     * @return String 以 text/plain 形式响应
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    

    /**
     * 方法处理 HTTP GET 请求。返回的对象以"application/xml"媒体类型
	 * 给客户端
     *
     * @return MyPojo 以 application/xml 形式响应
     */
    @GET
    @Path("pojoxml")
    @Produces(MediaType.APPLICATION_XML)
    public MyBean getPojoXml() {
    	MyBean pojo = new MyBean();
    	pojo.setName("waylau.com");
    	pojo.setAge(28);
        return pojo;
    }
    
    /**
     * 方法处理 HTTP GET 请求。返回的对象以"application/json"媒体类型
	 * 给客户端
     *
     * @return MyPojo 以 application/json 形式响应
     */
    @GET
    @Path("pojojson")
    @Produces(MediaType.APPLICATION_JSON)
    public MyBean getPojoJson() {
    	MyBean pojo = new MyBean();
    	pojo.setName("waylau.com");
    	pojo.setAge(28);
        return pojo;
    }
 
    /**
     * 将接收到的参数组成一个新对象返回
     * 
     * @param age
     * @param name
     * @return MyPojo 以 application/json 形式响应
     */
    @PUT
    @Path("pojo")
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    public MyBean putPojo(@QueryParam("age") int age, 
    		@QueryParam("name") String name) {
    	MyBean pojo = new MyBean();
    	pojo.setName(name);
    	pojo.setAge(age);
        return pojo;
    }
    
    /**
     * 将接收到的参数组成一个新对象返回（含默认值）
     * 
     * @param age
     * @param name
     * @return MyPojo 以 application/json 形式响应
     */
    @POST
    @Path("pojo")
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    public MyBean postPojoDefault(@DefaultValue("21") @QueryParam("age") int age, 
    		@DefaultValue("www.waylau.com")@QueryParam("name") String name) {
    	MyBean pojo = new MyBean();
    	pojo.setName(name);
    	pojo.setAge(age);
        return pojo;
    }
    
    /**
     * 处理登录时的表单请求
     * 
     * @param username
     * @param password
     * @return
     */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("username") String username,
			@FormParam("password") String password) {
		//业务逻辑省略...
		return null;
	}
}
