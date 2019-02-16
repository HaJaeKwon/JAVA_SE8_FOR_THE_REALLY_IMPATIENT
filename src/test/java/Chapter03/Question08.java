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

public class Question08 {

    /**
     * 임의의 두께와 색상으로 이미지에 프레임을 입힐 수 있는 ColorTransformer를 리턴하는
     * 정적 메서드를 작성하는 방법으로 연습문제 5를 일반화 하라
     */
    @Test
    public void solution() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);

            Image frameImage = transform(image, getFrameColorTransformer());

            File outputFile = new File("./src/test/java/Chapter03/question08.png");
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
                out.getPixelWriter().setColor(x, y, colorTransformer.apply(x < 10 ? x : width-x, y < 10 ? y : height-y, in.getPixelReader().getColor(x,y)));
            }
        }
        return out;
    }

    public static ColorTransformer getFrameColorTransformer() {
        return (x, y, color) -> {
            if (x < 10) {
                    return Color.GRAY;
                } else if ( y < 10) {
                    return Color.GRAY;
                }
                return color;
        };
    }
}
