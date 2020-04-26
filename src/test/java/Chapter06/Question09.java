package Chapter06;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * Created by hajaekwon on 2019-04-19.
 */
public class Question09 {

    /**
     * 피보나치 수열 계산을 병렬화하는 데 parallelPrefix 메서드를 사용할 수 있다.
     * n번째 피보나치 숫자는 F = (1 1 1 0)일 때 F^n의 좌측 상단 계수라는 사실을 이용한다.
     * 먼저 2 * 2 행렬로 채워진 배열을 만든다.
     * 다음으로 곱셈 메서드를 제공하는 Matrix 클래스를 정의한다.
     * parallelSetAll 을 사용해 행렬의 배열을 만들고, parallelPrefix 를 사용해 행렬들을 곱한다.
     */
    @Test
    public void solution() {

        final int length = 20;
        int[][] base = {{1, 1}, {1, 0}};

        int[][][] fibo = new int[length][2][2];
        Arrays.parallelSetAll(fibo, idx -> base);

        BinaryOperator op = (x, y) -> Matrix.multi((int[][]) x, (int[][]) y);

        Arrays.parallelPrefix(fibo, op);

        for (int i = 0; i < length; i++) {
            System.out.println(i + "th fibo number : " + fibo[i][0][0]);
        }
    }

    static class Matrix {
        public static int[][] multi(int[][] a, int[][] b) {

            int aX = a.length;
            int aY = a[0].length;
            int bX = b.length;
            int bY = b[0].length;

            int[][] result = new int[aX][bY];

            for (int i = 0; i < aX; i++) {
                for (int j = 0; j < bY; j++) {
                    result[i][j] = 0;
                }
            }

            for (int i = 0; i < aX; i++) {
                for (int j = 0; j < bY; j++) {
                    for (int k = 0; k < aY; k++) {
                        result[i][j] += a[i][k] * b[k][j];
                    }
                }
            }

            return result;
        }
    }
}
