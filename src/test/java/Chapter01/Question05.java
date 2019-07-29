package Chapter01;

import org.junit.Test;

/**
 * Created by jaekwonha on 2018. 11. 18..
 */
public class Question05 {

    static final int MAX_LOOP = 100000;

    /**
     * 다수의 ActionListener, Runnable 등을 포함하는 프로젝트 중 하나에서 파일을 불러와서 이러한 인터페이스를 람다 표현식으로 교체하라
     * 이 교체 작업으로 몇 행을 줄였는가?
     * 코드가 더 읽기 쉬워졌는가?
     * 메서드 레퍼런스를 사용할 수 있었는가?
     */
    @Test
    public void solution() throws InterruptedException {
        Runnable newRunnable = () -> job("NewRunnable");

        Thread thread1 = new Thread(new OldRunnable());
        Thread thread2 = new Thread(newRunnable);
        Thread thread3 = new Thread(() -> Question05.job("NewRunnable method expression"));
        Thread thread4 = new Thread(Question05::job);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

    class OldRunnable implements Runnable {

        @Override
        public void run() {
            job("OldRunnable");
        }
    }

    static public void job(String jobName) {
        int result = 0;
        for(int i=0; i<MAX_LOOP; i++) {
            result++;
        }
        System.out.println(String.format("%s result : %d", jobName, result));
    }

    static public void job() {

    }

    /**
     * 메소드 레퍼런스를 사용한 부분은 실제로는 람다 표현식을 사용하여 문제를 해결한 것이다
     * 람다식을 메소드 래퍼런스로 바꾸는 방법에는 3가지가 있다고 한다
     * http://asuraiv.blogspot.com/2017/02/java8.html
     * 1
     * (args) -> ClassName.staticmethod(args)
     * ClassName::staticMethod
     * 2
     * (arg0, rest) -> arg0.instanceMethod(rest)
     * ClassName::instanceMethod
     * 3
     * (args) -> expr.instanceMethod(args)
     * expr::instanceMethod
     *
     * job(jobName)을 호출하려면 어떤식으로 바꿀 수 있는지 아직은 모르겠다
     * -> run method는 args가 없기에 job(String jobName)을 메소드 레퍼런스 방식으로 사용할 수 없었던 것이다
     *
     * 워낙 작은 코드라 교체작업으로 행이 많이 줄지는 않았다
     * 여러 곳에서 Runnable 구현체를 사용한다고 하면 람다 표현식을 쓰는게 더 안좋다는 생각도 든다
     */
}
