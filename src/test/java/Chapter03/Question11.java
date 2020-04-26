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
import java.util.function.UnaryOperator;

public class Question11 {

    /**
     * 두 ColorTransformer 객체를 합성할 수 있는 정적 메서드와 UnaryOperator<Color> 를 x,y좌표를 무시하는
     * ColorTransformer 로 변환하는 정적 메서드를 구현하라
     * 다음으로 이들 메서드를 사용해서 밝게 처리한 이미지에 회색 프레임을 입혀라
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            Image frameImage = transform(image, compose(getColorTransformer(Color::brighter), getFrameColorTransformer()));

            File outputFile = new File("./src/test/java/Chapter03/question11.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(frameImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ColorTransformer compose(ColorTransformer op1, ColorTransformer op2) {
        return (x, y, color) -> {
            Color c = op1.apply(x, y, color);
            return op2.apply(x, y, c);
        };
    }

    public static ColorTransformer getColorTransformer(UnaryOperator<Color> op) {
        return (x, y, color) -> {
            return op.apply(color);
        };
    }


    public Image transform(Image in, ColorTransformer colorTransformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, colorTransformer.apply(x < 10 ? x : width - x, y < 10 ? y : height - y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }


    public static ColorTransformer getFrameColorTransformer() {
        return (x, y, color) -> {
            if (x < 10) {
                return Color.GRAY;
            } else if (y < 10) {
                return Color.GRAY;
            }
            return color;
        };
    }

}
