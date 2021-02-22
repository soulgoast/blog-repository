---
title: freemarker生成word文档
tags:
  - freemarker
categories:
  - autoGenerate
date: 2021-2-22 13:56:03
---
项目构建接口文档和数据库设计文档都是必不可少的，手动编写耗时耗力。根据数据库设计初步生成两份文档，只需少量修改就可形成正式文档。下面介绍使用freemarker生成word文档的方法。

<!--more-->

# 编写文档模板

`${}`是占位符，输入用户名的地方可以使用`${username}`。表格可以使用：

```xml
<#list  userList as user>
	姓名：${user.userName},性别：${user.sex}
</#list>
```

以下面的word模板原型为例：

![image-20210222140444670](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210222140444670.png)

最终生成的结果文件如下：

![image-20210222140535324](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210222140535324.png)

# 将word文档另存为xml格式

![image-20210222140716187](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210222140716187.png)

参考文档：https://blog.csdn.net/lun379292733/article/details/18673081

