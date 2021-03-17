package mph.panels;

import java.awt.*;
import java.awt.geom.AffineTransform;
import mph.entities.DuckImage;
import mph.util.DefaultColor;

public class MainDrawPanel extends DrawPanel {
    enum View {
        ALL, HAT, QUACK, CAPE, ROCK, PARTICLES;
    }

    private DuckImage workingDuckImage;
    private View currentView;
    private AffineTransform viewTransform = new AffineTransform();

    public MainDrawPanel(DuckImage workingDuckImage) {
        this.workingDuckImage = workingDuckImage;
        setPreferredSize(new Dimension(800, 800));
        setCurrentView(View.ALL);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(DefaultColor.DARK.getColor());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.drawImage(workingDuckImage.getImage(), viewTransform, null);
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
        viewTransform.setToIdentity();
        switch(currentView) {
            case ALL:
                viewTransform.scale(800d/DuckImage.WIDTH, 800d/DuckImage.WIDTH);
                break;
            case HAT:
                viewTransform.scale(800d/DuckImage.HATSIZE, 800d/DuckImage.HATSIZE);
                break;
            case QUACK:
                viewTransform.scale(800d/DuckImage.HATSIZE, 800d/DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.HATSIZE, 0);
                break;
            case CAPE:
                viewTransform.scale(800d/DuckImage.HATSIZE, 800d/DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.HATSIZE*2, 0);
                break;
            case ROCK:
                viewTransform.scale(800d/DuckImage.ROCKSIZE, 800d/DuckImage.ROCKSIZE);
                viewTransform.translate(0, -DuckImage.HATSIZE);
                break;
            case PARTICLES:
                viewTransform.scale(800d/DuckImage.ROCKSIZE, 800d/DuckImage.ROCKSIZE);
                viewTransform.translate(0, -DuckImage.HATSIZE);
                viewTransform.translate(-DuckImage.ROCKSIZE, 0);
                break;
        }
        repaint();
    }
}
