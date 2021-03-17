package mph.main;

import mph.frame.MainFrame;
import mph.panels.MainPanel;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame(new MainPanel());
        frame.setVisible(true);
    }
}
