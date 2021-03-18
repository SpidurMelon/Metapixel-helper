package mph.panels;

import java.awt.*;
import javax.swing.*;
import mph.entities.DuckImage;

public class MainPanel extends JPanel {
    public MainPanel(DuckImage workingDuckImage) {
        setLayout(new BorderLayout());

        MainDrawPanel mainDrawPanel = new MainDrawPanel(workingDuckImage);
        add(BorderLayout.WEST, mainDrawPanel);

        JTabbedPane tabs = new JTabbedPane();
        ViewOptionPanel viewOptionPanel = new ViewOptionPanel(mainDrawPanel);
        tabs.addTab("Views", null, viewOptionPanel, "Changes everything related to the views");
        add(BorderLayout.EAST, tabs);

        MetaPixelOptionPanel metaPixelOptionPanel = new MetaPixelOptionPanel(mainDrawPanel, workingDuckImage);
        tabs.addTab("Meta Pixels", null, metaPixelOptionPanel, "Changes everything related to the Meta pixels");
        add(BorderLayout.EAST, tabs);
    }
}
