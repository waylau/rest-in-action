package com.waylau.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
 
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.waylau.rest.entity.Response;
 
 

/**
 * 测试用的 Resource
 * 
 * @author waylau.com
 * 2015年1月12日
 */
@Path("login")
public class LoginResource {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("username") String username,
		        @FormParam("password") String password ){
		Subject currentUser = SecurityUtils.getSubject();
	    Response resp = new Response();
	    
		if ( !currentUser.isAuthenticated() ) {
		    //收集用户的主要信息和凭据，来自GUI中的特定的方式
		    //如包含用户名/密码的HTML表格，X509证书，OpenID，等。
		    //我们将使用用户名/密码的例子因为它是最常见的。.
			UsernamePasswordToken token = new UsernamePasswordToken(
					username, password );

		    //支持'remember me' (无需配置，内建的!):
		    //token.setRememberMe(true);

		    try {
		        currentUser.login( token );
		        resp = new Response();
		        resp.setToken(username + password);
		        resp.setId("001");
		        //无异常，说明就是我们想要的!
		    } catch ( UnknownAccountException uae ) {
		        //username 不存在，给个错误提示?
				uae.printStackTrace();
		    } catch ( IncorrectCredentialsException ice ) {
		        //password 不匹配，再输入?
				ice.printStackTrace();
		    } catch ( LockedAccountException lae ) {
		        //账号锁住了，不能登入。给个提示?
				lae.printStackTrace();
		    } catch ( AuthenticationException ae ) {
		        //未考虑到的问题 - 错误?
				ae.printStackTrace();
		    }
 
		}

        return  resp;
	}
	
 
	@Path("logout")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(){
		Subject currentUser = SecurityUtils.getSubject();
	 
		currentUser.logout();
		//调用subject.logout() 之后立即将用户引导到一个新页面，确保任何与安全相关的 Cookies 如期删除，
	    Response resp = new Response();
	    resp.setToken("");
        return  resp;
	}
    /**
     * 方法处理 HTTP GET 请求. 返回给客户端的对象是
     * 转成了"text/plain" 媒体类型
     *
     * @return String ，转成了 text/plain 响应
     */
    @GET
	@Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it! Welcome to waylau's REST world!";
    }
    
    @GET
	@Path("/hellotoken")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getHello() {
	    Response resp = new Response();
	    
        resp = new Response();
        resp.setToken("tooooken");
        resp.setUserid( "001");
        //无异常，说明就是我们想要的!
		   
        return  resp;
    }
	@POST
	@Path("/getloginpath")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getloginPath(@QueryParam("username") String username,
			@QueryParam("password") String password){
	    Response resp = new Response();
	    
        resp = new Response();
        resp.setToken(username + password);
        resp.setUserid( "001");
        //无异常，说明就是我们想要的!
		   
        return  resp;
	}
	
	@POST
	@Path("/getlogin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getlogin(@FormParam("username") String username,
	        @FormParam("password") String password){
	    Response resp = new Response();
	    
        resp = new Response();
        resp.setToken(username + password);
        resp.setUserid( "001");
        //无异常，说明就是我们想要的!
		   
        return  resp;
	}
}
