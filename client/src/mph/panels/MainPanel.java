package mph.panels;

import java.awt.*;
import javax.swing.*;
import mph.util.DefaultColor;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());
        add(BorderLayout.WEST, new MainDrawPanel());
        add(BorderLayout.EAST, new MainOptionPanel());
    }
}
