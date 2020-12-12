package com.qunce.task.scheduling.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class AsyncData implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:sss");
        System.out.println("同步数据任务执行！" + simpleDateFormat.format(new Date()));
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        Set<String> strings = mergedJobDataMap.keySet();
        if (CollectionUtils.isEmpty(strings)) {
            return;
        }

        strings.forEach(key -> System.out.println("key:" + key + ", value:" + mergedJobDataMap.get(key)));
    }
}
