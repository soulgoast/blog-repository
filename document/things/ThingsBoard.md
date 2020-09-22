ThingsBoard

ThingsBoard是一个开源物联网平台，可实现物联网项目的快速开发、管理和扩展。 

> TODO 了解ThingsBoard能为我们做什么？

ThingsBoard的特性：

- 管理设备， 资产和客户并定义他们之间的关系。  // TODO DCIM中没有资产与用户关系的管理
- 基于设备和资产收集数据并进行可视化。// TODO 有哪些可视化的方式
- 采集遥测数据并进行相关的事件处理进行警报响应  // TODO 告警方式
- 基于远程RPC调用进行设备控制。// TODO 与设备通信方式
- 基于生命周期事件、REST API事件、RPC请求构建工作流。 // TODO 工作流自定义吗？
- 基于动态设计和响应仪表板向你的客户提供设备或资产的遥测数据。// 与可视化重复
- 基于规则链自定义特定功能。 // 规则链
- 发布设备数据至第三方系统。 // 如果发送

架构图

![image-20200916090919461](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200916090919461.png)

![image-20200916091115259](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200916091115259.png)

ThingsBoard设计为:

- 可扩展: 使用领先开源技术构建的可水平扩展平台。
- 容错：无单点故障，集群中的每个节点都是相同的。
- 性能卓越：单个服务器节点可以根据用例处理几十甚至数十万个设备。ThingsBoard集群可以处理数百万台设备。 TODO 单节点支持数量太少，支持无线扩容
- 灵活：开发新功能可以方便的使用自定义部件、规则引擎等。
- 持久：数据永久保存