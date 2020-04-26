package Chapter05;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Question11 {

    /**
     * 돌아오는 항공기가 프랑크푸르트에서 14:05에 출발해서 16:40에 로스엔젤레스에 도착한다.
     * 비행하는데 얼마나 걸렸는가?
     */
    @Test
    public void solution() {
        ZonedDateTime startTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(14, 5), ZoneId.of("Europe/Berlin"));
        ZonedDateTime arriveTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 40), ZoneId.of("America/Los_Angeles"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(formatter.format(getFlightTime(startTime, arriveTime)));
    }

    public LocalTime getFlightTime(ZonedDateTime startTime, ZonedDateTime arriveTime) {
        int flightTime = (int) startTime.until(arriveTime, ChronoUnit.MINUTES);
        return LocalTime.of(flightTime / 60, flightTime % 60);
    }
    /**
     * 시간 간격을 구할때는 until 을 사용한다
     */
}
