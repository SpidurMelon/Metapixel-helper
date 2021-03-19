package mph.frame;

import mph.util.DefaultColor;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(JPanel mainPanel) {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(DefaultColor.DARK.getColor());

        add(mainPanel);
        pack();
        repaint();
    }


}
