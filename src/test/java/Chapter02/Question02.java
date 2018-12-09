package Chapter02;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question02 {

    /**
     * 처음 5개 긴 단어를 요청했을 때 일단 긴 단어를 5번째 발견하고 나면 filter 메서드를 호출하지 않음을 확인하라
     * 간단하게 각 메서드 호출을 기록하라
     */
    @Test
    public void solution() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}+]"));

            words.stream().filter(w -> { System.out.println(w); return w.length() > 5; }).limit(5).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 5개 찾고 추가 수행하지 않음
     */
}
