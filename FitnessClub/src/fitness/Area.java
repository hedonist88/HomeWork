package fitness;

import subscription.Day;
import subscription.Full;
import subscription.OneTime;

public abstract class Area extends Fitness {
    public static int AREA_LIMIT = 20;
    protected String name;
    protected int areaCount;

    public Area(String name, int AREA_LIMIT, int areaCount) {
        setName(name);
        setAreaCount(areaCount);
        setAreaLimit(AREA_LIMIT);
    }

    public static Area getArea(String type) {
        Area result = null;
        if("gym".equals(type)){
            result = new Gym(
                "Тренажерный зал", AREA_LIMIT,0
            );
        } else if("group".equals(type)){
            result = new Group(
                "Групповые занятия", AREA_LIMIT, 0
            );
        } else if("pool".equals(type)){
            result = new Pool(
                "Бассейн", AREA_LIMIT, 0
            );
        }
        return result;
    }

    public static int getAreaLimit() {
        return AREA_LIMIT;
    }

    public static void setAreaLimit(int areaLimit) {
        AREA_LIMIT = areaLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && name.length() > 3) {
            this.name = name;
        } else {
            System.out.println("Имя больше 3 символов");
        }
    }

    public int getAreaCount() {
        return areaCount;
    }

    public void setAreaCount(int areaCount) {
        if(areaCount >= 0 && areaCount <= AREA_LIMIT) {
            this.areaCount = areaCount;
        } else {
            System.out.println("Заполнение зоны не более 20 человек");
        }
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                "limit='" + AREA_LIMIT + '\'' +
                ", areaCount=" + areaCount +
                '}';
    }
}
