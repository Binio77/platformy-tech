package org.example;
import org.apache.commons.lang3.tuple.Pair;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Files;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*
        if (args.length == 0) {
            System.out.println("No arguments provided");
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("Current working directory is: " + currentDirectory);

            return;
        }
        */


        String directoryPath = "src/main/resources/images"; //args[0];
        String outputFilePath = "src/main/resources/new"; //args[1];
        int threads = 1; //Integer.parseInt(args[2])

        List<Path> files;
        Path source = Path.of(directoryPath);
        System.out.println(source.toAbsolutePath());
        try {
            Stream<Path> stream = Files.list(source);
            files = stream.toList();
        } catch (Exception e) {
            throw new RuntimeException("Error reading files", e);
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(threads);
        long time = System.currentTimeMillis();

        try
        {
            forkJoinPool.submit(() -> files.parallelStream().map(path -> {
                        try {
                            BufferedImage image = ImageIO.read(path.toFile());
                            return Pair.of(path.getFileName().toString(), image);
                        } catch (Exception e) {
                            throw new RuntimeException("Error reading image", e);
                        }
                    }).map(pair -> {
                        BufferedImage image = pair.getRight();
                        BufferedImage transformedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

                        for(int i = 0; i < image.getWidth(); i++)
                        {
                            for (int j = 0; j < image.getHeight(); j++)
                            {
                                int rgb = image.getRGB(i, j);
                                Color color = new Color(rgb);

                                int red = color.getBlue();
                                int green = color.getGreen();
                                int blue = color.getRed();
                                Color newColor = new Color(red, green, blue);
                                transformedImage.setRGB(i, j, newColor.getRGB());
                            }
                        }
                        return Pair.of(pair.getLeft(), transformedImage);
                    })
                    .forEach(pair -> {
                        try {
                            ImageIO.write(pair.getRight(), "jpg", Path.of(outputFilePath, pair.getLeft()).toFile());
                        } catch (Exception e) {
                            throw new RuntimeException("Error writing image", e);
                        }
                    })).get();
        } catch (Exception e) {
            throw new RuntimeException("Error processing images", e);
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time));
        forkJoinPool.shutdown();
    }
}