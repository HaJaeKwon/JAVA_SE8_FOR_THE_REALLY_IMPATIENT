package Chapter08;

import org.junit.Test;

import java.util.*;
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
    @Test
    public void solution() {
        Scanner sc = new Scanner(System.in);
        Stream<Integer> integerStream = getIntegerStream(sc);

        integerStream.forEach(System.out::println);
    }

    public Stream<Integer> getIntegerStream(Scanner sc) {
        Iterator var1 = new Iterator<Integer>() {
            Integer next = null;
            @Override
            public boolean hasNext() {
                if(this.next != null) {
                    return true;
                } else {
                    this.next = sc.nextInt();
                    return this.next != null;
                }
            }

            @Override
            public Integer next() {
                if(this.next == null && !this.hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Integer var1 = this.next;
                    this.next = null;
                    return var1;
                }
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(var1 ,272), false);
    }

}
