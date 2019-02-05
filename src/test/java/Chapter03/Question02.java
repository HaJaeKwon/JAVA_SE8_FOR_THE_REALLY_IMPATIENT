package Chapter03;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class Question02 {

    /**
     * ReentrantLock 을 사용할 때는 다음과 같은 형식으로 잠금과 잠금해제를 수행해야 한다
     *
     * myLock.lock();
     * try {
     *     몇몇 액션
     * } finally {
     *     myLock.unlock();
     * }
     *
     * 다음과 같이 호출할 수 있도록 withLock 메서드를 제공하라
     *
     * withLock(myLock, () -> { 몇몇 액션 })
     */
    @Test
    public void solution() {
        ReentrantLock myLock = new ReentrantLock();
        withLock(myLock, () -> {System.out.println("lock and run");});
    }

    public void withLock(ReentrantLock lock, Runnable action) {
        lock.lock();
        try {
            action.run();
        } finally {
            lock.unlock();
        }
    }
}
