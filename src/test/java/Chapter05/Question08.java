package Chapter05;

import com.sun.javafx.binding.StringFormatter;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Question08 {

    /**
     * 현재 시간 인스턴트에 대해 지원되는 모든 시간대에서 오늘 날짜의 오프셋을 구하라
     * 이때 ZoneId.getAvailableIds를 스트림으로 전환하고, 스트림 연산을 이용해 작업한다ㅓ
     */

    @Test
    public void solution() {
        ZoneId.getAvailableZoneIds().stream().forEach(id -> {
            System.out.println(String.format("%s %s", id, ZonedDateTime.now(ZoneId.of(id)).getOffset()));
        });
    }
}
