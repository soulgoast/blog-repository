Apache HttpComponents™项目负责创建和维护专注于HTTP和相关协议的轻量级Java组件的工具集。
HttpComponents概述
超文本传输协议（HTTP）可能是当今Internet上使用的最重要的协议。
Web服务，支持网络的设备和网络计算的增长继续将HTTP协议的作用扩展到用户驱动的Web浏览器之外，
同时增加了需要HTTP支持的应用程序的数量。 
HttpComponents是为扩展而设计的，同时提供了对基本HTTP协议的强大支持，
对于构建HTTP感知的客户端和服务器应用程序
（例如Web浏览器，Web Spider，HTTP代理，Web服务传输库或利用或扩展HTTP协议以进行分布式通信。

HttpComponents Structure

HttpComponents Core
HttpCore是一组轻量级HTTP传输组件，可用于以最小的占用空间构建自定义客户端和服务器端HTTP服务。 
HttpCore支持两种I / O模型：基于经典Java I / O的阻塞I / O模型和基于Java NIO的非阻塞事件驱动的I / O模型。
阻塞I / O模型可能更适合于数据密集型低延迟方案，而非阻塞模型可能更适合于高延迟方案，
在原始数据吞吐量中，原始数据吞吐量的重要性不如处理数千个同时HTTP连接的能力。资源高效的方式。

HttpComponents Client
HttpClient是基于HttpCore的HTTP / 1.1兼容HTTP代理实现。它还为客户端身份验证，
HTTP状态管理和HTTP连接管理提供可重用的组件。 
HttpComponents Client是Commons HttpClient 3.x的继承者和替代者。强烈建议Commons HttpClient用户进行升级。

HttpComponents AsyncClient
Asynch HttpClient是基于HttpCore NIO和HttpClient组件的HTTP / 1.1兼容HTTP代理实现。它是Apache HttpClient的补充模块，
适用于特殊情况，在特殊情况下，就原始数据吞吐量而言，处理大量并发连接的能力比性能更为重要。

Commons HttpClient (legacy)
Commons HttpClient 3.x代码行已寿终正寝。强烈建议Commons HttpClient 3.x的所有用户升级到HttpClient 4.1。


HttpCore是实现HTTP协议最基本方面的一组组件，尽管这些组件足以以最小的占用空间开发功能齐全的客户端和服务器端HTTP服务。 HttpCore具有以下范围和目标：

