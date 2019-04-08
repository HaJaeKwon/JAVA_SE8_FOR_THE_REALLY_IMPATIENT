package Chapter05;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hajaekwon on 2019-04-08.
 */
public class Question12 {

    /**
     * 144페이지에 있는 "5.5 구역 시간" 절의 시작 부분에서 설명한 문제를 해결하는 프로그램을 작성하라.
     * 서로 다른 시간대에서 잡힌 일련의 약속을 읽고, 지역 시간으로 한 시간 안에 예정된 약속들을 사용자에게 알린다.
     */

    class Event {
        ZonedDateTime time;
        String message;

        public Event(ZonedDateTime time, String message) {
            this.time = time;
            this.message = message;
        }
    }

    List<Event> events;

    @Before
    public void makeEvent() {
        Event event1 = new Event(ZonedDateTime.of(LocalDate.now(), LocalTime.of(15, 30), ZoneId.of("Europe/London")), "lunch");
        Event event2 = new Event(ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 00), ZoneId.of("Canada/Atlantic")), "study");
        Event event3 = new Event(ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 00), ZoneId.of("Asia/Vladivostok")), "dinner");
        Event event4 = new Event(ZonedDateTime.of(LocalDate.now(), LocalTime.of(17, 00), ZoneId.of("Asia/Ashgabat")), "meeting");
        Event event5 = new Event(ZonedDateTime.of(LocalDate.now(), LocalTime.of(03, 00), ZoneId.of("Brazil/Acre")), "work");
        events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
    }

    @Test
    public void solution() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        ZonedDateTime today = ZonedDateTime.now();
        System.out.println(String.format("%s %s", formatter.format(today), today.getOffset()));

        for (Event event : events) {
            ZonedDateTime time = event.time.withZoneSameInstant(today.getZone());
            System.out.println(String.format("%s %s", formatter.format(time), event.time.getOffset()));
//            long diffHour = today.until(time, ChronoUnit.HOURS); // HOURS로 하면 분차이가 짤림. 정확히 하려면 MIN으로 해야함
            long diffMin = today.until(time, ChronoUnit.MINUTES);
//            System.out.println(diffHour);
//            System.out.println(diffMin);
            if (0 <= diffMin && diffMin <= 60L) {
                System.out.println(event.message);
            }
        }
    }

    /**
     * 날짜, 시간 비교시에는 until 메소드를 사용하면 된다!
     * TemporalField = ChronoField
     * TemporalUnit = ChronoUnit
     */

}
