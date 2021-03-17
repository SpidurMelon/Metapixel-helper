package mph.entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DuckImage {
    public static final int WIDTH = 97, HEIGHT = 56, HATSIZE = 32, ROCKSIZE = 24;
    private BufferedImage image;
    public DuckImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}