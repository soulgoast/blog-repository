package com.qunce.async;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MultiThreadExecutor {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        indexPage();
    }

    public void indexPage() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> nameFuture = executorService.submit(this::getName);
        Future<String> addressFuture = executorService.submit(this::address);
        Future<String> professionFuture = executorService.submit(this::profession);
        System.out.println(nameFuture.get() + "居住在" + addressFuture.get() + ", 从事" + professionFuture.get());
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 );

    }

    public String getName() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "张三";
    }

    public String address() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "武汉市洪山区";
    }

    public String profession() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "水电工";
    }



}
