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

    public MetaPixel(int id, String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
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
                g = Integer.parseInt(this.param1)+128;
                b = Integer.parseInt(this.param2)+128;
                break;
            case NVEC:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*255);
                b = (int)((Integer.parseInt(this.param2)/(double)metaPixelData.getMax())*255);
                break;
            case NNVEC:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*127)+128;
                b = (int)((Integer.parseInt(this.param2)/(double)metaPixelData.getMax())*127)+128;
                break;
            case FLOAT:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*255);
                break;
            case NFLOAT:
                g = (int)((Integer.parseInt(this.param1)/(double)metaPixelData.getMax())*127)+128;
                break;
            case INT:
                g = (Integer.parseInt(this.param1))+128;
                break;
            case BOOL:
                break;
            case SPECIAL:
                break;
        }
        return new Color(r, g, b);
    }

    public static MetaPixel fromColor(Color color) {
        MetaPixel mp = new MetaPixel(color.getRed());
        switch (mp.getMetaPixelData().getMetaPixelType()) {
            case VEC:
                mp.setParam1(String.valueOf(color.getGreen()-128));
                mp.setParam2(String.valueOf(color.getBlue()-128));
                break;
            case NVEC:
                mp.setParam1(String.valueOf((int)(color.getGreen()/255d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf((int)(color.getBlue()/255d)*mp.getMetaPixelData().getMax()));
                break;
            case NNVEC:
                mp.setParam1(String.valueOf((int)((color.getGreen()-128)/128d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf((int)((color.getBlue()-128)/128d)*mp.getMetaPixelData().getMax()));
                break;
            case FLOAT:
                mp.setParam1(String.valueOf((int)(color.getGreen()/255d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf(0));
                break;
            case NFLOAT:
                mp.setParam1(String.valueOf((int)((color.getGreen()-128)/128d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf(0));
                break;
            case INT:
                mp.setParam1(String.valueOf(color.getGreen()-128));
                mp.setParam2(String.valueOf(0));
                break;
            case BOOL:
                mp.setParam1(String.valueOf(0));
                mp.setParam2(String.valueOf(0));
                break;
            case SPECIAL:
                break;
        }
        return mp;
    }
}
