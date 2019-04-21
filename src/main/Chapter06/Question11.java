package Chapter06;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Question11 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
            if ("secret".equals(String.valueOf(p.getPassword()))) {
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
}