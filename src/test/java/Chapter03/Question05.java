package Chapter03;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;
import sun.java2d.cmm.ColorTransform;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InterfaceAddress;


public class Question05 {

    /**
     * 다음은 ColorTransformer 의 구체적인 예제다.
     * 다음과 같이 이미지에 프레임을 입히려고 한다.
     * 먼저 75페이지에 있는 "3.3 함수형 인터페이스 선택" 절에서 살펴본 transform 메서드를 변형해
     * UnaryOperator<Color> 대신 ColorTransformer 를 받는 형태로 구현한다
     * 다음으로, 이미지의 경계에 있는 픽셀을 교체해 10픽셀 회색 프레임을 두는 람다 표현식을 전달하며 이 메서드를 호출한다
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            Image frameImage = transform(image, (in, x, y, frame) -> {
                if (x < frame || in.getWidth() - x < frame) {
                    return Color.GRAY;
                } else if ( y < frame || in.getHeight() - y < frame) {
                    return Color.GRAY;
                }
                return in.getPixelReader().getColor(x, y);
            });

            File outputFile = new File("./src/test/java/Chapter03/frameImage.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(frameImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image transform(Image in, ColorTransformer colorTransformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, colorTransformer.transform(in, x, y, 10));
            }
        }
        return out;
    }

    @FunctionalInterface
    interface ColorTransformer {
        Color transform(Image in, int x, int y, int frame);
    }
}
