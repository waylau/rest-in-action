Custom ResourceConfig 自定义资源配置
==========

##观察 web.xml

之前的 web.xml 配置是这样的：

	<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	    <servlet>
	        <servlet-name>Jersey Web Application</servlet-name>
	        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
	        <init-param>
	            <param-name>jersey.config.server.provider.packages</param-name>
	            <param-value>com.waylau.rest</param-value>
	        </init-param>
	        <load-on-startup>1</load-on-startup>
	    </servlet>
	    <servlet-mapping>
	        <servlet-name>Jersey Web Application</servlet-name>
	        <url-pattern>/webapi/*</url-pattern>
	    </servlet-mapping>
	</web-app>

其中 

    <init-param>
        <param-name>jersey.config.server.provider.packages</param-name>
        <param-value>com.waylau.rest</param-value>
    </init-param>

这段说的是，如果配置属性无需设置，要部署应用程序只包括存储在特定的包的资源和提供者，那么你可以指示 Jersey 自动扫描这些包，这样就能自动注册找到的任何资源和提供者,这样就能找到了  `com.waylau.rest`包下的 MyResource 资源并且注册。

##自定义配置

当需要更多的配置，上述方法显然不能满足。可以重写 [Application](http://jax-rs-spec.java.net/nonav/2.0/apidocs/javax/ws/rs/core/Application.html) 类。

	package com.waylau.rest;
	
	import org.glassfish.jersey.server.ResourceConfig;
	
	/**
	 * REST 主应用
	 * 
	 * @author waylau.com
	 * 2015年3月3日
	 */
	public class RestApplication extends ResourceConfig {
	
		public RestApplication() {
			//资源类所在的包路径  
		    packages("com.waylau.rest"); 
		}
	}

为了规范，我们在建立 `com.waylau.rest.resource` 包，用来专门放资源来。接着把我们的资源 MyResource 移到该包下面。

	public class RestApplication extends ResourceConfig {
	
		public RestApplication() {
			//资源类所在的包路径  
		    packages("com.waylau.rest.resource"); 
		}
	}

最终，RestApplication 在 web.xml 配置是这样的：

	<init-param>
	    <param-name>javax.ws.rs.Application</param-name>
	    <param-value>com.waylau.rest.RestApplication</param-value>
	</init-param>

##运行测试 

启动项目，访问 <http://localhost:8080/>,点击“Jersey resource”，显示“Got it!”，说明配置成功。

##源码

见`custom-resourceconfig` 项目


