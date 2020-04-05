package fitness;

public class Pool extends Area {

    public Pool(String name, int AREA_LIMIT, int areaCount) {
        super(name, AREA_LIMIT, areaCount);
    }

    @Override
    public String toString() {
        return "Pool{" +
                "name='" + name + '\'' +
                ", limit='" + AREA_LIMIT + '\'' +
                ", areaCount=" + areaCount +
                '}';
    }
}
