package Chapter09;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question05 {

    /**
     * 파일의 모든 문자를 읽어서 역순으로 쓰는 프로그램을 작성하라
     * 이때 Files.readAllBytes와 Files.write를 사용한다
     */
    @Test
    public void solution() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src", "test", "java", "Chapter06", "alice-the-pool-of-tears.txt"));
        byte[] reverseBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            reverseBytes[bytes.length - i - 1] = bytes[i];
        }
        Files.write(Paths.get("src", "test", "java", "Chapter09", "out05.txt"), reverseBytes);
    }
}
