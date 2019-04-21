package Chapter06;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by hajaekwon on 2019-04-18.
 */
public class Question05 {

    /**
     * 다수의 스레드가 파일 집합에서 모든 단어를 읽는 애플리케이션을 작성하라.
     * 각 단어가 어느 파일들에서 나타나는지 추적하는 데 ConcurrentHashMap<String, Set<File>>을 사용하고,
     * 맵을 업데이트하는 데 merge 메서드를 사용하라
     */
    @Test
    public void solution() {

        Set<File> fileSet = new LinkedHashSet<>();


        ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();


        int threadCount = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i=0; i<threadCount; i++) {
            pool.execute(new Thread (() -> {
                try {
                    String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter06/alice.txt")), StandardCharsets.UTF_8);
                    List<String> words = Arrays.asList(contents.split("[\\P{L}+]"));

                    words.stream().filter(w -> { System.out.println(w); return w.length() > 5; }).limit(5).collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
        pool.shutdown();

    }

}
