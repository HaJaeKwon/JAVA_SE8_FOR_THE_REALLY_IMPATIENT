package Chapter08;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question10 {

    /**
     * JDK src.zip 파일의 압축을 해제하라
     * Files.walk를 사용해 transient와 volatile을 포함하는 모든 자바 파일을 찾아라
     */
    @Test
    public void solution() {
        File src = new File("C:\\Program Files\\Java\\jdk1.8.0_181\\src");
        try {
            Files.walk(Paths.get(src.getAbsolutePath())).filter( p -> {
                return !p.toFile().isDirectory();
            }).filter( p -> {
                String contents = "";
                try {
                    contents = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return contents.contains("transient") || contents.contains("volatile");
            }).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
