package Chapter06;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by hajaekwon on 2019-04-18.
 */
public class Question06 {

    /**
     * 앞의 연습문제를 반복하되, 이번에는 computeIfAbsent 를 사용하라. 이 접근법의 장점은 무엇인가?
     */
    @Test
    public void solution() {

        try {
            ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

            Set<File> fileSet = new LinkedHashSet<>();
            File root = new File("./src/test/java/Chapter06");
            for (File child : Arrays.stream(root.listFiles()).filter(file -> file.getName().endsWith(".txt")).collect(Collectors.toList())) {
                fileSet.add(child);
            }

            ExecutorService pool = Executors.newCachedThreadPool();
            for (File file : fileSet) {
                pool.execute(new Thread (() -> {
                    try {
                        String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                        List<String> words = Arrays.asList(contents.split("[\\P{L}+]"));
                        for (String word : words) {
                            map.computeIfAbsent(word.toLowerCase(), k -> new LinkedHashSet<>()).add(file);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }
            pool.awaitTermination(1000, TimeUnit.MILLISECONDS);

            map.forEach((key, fileSet1) -> {
                System.out.println("key : " + key);
                for(File f : fileSet1) {
                    System.out.println("file : " + f.getName());
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * merge 를 쓸때는 미리 Set<File> 을 만들고 (필요없을때도) 없다면 add 해주는 방식이였는데
     * computeIfAbsent 는 필요할 때만 (기존에 없었을 때만) 생성 할 수 있다는 장점이 있다!
     */

}
