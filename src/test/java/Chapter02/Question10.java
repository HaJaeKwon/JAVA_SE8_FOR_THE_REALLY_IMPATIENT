package Chapter02;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 22..
 */
public class Question10 {

    /**
     * Stream<Double>의 평균을 계산하는 데 사용할 수 있는 reduce 호출을 작성하라
     * 단순히 합계를 계산해 count()로 나눌 수 없는 이유는 무엇인가
     */
    @Test
    public void solution() {

        double average = DoubleStream.of(1.2, 2.2, 3.2).average().getAsDouble();
        System.out.println(average);

        double average2 = Stream.of(1.2, 2.2, 3.2).reduce((x, y) -> x + y).orElse(0.0) / 3;
        System.out.println(average2);

        double[] arr = {1.2, 2.2, 3.2};
        double average3 = Arrays.stream(arr).average().orElse(0.0);
        System.out.println(average3);

    }
    /**
     * reduce 를 호출하는 type1 방식은 optional 을 리턴한다
     * 따라서 단순히 합계를 count 로 나누는 방식은 divide zero 상황이 생길 수 있다
     *
     * 참고로 getAsDouble 도 DoubleStream 이 비어있으면 에러난다
     * java9 에는 ifPresentOrElse() 함수가 추가된다고 한다
     *
     * 3번 풀이가 가장 깔끔한 것 같다
     */

}
