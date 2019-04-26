package Chapter08;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question14 {

    /**
     * Objects.requireNonNull 메서드의 사용을 보이고, 이 메서드가 어떻게 좀 더
     * 유용한 오류 메시지를 얻게 해주는지 증명하라
     */
//    @Test(expected = NullPointerException.class)
    @Test
    public void solution() {
        List<String> list = Arrays.asList("jaekwon", "sosim", null);
        for (String s : list) {
            Objects.requireNonNull(s, () -> "item must not be null");
        }
    }
    /**
     * NullPointerException 이 발생하고 문구로 param 으로 넘긴 값이 출력된다
     * requireNonNull 역시 지연로깅을 지원하고 로깅 조건에 부합하지 않으면 String 이 계산되어질일은 없다
     */

}
