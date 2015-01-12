集成 Jersey、Spring、Shiro
=============

在上面的 Jersey web 项目的基础上，添加 Spring、Shiro 的支持，实现初级的权限安全框架。

##增加 Spring、Shiro 的版本依赖
pom.xml 做如下修改:

`<properties>`中增加 Spring、Shiro 的版本

	<spring.version>4.1.4.RELEASE</spring.version>
	<shiro.version>1.2.3</shiro.version>

`<dependencies>`中增加 Spring、Shiro 的版本依赖


	<dependency>
		<groupId>org.glassfish.jersey.ext</groupId>
		<artifactId>jersey-spring3</artifactId>
		<version>${jersey.version}</version>
		<exclusions>

			<exclusion>
				<artifactId>jersey-server</artifactId>
				<groupId>org.glassfish.jersey.core</groupId>
			</exclusion>
			<exclusion>
				<artifactId>
            		jersey-container-servlet-core
        		</artifactId>
				<groupId>org.glassfish.jersey.containers</groupId>
			</exclusion>
			<exclusion>
				<artifactId>hk2</artifactId>
				<groupId>org.glassfish.hk2</groupId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-core</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-web</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-beans</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-orm</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-aop</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-aspects</artifactId>
			</exclusion>
			<exclusion>
				<groupId>org.springframework</groupId>
				<artifactId>spring-test</artifactId>
			</exclusion>

		</exclusions>
	</dependency>
	<!-- jersey end -->
	<!-- spring start -->

	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-core</artifactId>
		<version>${spring.version}</version>
	</dependency>

	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-orm</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-web</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-aop</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-beans</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-aspects</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-test</artifactId>
		<version>${spring.version}</version>
		<scope>test</scope>
	</dependency>
	<!-- spring end -->

	<!-- shiro start -->
	<dependency>
		<groupId>org.apache.shiro</groupId>
		<artifactId>shiro-core</artifactId>
		<version>${shiro.version}</version>
	</dependency>
	<dependency>
		<groupId>org.apache.shiro</groupId>
		<artifactId>shiro-spring</artifactId>
		<version>${shiro.version}</version>
	</dependency>
	<dependency>
		<groupId>org.apache.shiro</groupId>
		<artifactId>shiro-web</artifactId>
		<version>${shiro.version}</version>
	</dependency>
	<!-- shiro end -->

由于 Jersey 官方 只有 jersey-spring3 库是用 来对 Spring 进行集成支持的，所以我们在用 Spring 4.x 的依赖时，要将 jersey-spring3  中的 Spring 3 依赖剔除。

##配置 Spring

在 resource 目录下 增加一个 `applicationContext.xml`,这个就不详述了，搞过 Spring　的都知道，而后在　web.xml 中添加如下：

	<!-- spring start -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<filter>
		<description>处理编码的过滤器</description>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<!-- spring end -->

解释：`classpath:applicationContext.xml`,就是 Spring 配置文件的所在位置。`encodingFilter` 用来处理 编码的，这里是 UTF-8 来支持中文。

没错 ，Spring 的配置就是这么简单。

##问题

web.xml 可能会出现下面的问题：

	The content of element type "web-app" must match "(icon?,display-
	 name?,description?,distributable?,context-param*,filter*,filter-mapping*,listener*,servlet*,servlet-
	 mapping*,session-config?,mime-mapping*,welcome-file-list?,error-page*,taglib*,resource-env-
	 ref*,resource-ref*,security-constraint*,login-config?,security-role*,env-entry*,ejb-ref*,ejb-local-ref*)".

意思是 这个 web.xml  的解析文档太旧了，解决方法是 将原来的

	<!DOCTYPE web-app PUBLIC
	 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
	 "http://java.sun.com/dtd/web-app_2_3.dtd" >
	
	<web-app>

改为：

	<?xml version="1.0" encoding="UTF-8"?>
	
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns="http://xmlns.jcp.org/xml/ns/javaee"
		xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
		id="WebApp_ID" version="3.1">
	

##Shiro 在 Spring 的集成

为了方便管理，我们将 Shiro 的配置和 Spring 的常用配置分来处理。在相同路径下，复制一份 applicationContext.xml 更名为 applicationContext-shiro.xml。

修改 applicationContext.xml ，引用 Shiro 的配置

```xml

  	<!-- 引用各模块的spring配置文件 -->  
 	<import resource="applicationContext-shiro.xml"></import>  

```

修改  applicationContext-spring.xml：



在 web.xml 中定义下面的过滤器及过滤器映射：

	<!-- filter-name对应applicationContext.xml中定义的名字为“shiroFilter”的bean -->
	<filter>
	    <filter-name>shiroFilter</filter-name>
	    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	    <init-param>
	        <param-name>targetFilterLifecycle</param-name>
	        <param-value>true</param-value>
	    </init-param>
	</filter>

	<!-- 使用“/*”匹配所有请求，保证所有的可控请求都经过Shiro的过滤。通常这个filter-mapping
	放置到最前面(其他filter-mapping前面)，保证它是过滤器链中第一个起作用的 -->
	<filter-mapping>
	    <filter-name>shiroFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>
