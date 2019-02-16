package Chapter03;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Question07 {

    /**
     * Comparator<String> 을 생성하는 메서드를 작성하라. 이 객체는 일반 비교 또는 역 비교, 대소문자 구분 또는 무시,
     * 공백 구분 또는 무시, 이들의 조합으로 비교를 수행하는데 사용할 수 있다.
     * 구현하는 메서드에서는 람다 표현식을 리턴해야 한다.
     */
    @Test
    public void solution() {
        String[] strings = {"jaekwon", "en-ah ", " advice", "Back"};
        Arrays.sort(strings, getStringComparator());
        Arrays.stream(strings).forEach(System.out::println);
    }

    public static Comparator<String> getStringComparator() {
        return (s1, s2) -> {
            return s1.trim().toLowerCase().compareTo(s2.trim().toLowerCase());
        };
    }
}
