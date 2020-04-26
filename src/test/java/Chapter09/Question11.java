package Chapter09;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Question11 {

    /**
     * 사용자 홈 디렉토리의 모든 서브디렉터리에 들어 있는 모든 파일에서 신용 카드 번호를 찾기 위해 grep -r을
     * 호출하는 프로그램을 ProcessBuilder 클래스를 사용해 작성하라
     */
    @Test
    public void solution() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ls", "-al");
        builder.inheritIO();
        builder.start().waitFor(1, TimeUnit.SECONDS);

        builder = new ProcessBuilder("grep", "-o", "*main*");
        File input = Paths.get("src", "test", "java", "Chapter09", "Question11.java").toFile();
        builder.redirectInput(input);
        builder.redirectOutput(Paths.get("src", "test", "java", "Chapter09", "out11.txt").toFile());
        builder.start().waitFor(1, TimeUnit.SECONDS);

    }

    /**
     *  window 에서는 ls, grep 실행파일이 없어서 실행안됨
     */
}
