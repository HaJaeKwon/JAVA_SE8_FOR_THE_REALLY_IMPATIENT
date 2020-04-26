package Chapter03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Question19 {

    /**
     * Stream<T> 메서드인 <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BunaryOperator<U> combiner)를 살펴보자
     * BiFunction의 첫번째 타입 인자에서 U를 ? super U로 선언해야 하는가?
     * 해야 하는 이유 또는 하면 안되는 이유는 무엇인가?
     */
    @Test
    public void solution() {
    }

    public static ArrayList<String> reduce(BiFunction<? super ArrayList<String>, ? super ArrayList<String>, ArrayList<String>> accumulator) {
        ArrayList<String> arr1 = new ArrayList<>();
        arr1.addAll(Arrays.asList("jaekwon", "teahyung"));
        ArrayList<String> arr2 = new ArrayList<>();
        arr2.addAll(Arrays.asList("cow", "cat", "dog"));

        return accumulator.apply(arr1, arr2);
    }

    /**
     * BiFuntion<? super U, ? super T, U> 이 옳다
     * Bifunction 의 첫번째, 두번째는 파라미터로 쓰인다. 이거만 봐도 super 가 맞고
     * 첫번째 파라미터는 결과를 누적시킬 변수이기 때문에 write 되어야 한다
     */

}
