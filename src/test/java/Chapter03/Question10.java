package Chapter03;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Question10 {

    /**
     * 다음과 같이 호출할 수 없는 이유는 무엇인가?
     * UnaryOperator op = Color::brighter;
     * Image finalImage = transform(image, op.compose(Color::grayscale));
     *
     * compose 메서드의 리턴 타입이 UnaryOperator<T> 임을 주의 깊게 살펴보자
     * transform 메서드에는 이 타입이 적합하지 않은 이유가 무엇인가?
     * 함수 합성과 관련해 구조적 타입과 명목적 타입의 유용성에 관해서 어떻게 설명할 수 있는가?
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            UnaryOperator<Color> op = Color::brighter;
            Image finalImage = transform(image, op.compose(Color::grayscale));

            File outputFile = new File("./src/test/java/Chapter03/question10.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(finalImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    public static Image transform(Image in, Function<Color, Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    /**
     * compose 의 리턴타입은 Function<K, V> 이다
     * UnaryOperator 는 Function 을 상속받은 함수형 인터페이스이다
     * transform 의 인자 타입을 Function<Color, Color> 로 바꾸어 해결했다
     */
}
