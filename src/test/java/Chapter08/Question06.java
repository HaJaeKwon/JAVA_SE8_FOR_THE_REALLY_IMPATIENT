package Chapter08;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question06 {

    /**
     * Comparator 클래스의 메서드만 사용해서 Point2D용 전순서 비교자(즉, 동일한 객체들에 대해서만 0을 리턴)를 정의하라
     * 힌트 : 먼저 x좌표를 비교한 다음 y좌표를 비교한다. 또한 Rectangle2D용으로도 정의하라
     */
    @Test
    public void solution() {
        List<Point2D> point2DList = new ArrayList<>();
        point2DList.sort(Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY));

        List<Rectangle2D> rectangle2DList = new ArrayList<>();
        rectangle2DList.sort(Comparator.comparing(Rectangle2D::getMinX).thenComparing(Rectangle2D::getMinY));
    }

}
