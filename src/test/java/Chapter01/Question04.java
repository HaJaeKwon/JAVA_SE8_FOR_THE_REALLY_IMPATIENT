package Chapter01;

import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question04 {

    /**
     * File 객체 배열이 주어졌을 때 , 디렉토리 가 파일보다 앞에 위치하고 각 그룹 안에서 요소들이 경로 이름에 따라 정렬되도록 정렬하라
     * Comparator가 아닌 람다 표현식을 사용하라
     */
    @Test
    public void solution() {
        File[] arr = Paths.get("/Users", "jaekwonha", "workspace").toFile().listFiles();

        Arrays.sort(arr, (x, y) -> {
            if (x.isDirectory() == y.isDirectory()) {
                return x.getAbsolutePath().toLowerCase().compareTo(y.getAbsolutePath().toLowerCase());
            }
            return y.isDirectory() ? 1 : -1;
        });

        Arrays.stream(arr).forEach(System.out::println);
    }
    /**
     * Arrays.stream.sorted 함수는 stream 을 반환하기 때문에 리덕션 메소드를 마지막에 호출해줘야 실행이 된다
     * Arrays.sort 는 바로 실행된다
     * Comparator 는 return 이 1이면 바뀐다 -> 뒤의 값이 앞으로 와야 되는 상황이면 1을 리턴하면 된다
     */
}
