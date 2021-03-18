package mph.util;

import java.awt.*;

public enum DefaultColor{
    BLURPLE(0x7289DA),
    WHITE(0xFFFFFF),
    GREYPLE(0x99AAB5),
    DARK(0x2C2F33),
    DARKER(0x23272A),
    BLACK(0x000000),
    TRANSPARENT(0, 0, 0, 0);

    private final Color color;
    DefaultColor(int r, int g, int b, int a) {
        color = new Color(r,g,b,a);
    }
    DefaultColor(int rgb) {
        color = new Color(rgb, false);
    }

    public Color getColor() {
        return color;
    }
}
