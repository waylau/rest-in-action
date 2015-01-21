package com.waylau.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.waylau.rest.entity.User;
 

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
        return "Got it! Welcome to waylau's REST world!";
    }
    
    /**
     * 返回  xml 类型
     * 
     * @return
     */
    @GET
    @Path("xml")
    @Produces(MediaType.APPLICATION_XML)
    public User getXml() {
    	User user = new User(1,"waylau","23");
        return user;
    }
    
    /**
     * 返回  json 类型
     * 
     * @return
     */
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public User getJson() {
    	User user = new User(1,"waylau","23");
        return user;
    }
    
    /**
     * 返回  json 类型 的 列表
     * 
     * @return
     */
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
    	List<User> users = new ArrayList<User>();
    	
    	users.add(new User(1,"waylau","23"));
    	users.add(new User(2,"waylau2","24"));
    	users.add(new User(3,"waylau3","25"));
 
        return users;
    }
    
    /**
     * 根据 id 参数 返回 json 对象
     * 
     * @param id
     * @return
     */
    @GET
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) {
    	User user = new User(1,"waylau","23");
    	user.setId(id);
        return user;
    }
    
    /**
     * 根据 id ，json 对象，返回 json 对象
     * 
     * @param id
     * @param user
     * @return
     */
    @PUT
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User editUserById(@PathParam("id") int id,User user) {
    	user.setUsername(user.getUsername()+"_eidt");
        return user;
    }
    
    /**
     * 根据 json 对象，返回 json 列表
     * 
     * @param user
     * @return
     */
    @POST
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> addUserd(User user) {
    	List<User> users = new ArrayList<User>();
    	
    	users.add(new User(1,"waylau","23"));
    	users.add(new User(2,"waylau2","24"));
    	users.add(new User(3,"waylau3","25"));
    	users.add(user);
        return users;
    }
}
