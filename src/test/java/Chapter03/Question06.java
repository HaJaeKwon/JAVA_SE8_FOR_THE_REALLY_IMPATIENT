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
import java.util.function.BiFunction;

public class Question06 {

    /**
     * 79페이지에 있는 "3.4 함수 리턴"절의 다음 메서드를 완성하라
     * public static <T> Image transform(Image in, BiFunction<Color, T> f, T arg)
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
//            Image brightenImage = transform(image, (c, factor) -> c.deriveColor(0,1, factor, 1), 1.5);
            Image brightenImage = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.5);

            File outputFile = new File("./src/test/java/Chapter03/brightenImage2.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(brightenImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }
}
