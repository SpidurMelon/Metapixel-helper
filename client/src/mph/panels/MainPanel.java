package mph.panels;

import java.awt.*;
import javax.swing.*;
import mph.entities.DuckImage;
import mph.util.DefaultColor;

public class MainPanel extends JPanel {
    public MainPanel(DuckImage workingDuckImage) {
        setLayout(new BorderLayout());
        setBackground(DefaultColor.DARK.getColor());

        MainDrawPanel mainDrawPanel = new MainDrawPanel(workingDuckImage);
        add(BorderLayout.WEST, mainDrawPanel);

        JTabbedPane tabs = new JTabbedPane();
        ViewOptionPanel viewOptionPanel = new ViewOptionPanel(mainDrawPanel);
        tabs.addTab("Views", null, viewOptionPanel, "Changes everything related to the views");

        MetaPixelOptionPanel metaPixelOptionPanel = new MetaPixelOptionPanel(mainDrawPanel, workingDuckImage);
        tabs.addTab("Meta Pixels", null, metaPixelOptionPanel, "Allows you to select a MetaPixel");

        MetaPixelPropertiesPanel metaPixelPropertiesPanel = new MetaPixelPropertiesPanel(metaPixelOptionPanel);
        tabs.addTab("Properties", null, metaPixelPropertiesPanel, "Allows you to change the properties of a MetaPixel");
        add(BorderLayout.EAST, tabs);

        metaPixelOptionPanel.setMetaPixelPropertiesPanel(metaPixelPropertiesPanel);
    }
}
