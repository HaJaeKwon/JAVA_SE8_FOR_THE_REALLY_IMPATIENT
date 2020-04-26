package Chapter03;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public class Lecture {

    @Test
    public void lecture02() {
        repeatWithParameter(10, i -> System.out.println("Countdown: " + (9 - i)));
        repeatWithNoParameter(10, () -> System.out.println("Hello World!"));
    }

    public static void repeatWithParameter(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) action.accept(n);
    }

    public static void repeatWithNoParameter(int n, Runnable action) {
        for (int i = 0; i < n; i++) action.run();
    }

    @Test
    public void lecture03() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
            Image brightenImage = transform(image, Color::brighter);

            // jpg 저장은 안됨....왜인지는 모르겠음
            File outputFile = new File("./src/test/java/Chapter03/brightenImage.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(brightenImage, null);
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

    @Test
    public void lecture04() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
//            Image brightenImage = transform(image, (c, factor) -> c.deriveColor(0,1, factor, 1), 1.5);
            Image brightenImage = transform(image, brighten(1.5));

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

    public static UnaryOperator<Color> brighten(double factor) {
        return c -> c.deriveColor(0, 1, factor, 1);
    }

    @Test
    public void lecture05() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
            Image brightenImage = transform(image, compose(Color::brighter, Color::grayscale));

            File outputFile = new File("./src/test/java/Chapter03/brightenImage3.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(brightenImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> UnaryOperator<T> compose(UnaryOperator<T> op1, UnaryOperator<T> op2) {
        return t -> op2.apply(op1.apply(t));
    }

    @Test
    public void lecture06() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
            Image finalImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale).toImage();

            File outputFile = new File("./src/test/java/Chapter03/brightenImage4.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(finalImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lecture07() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/java/Chapter03/eiffel-tower.jpg");
            Image image = new Image(inputStream);
            Color[][] colors = convertImageToColor2dArray(image);
            colors = parallelTransform(colors, Color::brighter);
            Image finalImage = convertColor2dArrayToImage(colors);

            File outputFile = new File("./src/test/java/Chapter03/brightenImage5.png");
            BufferedImage outputImage = SwingFXUtils.fromFXImage(finalImage, null);
            ImageIO.write(outputImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Color[][] convertImageToColor2dArray(Image in) {
        int height = (int) in.getHeight();
        int width = (int) in.getWidth();
        Color[][] out = new Color[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out[y][x] = in.getPixelReader().getColor(x, y);
            }
        }
        return out;
    }

    public static Image convertColor2dArrayToImage(Color[][] in) {
        int height = in.length;
        int width = in[0].length;
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, in[y][x]);
            }
        }
        return out;
    }

    // javaFX PixelWriter 는 thread safe 하지 않기에 Color[][] 를 사용
    public static Color[][] parallelTransform(Color[][] in, UnaryOperator<Color> f) {
        int n = Runtime.getRuntime().availableProcessors();
        int height = in.length;
        int width = in[0].length;
        Color[][] out = new Color[height][width];

        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < n; i++) {
                int fromY = i * height / n;
                int toY = (i + 1) * height / n;
                pool.submit(() -> {
                    for (int x = 0; x < width; x++) {
                        for (int y = fromY; y < toY; y++) {
                            out[y][x] = f.apply(in[y][x]);
                        }
                    }
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return out;
    }

    // Async 로 함수가 동작시 예외를 던져서 처리를 바라는 것이 불가능하기 때문에 handler 를 전달하는 것이 옳다
    @Test
    public void lecture08() {
        doInOrderAsync(
                () -> System.out.println("first"),
                () -> System.out.println("second"),
                t -> t.printStackTrace()
        );

        String content = unchecked(() -> new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8)).get();
        System.out.println(content);
    }

    public static void doInOrderAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Thread t = new Thread() {
            public void run() {
                try {
                    first.run();
                    second.run();
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

    public static <T> Supplier<T> unchecked(Callable<T> f) {
        return () -> {
            try {
                return f.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    public void lecture09() {

        Person person = new Person();
        Child child = new Child();
        Student student = new Student();

        /**
         * ? extends Child 의 뜻은 List 에 뭐가 담겨있는지는 모르겠는데 최소한 Child 의 자식클래스라는 것이다
         * read 의 입장에서는 무슨 타입인지는 모르겠지만 최소한 Child 의 정보까지는 모두 채워진 데이터라는 것을 확신할 수 있다 (그 부모클래스들도)
         * 그래서 Child 와 Person 까지는 꺼내서 맵핑이 가능하다. upcasting이 된다
         * write 의 입장에서는 이 안에 있는 건 Child 의 자식클래스들 중 하나인데 어떤 데이터들을 채워줘야 하는지 알 수 없다
         * 따라서 write 할 수 없다. 부족한 데이터가 존재하면 안되기에. downcasting은 되지 않는다
         */
        List<? extends Child> covariantList = new ArrayList<>();
//        for (Person p : covariantList) {
//            System.out.println(p.getName());
//        }
//        for (Child e : covariantList) {
//            System.out.println(e.getAge());
//        }
//        for (Student s : covariantList) {
//            System.out.println(s.getSchool());
//        }
//
//        covariantList.add(person);
//        covariantList.add(child);
//        covariantList.add(student);

        /**
         * ? super Child 의 뜻은 List 에 뭐가 담겨있는지는 모르겠는데 최소한 Child 의 부모클래스라는 것이다
         * read 의 입장에서는 Child 의 부모클래스들을 어떻게 맵핑 시켜야 하는지를 알 수 없다. 맵핑 시켰을때 데이터가 부족할 수 있다. downcasting 이 되지 않는다
         * write 의 입장에서는 child 의 자식클래스들로 List를 채우면 부족한 데이터는 존재하지 않기에 write가 가능하다. upcasting 이 된다
         */
        List<? super Child> contravariantList = new ArrayList<>();
//        for (Person p : contravariantList) {
//            System.out.println(p.getName());
//        }
//        for (Child e : contravariantList) {
//            System.out.println(e.getAge());
//        }
//        for (Student s : contravariantList) {
//            System.out.println(s.getSchool());
//        }
//
//        contravariantList.add(person);
//        contravariantList.add(child);
//        contravariantList.add(student);

        /**
         * write 를 하기위해서는, parameter 로 주기 위해서는 super 로 타입 제한을 줘야 되고
         * read 를 하기위해서는, return type 으로 주기 위해서는 extends 로 타입 제한을 줘야 한다
         */
    }

    class Person {
        String name;

        public String getName() {
            return name;
        }
    }

    class Child extends Person {
        String age;

        public String getAge() {
            return age;
        }
    }

    class Student extends Child {
        String school;

        public String getSchool() {
            return school;
        }
    }


}
