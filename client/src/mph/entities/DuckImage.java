package mph.entities;

import mph.util.DefaultColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DuckImage {
    public static final int WIDTH = 97, HEIGHT = 56, HATSIZE = 32, ROCKSIZE = 24;
    private File file;
    private BufferedImage image;

    public DuckImage(String path) {
        file = new File(path);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPixel(int x, int y, Color color) {
        Graphics2D imageGraphics = (Graphics2D)image.getGraphics();
        imageGraphics.setColor(color);
        imageGraphics.drawRect(x, y, 0, 0);
        if (color.equals(DefaultColor.TRANSPARENT.getColor())) {
            AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f);
            imageGraphics.setComposite(composite);
            imageGraphics.drawRect(x, y, 0, 0);
        }
    }

    public Color getPixel(int x, int y) {
        return new Color(image.getRGB(x, y));
    }

    public BufferedImage getImage() {
        return image;
    }

    public void write() {
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
