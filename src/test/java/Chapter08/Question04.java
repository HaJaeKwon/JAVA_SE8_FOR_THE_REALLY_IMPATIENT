package Chapter08;

import org.junit.Test;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question04 {

    /**
     *
     */
    @Test
    public void solution() {
        System.out.println(-1 >> 1);
        System.out.println(-1 >>> 1);

        char c = (char) 0xffff;
        c++;
        System.out.printf("%s\n", c);
        System.out.printf("%d\n", (int)c);

    }
    /**
     * 음수를 2진수로 표현하는 방법
     * 양수 십진수를 2의 보수를 취하면 된다
     * 양수 십진수 -> 1의 보수 취하고 -> +1 하는게 2의 보수 구하는 방법
     * 0x80 = - 128
     * 128은 2진수로 1000,0000이다
     * 1의 보수 취하면 0111,1111이다
     * +1 하면 1000,0000이다
     * 때문에 -128은 1000,0000이다
     *
     * >> 와 >>>의 차이
     * https://stackoverflow.com/questions/19058859/what-does-mean-in-java
     *
     * Random class 를 보면 next 함수가 다음과 같이 구해진다
     *  var4 = var2 * 25214903917L + 11L & 281474976710655L;
     * 여기서 11L & 281474976710655L 란
     * 11 % 2^48 이란 뜻이다
     * https://stackoverflow.com/questions/3072665/bitwise-and-in-place-of-modulus-operator
     *
     * 10진수에서 N mod 10^k 라고 할때 뒤에 k 자릿수만큼만 숫자를 가져오면 되듯이
     * 2진수에서도 N mod 2^k 라고 할때 뒤에 k 자릿수들만 가져오면 된다 (이 연산이 AND 2^k-1 연산이다)
     */
}
