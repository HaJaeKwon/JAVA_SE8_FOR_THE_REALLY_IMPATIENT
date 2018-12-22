package Chapter02;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 22..
 */
public class Question08 {

    /**
     * public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) 메서드를 작성하라
     * 이 메서드는 first와 second 스트림의 요소들을 교환하며, 두 스트림 중 하나에서 요소가 바닥이 나면 작업을 중단한다
     */
    @Test
    public void solution() {
        Stream<String> first = Stream.of("jaekwon", "teahyung", "hesun");
        Stream<String> second = Stream.of("cow", "cat", "dog");
    }

//    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
//
//    }
}
