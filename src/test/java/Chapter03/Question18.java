package Chapter03;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Question18 {

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
