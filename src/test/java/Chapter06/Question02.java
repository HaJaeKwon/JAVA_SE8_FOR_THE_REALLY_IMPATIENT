package Chapter06;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by hajaekwon on 2019-04-08.
 */
public class Question02 {

    /**
     * LongAdder가 일련의 증가하는 ID를 생산하는 데 도움이 되는가?
     * 도움이 되는 이유는 무엇인가, 그렇지 않다면 그 이유는 무엇인가?
     */
    @Test
    public void solution() {
        long start, end;
        final int loopCount = 1000000;

        final LongAdder adder = new LongAdder();
        start = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            adder.increment();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        long l = 0L;
        start = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            l = l + 1;
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
    /**
     * 여러 스레드에서 접근할때는 LongAdder가 이점이 있다
     * 하지만 단일 스레드에서 ID를 생성하는 거라면 LongAdder은 이점이 없다
     */

}
