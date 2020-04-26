package Chapter09;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;

public class Question07 {

    /**
     * 웹 페이지의 내용을 읽어서 파일에 저장하는 프로그램을 작성하라
     * 이때 URL.openStream과 Files.copy를 사용한다.
     */
    @Test
    public void solution() {
        try {
            URL url = new URL("https://www.naver.com");
            Files.copy(url.openStream(), Paths.get("src", "test", "java", "Chapter09", "out07.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
