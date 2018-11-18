package Chapter01;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question01 {

    /**
     * Arrays.sort 메서드에서 비교자 코드는 sort 호출과 같은 스레드에서 호출되는가, 다른 스레드에서 호출되는가?
     */
    @Test
    public void solution() {
        List<String> list = Arrays.asList("dog", "cat", "cow", "sheep");
        String[] arr = new String[] { "dog", "cat", "cow", "sheep" };

        Comparator<String> comp = (x,y) -> {
            System.out.println(String.format("Thread Id : %d", Thread.currentThread().getId()));
            return x.compareTo(y);
        };

        System.out.println(String.format("Main Thread Id : %d", Thread.currentThread().getId()));

        System.out.println("list.sort");
        list.sort(comp);
        System.out.println("list.stream().sorted");
        list.stream().sorted(comp).collect(Collectors.toList());
        System.out.println("Arrays.sort");
        Arrays.sort(arr, comp);
        System.out.println("Arrays.parallelSort");
        Arrays.parallelSort(arr, comp);

        System.out.println(String.format("Result"));
        Arrays.stream(arr).forEach(System.out::println);

    }
    /**
     * 모두 같은 Thread 에서 실행됨
     */
}
