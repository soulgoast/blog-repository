# SQL Client Templates

 SQL Client Templates是一个小型库，旨在方便SQL查询的执行。 

# 用法

 要使用SQL客户端模板，请将以下依赖项添加到构建描述符的依赖项部分: 

- Maven (in your `pom.xml`):

```xml
<dependency>
 <groupId>io.vertx</groupId>
 <artifactId>vertx-sql-client-templates</artifactId>
 <version>4.0.1-SNAPSHOT</version>
</dependency>
```

- Gradle (in your `build.gradle` file):

```groovy
dependencies {
 implementation 'io.vertx:vertx-sql-client-templates:4.0.1-SNAPSHOT'
}
```

 # 入门指南

 下面是使用SQL模板的最简单方法。 

 SQL模板使用命名参数，因此(默认情况下)使用映射作为参数源，而不是元组。 

 SQL模板(默认情况下)产生一个RowSet<Row>，就像客户端PreparedQuery一样。事实上，模板是一个PreparedQuery的精简包装器。 

```java

```

