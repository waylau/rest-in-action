Encoding Format 处理编码问题
=========

一般的，我们都会将项目和代码设置为 UTF-8 编码格式，但有时候还远远不够。

我们在 `servlet-container`项目的基础上，进行修改成为另外一个新项目。

将  MyResource.java 改为如下：

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
	        return "你好，欢迎访问 www.waylau.com！";
	    }
	}

启动项目，访问浏览器 <http://localhost:8080/>，
点击“Jersey resource”，显示如下，中文已乱。

![](../images/encoding-format-01.jpg)
