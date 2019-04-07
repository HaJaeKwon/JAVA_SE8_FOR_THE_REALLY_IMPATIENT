package Chapter05;

import org.junit.Test;

import java.time.LocalDate;

public class Question02 {


    /**
     * LocalDate.of(2000,2,29)에 1년을 더하면 어떻게 되는가?
     * 한편 4년을 더할 때는 어떠한가?
     * 마지막으로 1년을 4번 더하면 어떻게 되는가?
     */
    @Test
    public void solution() {
        LocalDate date;
        date = LocalDate.of(2000,2,29).plusYears(1L);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());


        date = LocalDate.of(2000,2,29).plusYears(4L);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());

        date = LocalDate.of(2000,2,29).plusYears(1L).plusYears(1L).plusYears(1L).plusYears(1L);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
    }

    /**
     * 2004년은 윤년에 해당한다
     * 따라서 한번에 4년을 더하면 2004/2/29이 나올 수 있다
     * 하지만 1년씩 더하면 2001/2/28이 되기 때문에 그 이후 1년씩 더하는 것은 늘 2/28이 된다
     */
}
