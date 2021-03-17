package mph.main;

import mph.entities.DuckImage;
import mph.frame.MainFrame;
import mph.panels.MainPanel;

public class Main {
    public static void main(String[] args) {
        DuckImage workingDuckImage = new DuckImage(args[0]);
        MainFrame frame = new MainFrame(new MainPanel(workingDuckImage));
        frame.setVisible(true);
    }
}
