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
     *
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            LatentImage3 latentImage = LatentImage3.from(image)
                    .transform( (x,y,pr) -> {
                        int[] DX = {-1, 0, 1};
                        int[] DY = {-1, 0, 1};
                        double sumRed = 0.0;
                        double sumGreen = 0.0;
                        double sumBlue = 0.0;
                        for(int dx : DX) {
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
                        return new Color(sumRed/9.0, sumGreen/9.0, sumBlue/9.0, 1);
                    });

            File outputFile = new File("./src/test/java/Chapter03/question14.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(latentImage.toImage(), null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
