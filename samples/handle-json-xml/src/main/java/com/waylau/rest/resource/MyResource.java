package com.waylau.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    
}
