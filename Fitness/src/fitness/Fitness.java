package fitness;

import logger.Logger;
import subscription.Subscription;
import user.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Arrays;
import java.util.Random;

public class Fitness {

    public static int MINYEAR = 18;
    public static int MAXYEAR = 120;
    public static final int OPEN_HOUR = 8;
    public static final int OPEN_MINUT = 0;
    public static final int CLOSE_HOUR = 22;
    public static final int CLOSE_MINUT = 0;
    protected Subscription[] subscriptions;
    protected Area[] areas;

    public Fitness() {};

    public Fitness(Subscription[] subscriptions, Area[] areas) {
        setSubscriptions(subscriptions);
        setAreas(areas);
    }

    public void getReport(Fitness fitness) {
        for (Area a:fitness.getAreas()){
            System.out.println("*********************************************************");
            System.out.println("Отчет посетителей по зоне " + a.getName());
            String string = getAreaReport(fitness.getSubscriptions(), a.getName());
            String[] arr = string.split(";");
            Arrays.sort(arr);
            for (String str:arr){
                System.out.println(str);
            };
            System.out.println("\n");
        }
    }

    public String getAreaReport(Subscription[] subscriptions, String name) {
        String result = "";
        for (Subscription s:subscriptions){
            Area activeArea = s.getActiveArea();
            if(activeArea != null) {
                if(name.equals(activeArea.getName())) {
                      result += s.getUser().getLastName() + " " + s.getUser().getName() + ";";
                }
            }
        }
        return result;
    }

    public void workDay(User user, Subscription[] subscriptions, Area[] areas, LocalDateTime currTime){
        Random rand = new Random();
        Boolean accessDenied = false;
        Area validArea = null;
        Subscription validSbscr = null;
        Boolean validTime = false;

        System.out.println(currTime.format(Logger.formatter));

        // Проверяем есть ли активный абонемент
        validSbscr = validate(user, subscriptions, currTime);
        if(validSbscr == null){
            System.out.println(currTime.format(Logger.formatter) + " Нет абонемента оформленного на вас");
            accessDenied = true;
        }

        // Слушаем куда хочет пользователь
        if (accessDenied == false) {
            validArea = validateArea(validSbscr, areas[rand.nextInt(areas.length)]);
            if(validArea == null) {
                System.out.println(currTime.format(Logger.formatter) + " Ваш абонемент не подходит в данную зону");
                System.out.println("Доступные зоны:");
                for (Area a:validSbscr.getArea()) {
                    System.out.println("-" + a.getName());
                }
                accessDenied = true;
            }
            if(validArea != null && validArea.getAreaCount() >= 20) {
                System.out.println(currTime.format(Logger.formatter) + " Увы, зона переполнена, не более 20 человек");
                accessDenied = true;
            }
        }

        // Проверяем время
        if (accessDenied == false) {
            validTime = validateTime(validSbscr, currTime);
            if(validTime == false) {
                System.out.println(currTime.format(Logger.formatter) + " Ваш абонемент не подходит по времени или просрочен");
                accessDenied = true;
            }
        }

        // Если все хорошо
        if(accessDenied == false && validArea != null && validTime != false) {
            validSbscr.setInClub(true);
            validSbscr.setActiveArea(validArea);
            validArea.setAreaCount(validArea.getAreaCount() + 1);
            user.setInClub(true);
            System.out.println("Добро пожаловать, " + user.getName() + " "  + user.getLastName() + "!");
        }
        System.out.println("---------------------------------------------------------");

    }

    private Subscription validate(User user, Subscription[] subscriptions, LocalDateTime currTime) {
        for (Subscription s:subscriptions){
            if(s.getUser().equals(user)) {
                // При первом посещение активием абонемент
                if(s.getRegisterStart() == null) {
                    s.setRegisterStart(currTime.minusMinutes(1));
                    s.setRegisterEnd(currTime.plusYears(1));
                } else {
                    s.setRegisterStart(currTime.minusMinutes(1));
                }
                return s;
            }
        }
        return null;
    }

    private Area validateArea (Subscription subscription, Area area) {
        System.out.println("Вы хотите попасть в " + area.getName());
        for (Area a:subscription.getArea()) {
            if(a.equals(area)) {
                return a;
            }
        }
        return null;
    }

    private Boolean validateTime (Subscription subscription, LocalDateTime time) {
        System.out.println("Время абонемента с " + subscription.getStarTime() + " до " + subscription.getEndTime());
        Boolean result = false;
        if(time.isAfter(subscription.getRegisterStart()) && time.isBefore(subscription.getRegisterEnd())) {
            LocalTime cTime = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
            if(cTime.isAfter(subscription.getStarTime()) && cTime.isBefore(subscription.getEndTime())) {
                result = true;
            }
        }
        return result;
    }

    public void closeDay(Subscription[] subscriptions, Area[] areas) {
        // Выгоняем людей из зала с активированныи абонементами
        for (Subscription s:subscriptions){
            s.setInClub(false);
            s.setActiveArea(null);
            User user = s.getUser();
            user.setInClub(false);
        }
        for (Area a:areas){
            a.setAreaCount(0);
        }
    }

    public Subscription[] getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Subscription[] subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Area[] getAreas() {
        return areas;
    }

    public void setAreas(Area[] areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "Fitness{" +
                "subscriptions=" + Arrays.toString(subscriptions) +
                ", areas=" + Arrays.toString(areas) +
                '}';
    }
}
