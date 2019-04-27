package Chapter09;

import org.junit.Test;

import java.util.Objects;

public class Question09 {

    /**
     * 다음 클래스의 equals와 hashCode 메서드를 구현하라
     * public class LabeledPoint {
     *     private String label;
     *     private int x;
     *     private int y;
     * }
     */
    @Test
    public void solution() {

    }

    public class LabeledPoint {
        private String label;
        private int x;
        private int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LabeledPoint that = (LabeledPoint) o;
            return x == that.x &&
                    y == that.y &&
                    Objects.equals(label, that.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, x, y);
        }
    }

    /**
     * 사실 인텔리제이에서 제공해주는 equals, hashCode 구현 기능을 사용했다
     * 책에서 소개해준대로 Objects method 를 사용하였다
     */
}
