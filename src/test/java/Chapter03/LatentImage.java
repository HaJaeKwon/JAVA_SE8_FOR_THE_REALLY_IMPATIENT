package Chapter03;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class LatentImage {
    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;

    private LatentImage(Image in) {
        this.in = in;
        this.pendingOperations = new ArrayList<>();
    }

    public static LatentImage from(Image in) {
        LatentImage image = new LatentImage(in);
        return image;
    }

    public LatentImage transform(UnaryOperator<Color> op) {
        pendingOperations.add(op);
        return this;
    }

    public Image toImage() {
        pendingOperations.forEach(op -> in = Lecture.transform(in, op));
        return in;
    }
}
