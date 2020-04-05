package subscription;

import fitness.Area;
import user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class Full extends Subscription{

    public Full(String name, Area[] area, LocalTime starTime, LocalTime endTime, LocalDateTime registerStart, LocalDateTime registerEnd, User user, Boolean inClub, Area activeArea) {
        super(name, area, starTime, endTime, registerStart, registerEnd, user, inClub, activeArea);
    }

    @Override
    public void register(User user) {
        this.setUser(user);
    }


    @Override
    public String toString() {
        return "\nFull{" +
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
