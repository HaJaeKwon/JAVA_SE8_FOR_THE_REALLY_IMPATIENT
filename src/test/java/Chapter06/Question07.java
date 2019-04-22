package Chapter06;

import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hajaekwon on 2019-04-19.
 */
public class Question07 {

    /**
     * ConcurrentHashMap<String, Long>에서 최댓값(값이 같을 때는 임의로 선택)을 가진 키를 찾아라.
     * 힌트 : reduceEntries 를 사용한다
     */
    @Test
    public void solution() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

        for (int i=0; i<1000; i++) {
            Random random = new Random();
            map.put(i+"", random.nextLong());
//            map.put(i+"", (long)i);
        }

        Long result = map.reduceEntries(500, (k, v) -> {
            if (k.getValue() < v.getValue()){
                return v;
            }
            return k;
        }).getValue();

        System.out.println(result);
    }

}
