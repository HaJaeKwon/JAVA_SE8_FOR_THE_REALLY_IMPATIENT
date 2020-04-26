package Chapter03;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

public class LatentImage3 {

    private Image in;
    private List<ColorTransformerWithPixelReader> pendingOperations;
    private Map<Pair<Integer, Integer>, Color> cache;

    private LatentImage3(Image in) {
        this.in = in;
        this.pendingOperations = new ArrayList<>();
        this.cache = new HashMap<>();
    }

    public static LatentImage3 from(Image in) {
        LatentImage3 image = new LatentImage3(in);
        return image;
    }

    public LatentImage3 transform(ColorTransformerWithPixelReader op) {
        pendingOperations.add(op);
        return this;
    }

    public Image toImage() {
        pendingOperations.forEach(op -> in = transform(in, op));
        return in;
    }

    public Image transform(Image in, ColorTransformerWithPixelReader colorTransformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = cache.get(new Pair<>(x, y));
                if (color == null) {
                    color = in.getPixelReader().getColor(x, y);
                }
                color = colorTransformer.apply(x, y, in.getPixelReader());
                out.getPixelWriter().setColor(x, y, color);
                cache.put(new Pair<>(x, y), color);
            }
        }
        return out;
    }
}

@FunctionalInterface
interface ColorTransformerWithPixelReader {
    Color apply(int x, int y, PixelReader pr);
}
