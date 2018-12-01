package Chapter01;

import org.junit.Test;

/**
 * Created by jaekwonha on 2018. 11. 24..
 */
public class Question07 {

    /**
     * Runnable 인스턴스 두 개를 파라미터로 받고, 첫 번째 인스턴스를 실행한 후 두 번째를 실행하는
     * Runnable 을 리턴하는 andThen 이라는 정적 메서드를 작성하라.
     * main 메서드에서 andThen 호출에 람다 표현식 두 개를 전달하고,
     * 결과로 받은 인스턴스를 실행하라
     */
    @Test
    public void solution() {
        new Thread(andThen(
                () -> {
                    System.out.println("r1");
                },
                () -> {
                    System.out.println("r2");
                }
        )).start();
    }

    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> {
            r1.run();
            r2.run();
        };
    }
}
