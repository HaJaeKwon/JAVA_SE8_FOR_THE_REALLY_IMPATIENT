package Chapter01;

import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question03 {

    /**
     * java.io.File 클래스의 list(FilenameFilter) 메서드를 이용해 주어진 디렉터리에서 주어진 확장자를 지닌 모든 파일을 리턴하는 메서드를 작성하라
     * filenameFilter 가 아닌 람다 표현식을 사용하라
     * 이 람다 표현식이 자신을 감싸는 유효 범위에 있는 어느 변수를 캡처하는가?
     */
    @Test
    public void solution() {
        File root = Paths.get("/Users", "jaekwonha", "workspace").toFile();
        String suffix = ".pem";

        System.out.println("capture scope is main variable");
        Arrays.stream(root.list((dir, name) -> name.endsWith(suffix))).forEach(System.out::println);

        System.out.println("capture scope is inner class variable");
        Arrays.stream(root.list((dir, name) -> {
            String suffix2 = ".cpp";
            return name.endsWith(suffix2);
        })).forEach(System.out::println);
    }
    /**
     * scope 밖에 있는 변수 이름을 다시 사용할 수 없다
     * 이름이 중복되지 않으니깐 사실 무엇을 참조하는지 햇갈릴 일은 없을 것 같다
     */
}
