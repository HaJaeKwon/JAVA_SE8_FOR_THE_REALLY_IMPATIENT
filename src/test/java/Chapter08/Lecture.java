package Chapter08;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by hajaekwon on 2019-04-22.
 */
public class Lecture {

    @Test
    public void lecture01() {
        String pathToRoot = "./src";
        try (Stream<Path> entries = Files.walk(Paths.get(pathToRoot), 5)) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<Path> entries = Files.find(Paths.get(pathToRoot), 5, (p, f) -> f.isRegularFile())) {
            entries.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Files.list 함수는 서브디렉터리까지 반환하지 않는다.
     * walk 를 사용하면 BFS 방식으로 서브 디렉터리까지 반환해준다
     * filter 기능이 필요하다면 find 메서드를 사용해라
     */


}
