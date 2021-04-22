package com.qunce.reactor;

import io.lettuce.core.protocol.DemandAware;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class FluxTest {

    @Test
    public void interval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1000);
    }

    @Test
    public void fromIterable() {
        Flux.fromIterable(Arrays.asList("aaa", "bbb", "ccc")).subscribe(System.out::println);
    }

    @Test
    public void flatMapIterable() {
        Map<String, Object> maps = new HashMap<>();
        maps.put("aaa", "aaa");
        maps.put("bbb", "bbb");
        maps.put("ccc", "ccc");
        maps.put("ddd", "ddd");
        maps.put("eee", "eee");
    }

    @Test
    public void sink() {

        List<FluxSink<String>> list = new ArrayList<>();
        Flux.<String>create(sink -> {
            list.add(sink);
            sink.onDispose(() -> list.remove(sink));
        });
    }
}
