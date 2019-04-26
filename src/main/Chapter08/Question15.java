package Chapter08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question15 {

    /**
     * Files.lines와 Pattern.asPredicate를 사용해 정규 표현식과 일치하는 내용을 담고 있는
     * 모든 행을 출력하여 grep 유틸리티처럼 동작하는 프로그램을 작성하라
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }
        String reg = args[0];
        String path = args[1];

        Pattern pattern = Pattern.compile(reg);
        try {
            Files.lines(Paths.get(path, "")).filter(pattern.asPredicate()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println("usage : java Question15 regexp path");
    }

    /**
     * 정규표현식 쓰는 법이 grep 과 Pattern class 가 좀 다른 것 같아서 (ex. *main*)
     * 조금 다르긴 하지만 java 로도 script 처럼 사용해봤다는게 중요할 것 같다
     */

}
