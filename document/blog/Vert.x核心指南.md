名词解释：

Verticles：

Datagram Socket：



Vert.x的核心是一系列我们称为Vert.x Core 的Java APIs。

Vert.x Core 提供如下功能：

- 编写TCP客户端与服务器
- 编写HTTP客户端与服务端（包括WebSocket）
- 事件总线
- 共享数据——本地maps和集群中的分布式maps
- 定时和延时动作
- 发布和卸载 Verticles
- Datagram Socket
- DNS客户端
- 文件系统操作
- 高可用
- 本地传输
- 集群

核心包提供的功能是相当底层的。没有诸如数据库存储、权限认证、高级Web功能此类的组件，这些功能都在Vert.x ext（扩展包）中。

Vert.x Core小且轻量 。它可以整个的嵌入你现有的应用当中，不需要为了使用Vert.x而以特别的方式组织你的应用。 

你可以在任何Vert.x支持的语言中使用核心包。但是有一点要提一下，我们不会迫使你在Javascript或者Ruby里使用为Java准备的API；毕竟不同的语言有不同的约定和惯用法，强迫Ruby开发者使用Java的惯用法确实比较古怪。相反的，我们为每一种语言都生成了等价于核心Java API的**惯用法(idiomatic)**。

现在开始，我们将使用核心包（**core**）指代Vert.x core。 

如果你使用Maven或Gradle，把下列几行加入项目描述符的依赖配置即可使用核心包的API： 

- Maven（在pom文件中）

``` xml
        <dependency>
            <groupId>io.vertx</groupId>
            <artifactId>vertx-core</artifactId>
            <version>4.0.0</version>
        </dependency>
```

- gradle（在build.gradle文件中）

```kotlin
compile io.vertx:vertx-core:4.0.0
```

下面让我们看看核心包里的各种特性。 

# 开始使用Vert.x

在Vert.x里，如果没有[Vertx](https://link.jianshu.com/?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html)对象，那几乎什么都做不了！ 

Vertex对象是Vert.x的控制中心，许多功能都通过它实现。包括创建客户端和服务器、获取事件总线（event bus）的引用、设置定时器，等等。 

那么，如何获取它的实例呢？ 

如果你在程序中嵌入Vert.x，那么可以像下面这样创建实例： 

``` java
Vertx vertx = Vertx.vertx();
```

****

> **注意** 
>
> 绝大部分应用其实只需要一个Vert.x实例；当然，你也可以创建多个实例。例如，两个总线需要隔离时或客户端服务器需分组时。 

## 创建Vert.x实例并修改默认配置

创建Vert.x实例时，如果缺省选项不合适，也可以自己设定：

``` java
Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
```

[VertxOptions](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/VertxOptions.html)对象有很多设置项，你可以配置集群（clustering）、高可用性（high availability），worker 线程池的大小（pool sizes）等等。详细的内容请参见Javadoc。

## 创建集群模式（clustered）的Vert.x对象

如果你正在创建一个Vert.x的集群（ 有关cluster event bus的更多信息，请参阅有关 [event bus](https://vertx.io/docs/vertx-core/java/#event_bus) 的章节 ） , then you will normally use the asynchronous variant to create the Vertx object. 

 为了把集群里不同的vertx实例组织在一起，通常需要花一点时间（可能是几秒钟）。在这段时间里，为了不阻塞调用线程（the calling thread），结果会以异步的方式返回。 

# 你是流式编程的爱好者吗？（Are you fluent?）

你可能已经注意到，在前面的例子中，我们使用了**流式(fluent)**的API。

流式API是指多个方法可以用链式的方式一起调用。例如：

``` java
request.response().putHeader("Content-Type", "text/plain").end("some text");
```

这在Vert.x的API里是很普遍的模式，你要试着习惯它。 :)

链式调用允许你更简洁的编写代码。当然，如果你不喜欢这种方式，**这也不是必须的**。你可以愉快地忽略这些，然后像下面这样写：

```java
HttpServerResponse response = request.response();
response.putHeader("Content-Type", "text/plain");
response.write("some text");
response.end();
```

# 不要调用我们，我们会调用你（Don't call us, we'll call you.）

大部分Vert.x API 都是事件驱动的。这意味着如果你对Vert.x里发生的某事感兴趣，Vert.x会以向你发送事件（events）的方式通知你。 

 例如下面的事件： 

- 定时器被触发
- socket收到一些数据
- 从磁盘读取一部分数据
- 发生异常
- HTTP服务器接收到请求

 通过提供handlers，你可以处理这些事件。例如定义一个定时器事件： 

```java
vertx.setPeriodic(1000, id -> {
  // This handler will get called every second
  System.out.println("timer fired!");
});
```

 或者接受一个HTTP请求： 

```java
server.requestHandler(request -> {
  // This handler will be called every time an HTTP request is received at the server
  request.response().end("hello world!");
});
```

如果触发了某个事件，Vert.x将会**异步地(asynchronously)**调用它（the handler）。

这里我们发现了Vert.x的如下重要概念。

# 不要阻塞我！（Don't block me!）

除了极少的例外（即某些以‘Sync’结尾的文件系统操作），Vert.x里没有API会阻塞调用线程。

如果结果可以即刻获得，它会立刻被返回。否则，通常你需要提供一个处理器，以便稍后接收事件。

没有API会阻塞线程意味着：用少量的线程，就可以处理大量的并发。

传统的阻塞API可能会在哪些地方发生呢： 

- 从socket读取数据
- 写数据到磁盘
- 发消息给某个接收者，然后等待回应
- 。。。其他情况

在上面这些案例中，你的线程在等待一个结果时不能做其他任何事，这样是很低效的。

这也意味着，如果你想使用阻塞API处理大量并发，你将需要大量的线程来防止你的应用卡住。

线程在内存（例如：栈）和上下文切换方面的开销不容忽视。

以很多现代的应用所需求的并发级别，阻塞的方式根本实现不了。

# 反应堆和多反应堆

前面我们提到了Vert.x的API是事件驱动的，当handlers可用的时候，Vert.x会向它们传递事件。

绝大多数情况下，Vert.x通过一个叫**event loop**的线程调用你的handlers。

event loop可以在事件到达时持续不断地将其分发给不同的handler，因为Vert.x和你的应用中不会有什么是阻塞的。

同样，因为没什么是阻塞的，所以event loop具有在短时间内分发巨量事件的潜力。例如，单个event loop可以极迅速地处理数千的HTTP请求。

我们称之为[Reactor模式](https://link.jianshu.com?t=http://en.wikipedia.org/wiki/Reactor_pattern)。

你之前可能已经听说过它--nodejs就实现了这种模式。

标准的Reactor实现里，有一个**单独的event loop(single event loop)**线程，它会在所有事件到达时持续不断地将其分发给所有的handler。

单一线程的困扰在于，在任意时刻，它只能在一个cpu核心上运行。所以如果你希望你的单线程Reactor应用（或者Nodejs应用）能够运用上多核服务器的扩展能力（ scale over your multi-core server ），你需要启动多个进程并管理好它们。

Vert.x的工作方式不同于此，每个vertx实例会维护**数个event loop(several event loops)**。缺省情况下，我们基于机器上可用的核心数来确定这个数字，当然这个也可以设置。

不像Nodejs，这意味着单个Vert.x进程可以利用到服务器的扩展。

为了与单线程的reactor模式区分开，我们称之为**Multi-Reactor模式**。

> **注意**
>
> 虽然一个Vert.x实例会维护多个event loop，但任何特定的handler都绝不会被并发地执行，在绝大多数情况下(worker verticle除外),它都会被**某个固定的event loop（exact same event loop）**调用。

## 黄金准则：不要阻塞Event Loop（Don’t Block the Event Loop）



## 事件总线(The Event Bus)

event bus是Vert.x的神经系统。

每个Vert.x实例都拥有单独的一个event bus实例，你可以通过[eventBus](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#eventBus--)方法得到它。

应用的不同部分，不管是否在同一个Vert.x实例里，即使是不同语言编写的，都可以通过event bus彼此交流。

甚至浏览器里运行的的客户端JavaScript也可以通过同一个event bus相互通信。

event bus在多个服务器和多个浏览器间形成了一个分布式的点对点消息系统。

event bus支持发布/订阅(publish/subscribe)、点对点、请求-响应(request-response)这三种消息模式。

event bus的API很简单，主要包括注册handlers，注销handlers，发送和发布消息。

## 基本概念

### 寻址（Addressing）

消息通过event bus发送到某个**地址(address)**。

Vert.x没有花哨的令人困扰的寻址方案。Vert.x里地址就是字符串。任意字符串都有效。不过使用某种命名策略还是很明智的，例如使用分隔符限定命名空间。

这里是一些有效的地址：europe.news.feed1, acme.games.pacman, sausages, and X。

#### 处理器(Handlers)

消息由handlers接收，所以你需要把handler注册到地址上。

同一个地址可以注册多个不同的handler。

某个handler也可以被注册在多个不同的地址上。

# 通过Vert.x操作文件系统

