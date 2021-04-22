package com.qunce.reactor;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MonoTest {

    @Test
    public void empty() {
        Mono.empty()
                .subscribe(item -> System.out.println(item + "aaa"));
    }

    @Test
    public void just() {
        Mono<String> foo = Mono.just("foo");
        foo.subscribe(System.out::println);
    }

    @Test
    public void justOrEmpty() {
        Mono.justOrEmpty(null).subscribe(System.out::println);
        Mono.justOrEmpty("foo").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("aaa")).subscribe(System.out::println);
    }

    @Test
    public void error() {
        Mono.error(new RuntimeException()).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void never() {
        Mono.never().subscribe(item -> System.out.println(item));
    }

    /**
     * 消费一个源数据，返回另一个数据
     */
    @Test
    public void thenReturn() {
        Mono.just("foo")
                .thenReturn("aaa");
        Function<Integer, Integer> function = item -> item + 10;
        Integer apply = function.apply(20);
        System.out.println(apply);
    }

    /**
     * 判断数据是否为empty，如果为empty则走对应分支
     */
    @Test
    public void switchIfEmpty() {
        Mono.justOrEmpty(null).switchIfEmpty(Mono.error(new RuntimeException())).subscribe(System.out::println);
    }

    @Test
    public void flatMap() {
        Mono.just("aa").flatMap(item -> Mono.just(item + "vbvv")).subscribe(System.out::println);
    }

    /**
     * onErrorMap之前的任何map操作都会汇集到onErrorMap中操作。
     */
    @Test
    public void thenReturnAndOnErrorMap() {
        Mono.just("bbb")
                .flatMap(item -> {
                    System.out.println(item); throw new IndexOutOfBoundsException("自定义角标越界异常");})
                .map(item -> {
                    System.out.println(item); throw new NullPointerException("自定义空指针异常");})
                .thenReturn("bbb").onErrorMap(err -> new RuntimeException(err.getMessage()))
                .flatMap(item -> {System.out.println(item); return Mono.justOrEmpty(item);})
                .thenReturn(true).subscribe(System.out::println);
    }

    /**
     * 延迟创建数据源
     */
    @Test
    public void defer() {
        Mono.defer(() -> Mono.justOrEmpty(source())).map(item -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("睡眠一秒后");
            return item + "aaa";}).subscribe(System.out::println);
    }

    private String source() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("数据源生成完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "aa";
    }

    public void subscribeOn() {
        Mono.just("aa").subscribeOn(Schedulers.boundedElastic());
    }

    @Test
    public void filter() {
        Mono.just("aaa")
                .filter(StringUtils::isBlank)
                .onErrorContinue((err, res) -> System.out.println(err))
        .subscribe(System.out::println);
    }

    @Test
    public void toFlux() {
        Mono.just("aa").flux().subscribe(System.out::println);
    }

}
