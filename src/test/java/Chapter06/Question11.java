package Chapter06;

import org.junit.Test;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Question11 {

    /**
     * until 함수에서 수락하는 값을 생산할 때까지 비동기로 액션(action)을 반복하는 다음 메서드를 작성하라
     * public static <T> CompletableFuture<T> repeat(
     *  Supplier<T> action, Predicate<T> until)
     * 이때 until 함수도 비동기로 실행해야 한다.
     *
     * 콘솔에서 java.net.PasswordAuthentication을 읽는 함수와 1초 동안 잠든 후 패스워드가
     * "secret"인지 검사하는 방법으로 유효성 검사를 시뮬레이션하는 함수로 테스트한다.
     *
     * 힌트 : 재귀호출을 사용한다
     */
    @Test
    public void solution() throws InterruptedException, ExecutionException {

        CompletableFuture<PasswordAuthentication> future = repeat(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("user name :");
            String userName = scanner.nextLine();
            System.out.print("password :");
            String password = scanner.nextLine();
            return new PasswordAuthentication(userName, password.toCharArray());
        }, p -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("FALSE");
                return false;
            }
            if (p.getPassword().equals("secret".toCharArray())) {
                System.out.println("TRUE");
                return true;
            }
            System.out.println("FALSE");
            return false;
        });
        System.out.println("password OK : " + String.valueOf(future.get().getPassword()));
    }

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        CompletableFuture<T> future = CompletableFuture
                .supplyAsync(action)
                .thenComposeAsync(t -> {
                    if (until.test(t)) {
                        return CompletableFuture.supplyAsync(() -> t);
                    }
                    return repeat(action, until);
                });
        return future;
    }

    /**
     * thenApply와 thenCompose의 차이는 T를 리턴하느냐, CompletableFuture<T>를 리턴하느냐의 차이가 있다
     * Test module에서는 표준입력이 제대로 작동하지 않아서 main에서 작동시켰다
     */
}
