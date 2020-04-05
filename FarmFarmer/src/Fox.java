package ru.ifmo.farm;

import java.util.Random;

public class Fox extends Wild {

    public Fox(String name, int weight, int speed, int power, int countAttack) {
        super(name, weight, speed, power, countAttack);
    }

    @Override
    public void setWeight(int weight) {
        if(weight > 0 && weight < 50) {
            this.weight = weight;
        }
    }

    @Override
    public boolean goAway() {
        Random b = new Random();
        if(b.nextBoolean() == true) {
            System.out.println("o=|===> Фермер прогнал лису");
            return true;
        } else {
            System.out.println("-> Лиса прорвалась");
            return false;
        }
    }

    @Override
    public void eat() {

    }

    @Override
    public String toString() {
        return "\nFox{" +
                "power=" + power +
                ", countAttack=" + countAttack +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }


}
