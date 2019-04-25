package Chapter08;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Stream;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question11 {

    /**
     * 패스워드로 보호된 웹 페이지의 내용을 가져오는 프로그램을 작성하라
     * 먼저 URLConnection connection = url.openConnection();을 호출한다
     * 문자열 username:password를 만들어 base64로 인코딩한다.
     * 다음으로, connection.setRequestProperty("Authorization", "Basic " + encoded string), connection.connect(), connection.getInputStream() 순으로 호출한다
     */
    @Test
    public void solution() {

        String path = "";
        String user = "";
        String pass = "";

        try {
            URL url = new URL(path);
            URLConnection con = url.openConnection();

            Base64.Encoder encoder = Base64.getEncoder();
            String original = user + ":" + pass;
            String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
            con.setRequestProperty("Authorization", "Basic " + encoded);
            con.connect();

            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                Stream<String> lines = reader.lines();
                lines.forEach(s -> content.append(s));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(content.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 페이지 auth는 apache .htaccess 파일로 관리를 하는 것 같다.
     * base64 encoding을 하면 웹페이지 크롤링하는데 유용할듯!
     */
}
