package mph.panels;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import mph.entities.DuckImage;
import mph.util.DefaultColor;

public class MainDrawPanel extends DrawPanel {
    enum View {
        ALL, HAT, QUACK, CAPE, ROCK, PARTICLES;
    }

    private DuckImage workingDuckImage;
    private View currentView;
    private AffineTransform viewTransform = new AffineTransform();
    private boolean imageActive = true, helperActive = false, tongueActive = false;
    private BufferedImage helperImage, tongue;
    private static final int WIDTH = 600, HEIGHT = 600;

    public MainDrawPanel(DuckImage workingDuckImage) {
        this.workingDuckImage = workingDuckImage;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setCurrentView(View.ALL);
        try {
            helperImage = ImageIO.read(new File("Helper.png"));
            tongue = ImageIO.read(new File("Tongue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(DefaultColor.DARK.getColor());
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        if (helperActive) g2.drawImage(helperImage, viewTransform, null);
        if (tongueActive) g2.drawImage(tongue, viewTransform, null);
        if (imageActive) g2.drawImage(workingDuckImage.getImage(), viewTransform, null);
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
        viewTransform.setToIdentity();
        switch(currentView) {
            case ALL:
                viewTransform.scale(WIDTH/(double)DuckImage.WIDTH, WIDTH/(double)DuckImage.WIDTH);
                break;
            case HAT:
                viewTransform.scale(WIDTH/(double)DuckImage.HATSIZE, WIDTH/(double)DuckImage.HATSIZE);
                break;
            case QUACK:
                viewTransform.scale(WIDTH/(double)DuckImage.HATSIZE, WIDTH/(double)DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.HATSIZE, 0);
                break;
            case CAPE:
                viewTransform.scale(WIDTH/(double)DuckImage.HATSIZE, WIDTH/(double)DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.HATSIZE*2, 0);
                break;
            case ROCK:
                viewTransform.scale(WIDTH/(double)DuckImage.ROCKSIZE, WIDTH/(double)DuckImage.ROCKSIZE);
                viewTransform.translate(0, -DuckImage.HATSIZE);
                break;
            case PARTICLES:
                viewTransform.scale(WIDTH/(double)DuckImage.ROCKSIZE, WIDTH/(double)DuckImage.ROCKSIZE);
                viewTransform.translate(0, -DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.ROCKSIZE, 0);
                break;
        }
        repaint();
    }

    public void toggleImage() {
        imageActive = !imageActive;
        repaint();
    }

    public void toggleHelper() {
        helperActive = !helperActive;
        repaint();
    }

    public void toggleTongue() {
        tongueActive = !tongueActive;
        repaint();
    }

}
