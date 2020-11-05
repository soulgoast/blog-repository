ThingsBoard 3.1.1源码安装部署

推荐在有外网的环境下编译，不然就会有很多坑需要注意。这是一篇安装文档，也是一篇避坑指南。

ThingsBoard源码安装部署需要依赖`node`，`yarn`，`gradle`以及一些`aliyun maven`中没有的jar包。折腾了好久，终于编译通过了，希望对大家有所帮助。

<!-- more -->

![image-20200927150731531](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200927150731531.png)



## 可能会遇到的坑

### 缺依赖包

编译的过程中可能会应为某个依赖包下载不下来而报错，可能是一下两种情况。

1. maven仓库有该依赖包。
2. 配置的远程仓库本身就没有。

#### 有依赖包

本地下载的依赖可能是受损的，需要删掉重新下载。

#### 没有依赖包

这种情况下需要我们手动下载依赖。去官方仓库搜索该依赖，查看它所在的仓库，并下载。官方仓库地址：https://mvnrepository.com/。

我在编译的过程遇到的两个无法下载的依赖包：`com.sun.winsw:winsw:exe:2.0.1`，`org.gradle:gradle-tooling-api:jar:6.3`。

### 前段编译过程太慢

编译过程太慢是因为前段的依赖，比如node，yarn并不能从ali仓库中下载，网络下载太慢导致。解决办法：提前下载好，放入指定位置。

#### gradle

下载依赖：gradle-6.6-bin.zip

放置位置：user\wrapper\dists\gradle-6.6-bin\dflktxzwamd4bv66q00iv4ga9\gradle-6.6-bin.zip

#### node

mvn_repository\com\github\eirslett\node\12.16.1\node-12.16.1-win-x64.exe

#### yarn

mvn_repository\com\github\eirslett\yarn\1.22.4\yarn-1.22.4.tar.gz

#### pkg

下载地址：

放置地址：\user\\.pkg-cache\v2.6\fetched-v12.18.1-linux-x64，\user\\.pkg-cache\v2.6\fetched-v12.18.1-win-x64

### mvn clean 不起作用

可能是某个进程占用，执行命令

```
taskkill /f /im java.exe
```

## 环境安装

1. `java version "1.8.0_66"`
2. `PostgreSQL 12.4`
3. `Apache Maven 3.6.1`
4. Git工具
5. IDEA
6. `NodeJs v12.16.1`
7. `yarn v1.22.4`

## 安装编译



## 下载源码

thingsboard的[源代码](https://github.com/thingsboard/thingsboard)是放在github上。

```shell
$ git clone https://github.com/thingsboard/thingsboard.git
# 根据tag v3.1.1创建本地分支v3.1.1_local
$ git checkout v3.1.1_local v3.1.1
# 查看当前分支
$ git branch
# 切换分支
$ git branch v3.1.1_local
```

## Maven镜像源配置

修改maven的仓库地址：

```xml
<mirror>
    <id>alimaven</id>
    <mirrorOf>central</mirrorOf>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
</mirror>
```

## 编译

执行

```shell
$ mvn clean install -DskipTests
```

## 导入IDEA



## 创建数据库

1. 创建数据库thingsboard
2.  修改 *thingsboard\application\src\main\resources\thingsboard.yml* 里的数据源信息 
3.  将sql文件 *dao/src/main/resources/sql* 拷贝到 *application/src/main/data/sql* 
4.  执行安装程序ThingsboardInstallApplication.java 



```yml
  datasource:
    driverClassName: "${SPRING_DRIVER_CLASS_NAME:org.postgresql.Driver}"
    url: "${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/thingsboard}"
    username: "${SPRING_DATASOURCE_USERNAME:postgres}"
    password: "${SPRING_DATASOURCE_PASSWORD:123456}"
    hikari:
      maximumPoolSize: "${SPRING_DATASOURCE_MAXIMUM_POOL_SIZE:16}"
```

## 启动项目

 如果项目里的log语句都警告成红色的话，请安装lombok插件； 

启动ThingsboardServerApplication.java 

 进入页面 http://localhost:8080

只有系统管理员帐号，其他的租户等账号需要自己新建。
帐号：sysadmin@thingsboard.org
密码：sysadmin

## 6. 部署

 上面是在IDEA里以源码的形式运行，我们修改代码后，可以以同样的方式编译打包项目，然后会在application下生成各类打包好的Thingsboard包，然后按需求部署。 

![image-20200927153321589](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200927153321589.png)