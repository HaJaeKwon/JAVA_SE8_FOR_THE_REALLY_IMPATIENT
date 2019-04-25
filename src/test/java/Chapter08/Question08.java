package Chapter08;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question08 {

    /**
     * CheckedQueue 클래스의 장점을 보여주는 프로그램을 작성하라
     */
    @Test(expected = ClassCastException.class)
    public void solution() {
        Queue<String> q = new LinkedList<>();
        q = Collections.checkedQueue(q, String.class);

        q.offer("jaekwon");

        Queue q2 = q;
        q2.offer(123);
    }

}
