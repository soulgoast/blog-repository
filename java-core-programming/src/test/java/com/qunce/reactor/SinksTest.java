package com.qunce.reactor;

import org.junit.Test;
import reactor.core.publisher.Sinks;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class SinksTest {

    @Test
    public void many() {
        Sinks.many().multicast().onBackpressureBuffer(16, false);
    }

}
