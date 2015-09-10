package com.waylau.rest.resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
/**
 * 根资源 (暴露在"index"路径)
 * 
 * @author waylau.com 
 * 2015-9-7
 */
@Path("index")
public class IndexResource {

    /**
     * 方法处理 HTTP GET 请求。返回的对象以"text/plain"媒体类型
	 * 给客户端
     *
     * @return String 以 text/plain 形式响应
     */
    @GET
    public Viewable index()
    {
      return new Viewable("/index.jsp",null);
    }
    

    
}
