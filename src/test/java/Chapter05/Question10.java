package Chapter05;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Question10 {

    /**
     * 로스엔젤레스에서 프랑크푸르트로 가는 항공기가 지역 시간으로 오후 3:05에 출발하고
     * 10시간 50분이 걸린다. 항공기는 언제 도착하겠는가?
     */
    @Test
    public void solution() {
        ZonedDateTime startTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(15, 05), ZoneId.of("America/Los_Angeles"));
        ZonedDateTime arriveTime = getArriveTime(startTime, LocalTime.of(10, 50), ZoneId.of("Europe/Berlin"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
        System.out.println(formatter.format(arriveTime));
    }

    public ZonedDateTime getArriveTime(ZonedDateTime startTime, LocalTime flightTime, ZoneId arriveZoneId) {
        ZonedDateTime arriveTime = startTime.plusSeconds(flightTime.toSecondOfDay()).withZoneSameInstant(arriveZoneId);
        return arriveTime;
    }

    /**
     * withZoneSameInstant를 사용하여야 zoneId에 맞게 시간이 변경된다
     */

}
