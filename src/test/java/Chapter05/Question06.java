package Chapter05;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Question06 {

    /**
     * 20세기의 모든 13일의 금요일을 출력하라
     */
    @Test
    public void solution() {
        LocalDate day13 = LocalDate.of(1901, 1, 13);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (day13.getYear() / 100 + 1 == 20) {
            if (day13.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(formatter.format(day13));
            }
            day13 = day13.plusMonths(1L);
        }
    }

    /**
     * century를 구할때는 year/100 + 1 로 구할 수 있다
     * 출력시에는 DateTimeFormatter를 사용하자
     * plus 함수 호출하고 리턴받은걸 다시 변수에 넣어줘야 반영 된다
     */
}
