package Chapter08;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question05 {

    /**
     * 2장 시작 부분에서는 words.stream().filter(w => w.length() > 12).count()를 이용해
     * 리스트에 들어 있는 긴 단어의 개수를 셌다.
     * 람다 표현식을 이용하되, stream은 사용하지 않고 같은 작업을 구현하라.
     * 긴 리스트의 경우 어느 연산이 더 빠른가?
     */
    @Test
    public void solution() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter06/alice-down-the-rabbit-hole.txt")), StandardCharsets.UTF_8);
            List<String> words = new java.util.ArrayList<>(Arrays.asList(contents.split("[\\P{L}]+")));

            long start, end;
            int limit = 5;
            int step = 12;

            for(int i=0; i<step; i++) words.addAll(words);
            System.out.println(words.size());

            Predicate<String> p = w -> w.length() > limit;

            start = System.currentTimeMillis();
            for(Iterator it = words.iterator(); it.hasNext();) {
                if (p.test((String)it.next())) {
                    it.remove();
                }
            }
            long result3 = words.size();
            end = System.currentTimeMillis();
            System.out.printf("iter remove %d ms %d\n", end - start, result3);

            words = new java.util.ArrayList<>(Arrays.asList(contents.split("[\\P{L}]+")));
            for(int i=0; i<step; i++) words.addAll(words);
            start = System.currentTimeMillis();
            long result1 = words.stream().filter(p.negate()).count();
            end = System.currentTimeMillis();
            System.out.printf("stream %d ms %d\n", end - start, result1);

            words = new java.util.ArrayList<>(Arrays.asList(contents.split("[\\P{L}]+")));
            for(int i=0; i<step; i++) words.addAll(words);
            start = System.currentTimeMillis();
            words.removeIf(p);
            long result2 = words.size();
            end = System.currentTimeMillis();
            System.out.printf("removeIf %d ms %d\n", end - start, result2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 개수가 많아질수록 iterate 방식이 현저히 느리다.
     * iterate 방식은 O(N^2)이고 나머지는 O(N)이다
     *
     * stream과 removeIf는 시간이 거의 비슷하다
     */
}
