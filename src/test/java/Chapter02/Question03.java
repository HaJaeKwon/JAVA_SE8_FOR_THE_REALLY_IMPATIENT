package Chapter02;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question03 {

    /**
     * stream 대신 parallelStream을 이용해 긴 단어 개수를 셀 때 차이를 측정하라
     * 각 호출 이전과 이후에 System.currentTimeMills를 호출하고 차이를 출력한다
     * 빠른 컴퓨터를 보유하고 있다면 전쟁과 평화 War and Piece 처럼 더 큰 문서로 바꿔서 측정하라
     */
    @Test
    public void solution() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

            long start,end;
            int count;
            start = System.currentTimeMillis();
            count = (int)words.stream().filter(w -> w.length() > 5).count();
            end = System.currentTimeMillis();
            System.out.println(String.format("stream result : %d. spend time : %d", count, end - start));

            start = System.currentTimeMillis();
            count = (int)words.parallelStream().filter(w -> w.length() > 5).count();
            end = System.currentTimeMillis();
            System.out.println(String.format("parallelStream result : %d. spend time : %d", count, end - start));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * strema : 329
     * parallelStream : 17
     * 연산의 순서가 상관없을때는 parallelStream을 쓰는 것이 좋아보인다
     */
}
