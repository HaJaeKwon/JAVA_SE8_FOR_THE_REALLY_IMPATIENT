package Chapter05;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Question04 {

    /**
     * 유닉스 cal 프로그램을 작성하라
     */
    @Test
    public void solution() {
        cal(2013, 3);
    }

    public void cal(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            if (date.getDayOfWeek().getValue() == i) {
                break;
            }
            result.append("   ");
        }
        for (int i = 1; i <= date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {

            if (date.getDayOfMonth() < 10) result.append(" ");
            result.append(date.getDayOfMonth());

            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                result.append("\n");
            } else {
                result.append(" ");
            }

            date = date.plusDays(1L);
        }

        System.out.println(result.toString());
    }
}
