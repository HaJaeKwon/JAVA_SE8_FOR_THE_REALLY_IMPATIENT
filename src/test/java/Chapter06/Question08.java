package Chapter06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by hajaekwon on 2019-04-19.
 */
public class Question08 {

    /**
     * 자신의 컴퓨터에서 Arrays.parallelSort 가 Arrays.sort 보다 빠르게 동작하려면 배열이 얼마나 커야 하는가?
     */
    @Test
    public void solution() {
        long start, end;

        int min, max, step;
        min = 1000;
        max = 1000000;
        step = 1000;

        Random random = new Random();
        int[] arrays = new int[max];
        for (int i = 0; i < max; i++) {
            arrays[i] = random.nextInt();
        }

        for (int i = min; i < max; i += step) {
            long parallelSortTime, sortTime;
            start = System.currentTimeMillis();
            Arrays.parallelSort(Arrays.copyOf(arrays, i));
            end = System.currentTimeMillis();
            parallelSortTime = end - start;

            start = System.currentTimeMillis();
            Arrays.sort(Arrays.copyOf(arrays, i));
            end = System.currentTimeMillis();
            sortTime = end - start;

            if (sortTime > parallelSortTime) {
                System.out.println(String.format("index %d, parallel_sort : %d, sort : %d PARALLEL SORT", i, parallelSortTime, sortTime));
            } else {
                System.out.println(String.format("index %d, parallel_sort : %d, sort : %d SORT", i, parallelSortTime, sortTime));
            }
        }
    }

    /**
     * 대략 30000개 정도부터는 parallel sort 가 유리
     */
}
