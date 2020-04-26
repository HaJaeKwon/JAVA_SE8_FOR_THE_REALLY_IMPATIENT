package Chapter01;

import org.junit.Test;

import java.util.concurrent.Callable;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question06 {

    /**
     * Runnable 에서 검사 예외를 다뤄야 하는 점이 싫지 않은가?
     * 모든 검사 예외를 잡아내서 비검사 예외로 바꾸는 uncheck 메서드를 작성하라
     * 예를 들면, 다음과 같이 사용할 것이다
     * <p>
     * new Thread(uncheck(
     * () -> { System.out.println("Zzz"; Thread.sleep(1000); })).start();
     * // 여길 보자. catch (InterruptedException) 부분이 없다!
     * 힌트: run 메서드에서 모든 예외를 던질 수 있는 RunnableEx라는 인터페이스를 정의한다
     * 다음으로 public static Runnable uncheck(RunnableEx runner)를 구현한다
     * uncheck 함수 안에서 람다 표현식을 사용한다
     * <p>
     * RunnableEx 대신 단순히 Callable<Void>를 사용할 수 없는 이유는 무엇인가?
     */
    @Test
    public void solution() {
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();
//        new Thread(new Callable<Void>() {
//            @Override
//            public void call() throws Exception {
//            System.out.println("Zzz");
//            Thread.sleep(1000);
//            }
//        });
    }

    public static Runnable uncheck(RunnableEx runner) {
        return () -> {
            try {
                runner.run();
            } catch (Exception ignored) {
            }
        };
    }

    @FunctionalInterface
    interface RunnableEx {
        void run() throws Exception;
    }
    /**
     * 처음에는 RunnableEx 에 정의할 추상 함수에 try catch 안에서 Runnable 의 run 을 실행시켜야 겠다고 생각했다
     * 하지만 힌트에서는 '예외를 던질 수 있는'이지 '예외를 잡을 수 있는'은 아니다
     * 추상 함수는 로직을 담을 수 없기 때문에 try catch 는 runner 의 함수를 실행시킬 uncheck 에서 해주는게 옳다
     *
     * FunctionalInterface 라는 것과 인터페이스의 함수에서도 throws Exception 이 가능하다는 것을 알았다
     *
     * Callable<Void> 를 쓸 수 없는 이유는 잘 모르겠다
     * 일단 써보면 안된다.....
     */

}
