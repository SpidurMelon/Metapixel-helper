package mph.entities;

import java.util.ArrayList;
import java.util.HashMap;

public enum MetaPixelData {
    UNDEFINED(0,"Undefined", "Undefined", "Undefined", MetaPixelType.UNDEFINED, -1),
    HAT_OFFSET(1,"Hat offset", "X offset", "Y offset", MetaPixelType.VEC, 16),
    USE_DUCK_COLOR(2,"Use duck color", "", "", MetaPixelType.BOOL, 0),

    CAPE_OFFSET(10,"Cape offset", "X offset", "Y offset", MetaPixelType.VEC, 16),
    CAPE_IS_FOREGROUND(11,"Cape in front", "", "", MetaPixelType.BOOL, 0),
    CAPE_SWAY(12,"Cape sway", "X sway", "Y sway", MetaPixelType.NNVEC, 1),
    CAPE_WIGGLE(13,"Cape wiggle", "X wiggle", "Y wiggle", MetaPixelType.NNVEC, 1),
    CAPE_TAPER_START(14,"Cape taper start", "Narrowness top", "", MetaPixelType.FLOAT, 1),
    CAPE_TAPER_END(15,"Cape taper end", "Narrowness bottom", "", MetaPixelType.FLOAT, 1),
    CAPE_ALPHA_START(16,"Cape alpha start", "Alpha at the top", "", MetaPixelType.FLOAT, 1),
    CAPE_ALPHA_END(17,"Cape alpha end", "Alpha at the bottom", "", MetaPixelType.FLOAT, 1),
    CAPE_IS_TRAIL(20,"Cape becomes trail", "", "", MetaPixelType.BOOL, 1),

    PARTICLE_OFFSET(30,"Particle emitter offset", "X offset", "Y offset", MetaPixelType.VEC, 16),
    PARTICLE_DEFAULT(31,"Sets default behaviour", "<html>Not required</html>", "<html> 0:None <br> 1:Spit <br> 2:Burst <br> 3:Halo <br> 4:Exclamation </html>", MetaPixelType.SPECIAL, 4),
    PARTICLE_SHAPE(32,"Particle emitter shape", "<html> Shape: <br> 0:Point <br> 1:Circle <br> 2:Box </html>", "<html> Distribution: <br> 0:Random (on edge) <br> 1:Random (in area) <br> 2:Uniform (on edge) </html>", MetaPixelType.SPECIAL, 4),
    PARTICLE_SHAPE_SIZE(33,"Particle emitter shape size", "X size", "Y size", MetaPixelType.VEC, 24),
    PARTICLE_COUNT(34,"Particle count per emit", "Count", "", MetaPixelType.INT, 8),
    PARTICLE_LIFESPAN(35,"Particle lifespan", "Lifespan in seconds", "", MetaPixelType.FLOAT, 2),
    PARTICLE_VELOCITY(36,"Particle initial velocity", "X start velocity", "Y start velocity", MetaPixelType.NNVEC, 2),
    PARTICLE_GRAVITY(37,"Particle gravity", "Gravity in x direction", "Gravity in y direction", MetaPixelType.NNVEC, 2),
    PARTICLE_FRICTION(38,"Particle friction (multiplies value every frame)", "Friction in x direction", "Friction in y direction", MetaPixelType.NVEC, 1),
    PARTICLE_ALPHA(39,"Alpha transition of particles", "Start alpha", "End alpha", MetaPixelType.NVEC, 1),
    PARTICLE_SCALE(40,"Scale transition of particles", "Start scale", "End scale", MetaPixelType.NVEC, 2),
    PARTICLE_ROTATION(41,"Rotation transition of particles", "Start rotation", "End rotation", MetaPixelType.NVEC, 36),
    PARTICLE_OFFSET_2(42,"Additional particle offset", "X offset", "Y offset", MetaPixelType.VEC, 16),
    PARTICLE_IN_BACKGROUND(43,"Makes particles show in the background", "", "", MetaPixelType.BOOL, 0),
    PARTICLE_ANCHOR(44,"Makes particles anchor to you", "", "", MetaPixelType.BOOL, 0),
    PARTICLE_ANIMATION(45,"Makes the particle animate through all sprites", "", "", MetaPixelType.BOOL, 0),
    PARTICLE_ANIMATION_LOOP(46,"Makes the particle loop through it's animation", "", "", MetaPixelType.BOOL, 0),
    PARTICLE_ANIMATION_RANDOM_START(47,"Starts the animation at a random frame", "", "", MetaPixelType.BOOL, 0),
    PARTICLE_ANIMATION_SPEED(48,"Changes the speed of animation", "The speed ranging from 0 to 24 fps", "", MetaPixelType.FLOAT, 1),

    WET_LIPS(70,"Makes your beak closing sound like wet lips", "", "", MetaPixelType.BOOL, 0),
    MECHANICAL_LIPS(71,"Makes your beak closing sound like mechanical lips", "", "", MetaPixelType.BOOL, 0),

    X_RANDOMIZER(100, "Multiplies the first parameter of above MetaPixel by a random float", "Max multiplication", "Min multiplication", MetaPixelType.NNVEC, 1),
    Y_RANDOMIZER(101, "Multiplies the second parameter of above MetaPixel by a random float", "Max multiplication", "Min multiplication", MetaPixelType.NNVEC, 1),
    BOTH_RANDOMIZER(102, "Multiplies both parameters of above MetaPixel by the same random float", "Max multiplication", "Min multiplication", MetaPixelType.NNVEC, 1)
    ;

    public enum MetaPixelType {
        VEC, NVEC, NNVEC, FLOAT, NFLOAT, INT, BOOL, SPECIAL, UNDEFINED;
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

    public String toString() {
        return id + " " + getDescription();
    }
    private static ArrayList<MetaPixelType> negatives = new ArrayList<MetaPixelType>() {{
        add(MetaPixelType.VEC);
        add(MetaPixelType.NNVEC);
        add(MetaPixelType.NFLOAT);
    }};
    private static ArrayList<MetaPixelType> multiParameter = new ArrayList<MetaPixelType>() {{
        add(MetaPixelType.VEC);
        add(MetaPixelType.NVEC);
        add(MetaPixelType.NNVEC);
        add(MetaPixelType.SPECIAL);
    }};
    public static ArrayList<MetaPixelType> getNegatives() {
        return negatives;
    }

    public boolean canGoNegative() {
        return negatives.contains(getMetaPixelType());
    }

    public boolean hasMultipleParams() {
        return multiParameter.contains(getMetaPixelType());
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
    public static HashMap<Integer, MetaPixelData> getAll() {
        if (ids.isEmpty()) {
            for (MetaPixelData data:MetaPixelData.values()) {
                ids.put(data.id, data);
            }
        }
        return ids;
    }

}
