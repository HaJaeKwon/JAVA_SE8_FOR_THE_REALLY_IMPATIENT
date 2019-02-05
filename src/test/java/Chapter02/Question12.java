package Chapter02;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 22..
 */
public class Question12 {

    /**
     * 64페이지에 있는 "2.13 병렬 스트림" 절에서 설명한 것처럼 AtomicInteger 배열을 업데이트하는 방법으로
     * 병렬 Stream<String> 에 있는 모든 짧은 단어의 개수를 세라
     * 각 카운터를 안전하게 증가시키기 위해 원자적 메서드인 getAndIncrement 를 사용한다
     */
    @Test
    public void solution() {
        try {

            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

//            AtomicIntegerArray counter = new AtomicIntegerArray(12);
//            words.stream().parallel().unordered().filter(w -> w.length() < 12).forEach(w -> counter.getAndIncrement(w.length()));
//            System.out.println(counter);

//            AtomicInteger[] counter2 = new AtomicInteger[12]; -> 이렇게 오브젝트를 초기화하면 모두 null로 채워짐
//            Arrays.fill(counter2, new AtomicInteger(0)); -> 이렇게 오브젝트를 채우면 모두 래퍼런스를 공유하게 됨

            AtomicInteger[] counter2 = Stream.generate(AtomicInteger::new).limit(12).toArray(AtomicInteger[]::new); // -> 모두 각자 생성됨
            words.stream().parallel().unordered().filter(w -> w.length() < 12).forEach(w -> counter2[w.length()].getAndIncrement());
            Arrays.stream(counter2).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * AtomicInteger[] 를 사용하면 초기화가 안되서 null 오류가 난다
     * 초기화까지 되는 AtomicIntegerArray 를 사용했다....가 방법을 찾아보았다
     * Stream.generate 에 오브젝트의 생성자 (supplier) 를 넘겨주면 array 로 만들면서 생성자도 호출하여 초기화된 array 를 얻을 수 있다
     *
     * unordered 를 사용해서 순서에 대한 요구사항을 버려 효과적으로 병렬화 할 수 있다
     */
}
