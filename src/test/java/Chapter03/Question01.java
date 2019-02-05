package Chapter03;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Question01 {

    /**
     * 조건부 로깅을 제공해 지연 로깅 기법을 개선하라.
     * 일반적으로 logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10])
     * 과 같이 호출할 것이다
     * 로거에서 메시지를 로그로 남길 수 없을 때는 조건을 평가하지 않아야 한다
     */
    @Test
    public void solution() {
        int[] a = new int[20];
        Arrays.fill(a, 0);
        for (int i=0; i<20; i++) {
            final int j = i;
            logIf(Level.FINEST, () -> j == 10, () -> "a[10] = " + a[10]);
        }
    }

    public void logIf(Level level, Supplier<Boolean> condition, Supplier<String> message) {
        if (!Level.FINEST.equals(level)) {
            return;
        }
        if (condition.get()) {
            System.out.println(message.get());
        }
    }

    /**
     * 일반적으로 로깅을 할 때 로그레벨, 조건, 메시지를 다 넘긴다고 할 시
     * 파라미터로 넘길 때 이미 조건, 메시지까지 다 만들어서 계산한 뒤 함수 호출이 이뤄진다
     *
     * 이걸 람다식으로 바꾸면 필요한 시기에 계산을 하게 되므로
     * 실제로 로그를 남기지 않을 때는 조건, 메시지에 대한 계산을 수행하지 않는다는 뜻이다
     */
}
