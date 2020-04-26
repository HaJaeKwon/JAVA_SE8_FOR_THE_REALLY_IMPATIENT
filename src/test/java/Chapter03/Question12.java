package Chapter03;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Question12 {

    /**
     * 82페이지에 있는 "3.6 지연" 절의 LatentImage 클래스가 UnaryOperator<Color>와 ColorTransformer 모두 지원하도록 개선하라
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            LatentImage2 latentImage = LatentImage2.from(image).transform(Color::brighter).transform(getFrameColorTransformer());

            File outputFile = new File("./src/test/java/Chapter03/question12.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(latentImage.toImage(), null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
