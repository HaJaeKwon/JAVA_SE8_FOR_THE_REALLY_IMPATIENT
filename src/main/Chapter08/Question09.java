package Chapter08;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterators;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question09 {

    /**
     * Scanner를 단어, 행, 정수 또는 double 값들의 스트림으로 변환하는 메서드를 작성하라
     * 힌트 : BufferedReader.lines의 소스 코드를 살펴본다
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stream<Integer> integerStream = getIntegerStream(sc);
        integerStream.forEach(System.out::println);
        System.out.printf("end");

        Stream<Double> doubleStream = getDoubleStream(sc);
        doubleStream.forEach(System.out::println);

    }

    public static Stream<Integer> getIntegerStream(Scanner sc) {
        Iterator var1 = new Iterator<Integer>() {
            Integer next = null;
            boolean endFlag = false;

            @Override
            public boolean hasNext() {
                System.out.println("hasNext");
                if (endFlag) {
                    return false;
                }
                if (this.next != null) {
                    System.out.println(next);
                    return true;
                } else {
                    // 공백, ""을 넣어도 빠져나가지않는다...
                    if (!sc.hasNextLine()) {
                        return false;
                    } else {
                        String str = sc.next();
                        System.out.println(str);
                        this.next = Integer.parseInt(str);
                        return this.next != null;
                    }
                }
            }

            @Override
            public Integer next() {
                System.out.println("next");
                if (this.next == null && !this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Integer var1 = this.next;
                    this.next = null;
                    if (var1 <= 0) endFlag = true;
                    return var1;
                }
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(var1, 272), false);
    }

    public static Stream<Double> getDoubleStream(Scanner sc) {
        Iterator var1 = new Iterator<Double>() {
            Double next = null;

            @Override
            public boolean hasNext() {
                if (this.next != null) {
                    return true;
                } else {
                    this.next = sc.nextDouble();
                    return this.next != null;
                }
            }

            @Override
            public Double next() {
                if (this.next == null && !this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Double var1 = this.next;
                    this.next = null;
                    return var1;
                }
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(var1, 272), false);
    }
    /**
     * BufferedReader lines method를 참고하여 Scanner 객체를 받는 메소드들로 만들었다.
     * Spliterators는 병렬처리를 위한 iterator라고 한다
     *
     * Scanner 를 나가는 방법이 있으면 좀 더 깔끔하게 해결했을거 같은데 hasNextLine을 해도 빠져나가지지 않는다
     */

}
