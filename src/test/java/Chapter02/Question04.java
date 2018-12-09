package Chapter02;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question04 {

    /**
     * int[] values = { 1,4,9,16 }
     * 배열이 있다고 하자
     * Stream.of(values)의 결과는 무엇인가?
     * int 스트림은 어떻게 얻을 수 있는가?
     */
    @Test
    public void solution() {
        int[] values = {1, 4, 9, 16};
        Stream<int[]> st1 = Stream.of(values);
        IntStream st2 = Arrays.stream(values);
        System.out.println("Stream.of");
        st1.forEach(s -> Arrays.stream(s).forEach(System.out::println));
        System.out.println("Arrays.stream");
        st2.forEach(System.out::println);
    }

    /**
     * Stream.of(values) 의 결과는 Stream<int[]>이다
     * Stream.of()의 리턴값이 Stream<T>이기 때문에 int[]를 넣으면 T가 int[]가 된다
     * Stream<int>은 Arrays.stream()으로 얻을 수 있다
     */
}
