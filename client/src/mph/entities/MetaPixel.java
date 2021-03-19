package mph.entities;

import mph.util.DefaultColor;

import java.awt.*;

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
                g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*255);
                b = (int)((Float.parseFloat(this.param2)/(double)metaPixelData.getMax())*255);
                break;
            case NNVEC:
                if ((Float.parseFloat(this.param1) <= 0)){
                    g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*128)+128;
                } else {
                    g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*127)+128;
                }
                if ((Float.parseFloat(this.param2) <= 0)){
                    b = (int)((Float.parseFloat(this.param2)/(double)metaPixelData.getMax())*128)+128;
                } else {
                    b = (int)((Float.parseFloat(this.param2)/(double)metaPixelData.getMax())*127)+128;
                }
                break;
            case FLOAT:
                g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*255);
                break;
            case NFLOAT:
                if ((Float.parseFloat(this.param1) <= 0)){
                    g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*128)+128;
                } else {
                    g = (int)((Float.parseFloat(this.param1)/(double)metaPixelData.getMax())*127)+128;
                }
                break;
            case INT:
                g = Integer.parseInt(this.param1);
                break;
            case BOOL:
                break;
            case SPECIAL:
                g = Integer.parseInt(this.param1);
                b = Integer.parseInt(this.param1);
                break;
            case UNDEFINED:
                return DefaultColor.TRANSPARENT.getColor();
        }
        return new Color(r, g, b);
    }

    public static MetaPixel fromColor(Color color) {
        MetaPixel mp = new MetaPixel(color.getRed());
        switch(mp.getMetaPixelData().getMetaPixelType()) {
            case VEC:
                mp.setParam1(String.valueOf(color.getGreen()-128));
                mp.setParam2(String.valueOf(color.getBlue()-128));
                break;
            case NVEC:
                mp.setParam1(String.valueOf((color.getGreen()/255d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf((color.getBlue()/255d)*mp.getMetaPixelData().getMax()));
                break;
            case NNVEC:
                if (color.getGreen() <= 0) {
                    mp.setParam1(String.valueOf(((color.getGreen()-128)*mp.getMetaPixelData().getMax())/128d));
                } else {
                    mp.setParam1(String.valueOf(((color.getGreen()-128)*mp.getMetaPixelData().getMax())/127d));
                }
                if (color.getBlue() <= 0) {
                    mp.setParam2(String.valueOf(((color.getBlue()-128)*mp.getMetaPixelData().getMax())/128d));
                } else {
                    mp.setParam2(String.valueOf(((color.getBlue()-128)*mp.getMetaPixelData().getMax())/127d));
                }
                break;
            case FLOAT:
                mp.setParam1(String.valueOf((color.getGreen()/255d)*mp.getMetaPixelData().getMax()));
                mp.setParam2(String.valueOf(0));
                break;
            case NFLOAT:
                if (color.getGreen() <= 0) {
                    mp.setParam1(String.valueOf(((color.getGreen()-128)*mp.getMetaPixelData().getMax())/128d));
                } else {
                    mp.setParam1(String.valueOf(((color.getGreen()-128)*mp.getMetaPixelData().getMax())/127d));
                }
                mp.setParam2(String.valueOf(0));
                break;
            case INT:
                mp.setParam1(String.valueOf(color.getGreen()));
                mp.setParam2(String.valueOf(0));
                break;
            case BOOL:
                mp.setParam1(String.valueOf(0));
                mp.setParam2(String.valueOf(0));
                break;
            case SPECIAL:
                mp.setParam1(String.valueOf(color.getGreen()));
                mp.setParam2(String.valueOf(color.getBlue()));
                break;
        }
        return mp;
    }

    public boolean isComplete() {
        if (getMetaPixelData().hasMultipleParams()) {
            return param1 != null && param2 != null;
        } else {
            return param1 != null;
        }

    }
}
