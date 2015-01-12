用 Jersey 创建 REST 服务
=========

本项目实例，假设你已经具备了 Maven 的基础知识，如果是 Maven 新手，参见本文最后的[扩展阅读有关 Maven 的操作](#further)：

##初始化 Maven web 应用 

	mvn archetype:generate -DgroupId=com.waylau.rest -DartifactId=jersey2 -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false -DgroupId=com.waylau.rest -DartifactId=jersey-webapp -Dpackage=com.waylau.rest

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-01.jpg)

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-02.jpg)

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-03.jpg)

如果　没有未自动生成　java ,可以自己创建补齐。

导入 eclipse 中方便开发。

##添加 Tomcat 容器

可以内嵌 Jetty 、Tomcat 等 web　容器，这样就方便运行项目，进行调试

添加 maven 插件,嵌入 Tomcat 7
	
	<plugins>
	
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.2</version>
			<configuration>
				<source>1.7</source>
				<target>1.7</target>
			</configuration>
		</plugin>
	
		<plugin>
			<groupId>org.apache.tomcat.maven</groupId>
			<artifactId>tomcat7-maven-plugin</artifactId>
			<version>2.2</version>
			<configuration>
				<uriEncoding>UTF-8</uriEncoding>
				<path>/</path>
			</configuration>
		</plugin>
	</plugins>

修改  JUnit  的版本 ，如下：

	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.12</version>
		<scope>test</scope>
	</dependency>

进行编译

	mvn clean install

问题 

	The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-04.jpg)

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-05.jpg)

添加 

	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>javax.servlet-api</artifactId>
		<version>3.1.0</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>jsp-api</artifactId>
		<version>2.2</version>
		<scope>provided</scope>
	</dependency>


进行编译

	mvn clean install

运行 web 项目：

	mvn tomcat7:run

自从，一个简单的 maven web 项目已经完成。

##添加 Jersey，提供 REST 服务

添加 Jersey 模块

	<dependency>
		<groupId>org.glassfish.jersey.containers</groupId>
		<artifactId>jersey-container-servlet</artifactId>
		<version>${jersey.version}</version>
	</dependency>

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-moxy</artifactId>
		<version>${jersey.version}</version>
	</dependency>

这里用 jersey.version 变量来进行多个模块，相同版本的管理，其中 jersey-media-moxy 用来处理 json 的转换

在 pom.xml 中添加 

	<properties>
		<jersey.version>2.14</jersey.version>
	</properties>

此时，项目已经具备了开发 Jersey REST 的能力，下面添加代码

##一个 简单的 Jersey REST 应用

创建以下 java  文件

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-06.jpg)

RestApplication.java

	
	package com.waylau.rest;
	
	import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
	import org.glassfish.jersey.server.ResourceConfig;
	
	
	/** 
	 * REST 主应用 
	 * 
	 * @author waylau.com 
	 * 2015-1-12 
	 */  
	public class RestApplication extends ResourceConfig {  
	    public RestApplication() {  
	
	     //服务类所在的包路径  
	     packages("com.waylau.rest.resource");  
	     //注册 JSON 转换器  
	     register(MoxyJsonFeature.class);
	
	    }  
	}  

HelloResource.java

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
	 
	}

解释:在 hello 路径下，可以访问到 HelloResource 中的 getIt() 方法返回的资源。

在 web.xml 增加 Jersey 的过滤器

  
  	<!-- jersey start -->
	<servlet>
		<servlet-name>Jersey Web Application</servlet-name>
		<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
		<init-param>
			<param-name>javax.ws.rs.Application</param-name>
			<param-value>com.waylau.rest.RestApplication</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>Jersey Web Application</servlet-name>
		<url-pattern>/rest/*</url-pattern>
	</servlet-mapping>
	<!-- jersey end -->

解释：所有的Jersey REST 接口都发布到 `rest` 路径下。

ok ,服务端代码敲完，现在开发客户端，打开 index.jsp ，添加如下

	<a href="/rest/hello">hello world</a>

这句的含义是，调用 Jersey REST 发布的 `/rest/hello`接口

执行 `mvn tomcat7:run`

首页是这样的

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-07.jpg)

点击 `hello world`，就能调用 REST 接口了！

![](http://99btgc01.info/uploads/2015/01/jersey-webapp-08.jpg)

## <a name='further'>扩展阅读</a>

* 《Apache Maven 3.1.0 安装、部署、使用》:<http://www.waylau.com/apache-maven-3-1-0-installation-deployment-and-use/>
* 《使用maven编译Java项目》 <http://www.waylau.com/build-java-project-with-maven/>
* 《Jersey 2.x 用户指南》:<https://github.com/waylau/Jersey-2.x-User-Guide>
* 完整项目源码:<https://github.com/waylau/rest-in-action> 中的 `samples/jersey-webapp` 