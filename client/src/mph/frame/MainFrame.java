package mph.frame;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(JPanel mainPanel) {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel);
        pack();
    }
}
