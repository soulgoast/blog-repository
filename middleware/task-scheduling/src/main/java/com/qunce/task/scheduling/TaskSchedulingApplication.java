package com.qunce.task.scheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * 1. 在springboot中引入定时任务
 * 2. 在页面开启和关闭定时任务。
 * 2. 动态curd定时任务  暂时没有找到spring提供的方案，暂时使用quartz框架实现。
 * 2. SchedulerException 处理策略，当job中抛出异常时，可以修改执行引擎的执行状态。
 * 3. 将任务调度封装成一个业务模块，提供调用接口
 * 4. 启动项目时，初始化数据库中的定时任务
 * @Author hu zhongxi
 */
@SpringBootApplication
@EnableScheduling
public class TaskSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskSchedulingApplication.class, args);
    }
}
