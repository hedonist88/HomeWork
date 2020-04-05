package fitness;

public class Gym extends Area {

    public Gym(String name, int AREA_LIMIT, int areaCount) {
        super(name, AREA_LIMIT, areaCount);
    }

    @Override
    public String toString() {
        return "Gym{" +
                "name='" + name + '\'' +
                ", limit='" + AREA_LIMIT + '\'' +
                ", areaCount=" + areaCount +
                '}';
    }
}
