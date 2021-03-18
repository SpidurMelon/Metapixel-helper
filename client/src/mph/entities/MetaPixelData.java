package mph.entities;

import java.util.HashMap;

public enum MetaPixelData {
    UNDEFINED(0,"Undefined", "Undefined", "Undefined", MetaPixelType.SPECIAL, -1){},
    HAT_OFFSET(1,"Hat offset", "X offset", "Y offset", MetaPixelType.VEC, 16){};

    enum MetaPixelType {
        VEC, NVEC, NNVEC, FLOAT, NFLOAT, INT, BOOL, SPECIAL;
    }

    private int id, max;
    private final String description;
    private String param1Description, param2Description;
    private MetaPixelType type;

    MetaPixelData(int id, String description, String param1Description, String param2Description, MetaPixelType type, int max) {
        this.id = id;
        this.description = description;
        this.param1Description = param1Description;
        this.param2Description = param2Description;
        this.type = type;
        this.max = max;
    }

    public int getId() {
        return id;
    }
    public int getMax() {
        return max;
    }
    public String getDescription() {
        return description;
    }
    public String getParam1Description() {
        return param1Description;
    }
    public String getParam2Description() {
        return param2Description;
    }
    public MetaPixelType getMetaPixelType() {
        return type;
    }

    private static HashMap<Integer, MetaPixelData> ids = new HashMap<Integer, MetaPixelData>();
    public static MetaPixelData findById(int id) {
        if (ids.isEmpty()) {
            for (MetaPixelData data:MetaPixelData.values()) {
                ids.put(data.id, data);
            }
        }
        if (ids.containsKey(id)) {
            return ids.get(id);
        } else {
            return UNDEFINED;
        }
    }
}
