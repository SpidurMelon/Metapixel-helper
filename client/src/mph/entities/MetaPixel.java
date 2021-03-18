package mph.entities;

import java.awt.*;
import java.util.HashMap;

public class MetaPixel {
    /** id=red, param1=green, param2=blue */
    private String param1, param2;
    private MetaPixelData metaPixelData;

    public MetaPixel(int id) {
        metaPixelData = MetaPixelData.findById(id);
    }

    public MetaPixelData getMetaPixelData() {
        return metaPixelData;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }
    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }
    public String getParam2() {
        return param2;
    }

    public Color toColor() {
        int r = metaPixelData.getId(), g = 0, b = 0;
        switch (metaPixelData.getMetaPixelType()) {
            case VEC:
                g = (int)((Integer.parseInt(this.param1)/255d)*128)+128;
                b = (int)((Integer.parseInt(this.param2)/255d)*128)+128;
                break;
            case NVEC:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*255);
                b = (int)((Integer.parseInt(this.param2)/(double)metaPixelData.getMax())*255);
                break;
            case NNVEC:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*128)+128;
                b = (int)((Integer.parseInt(this.param2)/(double)metaPixelData.getMax())*128)+128;
                break;
            case FLOAT:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*255);
                break;
            case NFLOAT:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*128)+128;
                break;
            case INT:
                g = (Integer.parseInt(this.param1)*128)+128;;
                break;
            case BOOL:
                break;
            case SPECIAL:
                break;
        }
        return new Color(r, g, b);
    }
}
