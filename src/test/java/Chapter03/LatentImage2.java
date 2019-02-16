package Chapter03;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class LatentImage2 {

    private Image in;
    private List<ColorTransformer> pendingOperations;

    private LatentImage2(Image in) {
        this.in = in;
        this.pendingOperations = new ArrayList<>();
    }

    public static LatentImage2 from(Image in) {
        LatentImage2 image = new LatentImage2(in);
        return image;
    }

    public LatentImage2 transform(UnaryOperator<Color> op) {
        pendingOperations.add(getColorTransformer(op));
        return this;
    }

    public LatentImage2 transform(ColorTransformer op) {
        pendingOperations.add(op);
        return this;
    }

    public Image toImage() {
        pendingOperations.forEach(op -> in = transform(in, op));
        return in;
    }

    private ColorTransformer getColorTransformer(UnaryOperator<Color> op) {
        return (x,y,color) -> {
            return op.apply(color);
        };
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
}
