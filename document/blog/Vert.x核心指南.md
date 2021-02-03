---
title: Vert.x Core
tags:
  - core
categories:
  - Vert.x
date: 2021-02-03 22:16:03
---



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

我们已经了解了，Vert.x的API是非阻塞的，不会阻塞event loop；但是，如果你在handler中**自己(yourself)**阻塞了event loop，那么上面的其实都没啥用。

如果你这么干了，那么event loop被阻塞的时候它啥都干不了。再如果你阻塞了Vert.x实例里所有的event loop，那你的应用将会陷入完全停滞的状态！

所以千万别这么干！**我们已经警告过你了哈(You have been warned)**。

阻塞的例子包括：

- Thread.sleep()
- 等待一个锁
- 等待一个同步锁或监视器（例如同步块（synchronized section））
- 做一个耗时的数据库操作并等待结果
- 做一个复杂的计算，耗费大量的时间
- 在循环中（Spinning in a loop）

如果上面任何一步挂住了event loop，让它花了**大量时间( significant amount of time)**,那么你只能安心等待程序执行。

那么，什么样是**大量时间( significant amount of time)**呢？

这个时间，实际上取决于你的应用对并发量的需求。

如果你有单一的event loop并且想每秒处理一万个http请求，那么很明显，处理每个请求的时间不能超过0.1毫秒，所以阻塞不能超过这个时间。

**这里面的计算不难，作为练习，我们将之留给读者(The maths is not hard and shall be left as an exercise for the reader)。**

如果你的应用没有响应了，这可能是event loop被阻塞的信号。为了帮助诊断这样的问题，Vert.x会在检测到某个event loop一段时间后还未返回时自动打印警告日志。如果在你的日志中看见这样的警告，那你可得调查调查了。
`Thread vertx-eventloop-thread-3 has been blocked for 20458 ms`

为了精确定位阻塞发生在何处，Vert.x也会提供堆栈跟踪消息。

如果你想关闭这些警告或改变设置，可以在创建Vertx对象前，去[VertxOptions](https://link.jianshu.com/?t=http://vertx.io/docs/apidocs/io/vertx/core/VertxOptions.html)对象里设置。

## 预期结果（Future results）

Vert.x使用`futures`表示异步结果。

任何一个异步方法返回一个Future对象来表示方法调用后执行成功或者失败的结果。

您不能直接与future的结果交互，相反，您需要设置一个处理程序，当future完成并且结果可用时，将调用该处理程序，就像任何其他类型的事件一样。

``` java
    @Test
    public void test() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        Future<FileProps> future = fileSystem.props("D:\\迅雷下载\\aaa.mkv");
        future.onComplete(filePropsAsyncResult -> {
            if (filePropsAsyncResult.succeeded()) {
                FileProps result = filePropsAsyncResult.result();
                System.out.println("File size = " + result.size());
            } else {
                System.out.println("Failure: " + filePropsAsyncResult.cause().getMessage());
            }
        });
        TimeUnit.SECONDS.sleep(1);
    }
```

> **注意**
>
> Vert.x 3 provides a callback-only model. To allow an easy migration to Vert.x 4 we decided that each asynchronous method has also a callback version. The `props` method above has also a version `props` with a callback as method argument.

# 预期结果组合

`compose`方法可用于链式处理future：

- 如果当前的future成功时，应用给定的函数，它将返回一个future。当返回的future完成时，组合成功。
- 当当前future失败时，组合失败

```java
    @Test
    public void compose() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        FileSystem fs = vertx.fileSystem();
        Future<Void> compose = fs.createFile("D:\\迅雷下载\\bbb.txt")
                .compose(v -> {
                    return fs.writeFile("D:\\迅雷下载\\bbb.txt", Buffer.buffer("Hello, Vert.x"));
                }).compose(v -> {
                    return fs.move("D:\\迅雷下载\\bbb.txt", "C:\\");
                });
        boolean succeeded = compose.succeeded();
        System.out.println(succeeded);

        TimeUnit.SECONDS.sleep(2);
    }
```

在这个例子中，3个操作被联系在一起：

1. 创建一个文件
2. 数据写入这个文件
3. 移走文件

当这三个步骤成功时，最后的未来(future)将会成功。然而，如果其中一个步骤失败，最后的future也会失败。

除此之外，future提供更多：map、recover、otherwise，甚至是作为compose的别名的flatMap。

# 异步协同

多个异步结果的协同可以由Vert.x的[futures](https://link.jianshu.com/?t=http://vertx.io/docs/apidocs/io/vertx/core/Future.html)来实现。它支持并发组合(并行运行多个异步操作)和顺序组合(链式异步操作)。

[CompositeFuture.all](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/CompositeFuture.html#all-io.vertx.core.Future-io.vertx.core.Future-)接受数个future参数（到6为止）并返回一个future；当所有的future都成功了，就返回*成功(succeeded)*的future，否则返回*失败(failed)*的future：

```java
    @Test
    public void futureCoordination() {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        NetServer netServer = vertx.createNetServer();

        Future<HttpServer> httpServerFuture = httpServer.listen();
        Future<NetServer> netServerFuture = netServer.listen();

        CompositeFuture.all(httpServerFuture, netServerFuture.onComplete(ar -> {
            if (ar.succeeded()) {
                // All servers started
            } else {
                // At least one server failed
            }
        }));
    }
```

The operations run concurrently, the `Handler` attached to the returned future is invoked upon completion of the composition. When one of the operation fails (one of the passed future is marked as a failure), the resulting future is marked as failed too. When all the operations succeed, the resulting future is completed with a success.

或者，你也可以传递一个期货的列表(可能为空):

```
CompositeFuture.all(Arrays.asList(future1, future2, future3));
```

[CompositeFuture.any](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/CompositeFuture.html#any-io.vertx.core.Future-io.vertx.core.Future-)接受数个future参数（到6为止）并返回一个future；只要有一个future成功了，那返回的future也*成功(succeeded)*，否则就*失败(failed)*：

```java
CompositeFuture.any(future1, future2).onComplete(ar -> {
  if (ar.succeeded()) {
    // At least one is succeeded
  } else {
    // All failed
  }
});
```

Future的列表也可以使用:

```java
CompositeFuture.any(Arrays.asList(f1, f2, f3));
```

join组合会一直等待，直到所有的future完成，或者成功，或者失败。
CompositeFuture.join接受几个期货参数(最多6个)，当所有期货都成功时返回一个成功的期货，当所有期货都完成且至少有一个失败时返回一个失败的期货:

```java
CompositeFuture.join(future1, future2, future3).onComplete(ar -> {
  if (ar.succeeded()) {
    // All succeeded
  } else {
    // All completed and at least one failed
  }
});
```

Future的列表也可以使用:

```java
CompositeFuture.join(Arrays.asList(future1, future2, future3));
```

### CompletionStage互操作性

Vert.x 的Future API提供了与CompletionStage之间的兼容性，CompletionStage是用于可组合异步操作的JDK接口。

可以通过 `toCompletionStage`方法将Vert.x 的future转换为`CompletionStage` ，例如：

```java
Future<String> future = vertx.createDnsClient().lookup("vertx.io");
future.toCompletionStage().whenComplete((ip, err) -> {
  if (err != null) {
    System.err.println("Could not resolve vertx.io");
    err.printStackTrace();
  } else {
    System.out.println("vertx.io => " + ip);
  }
});
```

相反，我们可以将`CompletionStage` 转换为Vert.x 的Future来使用。有两种变体：

1. 第一个变体只接受CompletionStage，并从解析CompletionStage实例的线程调用Future方法。
2. the second variant takes an extra `Context` parameter to call the `Future` methods on a Vert.x context.

> | Important                                                    |
> | ------------------------------------------------------------ |
> | In most cases the variant with a `CompletionStage` and a `Context` is the one you will want to use to respect the Vert.x threading model, since Vert.x `Future` are more likely to be used with Vert.x code, libraries and clients. |

Here is an example of going from a `CompletionStage` to a Vert.x `Future` and dispatching on a context:

```java
Future.fromCompletionStage(completionStage, vertx.getOrCreateContext())
  .flatMap(str -> {
    String key = UUID.randomUUID().toString();
    return storeInDb(key, str);
  })
  .onSuccess(str -> {
    System.out.println("We have a result: " + str);
  })
  .onFailure(err -> {
    System.err.println("We have a problem");
    err.printStackTrace();
  });
```

## Verticles

Vert.x有一个简单的、可扩展的，类似actor的部署方式（actor-like deployment ）和开箱即用的并发模型，这方面可以节省下你亲自动手的时间精力。

**这个模型是完全可选的，如果你不想，Vert.x并不会强迫你以这种方式创建自己的应用。。**

这个模型并未严格地实现actor模型，但确实与其有相似之处，尤其在并发性、扩展，部署方面。

为了使用这个模型，你需要将代码写成**verticles**的集合。

verticles是由Vert.x部署和运行的代码块。verticles可以由任何Vert.x支持的语言写成，并且单独的应用可以包含多种语言写就的verticles。

你可以把verticle看成有点像[Actor Model](https://link.jianshu.com?t=http://en.wikipedia.org/wiki/Actor_model)里的actor。

一个典型的应用由同一时间运行在同一Vert.x实例里的很多verticle实例组成的。不同的verticle实例之间通过在[event bus](https://link.jianshu.com?t=http://vertx.io/docs/vertx-core/java/?t=1461935711551#event_bus)上向彼此发送消息来通信。

### 编写verticle

verticle类必须实现[Verticle](https://link.jianshu.com/?t=http://vertx.io/docs/apidocs/io/vertx/core/Verticle.html)接口。

你可以直接实现这个接口，但通常有个更简单的办法，就是继承下面这个抽象类：[AbstractVerticle](https://link.jianshu.com/?t=http://vertx.io/docs/apidocs/io/vertx/core/AbstractVerticle.html)。

举个例子：

```java
public class MyVerticle extends AbstractVerticle {

  // Called when verticle is deployed
  public void start() {
  }

  // Optional - called when verticle is undeployed
  public void stop() {
  }

}
```

通常你需要像上面的例子一样，重载start方法。

Vert.x部署verticle时，会调用其start方法。当该start方法完成时，就认为该verticle已启动。

你也可以选择重载stop方法，当verticle被卸载（undeployed）时会调用这个方法。同样，stop方法完成时，会认为verticle已被终止。

### 异步verticle的启动和终止

有时候你可能想在verticle启动时做点耗时的事，除非完成了，否则不应该认定verticle已成功部署。比如你可能想在start方法里部署其他的verticles。

你不能在start方法里阻塞地等待其他verticles部署完成，这会打破我们的黄金准则。

那该怎么做呢？

合适的途径是实现**异步的**start方法。这个版本的start方法有一个future参数，这个方法返回时verticle并**不会**被认定已经部署完成。

等干完所有活之后（例如启动其他的verticles）你就可以调用future对象的complete（或者fail）方法；这是一个信号，标记你这里已经都完成了。

下面有个例子：

```java
public class MyVerticle extends AbstractVerticle {

  public void start(Future<Void> startFuture) {
    // Now deploy some other verticle:

    vertx.deployVerticle("com.foo.OtherVerticle", res -> {
      if (res.succeeded()) {
        startFuture.complete();
      } else {
        startFuture.fail();
      }
    });
  }
}
```

类似的，stop方法也有一个异步的版本。如果你做清理工作要花点时间，就可以用它。

```java
public class MyVerticle extends AbstractVerticle {

  public void start() {
    // Do something
  }

  public void stop(Future<Void> stopFuture) {
    obj.doSomethingThatTakesTime(res -> {
      if (res.succeeded()) {
        stopFuture.complete();
      } else {
        stopFuture.fail();
      }
    });
  }
}
```

提示：并不需要在stop方法中手动卸载某个verticle的子verticles(child verticles)，因为Vert.x会在父verticle被卸载时自动卸载它们。

### Verticle的类型

有三种不同类型的verticle。

#### 标准verticle(Standard Verticles)

这是最平常并有用的版本，它们会一直由同一个event loop线程执行。下一节里我们会更详细地讨论这个。

#### Worker Verticles

这一类由worker pool里的线程运行。绝不会有超过一个线程并发地执行单一实例。

#### 多线程版(Multi-threaded) worker verticles

这些还是由worker pool里的线程运行，不过单一实例可以被多个线程并发执行。

### 标准verticle

标准verticle被创建的时候，它们会被指定给一个event loop线程，然后event loop会调用其start方法。当你从event loop调用任意核心API上可以接受handler的方法时，Vert.x保证那些handlers会由同样的event loop执行。

这意味着我们可以保证一个verticle实例里的所有代码都会在同一个event loop上执行（只要你不自己创建线程并调用它！）。

这同样意味着可以像单线程应用那样来写所有代码，至于多线程并发和扩展的问题交给Vert.x就可以了。不需要有同步和易变性（volatile）的困扰，这样你也可以避免‘传统的’手写多线程应用中普遍会碰到的状况，譬如竞争条件（race conditions）和死锁（deadlock）。

### Worker Verticles

worker verticle和标准verticle挺像的，不同点在于worker verticle由Vert.x的worker 线程池中的线程执行，而标准verticle由event loop执行。

worker verticle是为调用阻塞代码而设计的。它们不会阻塞任意的event loop。

如果你不想用worker verticle来执行阻塞代码，那也可以通过直接运行内联阻塞代码的方式（就是前文所述的executeBlocking）。

如果你想将某个verticle作为worker verticle部署，可以通过调用[setWorker](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/DeploymentOptions.html#setWorker-boolean-)方法。

```java
DeploymentOptions options = new DeploymentOptions().setWorker(true);
vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options);
```

worker verticle实例绝不会被多个线程并发执行，但可以被不同线程在不同时候执行。

#### 多线程版worker verticle

一个多线程版worker verticle就像普通的worker verticle一般，但它**可以**被不同线程并发执行。

> 警告：多线程版worker 线程是个高级特性，绝大多数应用对此并无需求。为了这些verticle的并发执行，你需要使用标准的Java多线程编程技能，小心地使verticles保持一致的状态。

### 以编程的方式部署verticle

你可以使用[deployVerticle](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#deployVerticle-io.vertx.core.Verticle-)系列方法中的一个来部署verticle，只需要知道verticle的名称或者你自己创建一个verticle实例丢过去。

> 注意：部署verticle实例的方式是Java专有的。



```cpp
Verticle myVerticle = new MyVerticle();
vertx.deployVerticle(myVerticle);
```

你也可以通过指定verticle的**名称**来部署。

verticle的实例化需要用到特定的[VerticleFactory](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/spi/VerticleFactory.html)，verticle的名称就是用来查询这个特定的工厂类。

不同的语言有不同的工厂类，用来初始化verticle。原因多种多样，比如运行时从Maven加载服务或者获取verticles。

这样你可以部署任意以Vert.x支持的语言写就的verticle。

下面是一个部署不同种verticle的例子：

```cpp
vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle");

// Deploy a JavaScript verticle
vertx.deployVerticle("verticles/myverticle.js");

// Deploy a Ruby verticle verticle
vertx.deployVerticle("verticles/my_verticle.rb");
```

### 从verticle的名称映射到verticle factory的规则

当使用名称部署verticle时，名称的作用是选出实际中用来实例化这个verticle的verticle factory。

verticle的名称可以有一个前缀：前缀是个字符串，后面紧跟着一个冒号；如果前缀存在将被用于查询对应的factory。
 即：
 js:foo.js // Use the JavaScript verticle factory
 groovy:com.mycompany.SomeGroovyCompiledVerticle // Use the Groovy verticle factory
 service:com.mycompany:myorderservice // Uses the service verticle factory

如果没有前缀，Vert.x会寻找后缀来查询factory。
 即：
 foo.js // Will also use the JavaScript verticle factory
 SomeScript.groovy // Will use the Groovy verticle factory

如果前后缀都不存在，那么Vert.x会假定这是一个完全限定类名（FQCN）的Java verticle，并试着循此实例化。

### Verticle Factories如何定位呢？

绝大多数verticle factories都是从类路径（classpath）中加载的，在Vert.x启动时注册。

同样地，如果你希望用编程的方式注册、注销verticle factories，那么有[registerVerticleFactory](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#registerVerticleFactory-io.vertx.core.spi.VerticleFactory-)和[unregisterVerticleFactory](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#unregisterVerticleFactory-io.vertx.core.spi.VerticleFactory-)可用。

### 等待部署完成

verticle的部署是异步进行的，可能完成的时候对部署方法的调用都已经返回一阵子了。

如果你想在部署完成时收到通知，可以在部署时指定一个完成处理器（completion handler）：



```csharp
vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", res -> {
  if (res.succeeded()) {
    System.out.println("Deployment id is: " + res.result());
  } else {
    System.out.println("Deployment failed!");
  }
});
```

如果部署成功，此handler会收到一个字符串，这里面包含了部署的ID。

后面在你卸载这次部署的verticle时，会用到这个ID。

### 卸载部署的verticle

可以使用[undeploy](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#undeploy-java.lang.String-)来卸载已部署的verticle。

卸载本身也是异步的。所以如果你想在完成的时候收到通知，处理方法同部署的时候：



```csharp
vertx.undeploy(deploymentID, res -> {
  if (res.succeeded()) {
    System.out.println("Undeployed ok");
  } else {
    System.out.println("Undeploy failed!");
  }
});
```

### 指定verticle实例的数量

用verticle的名称部署时，可以指定verticle实例的数量：



```cpp
DeploymentOptions options = new DeploymentOptions().setInstances(16);
vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options);
```

这个特性在扩展到多核cpu上时很有帮助。比如你要部署一个web服务器的verticle，并且你的机器上有多个核心；为了这多个核心能充分发挥自己的光和热，你可以部署上多个实例。

### 给verticle传递配置参数

部署时可以将配置以JSON的形式传递给verticle：



```cpp
JsonObject config = new JsonObject().put("name", "tim").put("directory", "/blah");
DeploymentOptions options = new DeploymentOptions().setConfig(config);
vertx.deployVerticle("com.mycompany.MyOrderProcessorVerticle", options);
```

之后配置信息将可通过[Context](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Context.html)对象使用，或者直接使用[config](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/AbstractVerticle.html#config--)方法。

返回的配置是一个JSON对象，所以你可以像下面这样取数据：
 `System.out.println("Configuration: " + config().getString("name"));`

### 在verticle中访问环境变量

环境变量和系统属性可以用Java API访问：



```bash
System.getProperty("prop");
System.getenv("HOME");
```

### verticle隔离组（Verticle Isolation groups）

缺省情况下，Vert.x有一个*扁平的类路径(flat classpath)*，部署vertilce时，Vert.x会使用当前的类加载器（classloader）--而不是创建一个新的。多数情况下，这都是最简单、清晰、稳健的做法。

然而，有时候你可能想把某些verticle的部署与其他的隔离开来。

譬如，你想在同一个Vert.x实例中部署同一个verticle的不同版本，他俩还有着相同的类名；又或者你的两个不同verticle分别用到了同一个类库的不同版本。

使用隔离组时，你需要提供待隔离类的名称列表。方法[setIsolatedClasses](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/DeploymentOptions.html#setIsolatedClasses-java.util.List-)可以搞定这个事。传入的名称可以是类似**com.mycompany.myproject.engine.MyClass**这样的完全限定类名；还可以是类似**com.mycompany.myproject.***这样带通配符的，这会匹配到包**com.mycompany.myproject**里的任意类和任意子包。

请注意**唯有**匹配到的类才会被隔离，其他的类仍然由当前的类加载器载入。

如果想从非主类路径中加载类和资源，那你可以用[setExtraClasspath](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/DeploymentOptions.html#setExtraClasspath-java.util.List-)方法提供额外的类路径条目。

> 警告：使用这个特性要小心。类加载器们也可能带来一堆bug，使你的排错工作变得困难（译注：大家知道bug在英文里有虫子和计算机程序错误的意思；所以此处前面说虫子，后面说除错困难）。

这儿有个利用隔离组来隔离一个verticle的部署的例子。



```cpp
DeploymentOptions options = new DeploymentOptions().setIsolationGroup("mygroup");
options.setIsolatedClasses(Arrays.asList("com.mycompany.myverticle.*",
                   "com.mycompany.somepkg.SomeClass", "org.somelibrary.*"));
vertx.deployVerticle("com.mycompany.myverticle.VerticleClass", options);
```

### 高可用性

部署verticle时可以打开高可用性（HA），在这样的上下文环境里，若某个Vert.x实例上的某个vertilce意外地挂掉，集群里的另一个Vert.x实例将会重新部署这个verticle。

以高可用性运行verticle时，只需要在命令行后面加上** -ha **：
 `vertx run my-verticle.js -ha`

打开高可用性时，无需添加** -cluster **。

更多关于高可用性和配置的细节可以在下面的**高可用性和故障转移（High Availability and Fail-Over）**一节中找到。

### 从命令行运行verticles

将依赖添加到Vert.x核心包里，就能以正常方式在你的maven或gradle项目中直接使用Vert.x。

不过你也可以直接从命令行运行verticle。

为了达成这个目的，你要下载并安装好Vert.x的发布包，并将安装目录下的** bin **目录添加到** PATH **环境变量，同样要确保Java8 的JDK在** PATH **里。

> 注意：为了动态编译Java代码，JDK是必要的（言下之意，只装JRE是不够的）。

一切就绪，现在可以用** vertx run **运行verticle了。下面有几个例子：



```bash
# Run a JavaScript verticle
vertx run my_verticle.js

# Run a Ruby verticle
vertx run a_n_other_verticle.rb

# Run a Groovy script verticle, clustered
vertx run FooVerticle.groovy -cluster
```

至于Java verticle，甚至不需要编译你就可以直接运行它！
 `vertx run SomeJavaSourceFile.java`

Vert.x会在运行前动态地编译它。这点在快速建立原型和演示时特别有用。不需要先设置Maven或Gradle就能开始了。

有关在命令行执行**vertx**时各种其他可用的选项的所有信息，只需要在命令行输入**vertx**即可获得。

### 退出Vert.x

Vert.x实例维护的进程不是守护进程（daemon threads），所以它们会阻止JVM退出。

如果你以嵌入的方式使用Vert.x，工作完成的时候，你可以调用[close](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#close--)方法关闭它。

这样做会关闭所有内部线程池、其他的资源，并允许JVM退出。

### 上下文对象（The Context object）

Vert.x给handler提供事件、或者调用[verticle](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Verticle.html)的start/stop方法时，其执行状况都是与一个**Context(上下文)**联系在一起的。通常，这个context是一个与特定的event loop线程绑定的**event-loop context**。所以与此context相关的执行动作都发生在同一确定的event loop线程上。至于worker verticle和运行内联的阻塞代码时，会有一个worker context与之关联，这些动作都由worker 线程池里的线程运行。

利用[getOrCreateContext](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#getOrCreateContext--)方法，可以获得上下文环境：
 `Context context = vertx.getOrCreateContext();`

如果当前线程已经存在一个context与之关联，它会重用这个context对象。否则会创建context的一个新实例。可以像下面这样测试下取到的context的*类型*：



```csharp
Context context = vertx.getOrCreateContext();
if (context.isEventLoopContext()) {
  System.out.println("Context attached to Event Loop");
} else if (context.isWorkerContext()) {
  System.out.println("Context attached to Worker Thread");
} else if (context.isMultiThreadedWorkerContext()) {
  System.out.println("Context attached to Worker Thread - multi threaded worker");
} else if (! Context.isOnVertxThread()) {
  System.out.println("Context not attached to a thread managed by vert.x");
}
```

在你拿到一个context对象后，可以在此context里异步地运行代码。换句话说，你提交的任务最终会运行在同样的context里：



```csharp
vertx.getOrCreateContext().runOnContext( (v) -> {
  System.out.println("This will be executed asynchronously in the same context");
});
```

当有数个handler运行在同一context里时，它们可能会希望共享一些数据。context对象提供了存取共享在上下文里数据的方法。例如，你要传递数据过去做点事，可以用[runOnContext](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Context.html#runOnContext-io.vertx.core.Handler-)方法：



```dart
final Context context = vertx.getOrCreateContext();
context.put("data", "hello");
context.runOnContext((v) -> {
  String hello = context.get("data");
});
```

context对象也允许你通过[config](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Context.html#config--)方法访问verticle的配置信息。去看看** 给verticle传递配置 **一节吧，你将获得关于此项配置的更多细节。

### 定期、延时执行

在Vert.x里，延时或定期执行是很普遍的。

在标准verticle里，你不能以使线程休眠的方式引入延迟；这样干会阻塞event loop线程。

取而代之的是Vert.x定时器，定时器分为**一次性（one-shot）**和**周期性（periodic）**的。下面我们会分别讨论。

#### 一次性定时器

一段确定的延时过后，一次性定时器将调用事件handler，度量衡是毫秒。

设置一个触发一次的定时器用到[setTimer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#setTimer-long-io.vertx.core.Handler-)方法，它有两个参数：延时和一个handler。



```csharp
long timerID = vertx.setTimer(1000, id -> {
  System.out.println("And one second later this is printed");
});

System.out.println("First this is printed");
```

返回值是定时器的ID（long类型），它具有唯一属性。之后你可以用这个ID来取消定时器。这个handler也会收到定时器的ID。

#### 周期性定时器

类似的，利用[setPeriodic](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#setPeriodic-long-io.vertx.core.Handler-)方法可以设置一个定期触发的定时器。

初始的延迟值就是周期间隔。

返回值与一次性定时器一样，此处不再赘述。

定时器的事件handler的参数也与一次性定时器一致：

记住，定时器会定期触发。如果你的定期处理需要耗费大量时间，你的定时器事件可能会连续运行甚至糟糕到堆积在一起。

在这种情况下，你应该考虑转而使用[setTimer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#setTimer-long-io.vertx.core.Handler-)。一旦你的处理完成了，你可以再设置下一个定时器。



```csharp
long timerID = vertx.setPeriodic(1000, id -> {
  System.out.println("And every second this is printed");
});

System.out.println("First this is printed");
```

#### 取消定时器

像下面这样，调用[cancelTimer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/Vertx.html#cancelTimer-long-)方法，指定定时器ID，即可取消周期定时器。

#### verticle里的自动清理（Automatic clean-up in verticles）

如果你是在verticle内部创建的定时器，那么verticle被卸载时，这些定时器将被自动关闭。

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

#### 发布/订阅消息(Publish / subscribe messaging)

event bus支持**发布(publishing)**消息。

消息被发布到某个地址，这意味着把消息分发到注册在此地址上的所有handlers。

这就是我们很熟悉的**发布/订阅**模式。

#### 点对点和请求-响应(Point to point and Request-Response messaging)

event bus也支持**点对点**消息。

当消息被发送到某个地址，Vert.x会把消息路由给注册在此地址上的某个handler。

如此此地址上注册了超过一个handler，Vert.x将会通过一个不严格的轮询算法(non-strict round-robin algorithm)从中选择一个。

在点对点的消息机制中，发消息时可以选择指定一个应答handler(reply handler)。

当有接收者收到消息并处理后，接收者可以选择是否答复此消息。如果选择答复，上述的reply handler将被调用。

发送者收到消息回应后，同样可以做出回应。这可以无限地重复下去，并允许两个不同的verticle间形成对话。

这种通用的消息模式称为请求-响应模式。

#### 尽力分发(Best-effort delivery)

Vert.x会尽最大的努力分发消息，绝不会有意丢弃某些消息。这被称为尽力分发。

然而，在event bus部分或全部失效的情况下，消息还是有可能丢失。

如果你的应用很关心这一点，那么编码时应该注意使你的handler是幂等的(be idempotent)，并且发送方应该在恢复后尝试重新发送消息。

#### 消息的类型

任何的基本类型/简单类型，字符串或者[buffers](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/buffer/Buffer.html)都可以被当成消息发送出去。

但是Vert.x里通常使用JSON格式的消息。

在Vert.x支持的语言里，创建、读取、解析JSON都很容易，所以它就成了Vert.x上的*通用语(lingua franca)*。

当然了，并不是必须使用JSON。

event bus是很灵活的，支持在其上发送任意专有的对象。你只需为此定义一个[编解码器(codec)](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageCodec.html)。

### The Event Bus API

下面让我们来看看API。

#### 获取event bus对象

可以像下面这样拿到event bus对象的引用：
 `EventBus eb = vertx.eventBus();`

每个Vert.x实例有唯一的event bus实例。

#### 注册handlers

注册handler最简单的方法是用[consumer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#consumer-java.lang.String-io.vertx.core.Handler-)方法。看例子：



```csharp
EventBus eb = vertx.eventBus();

eb.consumer("news.uk.sport", message -> {
  System.out.println("I have received a message: " + message.body());
});
```

当你的handler收到一条消息时，handler会被调用，而[消息(message)](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html)会作为参数传递过去。

consumer方法会返回一个[MessageConsumer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageConsumer.html)实例。

这个对象可以用来注销handler，或将handler当作流(stream)来使用。

或者你也可以用[consumer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#consumer-java.lang.String-io.vertx.core.Handler-)方法得到一个未设置handler的MessageConsumer 对象，随后再设置handler：



```rust
EventBus eb = vertx.eventBus();

MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
consumer.handler(message -> {
  System.out.println("I have received a message: " + message.body());
});
```

当在一个集群event bus上注册了handler时，完成在集群上每个节点的注册需要花点时间。

如果你想在完成时得到通知，你可以在MessageConsumer 对象上注册一个[completion handler](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageConsumer.html#completionHandler-io.vertx.core.Handler-)。



```csharp
consumer.completionHandler(res -> {
  if (res.succeeded()) {
    System.out.println("The handler registration has reached all nodes");
  } else {
    System.out.println("Registration failed!");
  }
});
```

#### 注销handlers

注销handler可以通过调用[unregister](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageConsumer.html#unregister--)方法完成。

在集群event bus做这件事时，同样要花点时间等其传播到各个节点，所以你也可以通过[unregister](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageConsumer.html#unregister-io.vertx.core.Handler-)方法得到通知。



```csharp
consumer.unregister(res -> {
  if (res.succeeded()) {
    System.out.println("The handler un-registration has reached all nodes");
  } else {
    System.out.println("Un-registration failed!");
  }
});
```

#### 发布消息

发布一个消息只需简单地调用[publish](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#publish-java.lang.String-java.lang.Object-)方法，指定要发往的地址。
 `eventBus.publish("news.uk.sport", "Yay! Someone kicked a ball");`

这个消息会被分发到注册在地址 news.uk.sport 上的所有handlers。

#### 发送消息

发送消息的结果是注册在此地址上的handler只有一个会收到消息。这是点对点的消息模式。

你可以用[send](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#send-java.lang.String-java.lang.Object-)方法发送消息:
 `eventBus.send("news.uk.sport", "Yay! Someone kicked a ball");`

#### 设置消息头(Setting headers on messages)

event bus 上传输的消息也可以包含一些消息头(headers)。

可以在发送/发布消息时指定一个[DeliveryOptions](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/DeliveryOptions.html)对象来做这件事：



```cpp
DeliveryOptions options = new DeliveryOptions();
options.addHeader("some-header", "some-value");
eventBus.send("news.uk.sport", "Yay! Someone kicked a ball", options);
```

#### 消息的顺序

Vert.x会将消息以发送者送出的顺序分发给handler。

#### 消息对象

你在handler里收到的消息是一个[Message](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html)对象。

消息的[body](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html#body--)就是发送过来的对象。

消息头可以通过[headers](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html#headers--)方法得到。

#### 消息/发送回应的知识点(Acknowledging messages / sending replies)

使用[send](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#send-java.lang.String-java.lang.Object-)方法时event bus会尝试把消息发送到注册在event bus上的[MessageConsumer](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageConsumer.html)对象。

某些情况下，发送方可能想知道消息已经被接收并处理了。

为了让发放方了解消息已被处理，consumer可以通过调用[reply](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html#reply-java.lang.Object-)方法给予回应。

如果这么做了，那么发送方将会收到一个回应，并且回应handler将被调用。看下面这个例子：
 接收方：



```rust
MessageConsumer<String> consumer = eventBus.consumer("news.uk.sport");
consumer.handler(message -> {
  System.out.println("I have received a message: " + message.body());
  message.reply("how interesting!");
});
```

发送方：



```csharp
eventBus.send("news.uk.sport", "Yay! Someone kicked a ball across a patch of grass", ar -> {
  if (ar.succeeded()) {
    System.out.println("Received reply: " + ar.result().body());
  }
});
```

回应可以带一个消息体，你可以在其中放置一些有用的信息。

“处理”实际上是由应用程序定义的，其中会发生什么完全依赖于consumer做了什么；Vert.x的event bus并不知道也不关心这些。

例如：

- 一个简单的消息consumer，它实现了返回当天时间的服务；可以确认回应的消息体中包含了这个时间。
- 一个实现了持久化队列的消息consumer，如果消息被成功地持久化在存储中可以确认是**true**，反之则为**false**。
- 一个处理订单的消息consumer，当订单被成功处理时，它可以从数据库里被删掉，这时候可以确认是**true**。

#### 指定超时时间的发送(Sending with timeouts)

在发送一个带回应handler的消息时，可以在[DeliveryOptions](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/DeliveryOptions.html)对象里指定超时的时间。

如果在这段时间里没有收到回应，回应handler将被调用，并以失败结束。

缺省的超时时间是30秒。

#### 发送失败(Send Failures)

消息发放也可能因为其他原因失败，包括：

- 消息发送到的地址没有handler可用。
- 接收方显示地调用[fail](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/Message.html#fail-int-java.lang.String-)方法返回了失败的信息。

所有的情况下回应的handler都会被调用，并返回特定的错误。

#### 消息编解码器(Message Codecs)

只要你定义并注册了相关的[message codec](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/MessageCodec.html)，就可以在event bus上发送任意的对象。

当发送/发布消息时，你需要在[DeliveryOptions](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/DeliveryOptions.html)对象上指定codec的名称：



```cpp
eventBus.registerCodec(myCodec);

DeliveryOptions options = new DeliveryOptions().setCodecName(myCodec.name());

eventBus.send("orders", new MyPOJO(), options);
```

如果你想一直使用同一个codec，可以将它注册成缺省的codec，这样后面就不用每次发消息时再专门指定：



```java
eventBus.registerDefaultCodec(MyPOJO.class, myCodec);

eventBus.send("orders", new MyPOJO());
```

[unregisterCodec](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/eventbus/EventBus.html#unregisterCodec-java.lang.String-)方法可以用来注销codec。

消息codec并不总是对同样的类型进行编码、解码。例如，你可以写一个codec用来发送MyPOJO类，而当消息送达handler时，可以是MyOtherPOJO类。

#### 集群event bus

event bus并不只是存在于单一的Vert.x实例中。将多个Vert.x实例组成集群后，可以形成一个单独的，分布式的event bus。

#### 以编程实现集群

如果你以编码的方式创建了Vert.x实例，将Vert.x实例配置成集群式的即可得到集群event bus；



```csharp
VertxOptions options = new VertxOptions();
Vertx.clusteredVertx(options, res -> {
  if (res.succeeded()) {
    Vertx vertx = res.result();
    EventBus eventBus = vertx.eventBus();
    System.out.println("We now have a clustered event bus: " + eventBus);
  } else {
    System.out.println("Failed: " + res.cause());
  }
});
```

当然你应该确保classpath中有一个[ClusterManager](https://link.jianshu.com?t=http://vertx.io/docs/apidocs/io/vertx/core/spi/cluster/ClusterManager.html)的实现，例如缺省的**HazelcastClusterManager**。

#### 命令行里实现集群

你可以在命令行里运行集群vertx：
 `vertx run my-verticle.js -cluster`

#### verticle的自动清理

如果你在verticle内部注册了event bus handlers，当这些verticle被卸载时handlers将被自动注销。

# 配置事件总线（Configuring the event bus）

可以配置事件总线。当事件总线分布在集群时，它特别有用。



# 通过Vert.x操作文件系统

