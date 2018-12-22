package Chapter02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by jaekwonha on 2018. 12. 22..
 */
public class Question09 {

    /**
     * Stream<ArrayList<T>>에 있는 모든 요소들을 ArrayList<T> 하나로 결합하라.
     * reduce의 세 가지 형태를 이용해 이 작업을 수행하는 방법을 보여라
     */
    @Test
    public void solution() {
        ArrayList<String> arr1 = new ArrayList<>();
        arr1.addAll(Arrays.asList("jaekwon", "teahyung"));
        ArrayList<String> arr2 = new ArrayList<>();
        arr2.addAll(Arrays.asList("cow", "cat", "dog"));

        Stream<ArrayList<String>> listStream;

        System.out.println(arr1);
        System.out.println(arr2);

        listStream = Stream.of(arr1, arr2);
        Optional<ArrayList<String>> result1 = listStream.reduce((x, y) -> { x.addAll(y); return x; });
        System.out.println("reduce function type 1");
        result1.ifPresent(list -> list.forEach(System.out::println));

        System.out.println(arr1);
        System.out.println(arr2);

        listStream = Stream.of(arr1, arr2);
        ArrayList<String> result2 = listStream.reduce(new ArrayList<>(), (x, y) -> { x.addAll(y); return x; });
        System.out.println("reduce function type 2");
        result2.forEach(System.out::println);

        System.out.println(arr1);
        System.out.println(arr2);

        listStream = Stream.of(arr1, arr2);
        ArrayList<String> result3 = listStream.reduce(new ArrayList<>(), (x, y) -> { x.addAll(y); return x; }, (x,y) -> { x.addAll(y); return x;});
        System.out.println("reduce function type 3");
        result3.forEach(System.out::println);

        System.out.println(arr1);
        System.out.println(arr2);
    }

    /**
     * type 1 : stream의 첫번째 요소부터 넘겨받은 누적함수를 수행
     * type 2 : 항등값 c 가 존재할때 c 부터 누적함수를 수행
     * type 3 : 계산을 병렬화하기 위해 누적합계를 만들어내는 함수와 누적합계들을 결합하는 함수를 수행
     *
     * 여기선 addAll 함수를 사용하였기 때문에 type 1 의 경우 원본에 addAll 이 되어버리는 문제가 있다
     */
}
