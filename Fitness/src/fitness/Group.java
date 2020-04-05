package fitness;

public class Group extends Area{

    public Group(String name, int AREA_LIMIT, int areaCount) {
        super(name, AREA_LIMIT, areaCount);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", limit='" + AREA_LIMIT + '\'' +
                ", areaCount=" + areaCount +
                '}';
    }
}
