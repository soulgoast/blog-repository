```xml
<!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.6.0</version>
</dependency>
```

## Kafka生产者

 ![img](https://img2020.cnblogs.com/blog/660329/202005/660329-20200519142849888-895968210.png) 

kakfa发送消息的主要步骤： 

1. 

核心类

```java
org.apache.kafka.clients.producer.KafkaProducer; // 
org.apache.kafka.clients.producer.ProducerConfig;
org.apache.kafka.clients.producer.ProducerRecord;
```

### KafkaProducer

```java
Properties properties = new Properties();
properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.8.11.65:9092");
properties.put(ProducerConfig.ACKS_CONFIG, "all");
properties.put(ProducerConfig.RETRIES_CONFIG, 0);
properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 10);
properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "1");
properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
for (int i = 1; i < 600; i++) {
    kafkaProducer.send(new ProducerRecord<>("quickstart-events", "message" + i));
}
kafkaProducer.flush();
kafkaProducer.close();
```

生产者的缓冲空间池保留尚未发送到服务器的消息，后台I/O线程负责将这些消息转换成请求发送到集群。如果使用后不关闭生产者，则会泄露这些资源。`send()`方法是异步的，添加消息到缓冲区等待发送，并立即返回。生产者将单个的消息批量在一起发送来提高效率。 

### ProducerConfig

```properties
# long, 默认值。
# 用于设置PRODUCER为空闲的TOPIC缓存metadata的时间。如果TOPIC超过metadata的空闲时间没有生产，则该TOPIC的metadata将会失效，下一次访问metadata时将强制执行元数据获取请求。
METADATA_MAX_IDLE_CONFIG = metadata.max.idle.ms

# int，默认值16384
# Producer可以将发往同一个Partition的数据做成一个Produce Request发送请求，即Batch批处理，以减少请求次数，该值即为每次批处理的大小。
# 另外每个Request请求包含多个Batch，每个Batch对应一个Partition，且一个Request发送的目的Broker均为这些partition的leader副本。
# 若将该值设为0，则不会进行批处理。
BATCH_SIZE_CONFIG = batch.size

# list
# 用于建立与kafka集群的连接，这个list仅仅影响用于初始化的hosts，来发现全部的servers。
# 格式：host1:port1,host2:port2,…，数量尽量不止一个，以防其中一个down了。
BOOTSTRAP_SERVERS_CONFIG = bootstrap.servers 

# 字符串, 默认值是"1"。 TODO
# Server完成 producer request 前需要确认的数量。
# acks=0时，producer不会等待确认，直接添加到socket等待发送；
# acks=1时，等待leader写到local log就行；
# acks=all或acks=-1时，等待isr中所有副本确认
# （注意：确认都是 broker 接收到消息放入内存就直接返回确认，不是需要等待数据写入磁盘后才返回确认，这也是kafka快的原因）
ACKS_CONFIG = acks 

ProducerConfig.MAX_BLOCK_MS_CONFIG = max.block.ms

# long, 默认值33554432。
# 生产者可以用来缓冲等待发送到服务器的记录的总内存字节。


```

### ProducerRecord

## Kafka消费者



