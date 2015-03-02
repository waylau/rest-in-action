Exploring the Newly Created Project 探索新项目
========================

`simple-service-webapp`这个是由 Jersey 提供 Maven archetype 用来创建的 web 项目，在你的项目里面随意调整 pom.xml 内的 groupId，包号和版本号就可以成为一个新的项目。此时，simple-service-webapp 已经创建， 符合 Maven 的项目结构：

* 标准的管理配置文件 pom.xml
* 源文件路径 src/main/java
* 资源文件路径 src/main/resources
* web 应用文件 src/main/webapp


该项目包含一个名为 MyResouce 的JAX-RS 资源类。在 src/main/webapp/WEB-INF 下，它包含了标准的JavaEE Web 应用的 web.xml 部署描述符。项目中的最后一个组件是一个 index.jsp 页面作为这次 MyResource 资源类打包和部署的应用程序客户端。

MyResource 类是 JAX-RS 的一个实现的源代码，如下： 

```java

	package com.waylau.rest;
	
	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	
	/**
	 * 根资源 (暴露在"myresource"路径)
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
	}

```

一个 JAX-RS 资源是一个可以处理绑定了资源的 URI 的HTTP请求的带有注解的 POJO。在我们的例子中，单一的资源暴露了一个公开的方法，能够处理HTTP GET请求，绑定在`/myresource` URI路径下，可以产生媒体类型为“text/plain”的响应消息。在这个示例中，资源返回相同的“Got it!”应对所有客户端的要求。

