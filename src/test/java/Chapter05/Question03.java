package Chapter05;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class Question03 {

    /**
     * Predicate<LocalDate>를 받고 조정기(adjuster)를 리턴하는 next 메서드를 구현하라
     * 이 조정기는 프레디케이트를 수행하는 다음 번 날짜를 돌려줘야 한다
     * 예를 들어, 다음 호출은 다음번 평일을 계산한다
     * today.with(next(w -> w.getDayOfWeek().getValue() >= 6))
     */

    @Test
    public void solution() {
        LocalDate today = LocalDate.now();
        today = today.with(next(w -> w.getDayOfWeek().getValue() >= 6));

        System.out.println(String.format("%d/%d/%d", today.getYear(), today.getMonthValue(), today.getDayOfMonth()));
    }

    private TemporalAdjuster next(Predicate<LocalDate> pred) {
        return TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (pred.test(result));
            return result;
        });
    }

    /**
     * TemporalAdjuster (날짜 조정기)는 함수형 인터페이스이기에 람다식으로 함수 내용을 주면 된다
     * w는 Temporal객체이기에 LocalDate로 캐스팅이 필요하지만 ofDateAdjuster를 사용하면 캐스팅 하지 않아도 된다.
     */
}
