import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");
        ZonedDateTime arrivalTime;
        LocalDateTime time;
        Route[] route = new Route[4];

        // Сидней - Australia/Sydney - 16 июня 2020) в 19:00
        route[0] = new Route("Сидней", "Australia/Sydney", null, null);
        route[1] = new Route("Хьюстон", "America/Chicago", LocalTime.of(15,35), LocalTime.of(1,0));
        route[2] = new Route("Вашингтон", "America/New_York", LocalTime.of(2,49), LocalTime.of(1,10));
        route[3] = new Route("Оттава", "America/Toronto", LocalTime.of(1,40), null);

        time = LocalDateTime.of(2020, Month.JUNE, 16, 19, 00);
        arrivalTime = setZone(time, route[0].getDateZone());

        System.out.println("Время отправления " + formatter.format(arrivalTime));

        // Полетели
        for (Route r:route) {
            if(r.getTravelTime() != null) {
                LocalTime flightTime = r.getTravelTime();
                arrivalTime = arrivalTime.plusHours(flightTime.getHour()).plusMinutes(flightTime.getMinute());
                arrivalTime = setTimeZone(arrivalTime, r.getDateZone());
                System.out.println("=> Время прибытия в город " + r.getName() + " " + formatter.format(arrivalTime));
            }
            if(r.getWaitTime() != null) {
                LocalTime waitTime = r.getWaitTime();
                arrivalTime = arrivalTime.plusHours(waitTime.getHour()).plusMinutes(waitTime.getMinute());
                System.out.println("<= Время отправления из города " + r.getName() + " " + formatter.format(arrivalTime));
            }
        }

        System.out.println("Время прибытия в конечный пункт " + formatter.format(arrivalTime));

    }

    private static ZonedDateTime setZone(LocalDateTime time, String zone){
        ZonedDateTime ztime = time.atZone(ZoneId.of(zone));
        return ztime;
    }

    private static ZonedDateTime setTimeZone(ZonedDateTime time, String zone){
        ZonedDateTime ztime = time.withZoneSameInstant(ZoneId.of(zone));
        return ztime;
    }

}
