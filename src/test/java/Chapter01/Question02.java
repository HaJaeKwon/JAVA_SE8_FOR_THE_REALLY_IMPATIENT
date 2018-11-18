package Chapter01;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question02 {

    /**
     * java.io.File 클래스의 listFiles(FileFilter)와 isDirectory 메서드를 이용해 주어진 디렉터리의 모든 서브드렉터리를 리턴하는 메서드를 작성하라
     * FileFilter 객체 대신 람다 표현식을 사용하라
     * 메서드 표현식을 이용해 같은 작업을 반복하라
     */
    @Test
    public void solution() {
        Path path = Paths.get("/Users", "jaekwonha", "workspace");
//        Path path = Paths.get(".");

        File root = path.toFile();
//        File root = new File(path.toString());

        System.out.println("lambda expression");
        Arrays.stream(root.listFiles(file -> file.isDirectory())).forEach(System.out::println);

        System.out.println("method expression");
        Arrays.asList(root.listFiles(File::isDirectory)).forEach(System.out::println);
    }
    /**
     * path 를 절대경로로 얻어올때는 가장 앞에 /를 붙여야 한다
     * 하위 디렉토리에서 다시 재귀적으로 서브 디렉토리 탐색을 진행하고 싶었지만 하지 않았다
     */
}
