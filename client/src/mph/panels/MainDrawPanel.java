package mph.panels;

import java.awt.*;
import mph.util.DefaultColor;

public class MainDrawPanel extends DrawPanel {
    public MainDrawPanel() {
        setPreferredSize(new Dimension(800, 800));
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(DefaultColor.DARK.getColor());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
