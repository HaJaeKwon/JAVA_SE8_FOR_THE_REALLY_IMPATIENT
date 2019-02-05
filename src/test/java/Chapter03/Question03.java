package Chapter03;

import org.junit.Test;

import java.util.function.Predicate;

public class Question03 {

    /**
     * 자바 1.4는 언어에 assert 키워드를 이용한 assertions 기능을 추가했다
     * assertions 이 라이브러리 기능으로 제공되지 않는 이유는 무엇인가?
     * 자바8에서는 assertions 을 라이브러리 기능으로 구현할 수 있는가?
     */
    @Test
    public void solution() {
        String str = "jaekwon";
        assertion(str, t -> t.equals("jaekwon"));
    }

    public <T> void assertion(T t, Predicate<T> predicate) {
        if (!predicate.test(t)) {
            throw new AssertionError();
        }
    }
}
