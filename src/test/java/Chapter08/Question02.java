package Chapter08;

import org.junit.Test;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question02 {

    /**
     * Math.negateExact(n)은 어떤 정수 n에 대해 예외를 던지는가?
     * 힌트 : 하나밖에 없다
     */
    @Test(expected = ArithmeticException.class)
    public void solution() {
        int a = -2147483648;
        int b = 2147483647;

        Math.negateExact(a);
    }

    /**
     * 0x80000000 (-2147483648)
     * int 범위 내의 가장 작은 값의 역부호화시에 overflow가 되기 때문에 예외를 던진다
     */

}
