package com.qunce.raft;

import sun.plugin.com.Utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName RaftCore
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/11/4 14:26
 * @ModifyDate 2020/11/4 14:26
 * @Version 1.0
 */
public class RaftCore {

    // 投票
    public static final String API_VOTE = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/vote";

    // 心跳
    public static final String API_BEAT = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_PUB = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_DEL = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_GET = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_ON_PUB = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_ON_DEL = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    public static final String API_ON_PEER = UtilsAndCommons.NACOS_NAMING_CONTEXT + "/raft/beat";

    private ScheduledExecutorService executor =new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    });






}
