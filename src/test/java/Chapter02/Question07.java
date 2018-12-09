package Chapter02;

import javafx.util.converter.IntegerStringConverter;
import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question07 {

    /**
     * 여러분의 관리자가 public static <T> boolean isFinite(Stream<T> stream)
     * 메서드를 작성하라고 했다
     * 이 메서드를 작성하는 일이 썩 좋지 못한 생각인 이유는 무엇인가
     * 어쨌든 지금 바로 작성하자
     */
    @Test
    public void solution() {
        Stream<Double> infiniteStream = Stream.generate(Math::random);
        Stream<Integer> finiteStream = IntStream.range(0, 1000).mapToObj(i -> i);

        System.out.println(isFinite(infiniteStream));
        System.out.println(isFinite(finiteStream));
    }

    public static <T> boolean isFinite(Stream<T> stream) {
        return stream.spliterator().estimateSize() != Long.MAX_VALUE;
    }

    /**
     * estimateSize 함수의 반환값이 Long.MAX_VALUE 인 경우는 infinite, unknown, expensive to compute
     * 이기 때문에 정확한 infinite 확인을 할 수는 없다
     * infinite 인지를 확인하는 것은 비용이 많이 들기 때문에 sized stream 을 사용하는 것이 더 안전하고 효율적으로 보인다
     */
}
