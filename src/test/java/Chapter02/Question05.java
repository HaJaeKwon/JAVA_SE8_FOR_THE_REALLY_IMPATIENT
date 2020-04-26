package Chapter02;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 9..
 */
public class Question05 {

    /**
     * Stream.iterate를 사용해 난수의 무한 스트림을 만들어라
     * 이때 Math.random을 사용하지 말고 선형 적합 발생기를 직접 구현해서 사용한다
     * 이와 같은 발생기에서는 X0 = seed로 시작해서 a,c,m 값에 대해 Xn+1 = (aXn + c)%m 을 생성한다
     * 파라미터로 a,c,m,seed를 받고 Stream<Long>을 리턴하는 메서드를 구현해야 한다
     * a = 25214903917, c = 11, m = 2^48 값으로 난수의 무한 스트림을 만들어본다
     */
    @Test
    public void solution() {

        Stream<Long> infiniteStream = Stream.iterate(0L, n -> LinearCongruentialGenerator.generate(n, 25214903917L, 11L, (long) Math.pow(2, 48)));
        infiniteStream.forEach(System.out::println);

    }
    /**
     * 인자가 없는 무한 스트림 생성은 generate
     * 인자가 있는 무한 스트림 생성은 iterate
     * 다만 LongStream으로 받을 수는 없다 Stream.iterate는 반환값이 Stream<T>이다
     */
}

class LinearCongruentialGenerator {
    public static long generate(long x, long a, long c, long m) {
        return (a * x + c) % m;
    }
}
