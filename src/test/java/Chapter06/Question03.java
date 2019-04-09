package Chapter06;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by hajaekwon on 2019-04-09.
 */
public class Question03 {

    /**
     * 각각 카운터를 100,000번 증가시키는 스레드 1,000개를 생성하라.
     * 각각 AtomicLong과 LongAdder를 사용할 때의 성능을 비교하라
     */
    @Test
    public void solution() {
        long start, end;
        int MAX = 100000;
        int threadCount = 1000;
        AtomicLong atomicLong = new AtomicLong();
        LongAdder longAdder = new LongAdder();

        ExecutorService pool = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();
        for (int i=0; i<threadCount; i++) {
            pool.execute(new Thread (() -> {
                for (int j=0; j<MAX; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        pool.shutdown();
        end = System.currentTimeMillis();
        System.out.println(String.format("AtomicLong : %d", end - start));

        ExecutorService pool2 = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();
        for (int i=0; i<threadCount; i++) {
            pool2.execute(new Thread (() -> {
                for (int j=0; j<MAX; j++) {
                    longAdder.increment();
                }
            }));
        }
        pool2.shutdown();
        end = System.currentTimeMillis();
        System.out.println(String.format("LongAdder : %d", end - start));
    }

    /**
     * 높은 경쟁 상황에서는 LongAdder를 사용하는 것이 더 효율적이다
     */
}
