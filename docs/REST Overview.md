REST Overview
=========

## About REST

REST 是英文 Representational State Transfer 的缩写，有中文翻译为“表述性状态转移”。REST 这个术语是由 [Roy Fielding](http://www.ics.uci.edu/~fielding/) 在他的博士论文 《 [Architectural Styles and the Design of Network-based Software Architectures](http://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm) 》中提出的。REST 并非标准，而是一种开发 Web 应用的架构风格，可以将其理解为一种设计模式。REST 基于 HTTP，URI，以及 XML 这些现有的广泛流行的协议和标准，伴随着 REST，HTTP 协议得到了更加正确的使用。

相较于基于 SOAP 和 WSDL 的 Web 服务，REST 模式提供了更为简洁的实现方案。REST Web 服务(RESTful web services)是松耦合的，这特别适用于为客户创建在互联网传播的轻量级的 Web 服务 API。REST 应用是围绕“资源表述的转移（the transfer of representations of resources）”为中心来做请求和响应。数据和功能均被视为资源，并使用统一资源标识符（URI）来访问资源。网页里面的链接就是典型的 URI。该资源由文档表述，并通过使用一组简单的、定义明确的操作来执行。

例如，一个 REST 资源可能是一个城市当前的天气情况。该资源的表述可能是一个XML文档，图像文件，或 HTM L页面。客户端可以检索特定表述，通过更新其数据修改的资源，或者完全删除该资源。

目前，越来越多的 Web 服务开始采用 REST 风格设计和实现，真实世界中比较著名的 REST 服务包括：Google AJAX 搜索 API、Amazon Simple Storage Service (Amazon S3) 等。

基于 REST 的 Web 服务遵循一些基本的设计原则，使得 RESTful 应用更加简单、轻量，开发速度也更快：

* **通过 URI 来标识资源**:系统中的每一个对象或是资源都可以通过一个唯一的 URI 来进行寻址，URI 的结构应该简单、可预测且易于理解，比如定义目录结构式的 URI。
* **统一接口**：以遵循 RFC-2616 所定义的协议的方式显式地使用 HTTP 方法，建立创建、检索、更新和删除（CRUD：Create, Retrieve, Update and Delete）操作与 HTTP 方法之间的一对一映射：
  * 若要在服务器上创建资源，应该使用 POST 方法；
  * 若要检索某个资源，应该使用 GET 方法；
  * 若要更新或者添加资源，应该使用 PUT 方法；
  * 若要删除某个资源，应该使用 DELETE 方法。
* **资源多重表述**:URI 所访问的每个资源都可以使用不同的形式加以表示（比如 XML 或者 JSON），具体的表现形式取决于访问资源的客户端，客户端与服务提供者使用一种内容协商的机制（请求头与 MIME 类型）来选择合适的数据格式，最小化彼此之间的数据耦合。*在 REST 的世界中，资源即状态，而互联网就是一个巨大的状态机，每个网页是其一个状态；URI 是状态的表述；REST 风格的应用则是从一个状态迁移到下一个状态的状态转移过程。早期互联网只有静态页面的时候，通过超链接在静态网页间浏览跳转的 page->link->page->link… 模式就是一种典型的状态转移过程。也就是说早期的互联网就是天然的 REST*
* **无状态**:对服务器端的请求应该是无状态的，完整、独立的请求不要求服务器在处理请求时检索任何类型的应用程序上下文或状态。无状态约束使服务器的变化对客户端是不可见的，因为在两次连续的请求中，客户端并不依赖于同一台服务器。一个客户端从某台服务器上收到一份包含链接的文档，当它要做一些处理时，这台服务器宕掉了，可能是硬盘坏掉而被拿去修理，可能是软件需要升级重启——如果这个客户端访问了从这台服务器接收的链接，它不会察觉到后台的服务器已经改变了。通过超链接实现有状态交互，即请求消息是自包含的（每次交互都包含完整的信息），有多种技术实现了不同请求间状态信息的传输，例如 URI 重新，cookies 和隐藏表单字段等，状态可以嵌入到应答消息里，这样一来状态在接下来的交互中仍然有效。*REST 风格应用可以实现交互，但它却天然地具有服务器无状态的特征。在状态迁移的过程中，服务器不需要记录任何 Session，所有的状态都通过 URI 的形式记录在了客户端。更准确地说，这里的无状态服务器，是指服务器不保存会话状态(Session)；而资源本身则是天然的状态，通常是需要被保存的；这里所指无状态服务器均指无会话状态服务器。*

<table border="1">
<caption>HTTP 请求方法在RESTful Web 服务中的典型应用</caption>
<tbody><tr>
<th>资源</th>
<th>GET</th>
<th>PUT</th>
<th>POST</th>
<th>DELETE</th>
</tr>
<tr>
<th>一组资源的URI，比如<code>http://www.waylau.com/resources/</code></th>
<td><b>列出</b> URI，以及该资源组中每个资源的详细信息（后者可选）。</td>
<td>使用给定的一组资源<b>替换</b>当前整组资源。</td>
<td>在本组资源中<b>创建/追加</b>一个新的资源。 该操作往往返回新资源的URL。</td>
<td><b>删除</b> 整组资源。</td>
</tr>
<tr>
<th>单个资源的URI，比如<code>http://www.waylau.com/resources/142</code></th>
<td><b>获取</b> 指定的资源的详细信息，格式可以自选一个合适的网络媒体类型（比如：XML、JSON等）</td>
<td><b>替换/创建</b> 指定的资源。并将其追加到相应的资源组中。</td>
<td>把指定的资源当做一个资源组，并在其下<b>创建/追加</b>一个新的元素，使其隶属于当前资源。</td>
<td><b>删除</b> 指定的元素。</td>
</tr>
</tbody>
</table>

## Java REST

针对 REST 在 Java 中的规范，主要是 JAX-RS（Java API for RESTful Web Services），该规范使得 Java 程序员可以使用一套固定的接口来开发 REST 应用，避免了依赖于第三方框架。同时，JAX-RS 使用 POJO 编程模型和基于标注的配置，并集成了 JAXB，从而可以有效缩短 REST 应用的开发周期。Java EE 6 引入了对 [JSR-311](https://jsr311.java.net/) 的支持，Java EE 7 支持[JSR-339](http://jcp.org/en/jsr/detail?id=339) 规范。

JAX-RS 定义的 API 位于 javax.ws.rs 包中。

伴随着 JSR 311 规范的发布，Sun 同步发布该规范的参考实现 [Jersey](https://jersey.java.net/)。JAX-RS 的具体实现第三方还包括 Apache 的 [CXF](http://cxf.apache.org/) 以及 JBoss 的 [RESTEasy](http://resteasy.jboss.org/) 等。未实现该规范的其他 REST 框架还包括 [SpringMVC](http://spring.io/) 等。

截至目前， JAX-RS 最新的版本是 2.0 （[JSR-339](http://jcp.org/en/jsr/detail?id=339)）

## Why Jersey 

在 Java 中，既然 规范的制定者和实现者都是 Sun 公司（现在是 Oracle）,那么 Jersey 毫无疑问就是事实上的标准，对于 Java REST 的初学者来说尽量要跟着标准走。当然，所有规范的实现，在用法上基本上没有差别，只是相对来说 Jersey 的实现更全面一些。

本书所有的例子都是基于 Jersey 的，有关 Jersey 的参考，可详见《[Jersey 2.x 用户指南](https://github.com/waylau/Jersey-2.x-User-Guide)》。
