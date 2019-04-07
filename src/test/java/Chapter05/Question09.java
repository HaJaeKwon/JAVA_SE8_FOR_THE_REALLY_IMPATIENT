package Chapter05;

import org.junit.Test;

import java.time.ZoneId;

public class Question09 {

    /**
     * 다시 스트림 연산을 이용해 오프셋이 정각이 아닌 모든 시간대를 찾아라
     */
    @Test
    public void solution() {
        ZoneId.getAvailableZoneIds().stream().filter(zone -> zone.compareTo("Z") != 0).forEach(System.out::println);
    }
}
