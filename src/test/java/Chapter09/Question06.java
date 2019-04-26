package Chapter09;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question06 {

    /**
     * 파일의 모든 행을 읽어서 역순으로 쓰는 프로그램을 작성하라
     * 이때 Files.readAllLines와 Files.write를 사용한다
     */
    @Test
    public void solution() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src", "test", "java", "Chapter06", "alice-the-pool-of-tears.txt"));
        Collections.reverse(lines);
        Files.write(Paths.get("src", "test", "java", "Chapter09", "out06.txt"), lines);
    }
}
