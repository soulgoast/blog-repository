kafka源码阅读环境搭建

kafka使用java和scala语言编写，项目构建工具使用的是gradle。

> `kafka:2.6.0`,

## 搭建阅读环境

从github上拉去源代码，最新的发布版本是2.6.0。

> https://github.com/apache/kafka.git

根据远程库的tag，创建本地分支。

### 安装Scala

从根目录下的文件gradle.properties，可以得知依赖的scala版本。下载并配置环境变量。

### 安装gradle

这里使用的gradle的版本为6.3，下载并配置环境变量。

### 编译kafka

 修改kafka的路径下的build.gradle文件，修改maven仓库的下载地址即可： 

```groovy
buildscript {
    repositories {
        maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}
    }
}
 
allprojects {
    repositories {
        maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}
    }
}
```

编译：

```cmd
$ gradle idea
```

### 导入到idea

 将Kafka导入到IDEA：打开IDEA---open---选择kafka目录即可； 

## KAFKA的模块简介

kafka的核心模块是用scala编写的。

### Core模块

- admin： kafka的管理员模块，操作和管理其topic，partition相关，包含创建，删除topic，或者拓展分区等。 
- api： 主要负责数据交互，客户端与服务端交互数据的编码与解码。 
- cluster：  这里包含多个实体类，有Broker，Cluster，Partition，Replica。其中一个Cluster由多个Broker组成，一个Broker包含多个Partition，一个Topic的所有Partition分布在不同的Broker中，一个Partition包含多个Replica。 
- common：  这是一个通用模块，其只包含各种异常类以及错误验证。 
- consumer： 消费者处理模块，负责所有的客户端消费者数据和逻辑处理。 
- controller： 此模块负责中央控制器的选举，分区的Leader选举，Replica的分配或其重新分配，分区和副本的扩容等。 
- coordiantor： 负责管理部分consumer group和他们的offset。 
- log： 这是一个负责Kafka文件存储模块，负责读写所有的Kafka的Topic消息数据。 
- message： 封装多条数据组成一个数据集或者压缩数据集。
- metrics： 负责内部状态的监控模块。 
- network： 该模块负责处理和接收客户端连接，处理网络时间模块。 
- security： 负责Kafka的安全验证和管理模块。 
- server： 该模块涉及的内容较多，有Leader和Offset的checkpoint，动态配置，延时创建和删除Topic，Leader的选举，Admin和Replica的管理，以及各种元数据的缓存等内容。 
- tools： 阅读该模块，就是一个工具模块，涉及的内容也比较多。有导出对应consumer的offset值；导出LogSegments信息，以及当前Topic的log写的Location信息；导出Zookeeper上的offset值等内容。 
- utils： 各种工具类，比如Json，ZkUtils，线程池工具类，KafkaScheduler公共调度器类，Mx4jLoader监控加载器，ReplicationUtils复制集工具类，CommandLineUtils命令行工具类，以及公共日志类等内容。 
- zk
- zookeeper

## 启动KAFKA

### 启动问题

> 找不到或无法加载主类 kafka.Kafka

