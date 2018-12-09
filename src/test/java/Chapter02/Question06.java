package Chapter02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question06 {

    /**
     * 44페이지에 있는 "2.3 filter, map, flatmap 메서드"절의 characterStream 메서드는 먼저 ArrayList를 채운 후
     * 스트림으로 변환하기 때문에 약간은 세련되지 못했다
     * 스트림에 기반을 둔 한 행짜리 메서드로 작성하라
     * 한 가지 접근법은 0 ~ s.length() - 1 범위에서 정수 스트림을 만들어서 메서드 레퍼런스에 맵핑하는 것이다
     */
    @Test
    public void solution() {
        String[] strings = {"your", "boat"};
        Stream<String> stringStream = Arrays.stream(strings);

//        Stream<Character> stream = stringStream.flatMap(w -> oldCharacterStream(w));
        Stream<Character> stream = stringStream.flatMap(Question06::newCharacterStream);

        stream.forEach(System.out::println);

        stream.spliterator().estimateSize();
    }

    public static Stream<Character> oldCharacterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) result.add(c);
        return result.stream();
    }

    public static Stream<Character> newCharacterStream(String s) {
        return IntStream.range(0, s.length()).mapToObj(i -> s.charAt(i));
//        Arrays.stream((Character[])s.toCharArray());
//        Arrays.stream(s.split(""));
    }

    /**
     * Arrays.stream(T[]) 를 사용하려고 했지만 char[]는 파라미터로 들어가지 않는다
     * 허용된 primitive type 이 int, long, double 뿐인 것 같다
     * IntStream.range 와 mapToObj 를 이용하여 각 인덱스에 접근하여 map 을 이용하여
     * 다른 값으로 치환해주는 방식으로 해결하였다
     */
}