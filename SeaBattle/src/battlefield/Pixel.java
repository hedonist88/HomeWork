package battlefield;

import java.util.Arrays;

public class Pixel extends Battlefield{
    Boolean health = false;
    Boolean available = true;
    String type;
    int[] coords;
    int objId;

    public Pixel() {}

    public Pixel(int[] coords, String type, Boolean available) {
        setType(type);
        setCoords(coords);
        setAvailable(available);
    }

    public Pixel(int[] coords, String type, Boolean available, Boolean health) {
        setHealth(health);
        setType(type);
        setCoords(coords);
        setAvailable(available);
    }

    public Pixel(int[] coords, String type, Boolean available, Boolean health, int objId) {
        setHealth(health);
        setType(type);
        setCoords(coords);
        setAvailable(available);
        setObjId(objId);
    }

    /* === GetSet === */
    public Boolean getHealth() {
        return health;
    }

    public void setHealth(Boolean health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(!"null".equals(type)) {
            this.type = type;
        }
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    @Override
    public String toString() {
        return "\n    Pixel{" +
                "\n      objId=" + objId +
                "\n      health=" + health +
                ", \n      available=" + available +
                ", \n      type='" + type + '\'' +
                ", \n      coords=" + Arrays.toString(coords) +
                '}';
    }
}
