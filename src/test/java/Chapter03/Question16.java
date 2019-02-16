package Chapter03;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Question16 {

    @Test
    public void solution() {
        doInOrderAsync(() -> "s", (s, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(s);
            }
        });
    }

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                T result = null;
                Throwable throwable = null;
                try {
                    result = first.get();
                } catch (Throwable t) {
                    throwable = t;
                } finally {
                    second.accept(result, throwable);
                }
            }
        };
        t.start();
    }
    /**
     * first 에서 발생하는 에러를 이용하여 second 를 실행하여야 할 때 사용할 수 있을 듯 하다
     * 세번째 파라미터를 받지 않으면 second 에서 예외 발생 시 처리할 로직을 줄 수 없다
     *
     * second 를 BiConsumer 로 만들면 first 와 second 의 디펜던시만 강해지는 느낌인데....
     * 차라리 first, second 를 같은 로직으로 만들고 handler 를 따로 주는게 낫지 않나?
     */
}
