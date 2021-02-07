package com.qunce.atomic;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class AtomicLongTest {

    AtomicLong atomicLong = new AtomicLong();

    @Test
    public void constructor() {
        AtomicLong atomic = new AtomicLong(10);
        System.out.println(atomic.get());
    }

    @Test
    public void setAndGet() {
        atomicLong.set(10);
        long l = atomicLong.get();
        System.out.println(l);
    }

    @Test
    public void lazySet() {
        atomicLong.lazySet(12);
        System.out.println(atomicLong.get());
    }

    @Test
    public void atomic() {
        long andSet = atomicLong.getAndSet(20);
        System.out.println(andSet);
        boolean b = atomicLong.compareAndSet(10, 30);
        System.out.println(b);
        boolean b1 = atomicLong.compareAndSet(20, 30);
        System.out.println(b1);
        System.out.println(atomicLong.get());

        // JDK 9 之前 weakCompareAndSet和compareAndSet没有区别
        boolean b2 = atomicLong.weakCompareAndSet(10, 40);
        System.out.println(b2);
        boolean b3 = atomicLong.weakCompareAndSet(30, 40);
        System.out.println(b3);
        System.out.println(atomicLong.get());

        // 得到之前的值
        long andIncrement = atomicLong.getAndIncrement();
        System.out.println(andIncrement);
        System.out.println(atomicLong.get());

        long andDecrement = atomicLong.getAndDecrement();
        System.out.println(andDecrement);
        System.out.println(atomicLong.get());

        long andAdd = atomicLong.getAndAdd(10);
        System.out.println(andAdd);
        System.out.println(atomicLong.get());

        long l = atomicLong.updateAndGet(item -> item + 1);
        System.out.println(l);

    }

    @Test
    public void test01() {
        AtomicLong amountItemStock = new AtomicLong(0);
        int n = 2;
        class RemoveData {
            private long remove;
        }

        RemoveData removeData = new RemoveData();
        // TODO 返回的数量如上面的代码注释所示，计算过程是在一个Lambda表达式内进行的，因为作用域的问题，基本类型的值没办法逃逸出去（这样做也保证了计算的无状态性）。
        amountItemStock.accumulateAndGet(n, (pre, mount) -> pre >= n ? pre - (removeData.remove = mount) : pre - (removeData.remove = 0L));
    }

    @Test
    public void test03() {
        int b;
        int a = 10 - (b = 2);
        System.out.println(a);
    }

    public static void main(String[] args) {
        List<CategorypropDailySticDTO> wList = new ArrayList<>();
        Map<Long, List<CategorypropDailySticDTO>> wCateMapLst = wList.stream().collect(Collectors.groupingBy(CategorypropDailySticDTO::getCategoryId));
        for(Long id : wCateMapLst.keySet()) {
            if (wCateMapLst.get(id) == null) {
                continue;
            }
        }
    }

    @Data
    class CategorypropDailySticDTO{
        private Long categoryId;
    }
}
