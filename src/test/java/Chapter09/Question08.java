package Chapter09;

import org.junit.Test;

import java.awt.*;

public class Question08 {

    /**
     * 239페이지에 있는 "숫자 탕비 비교하기" 절에 나온 Point 클래스의 compareTo 메서드를 Integer.compareTo를 사용하지 않고 구현하라
     */
    @Test
    public void solution() {

    }

    public int compareTo(Point a, Point b) {
        int diff =  a.x == b.x ? 0 : a.x < b.x ? -1 : 1;
        if (diff != 0) return diff;
        return a.y == b.y ? 0 : a.y < b.y ? -1 : 1;
    }

    /**
     *  compare 함수 자체가 값 차이를 리턴해주는게 아니라 뒤에게 크면 -1, 앞에가 크면 1, 같으면 0이니에
     *  overflow 발생 위험이 없는 비교문으로 구현하였다
     */
}
