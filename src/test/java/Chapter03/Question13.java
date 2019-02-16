package Chapter03;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;

public class Question13 {

    /**
     * 블러나 가장자리 검출 같은 컨벌루션 필터는 주변 픽셀들로부터 픽셀을 계산한다
     * 이미지를 흐리게 하려면 각 색상 값을 해당 픽셀 주변 8개 픽셀의 평균값으로 교체한다
     * 가장 자리 검출인 경우 각 색상 값 c를 4c - n - e - w - s로 교체한다
     * 이때 다른 색상 값을 각각 북, 서, 동, 남 의 픽셀이다
     * 이러한 연산은 이전 단계의 이미지가 계산되어야 하기 때문에
     * 82페이지에 있는 "3.6 지연" 절 에서 설명한 접근법으로는 지연 처리 방식을 구현할 수 없다는 점을 유념하기 바란다
     * 이와 같은 연산을 다룰 수 있도록 이미지 처리를 개선하라
     * 이들 연산자 중 하나가 평가되면 이전 단계의 계산을 강제한다
     */
    @Test
    public void solution() {
    }

}
