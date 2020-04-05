package subscription;

import fitness.Area;
import user.User;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public abstract class Subscription implements Registration{
    protected String name;
    protected Area[] area;
    protected LocalTime starTime;
    protected LocalTime endTime;
    protected LocalDateTime registerStart;
    protected LocalDateTime registerEnd;
    protected User user;
    protected Boolean inClub = false;
    protected Area activeArea;

    public Subscription(String name, Area[] area, LocalTime starTime, LocalTime endTime, LocalDateTime registerStart, LocalDateTime registerEnd, User user, Boolean inClub, Area activeArea) {
        setName(name);
        setArea(area);
        setStarTime(starTime);
        setEndTime(endTime);
        setRegisterStart(registerStart);
        setRegisterEnd(registerEnd);
        setUser(user);
        setInClub(inClub);
        setActiveArea(activeArea);
    }
    public static Subscription getSubscription(String type, Area[] areas) {
        Subscription result = null;
        if("day".equals(type)){
            result = new Day(
                "Дневной абонемент",
                    areas,
                    LocalTime.of(8, 00, 00),
                    LocalTime.of(18, 00, 00),
                    null,
                    null,
                    null,
                    false,
                    null
            );
        } else if("full".equals(type)){
            result = new Full(
                    "Полный абонемент",
                    areas,
                    LocalTime.of(8, 00, 00),
                    LocalTime.of(22, 00, 00),
                    null,
                    null,
                    null,
                    false,
                    null
            );
        } else if("one".equals(type)){
            result = new OneTime(
                    "Разовый абонемент",
                    areas,
                    LocalTime.of(8, 00, 00),
                    LocalTime.of(22, 00, 00),
                    null,
                    null,
                    null,
                    false,
                    null
            );
        }
        return result;
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

    public Area[] getArea() {
        return area;
    }

    public void setArea(Area[] area) {
        this.area = area;
    }

    public LocalTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalTime starTime) {
        this.starTime = starTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getRegisterStart() {
        return registerStart;
    }

    public void setRegisterStart(LocalDateTime registerStart) {
        this.registerStart = registerStart;
    }

    public LocalDateTime getRegisterEnd() {
        return registerEnd;
    }

    public void setRegisterEnd(LocalDateTime registerEnd) {
        this.registerEnd = registerEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getInClub() {
        return inClub;
    }

    public void setInClub(Boolean inClub) {
        this.inClub = inClub;
    }

    public Area getActiveArea() {
        return activeArea;
    }

    public void setActiveArea(Area activeArea) {
        this.activeArea = activeArea;
    }

    @Override
    public void register(User user) {
        // Random rand = new Random();
        // LocalDateTime date = LocalDateTime.now();
        // date = date.plusDays(rand.nextInt(31)).plusHours(rand.nextInt(12)).plusMinutes(60);
        this.setUser(user);
        // this.setRegisterStart(date);
        // date = date.plusYears(1);
        // this.setRegisterEnd(date);
    }

    @Override
    public String toString() {
        return "\nSubscription{" +
                "\nname='" + name + '\'' +
                ",\n area=" + Arrays.toString(area) +
                ",\n starTime=" + starTime +
                ",\n endTime=" + endTime +
                ",\n registerStart=" + registerStart +
                ",\n registerEnd=" + registerEnd +
                ",\n user=" + user +
                ",\n inClub=" + inClub +
                ",\n activeArea=" + activeArea +
                '}';
    }
}
