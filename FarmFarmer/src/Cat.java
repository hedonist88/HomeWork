package ru.ifmo.farm;

public class Cat extends Pets {

    public Cat(String name, int weight, int speed, int health, Boolean onFarm, Boolean haveRes) {
        super(name, weight, speed, health, onFarm, haveRes);
    }

    @Override
    public void setWeight(int weight) {
        if(weight > 0 && weight < 20) {
            this.weight = weight;
        }
    }

    @Override
    public void giveResource(Farmer farmer) {}

    @Override
    public void eaten(Farmer farmer) {}

    @Override
    public String toString() {
        return "\nCat{" +
                "health=" + health +
                ", onFarm=" + onFarm +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }



}
