package Chapter08;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question16 {

    /**
     * 네임드 캡쳐링 그룹을 포함하는 정규 표현식을 사용해 도시, 주, 우편번호를 담고 있는 행을 파싱하라.
     * 이때 5자리와 9자리 숫자 우편번호 모두 허용한다.
     */
    @Test
    public void solution() {
        String[] lines = {
                "03139",
                "26412"
        };

        String reg = "(?<si>[0-9]{2})(?<gu>[0-9]{1})(?<street>[0-9]{2})";
        Pattern pattern = Pattern.compile(reg);

        for(String str : lines) {
            Matcher m = pattern.matcher(str);
            if(m.matches()) {
                String si = m.group("si");
                String gu = m.group("gu");
                String street = m.group("street");

                System.out.printf("%s / %s / %s\n", si, gu, street);
            }
        }
    }

    /**
     * 미국은 zip(5자리), zip-4(9자리)로 분류하여 쓰지만 한국은 우편번호가 5자리다
     */
}
