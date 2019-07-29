package Chapter03;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Question17 {

    /**
     * first와 second를 병렬로 실행하는 doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) 메서드를 구현하라
     * 만일 이 둘의 메서드에서 예외를 던지면 handler를 호출한다
     */
    @Test
    public void solution() {
        doInParallelAsync(
                () -> {
                    System.out.println("hello first");
                },
                () -> {
                    System.out.println("hello second");
                },
                (t) -> {
                    t.printStackTrace();
                }
        );
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            pool.submit(first);
            pool.submit(second);
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (Throwable t) {
            handler.accept(t);
        }

    }
}
