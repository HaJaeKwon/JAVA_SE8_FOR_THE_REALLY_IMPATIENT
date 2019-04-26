package Chapter09;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question01 {

    /**
     * 227페이지에 있는 "try-with-resources 문" 절의 Scanner와 PrintWriter를 생성하는 코드 조각을
     * try-with-resources 문을 사용하지 않고 구현하라.
     * 두 객체가 제대로 생성된 경우 반드시 닫아야 한다.
     * 다음 상황을 고려해야 한다.
     *
     * Scanner 생성자는 예외를 던진다
     * PrintWriter 생성자는 예외를 던자
     * hasNext, next 또는 println은 예외를 던진다
     * in.close(). out.close() 는 예외를 던진다
     */
    @Test
    public void solution() {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(Paths.get("./src/test/java/Chapter02/alice.txt"));
            out = new PrintWriter("./src/test/java/Chapter09/out.txt");

            while(in.hasNext()) {
                out.println(in.next().toLowerCase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
