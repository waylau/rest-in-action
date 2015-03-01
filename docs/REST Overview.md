REST Overview
=========

##About REST

REST 是英文 Representational State Transfer 的缩写，有中文翻译为“具象状态传输”。REST 这个术语是由 [Roy Fielding](http://www.ics.uci.edu/~fielding/) 在他的博士论文 《 [Architectural Styles and the Design of Network-based Software Architectures](http://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm) 》中提出的。REST 并非标准，而是一种开发 Web 应用的架构风格，可以将其理解为一种设计模式。REST 基于 HTTP，URI，以及 XML 这些现有的广泛流行的协议和标准，伴随着 REST，HTTP 协议得到了更加正确的使用。

相较于基于 SOAP 和 WSDL 的 Web 服务，REST 模式提供了更为简洁的实现方案。目前，越来越多的 Web 服务开始采用 REST 风格设计和实现，真实世界中比较著名的 REST 服务包括：Google AJAX 搜索 API、Amazon Simple Storage Service (Amazon S3) 等。

基于 REST 的 Web 服务遵循一些基本的设计原则：

* 系统中的每一个对象或是资源都可以通过一个唯一的 URI 来进行寻址，URI 的结构应该简单、可预测且易于理解，比如定义目录结构式的 URI。
* 以遵循 RFC-2616 所定义的协议的方式显式地使用 HTTP 方法，建立创建、检索、更新和删除（CRUD：Create, Retrieve, Update and Delete）操作与 HTTP 方法之间的一对一映射：
* 若要在服务器上创建资源，应该使用 POST 方法；
* 若要检索某个资源，应该使用 GET 方法；
* 若要更改资源状态或对其进行更新，应该使用 PUT 方法；
* 若要删除某个资源，应该使用 DELETE 方法。

URI 所访问的每个资源都可以使用不同的形式加以表示（比如 XML 或者 JSON），具体的表现形式取决于访问资源的客户端，客户端与服务提供者使用一种内容协商的机制（请求头与 MIME 类型）来选择合适的数据格式，最小化彼此之间的数据耦合。

##Java REST

针对 REST 在 Java 中的规范，主要是 JAX-RS（Java API for RESTful Web Services）。Java EE 6 引入了对 [JSR-311](https://jsr311.java.net/) 的支持。JSR-311（JAX-RS：Java API for RESTful Web Services）旨在定义一个统一的规范，使得 Java 程序员可以使用一套固定的接口来开发 REST 应用，避免了依赖于第三方框架。同时，JAX-RS 使用 POJO 编程模型和基于标注的配置，并集成了 JAXB，从而可以有效缩短 REST 应用的开发周期。

JAX-RS 定义的 API 位于 javax.ws.rs 包中。

伴随着 JSR 311 规范的发布，Sun 同步发布该规范的参考实现 [Jersey](https://jersey.java.net/)。JAX-RS 的具体实现第三方还包括 Apache 的 [CXF](http://cxf.apache.org/) 以及 JBoss 的 [RESTEasy](http://resteasy.jboss.org/) 等。未实现该规范的其他REST 框架还包括 [SpringMVC](http://spring.io/) 等

##Why Jersey 

在 Java 中，既然 规范的制定者和实现者都是 Sun 公司（现在是 Oracle）,那么 Jersey 毫无疑问就是事实上的标准，对于 Java REST 的初学者来说尽量要跟着标准走。当然，所有规范的实现，在用法上基本上没有差别，只是相对来说 Jersey 的实现更全面一些。

本书所有的例子都是基于 Jersey 的，有关 Jersey 的参考，可见《[Jersey 2.x 用户指南](https://github.com/waylau/Jersey-2.x-User-Guide)》。