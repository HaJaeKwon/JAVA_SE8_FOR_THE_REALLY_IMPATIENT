package Chapter08;

import org.junit.Test;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question01 {

    /**
     * int 값과 부호 없는 연산을 사용해 0 ~ 2^32 - 1 사이의 숫자들을 더하고, 빼고 ,나누고, 비교하는
     * 프로그램을 작성하라. divideUnsigned 와 remainderUnsigned 가 필요한 이유를 설명하라
     */
    @Test
    public void solution() {
        int a = -123456;
        int b = -654321;

        System.out.println("+ : " + (a + b));
        System.out.println("- : " + (a - b));
        System.out.println("* : " + (a * b));
        System.out.println("/ : " + (a / b));
        System.out.println("% : " + (a % b));

        System.out.println(Integer.toUnsignedLong(a));
        System.out.println(Integer.toUnsignedLong(b));

        System.out.println("/ : " + Integer.divideUnsigned(a, b));
        System.out.println("% : " + Integer.remainderUnsigned(a, b));
        System.out.println(Integer.compareUnsigned(a, b));
    }

    /**
     * divideUnsigned, remainderUnsigned 는
     * 내부적으로 toUnsignedLong 을 호출하여 long 으로 캐스팅한 뒤 계산 후 다시 int 캐스팅을 한다
     * 계산 과정에서 음수가 나오거나 int 범위를 초과해도 제대로 계산이 된다
     */
}
