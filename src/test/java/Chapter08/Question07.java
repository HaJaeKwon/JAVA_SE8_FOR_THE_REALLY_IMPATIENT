package Chapter08;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question07 {

    /**
     * reversed를 호출하지 않고 nullsFirst(naturalOrder()).reversed()를 표현하라
     */
    @Test
    public void solution() {
        List<String> list = new ArrayList<>();

        list.sort(Comparator.comparing(String::length, Comparator.nullsFirst(Comparator.reverseOrder())));
    }
    /**
     * naturalOrder란 자연순서. Comparable을 구현하는 어떤 클래스든 그에 해당하는 비교자를 만든다.
     * reverseOrder는 그 반대순서를 준다
     */
}
