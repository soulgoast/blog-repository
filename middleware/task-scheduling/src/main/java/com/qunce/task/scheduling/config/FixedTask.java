package com.qunce.task.scheduling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description 启动项目就开始执行的，并且不会发生改变的定时任务
 * @Author hu zhongxi
 */
@Configuration
@EnableScheduling
public class FixedTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void deleteLogTimer() {
        System.out.println("开始删除系统中累积的日志！");
    }

}
