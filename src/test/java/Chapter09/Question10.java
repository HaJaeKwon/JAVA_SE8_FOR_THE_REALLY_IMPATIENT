package Chapter09;

import org.junit.Test;

import java.util.Objects;

public class Question10 {

    /**
     * 연습문제 9번에 나온 LabeledPoint 클래스의 compareTo 메서드를 구현하라
     */
    @Test
    public void solution() {
        LabeledPoint a = new LabeledPoint();
        a.label = "label1";
        a.x = 1;
        a.y = 2;
        LabeledPoint b = new LabeledPoint();
        b.label = "label2";
        b.x = 2;
        b.y = 2;

        System.out.println(a.compareTo(b));
    }

    public class LabeledPoint {
        private String label;
        private int x;
        private int y;

        public int compareTo(LabeledPoint other) {
            if (!Objects.equals(label, other.label)) {
                return label.compareTo(other.label);
            }
            if (x != other.x) {
                return x < other.x ? -1 : x == other.x ? 0 : 1;
            }
            return y < other.y ? -1 : y == other.y ? 0 : 1;
        }
    }

}
