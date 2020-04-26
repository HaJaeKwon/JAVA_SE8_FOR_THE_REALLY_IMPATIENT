package Chapter09;

import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question02 {

    /**
     * in.close() 또는 out.close() 에서 예외를 던진 경우 해당 예외를 원래 예외에 대한 생략 예외로 추가하는 방법으로
     * 앞의 연습문제를 개선하라
     */
    @Test
    public void solution() {
        Scanner in = null;
        PrintWriter out = null;
        Throwable primaryException = null;
        try {
            in = new Scanner(Paths.get("./src/test/java/Chapter02/alice.txt"));
            out = new PrintWriter("./src/test/java/Chapter09/out.txt");

            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }

        } catch (Exception e) {
            primaryException = e;
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (Exception e) {
                if (primaryException != null) {
                    primaryException.addSuppressed(e);
                }
            }
        }
    }
    /**
     * 개발자의 관심은 주 예외이기에 주 예외 객체를 미리 선언해두고
     * 이후에 주 예외가 있을때는 close 에서 발생한 예외를 서브 예외로 처리하는 방법
     */
}
