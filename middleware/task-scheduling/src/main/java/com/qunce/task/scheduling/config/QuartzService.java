package com.qunce.task.scheduling.config;

import com.qunce.task.scheduling.job.AsyncData;
import com.qunce.task.scheduling.job.ReadBook;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Description 提供定时任务的curd操作
 * @Author hu zhongxi
 */
@Component
@AllArgsConstructor
public class QuartzService {

    private final Scheduler scheduler;

    public static final String GROUP_NAME = "group_name";
    public static final String JOB_NAME = "job_name";


    @PostConstruct
    public void init() throws SchedulerException, InterruptedException {
        scheduler.start();
        String jobName = "asyncData";
        System.out.println("添加定时任务");
        addJob(jobName, AsyncData.class, null, "0/5 * * * * ?");
        // addJob("readBook", ReadBook.class, null, "0/10 * * * * ?");
        Thread.sleep(20 * 1000);
        System.out.println("修改定时任务");
        updateJob(jobName, "0/2 * * * * ?");
        Thread.sleep(20 * 1000);
        System.out.println("停止定时任务");
        stopJob(jobName);
        Thread.sleep(20 * 1000);
        System.out.println("重启定时任务");
        restartJob(jobName);
        Thread.sleep(20 * 1000);
        System.out.println("删除定时任务");
        removeJob(jobName);
        Thread.sleep(20 * 1000);
        System.out.println("定时任务执行结束");

    }

    /**
     * 添加一个定时任务
     */
    public void addJob(String jobName, Class clazz, Map<String, String> jobData, String cron) throws SchedulerException {
        // 创建HelloworldJob的JobDetail实例，并设置name/group
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName,GROUP_NAME)
                //JobDataMap可以给任务传递参数
                .usingJobData("job_param","job_param1")
                .build();
        // 创建Trigger触发器设置使用cronSchedule方式调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, GROUP_NAME)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }


    /**
     * 修改定时任务
     */
    public void updateJob(String jobName, String newTime) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(jobName, GROUP_NAME);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = trigger.getCronExpression();
        if (!oldTime.equals(newTime)) {
            JobDetail jobDetail = getJobDetail(jobName, GROUP_NAME);
            Class objJobClass = jobDetail.getJobClass();
            removeJob(jobName);
            addJob(jobName, objJobClass, null, newTime);
        }
    }

    /**
     * 暂停定时任务
     * @param jobName
     */
    public void stopJob(String jobName) throws SchedulerException {
        // 停止触发器
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, GROUP_NAME));
    }

    /**
     * 重新开始定时任务
     * @param jobName
     */
    public void restartJob(String jobName) throws SchedulerException {
        // 停止触发器
        scheduler.resumeJob(JobKey.jobKey(jobName, GROUP_NAME));
    }


    public void removeJob(String jobName) throws SchedulerException {
        // 停止触发器
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, GROUP_NAME));
        // 移除触发器
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, GROUP_NAME));
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName, GROUP_NAME));
    }

    public CronTrigger getCronTrigger(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return cronTrigger;
    }

    public JobDetail getJobDetail(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        return jobDetail;
    }
}
