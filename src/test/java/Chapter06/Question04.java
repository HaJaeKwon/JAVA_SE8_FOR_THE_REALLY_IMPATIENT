package Chapter06;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class Question04 {

    /**
     * 누적요소들의 최댓값 또는 최솟값을 계산하는데 LongAccumulater를 사용하라
     */

    @Test
    public void solution() {
        int[] items = {1,2,3,4,5};
        int threadCount = 4;

        try {
            LongAccumulator maxLongAccumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
            ExecutorService pool = Executors.newFixedThreadPool(8);
            for (int i=0; i<threadCount; i++) {
                pool.execute(new Thread (() -> {
                    int item = items[(int)Thread.currentThread().getId()%items.length];
                    System.out.println(String.format("max insert : %d", item));
                    maxLongAccumulator.accumulate(item);
                }));
            }
            pool.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println(String.format("MAX result : %d", maxLongAccumulator.get()));

            System.out.println("start min");

            LongAccumulator minLongAccumulator = new LongAccumulator(Long::min, Long.MAX_VALUE);
            pool = Executors.newFixedThreadPool(8);
            for (int i=0; i<threadCount; i++) {
                pool.execute(new Thread (() -> {
                    long item = (long)items[(int)Thread.currentThread().getId()%items.length];
                    System.out.println(String.format("min insert : %d", item));
                    minLongAccumulator.accumulate(item);
                }));
            }
            pool.awaitTermination(1, TimeUnit.SECONDS);
//            pool.shutdown();

            System.out.println(String.format("MIN result : %d", minLongAccumulator.get()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
