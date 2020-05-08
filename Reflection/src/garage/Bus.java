package garage;

import Passengers.Passenger;

public class Bus extends Transport {
    private boolean wiFi;
    private String color;
    private String[] equip;
    Passenger[] passengers;

    public Bus(String departureSt, String destSt, String num, boolean wiFi, String color, String[] equip, Passenger[] passenger) {
        super(departureSt, destSt, num);
        setColor(color);
        setWiFi(wiFi);
        setEquip(equip);
        setPassengers(passenger);
    }

    public boolean isWiFi() {
        return wiFi;
    }

    public void setWiFi(boolean wiFi) {
        this.wiFi = wiFi;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getEquip() {
        return equip;
    }

    public void setEquip(String[] equip) {
        this.equip = equip;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    @Override
    public void repair() {
        System.out.println("Ремонт автобуса");
    }
}
