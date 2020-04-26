package Chapter06;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hajaekwon on 2019-04-08.
 */
public class Question01 {

    /**
     * 다수의 스레드에서 관찰하는 가장 긴 문자열을 추적하는 프로그램을 작성하라
     * AtomicReference와 적합한 누산자 (accumulator)를 사용한다
     */
    @Test
    public void solution() {

        AtomicReference<Long> result = new AtomicReference<>(0L);

        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

            int core = Runtime.getRuntime().availableProcessors();
            int step = (int) Math.ceil((double) words.size() / core);
            ExecutorService pool = Executors.newFixedThreadPool(core);
            for (int i = 0; i < core; i++) {
                final int index = i;
                pool.submit(() -> {
                    for (String word : words.subList((index * step), Math.min((index + 1) * step, words.size()))) {
                        result.accumulateAndGet(Long.valueOf(word.length()), Math::max);
                    }
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result.get());
    }

    /**
     * core 수만큼 threadPool 만들어서 실행시켰다
     * updateAndGet or accumulateAndGet 을 이용하면 된다
     */

}
