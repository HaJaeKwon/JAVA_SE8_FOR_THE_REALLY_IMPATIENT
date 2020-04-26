package Chapter03;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Question14 {

    /**
     * 픽셀별로 지연 평가를 다룰 수 있도록, 변환기를 변경해서 이미지에 있는 다른 픽셀들을 읽을 수 있게 해주는 PixelReader 객체를 받을 수 있게 하라
     * 예를 들어 (x, y, reader) -> reader.get(width - x, y) 는 미러링 연산이다
     * 앞의 컨벌루션 필터는 이와 같은 리더의 관점에서 쉽게 구현할 수 있다
     * 직접적인 연산은 단순히 (x, y, reader) -> reader.get(x, y).grayscale() 형태가 될 것이며,
     * UnaryOperator<Color>로 부터 어댑터를 제공할 수 있다.
     * PixelReader는 연산의 파이프라인에서 특정레벨에 위치한다.
     * 파이프라인의 각 레벨에서 최근에 읽은 픽셀들의 캐시를 관리하라.
     * 만일 리더가 픽셀을 요청받으면 먼저 캐시(레벨 0의 원본 이미지)에서 찾아보고, 찾지 못한 경우 이전 변환을 요청하는 리더를 만든다.
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            LatentImage3 latentImage = LatentImage3.from(image)
                    .transform((x, y, pr) -> {
                        int[] DX = {-1, 0, 1};
                        int[] DY = {-1, 0, 1};
                        double sumRed = 0.0;
                        double sumGreen = 0.0;
                        double sumBlue = 0.0;
                        for (int dx : DX) {
                            for (int dy : DY) {
                                int _x = x + dx;
                                int _y = y + dy;
                                try {
                                    sumRed += pr.getColor(_x, _y).getRed();
                                    sumGreen += pr.getColor(_x, _y).getGreen();
                                    sumBlue += pr.getColor(_x, _y).getBlue();
                                } catch (IndexOutOfBoundsException e) {
                                    sumRed += pr.getColor(x, y).getRed();
                                    sumGreen += pr.getColor(x, y).getGreen();
                                    sumBlue += pr.getColor(x, y).getBlue();
                                }
                            }
                        }
                        return new Color(sumRed / 9.0, sumGreen / 9.0, sumBlue / 9.0, 1);
                    });

            File outputFile = new File("./src/test/java/Chapter03/question14.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(latentImage.toImage(), null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
