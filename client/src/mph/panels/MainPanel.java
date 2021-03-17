package mph.panels;

import java.awt.*;
import javax.swing.*;
import mph.entities.DuckImage;

public class MainPanel extends JPanel {
    public MainPanel(DuckImage workingDuckImage) {
        setLayout(new BorderLayout());
        MainDrawPanel mainDrawPanel = new MainDrawPanel(workingDuckImage);
        add(BorderLayout.WEST, mainDrawPanel);
        MainOptionPanel mainOptionPanel = new MainOptionPanel(mainDrawPanel);
        add(BorderLayout.EAST, mainOptionPanel);
    }
}
