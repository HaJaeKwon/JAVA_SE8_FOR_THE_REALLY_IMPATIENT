package Chapter01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaekwonha on 2018. 11. 24..
 */
public class Question08 {

    /**
     * 람다 표현식이 다음과 같은 향상된 for 루프에 있는 변수를 캡처할 때
     * 무슨 일이 일어나는가?
     * <p>
     * 규칙에 맞는 작업인가?
     * 각 람다 표현식이 다른 값을 캡쳐하는가
     * 아니면 모두 마지막 값을 얻는가
     * 만일 전통적인 루프인 for (int = 0; i < names.length; i++) 를
     * 사용하면 무슨 일이 일어나는가?
     */
    @Test
    public void solution() {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }
        runners.forEach(r -> r.run());
    }
    /**
     * 모두 다른 값을 캡처한다
     */

}
