package Chapter05;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Question05 {

    /**
     * 지금까지 며칠을 살아왔는지 출력하시오
     */
    @Test
    public void solution() {
        LocalDate birthDay = LocalDate.of(1991, 4, 15);
        LocalDate today = LocalDate.now();

        System.out.println(birthDay.until(today, ChronoUnit.DAYS));
    }
    /**
     * 현재 날짜와의 차이를 unitl을 이용해서 구하면 Period 객체를 얻을 수 있다. ChronoUnit을 추가로 주면 출력 형식을 정할 수 있다
     */

}
