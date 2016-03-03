使用 Java SE 部署环境
====

上面几个例子，我们是使用了内嵌的 Tomcat 或者 Jetty 的服务器形式，用来运行和测试项目。最终，项目也会打包成 WAR 部署在 Tomcat 或者 Jetty 等 Servlet 容器中。这种部署形式被称为基于 Servlet 的部署（ Servlet-based Deployment）。这种部署环境也是最广泛使用的。

有时，我们会有这样的需求，当 Web 应用不是很复杂，对应用性能要求不是很高时，需要将 Http Server 内嵌在我们的 Java 程序中， 只要运行 Java 程序，相应的 Http Server 也就跟着启动了，而且启动速度很快。这就是本文所介绍的基于 Java SE 部署环境（Java SE Deployment）来提供 REST 服务。

## HTTP 服务器

基于 Java 的 HTTP 服务器展现了一种简约、灵活的部署 Jersey 应用程序的方式。HTTP 服务器通常是嵌入在应用程序中，并通过配置,以编程形式来启动。一般来说,Jersey 容器为特定的 HTTP 服务器提供了一个定制化的工厂方法，用来返回一个正确初始化的 HTTP 服务器实例。

下面展示了常见 HTTP 服务器的内嵌在 Jersey 应用中的使用方法：

### JDK Http Server

从 Java SE 6 开始,Java 运行时附带一个内置的轻量级的 HTTP 服务器。Jersey 通过  jersey-container-jdk-http 容器扩展模块，提供集成这个 Java SE HTTP 服务器。此时，不是直接创建 [HttpServer](http://docs.oracle.com/javase/6/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html) 实例,而是使用 [JdkHttpServerFactory](https://jersey.java.net/apidocs/2.21/jersey/org/glassfish/jersey/jdkhttp/JdkHttpServerFactory.html) 的 createHttpServer()方法,它根据 Jersey 容器配置和 Application 子类提供的初始化来创建 HttpServer 实例 。

创建一个 内嵌 Jersey 的jdk http server 非常简单:

Jersey 和 JDK HTTP Server 用法：

	URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
	ResourceConfig config = new ResourceConfig(MyResource.class);
	HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);

JDK HTTP 容器依赖：

	<dependency>
	    <groupId>org.glassfish.jersey.containers</groupId>
	    <artifactId>jersey-container-jdk-http</artifactId>
	    <version>2.21</version>
	</dependency>

### Grizzly HTTP Server

[Grizzly](http://grizzly.java.net/) 是一个建立在 Java [NIO](http://docs.oracle.com/javase/6/docs/api/java/nio/package-summary.html) 之上的支持多协议的框架。Grizzly 旨在简化强大的和可扩展的服务器开发。Jersey 提供了一个容器的扩展模块，可以使用 Grizzly 作为运行 JAX-RS 应用普通的 HTTP 容器支持。从 Grizzly 服务器运行 JAX-RS 或 Jersey 的应用是一种最轻量和最容易的方法，用来展现 RESTful 服务。

Grizzly 容器支持 HTTP 注射 Grizzly 的特性 org.glassfish.grizzly.http.server.Request 和org.glassfish.grizzly.http.server.Response 实例到 JAX-RS 和Jersey 应用资源和供应者。然而，由于 Grizzly 的 Request 是非代理性的，Grizzly Request的注入到单例（默认）的JAX-RS /和Jersey 提供者只可能通过 javax.inject.Provider 实例。（Grizzly Response会遭受同样的限制。）

Jersey 和 Grizzly HTTP Server 用法：

	URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
	ResourceConfig config = new ResourceConfig(MyResource.class);
	HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

容器扩展模块依赖要加入:

	<dependency>
	    <groupId>org.glassfish.jersey.containers</groupId>
	    <artifactId>jersey-container-grizzly2-http</artifactId>
	    <version>2.21</version>
	</dependency>

**注意**：通过[测试框架](https://jersey.java.net/documentation/latest/test-framework.html)， Jersey 使用 Grizzly 已经广泛的在项目单元和端到端进行了测试。

### Simple 服务器

[Simple](http://www.simpleframework.org/) 是一个框架允许开发者创建 HTTP 服务器，并嵌入到应用中。同样的，通过从  jersey-container-simple-http 容器扩展模块调用工厂方法实现创建服务器实例。

Simple 的框架支持 HTTP 容器注入 Simple 框架特性 的org.simpleframework.http.Request 和 org.simpleframework.http.Response 实例到 JAX-RS 和 Jersey 应用资源和供应者。

Jersey 和 Simple 框架用法：

	URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
	ResourceConfig config = new ResourceConfig(MyResource.class);
	SimpleContainer server = SimpleContainerFactory.create(baseUri, config);

容器扩展模块依赖要加入:
 
	<dependency>
	    <groupId>org.glassfish.jersey.containers</groupId>
	    <artifactId>jersey-container-simple-http</artifactId>
	    <version>2.21</version>
	</dependency>

**注意**：Simple HTTP 容器不支持部署在除了根路径是 ("/")以外的上下文路径。非根路径的上下文路径在部署中是被忽略的。

### Jetty HTTP Server

Jetty 是流行的 Servlet 容器和 HTTP 服务器。在此我们不深究 Jetty 作为 Servlet 容器的能力（尽管我们在我们的测试和实例使用它），因为作为基于 Servlet 部署模型并没有什么特别，具体会在 第4.7节，“[基于 Servlet 部署](https://jersey.java.net/documentation/latest/deployment.html#deployment.servlet)”部分进行描述。我们将在这里只重点描述如何使用 Jetty 的 HTTP 服务器。

Jetty HTTP 容器支持注入 Jetty 特性的org.eclipse.jetty.server.Request 和 org.eclipse.jetty.server.Response 实例到 JAX-RS 和 Jersey 应用资源和供应者。然而，由于 Jetty HTTP Request 是非代理性的，Jetty Request 的注入到单例（默认）的JAX-RS /和Jersey 提供者只可能通过 javax.inject.Provider 实例。（Jetty Response 会遭受同样的限制。）

Jersey 和 Jetty HTTP Server 用法：


	URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
	ResourceConfig config = new ResourceConfig(MyResource.class);
	Server server = JettyHttpContainerFactory.createServer(baseUri, config);

容器扩展模块依赖要加入（**译者注：**原文中依赖包有误，这里做了更正）:

	<dependency>
		<groupId>org.glassfish.jersey.containers</groupId>
		<artifactId>jersey-container-jetty-http</artifactId>
	    <version>2.21</version>
	</dependency>

**注意**：Jetty HTTP 容器不支持部署在除了根路径是 ("/")以外的上下文路径。非根路径的上下文路径在部署中是被忽略的。

## 构建 REST 程序

回顾之前的内容，从《[处理 JSON 和 XML](Handle JSON and XML.md)》的源代码，我们进行了修改：

### 实体

MyBean.java

	@XmlRootElement
	public class MyBean {
	
		private String name;
		private int age;
	 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}

MyBean 作为我们数据相应的实体。

### 资源

MyResource.java

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

分别向外暴露各种类型资源，包括：本文、XML、JSON

### 应用配置

RestApplication.java

	public class RestApplication extends ResourceConfig {
	
		public RestApplication() {
			// 资源类所在的包路径  
		    packages("com.waylau.rest.resource");
		    
		    // 注册 MultiPart
		    register(MultiPartFeature.class);
		}
	}

该配置说明了要扫描的资源包的路径`com.waylau.rest.resource`，以及支持 JSON 转换 MultiPartFeature

### 主应用

App.java

	public class App {
		// HTTP server 所要监听的 uri
		public static final String BASE_URI = "http://192.168.11.125:8081/";
	
		/**
		 * Main method.
		 * 
		 * @param args
		 * @throws IOException
		 */
		public static void main(String[] args) throws IOException {
	
			// 若使用 Jdk Http Server请去掉下面的注释
			// JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), new
			// RestApplication());
	
			// 若使用 Grizzly Http Server请去掉下面的注释
			// GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new
			// RestApplication());
	
			// 若使用 Simple Http Server请去掉下面的注释
			// SimpleContainerFactory.create(URI.create(BASE_URI), new
			// RestApplication());
			// 若使用 Jetty Http Server请去掉下面的注释
			JettyHttpContainerFactory.createServer(URI.create(BASE_URI),
					new RestApplication());
		}
	}

各种服务器的用法在上面已经说了，这里就不再解析。

## 源码

见 `javase-rest` 项目

##参考：

* [Jersey 2.x 用户指南](https://github.com/waylau/Jersey-2.x-User-Guide)
