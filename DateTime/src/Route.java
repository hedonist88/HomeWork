import java.time.LocalTime;

public class Route {
    private String name;
    private String dateZone;
    private LocalTime travelTime;
    private LocalTime waitTime;

    public Route(String name, String dateZone, LocalTime travelTime, LocalTime waitTime) {
        setName(name);
        setDateZone(dateZone);
        setTravelTime(travelTime);
        setWaitTime(waitTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateZone() {
        return dateZone;
    }

    public void setDateZone(String dateZone) {
        this.dateZone = dateZone;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public LocalTime getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(LocalTime waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", dateZone='" + dateZone + '\'' +
                ", travelTime=" + travelTime +
                ", waitTime=" + waitTime +
                '}';
    }
}
