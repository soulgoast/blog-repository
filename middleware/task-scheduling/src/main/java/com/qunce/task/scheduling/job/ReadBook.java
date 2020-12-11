package com.qunce.task.scheduling.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class ReadBook implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("开始看书！");
    }
}
