基于Jest Rest客户端的ElasticSearch的Spring Data实现
将Spring Data与仅可通过HTTP访问的ElasticSearch集群一起使用（例如在AWS上）非常有用。

版本对应关系

| spring data jest | spring boot | spring data elasticsearch | jest  | elasticsearch |
| ---------------- | ----------- | ------------------------- | ----- | ------------- |
| 3.3.2.RELEASE    | 2.2.1       | 3.2.1.RELEASE             | 6.3.1 | 6.8.4         |
| 3.2.5.RELEASE    | 2.1.2       | 3.1.3.RELEASE             | 6.3.1 | 6.4.3         |
| 3.1.5.RELEASE    | 2.0.5       | 3.0.10.RELEASE            | 5.3.4 | 5.5.0         |
| 3.0.0.RELEASE    | 2.0.0.M4    | 3.0.0.RELEASE             | 5.3.2 | 5.5.0         |
| 2.3.1.RELEASE    | 1.5.x       | 2.1.0.RELEASE             | 2.0.4 | 2.4.4         |
| 2.2.0.RELEASE    | >= 1.4.3    | 2.0.6.RELEASE             | 2.0.4 | 2.4.3         |
| 2.1.4.RELEASE    | < 1.4.3     | 2.0.5.RELEASE             | 2.0.3 | 2.2.0         |
| 1.0.2.RELEASE    | 1.3.x       | 1.3.4.RELEASE             | 1.0.3 | 1.5.2         |

快速入门
```pom
<dependency>
    <groupId>com.github.vanroy</groupId>
    <artifactId>spring-boot-starter-data-jest</artifactId>
    <version>3.3.0.RELEASE</version>
</dependency>

<!-- Only required for start local ES node -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
</dependency>
```


