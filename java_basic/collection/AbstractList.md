---
title: AbstractList
tags:
  - Framework
categories:
  - Java基础
date: 2020-09-07 22:12:49
---
为了减少程序员实现List接口所需的工作量，AbstractList提供了支持随机访问数据结构（比如数组）的最基本的实现。AbstractSequentialLIst提供了支持循序访问数据结构（比如链表）的最基本的实现。

<!--more-->

程序员只需要实现AbstractList的`get(int)`和`{@link #}`方法，便可实现一个不可变线性表。要实现一个可以修改的线性表，程序员另外实现`set(int, E)`方法，否则将会抛出`UnsupportedOperationException`。如果需要实现一个可变大小的线性表，程序员另外实现`add(int, E)`方法和`remove(int)`方法。根据Collection接口规范中的建议，程序员通常应该提供一个void（无参数）和集合构造函数。不像其他的抽象集合实现，程序员不必提供迭代器实现; 迭代器和列表迭代器由此类实现的，并且在此之上提供随机访问方法：

```java
get(int)
set(int, E)
add(int, E)
remove(int)
```

这个类的每个抽象方法的说明文档都详细描述了它的实现。如果有更好的方式，实现类可以重写任务一个方法。