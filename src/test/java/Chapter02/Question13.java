package Chapter02;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Question13 {

    /**
     * 연습문제 12를 다시 풀되, 이번에는 짧은 문자열을 걸러내고
     * collect 메서드를 Collectors.groupingBy, Collectors.counting 과 조합해 사용하라
     */

    @Test
    public void solution() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

            Map<Integer, Long> counter = words.stream().filter(w -> w.length() < 12).collect(Collectors.groupingBy(String::length, Collectors.counting()));
            counter.forEach((k, v) -> {
                System.out.println(String.format("key : %d, value : %d", k, v));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
