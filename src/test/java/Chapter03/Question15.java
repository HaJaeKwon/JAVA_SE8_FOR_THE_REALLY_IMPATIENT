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

public class Question15 {

    /**
     * 지연평가와 연산 병렬화를 결합하라
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
            LatentImage latentImage = LatentImage.from(image).transform(Color::brighter);

            File outputFile = new File("./src/test/java/Chapter03/question15.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(latentImage.toImageParallel(), null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
