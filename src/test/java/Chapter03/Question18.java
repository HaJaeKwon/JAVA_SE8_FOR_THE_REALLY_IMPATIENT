package Chapter03;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Question18 {

    /**
     * 85페이지에 있는 "3.8 예외 다루기" 절에서 살펴본 unchecked 메서드의 변형 버전을 구현하라.
     * 이 메서드는 검사 예외를 던지는 람다로부터 Function<T, U>를 생성한다
     * 추상 메서드에서 임의의 예외를 던질 수 있는 함수형 인터페이스를 찾거나 제공해야 한다는 점에 유의하자
     */
    @Test
    public void solution() {
        String content = unchecked(() -> new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8)).apply("s");
        System.out.println(content);
    }

    public static <T, U> Function<T, U> unchecked(Callable<U> f) {
        return (t) -> {
            try {
                System.out.println(t);
                return f.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
