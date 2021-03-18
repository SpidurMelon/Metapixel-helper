package mph.entities;

import mph.util.DefaultColor;

import java.awt.Color;
import java.util.HashMap;

public class MetaPixelManager {
    private HashMap<Integer, MetaPixel> metaPixelMap = new HashMap<Integer, MetaPixel>();

    public void setMetaPixel(int y, int id, String param1, String param2) {
        metaPixelMap.put(y, new MetaPixel(id, param1, param2));
    }

    public void setMetaPixel(int y, MetaPixel mp) {
        metaPixelMap.put(y, mp);
    }

    public void removeMetaPixel(int y) {
        metaPixelMap.remove(y);
    }

    public void apply(DuckImage image) {
        for (int y = 0; y < DuckImage.HEIGHT; y++) {
            if (metaPixelMap.containsKey(y)) {
                MetaPixel mp = metaPixelMap.get(y);
                Color mpColor = mp.toColor();
                image.setPixel(DuckImage.WIDTH - 1, y, mpColor);
                System.out.println("x: " + (DuckImage.WIDTH - 1) + ", y:" + y + ", color:" + mpColor);
            }
        }
    }

}
