Kakfa安装和启动

## 获取KAFKA

[下载](https://mirrors.tuna.tsinghua.edu.cn/apache/kafka/2.6.0/kafka_2.13-2.6.0.tgz)当前版本的安装包。

``` shell
$ tar -xzf kafka_2.13-2.6.0.tgz
$ cd kafka_2.13-2.6.0/
```

## 启动KAFKA服务

> 注意：您的本地环境必须安装Java 8+。 

 运行以下命令，以正确的顺序启动所有服务: 

```shell
# 启动ZooKeeper服务
# 注意:很快，ZooKeeper将不再被Apache Kafka所需要。
$ zkServer.sh start
```

配置kafka可远程连接：

```properties
############################# Socket Server Settings #############################

# The address the socket server listens on. It will get the value returned from
# java.net.InetAddress.getCanonicalHostName() if not configured.
#   FORMAT:
#     listeners = listener_name://host_name:port
#   EXAMPLE:
#     listeners = PLAINTEXT://your.host.name:9092
#listeners=PLAINTEXT://:9092
listeners=PLAINTEXT://:9092

# Hostname and port the broker will advertise to producers and consumers. If not set,
# it uses the value for "listeners" if configured.  Otherwise, it will use the value
# returned from java.net.InetAddress.getCanonicalHostName().
#advertised.listeners=PLAINTEXT://your.host.name:9092
advertised.listeners=PLAINTEXT://10.8.11.65:9092
```

 打开另一个终端会话并运行: 

```shell
# 启动Kafka节点服务
$ bin/kafka-server-start.sh config/server.properties &
```

## 创建一个主题（Topic）

Kafka是一个分布式事件流平台，它允许您跨多台机器读、写、存储和处理事件(在文档中也称为记录或消息)。 

示例事件有支付事务、来自移动电话的地理位置更新、发货订单、来自物联网设备或医疗设备的传感器测量等等。这些事件被组织并存储在主题中。非常简单，主题类似于文件系统中的文件夹，事件是该文件夹中的文件。 

 因此，在编写第一个事件之前，必须创建一个主题。打开另一个终端会话并运行: 

``` shell
# 创建一个名为“test”的Topic，只有一个分区和一个备份
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
```

所有Kafka的命令行工具都有额外的选项:运行Kafka -topic .sh命令而不带任何参数来显示使用信息。例如，它还可以显示详细信息，如新主题的分区计数: 

```shell
$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
Topic: quickstart-events	PartitionCount: 1	ReplicationFactor: 1	Configs: segment.bytes=1073741824
Topic: quickstart-events	Partition: 0	Leader: 0	Replicas: 0     Isr: 0
```

## 发送消息

Kafka客户端通过网络与Kafka代理通信以编写(或读取)事件。一旦接收到这些事件，代理将以持久和容错的方式存储这些事件，您需要多长时间就存储多长时间——甚至永远存储。 

运行控制台生成器客户端，在主题中写入一些事件。默认情况下，您输入的每一行都将导致向主题写入一个单独的事件。 

``` shell
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
This is first message
This is another message
```

 您可以在任何时候使用`Ctrl-C`停止生成器客户端。 

## 读取消息

打开另一个终端会话并运行控制台消费者客户端来读取您刚刚创建的事件: 

```shell
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
This is a message
This is another message
```

您可以在任何时候使用`Ctrl-C`停止客户端。 

您可以自由试验:例如，切换回您的生产者终端(上一步)来编写额外的事件，并查看事件如何立即显示在您的消费者终端中。 

因为事件长期存储在Kafka中，所以它们可以被任意多的消费者读取。您可以通过打开另一个终端会话并再次运行之前的命令来轻松地验证这一点。 

## 使用KAFKA CONNECT将数据导入/导出为事件流 

一旦您的数据以事件的形式存储在Kafka中，您就可以使用Kafka Streams客户端库处理数据。 它允许您实现关键任务的实时应用程序和微服务，其中输入或输出数据存储在Kafka主题中。Kafka Connect允许您不断地从外部系统摄取数据到Kafka，反之亦然。因此，与Kafka集成现有系统是非常容易的。为了使这个过程更容易，有数百个这样的连接器可用。 

看一看Kafka Connect部分，了解更多关于如何持续地导入/导出数据到Kafka和导出。 

##  使用KAFKA流处理您的事件 

一旦您的数据以事件的形式存储在Kafka中，您就可以使用Kafka Streams客户端库处理数据。它允许您实现关键任务的实时应用程序和微服务，其中输入或输出数据存储在Kafka主题中。Kafka Streams将客户端编写和部署标准Java和Scala应用程序的简单性与Kafka服务器端集群技术的优势结合在一起，使这些应用程序具有高度的可伸缩性、弹性、容错性和分布式。这个库完全支持一次处理、有状态操作和聚合、窗口、连接、基于事件时间的处理等等。 

给你一个初步的体验，这里是如何实现流行的WordCount算法: 

```java
KStream<String, String> textLines = builder.stream("quickstart-events");

KTable<String, Long> wordCounts = textLines
            .flatMapValues(line -> Arrays.asList(line.toLowerCase().split(" ")))
            .groupBy((keyIgnored, word) -> word)
            .count();

wordCounts.toStream().to("output-topic"), Produced.with(Serdes.String(), Serdes.Long()));
```

Kafka Streams演示和应用程序开发教程演示了如何从头到尾编写和运行这样的流媒体应用程序。 

## 终止KAFKA的环境

既然你已经完成了《快速起步》，那么你可以随意拆除KAFKA的环境——或者继续玩下去。 

1. 如果您还没有这样做，那么使用`Ctrl-C`停止生产者和消费者客户端。
2. 用`Ctrl-C`停止Kafka代理。
3. 最后，用`Ctrl-C`停止ZooKeeper服务器。

如果您还想删除您本地Kafka环境的任何数据，包括您在此过程中创建的任何事件，运行命令: 

```shell
$ rm -rf /tmp/kafka-logs /tmp/zookeeper
```

