package mph.panels;

import java.awt.*;
import mph.util.DefaultColor;

public class MainOptionPanel extends OptionPanel {
    public MainOptionPanel() {
        setPreferredSize(new Dimension(200, 800));
        setBackground(DefaultColor.DARKER.getColor());
    }
}
