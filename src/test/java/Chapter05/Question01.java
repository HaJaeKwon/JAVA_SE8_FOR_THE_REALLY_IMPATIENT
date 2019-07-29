package Chapter05;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

public class Question01 {

    /**
     * plusDays를 사용하지 않고 프로그래머의 날을 계산하라
     */
    @Test
    public void solution() {
        LocalDate programmersDay = LocalDate.of(2019, 1, 1).plus(Period.ofDays(255));
        System.out.println(programmersDay.getYear());
        System.out.println(programmersDay.getMonth());
        System.out.println(programmersDay.getDayOfMonth());
    }
    /**
     * plus(Period.ofDays(255)) 를 사용했다
     */
}
