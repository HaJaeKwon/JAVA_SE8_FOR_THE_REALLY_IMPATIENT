package Chapter09;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question03 {

    /**
     * 다중 catch 절에서 잡은 예외를 다시 던질 때 이를 감싸고 있는 메서드의
     * throws 선언부에 해당 예외의 타입을 어떻게 선언하는가?
     * 예를 들어, 다음 코드를 기준으로 설명하라
     */
    @Test
    public void solution() {
        try {
            process(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(int a) throws FileNotFoundException, UnknownHostException {
        try {
            if (a == 1) {
                throw new FileNotFoundException();
            } else {
                throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }
}
