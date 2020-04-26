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
import java.util.concurrent.TimeUnit;
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

        try {
            ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

            Set<File> fileSet = new LinkedHashSet<>();
            File root = new File("./src/test/java/Chapter06");
            for (File child : Arrays.stream(root.listFiles()).filter(file -> file.getName().endsWith(".txt")).collect(Collectors.toList())) {
                fileSet.add(child);
            }

            ExecutorService pool = Executors.newCachedThreadPool();
            for (File file : fileSet) {
                pool.execute(new Thread(() -> {
                    try {
                        String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                        List<String> words = Arrays.asList(contents.split("[\\P{L}+]"));
                        for (String word : words) {
                            Set<File> set = new LinkedHashSet<>();
                            set.add(file);
                            map.merge(word.toLowerCase(), set, (a, b) -> {
                                a.add(file);
                                return a;
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }
            pool.awaitTermination(1000, TimeUnit.MILLISECONDS);

            map.forEach((key, fileSet1) -> {
                System.out.println("key : " + key);
                for (File f : fileSet1) {
                    System.out.println("file : " + f.getName());
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
