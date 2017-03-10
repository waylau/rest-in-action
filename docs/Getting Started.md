Getting Started 开始
========================

本章通过简单的示例带你快速入门。当你读完本章节，你马上就可以用 Jersey 写出 Web 应用。

##Before Getting Started 开始之前

因为我们的示例都是通过 Maven 进行管理，所谓，在开始之前，假设你已经具备 Maven 的基础知识。如果你是 Maven 新手，可以参考<http://www.waylau.com/apache-maven-3-1-0-installation-deployment-and-use/> 进行 Maven 的安装，参考<http://www.waylau.com/build-java-project-with-maven/>快速进行 Maven 入门。 

##Requirements 需要环境

* JDK 7+
* Maven 3.2.x

OK,这就是所有必需的环境。当然，你可以根据自己的喜好选择使用 IDE。本书使用 [Eclipse](http://www.eclipse.org/) 4.4。

##First REST App

在工作目录，创建第一个 Maven 管理的应用，执行 

```
mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-webapp -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=com.waylau -DartifactId=simple-service-webapp -Dpackage=com.waylau.rest -DarchetypeVersion=2.16
```



项目打包成 WAR,执行:

```
mvn clean package
```

打包成功后，打包的 WAR（位于`./target/simple-service-webapp.war`）可以将它部署到您任意的 Servlet 容器，比如 [Tomcat](http://tomcat.apache.org/)、 [Jetty](http://www.eclipse.org/jetty/)、JBoss 等。
 
![target](http://i1288.photobucket.com/albums/b484/waylau/waylau%20blog/Jersey-2-User-Guide/14-002_zps4abe828a.jpg)

浏览器访问该项目

![Servlet](http://i1288.photobucket.com/albums/b484/waylau/waylau%20blog/Jersey-2-User-Guide/14-003_zpsea860000.jpg)

点击“Jersey resource”,可以在页面输出资源“Got it!”

![got it](http://i1288.photobucket.com/albums/b484/waylau/waylau%20blog/Jersey-2-User-Guide/14-004_zpse1995c15.jpg)

**注意**：部署 Jersey 项目，Servlet 容器版本应该是不低于2.5，如果想支持更高的特性(比如 JAX-RS 2.0 Async Support) ，Servlet容器版本应该是不低于3.0

自此，第一个 REST 项目完成。

##Source code 源码

见 `simple-service-webapp`。
