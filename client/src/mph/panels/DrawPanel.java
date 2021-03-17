package mph.panels;

import java.awt.*;
import javax.swing.*;

public abstract class DrawPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        draw((Graphics2D) g);
    }
    public abstract void draw(Graphics2D g2);
}
