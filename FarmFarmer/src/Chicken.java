package ru.ifmo.farm;

public class Chicken extends Pets implements
        GiveResouces,
        CanBeRun,
        CanBeEaten,
        AddHealth {
    private int egg = 1;

    public Chicken(String name, int weight, int speed, int health, Boolean onFarm, Boolean haveRes) {
        super(name, weight, speed, health, onFarm, haveRes);
        setEgg(egg);
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }

    @Override
    public void setWeight(int weight) {
        if(weight > 0 && weight < 10) {
            this.weight = weight;
        }
    }

    @Override
    public void giveResource(Farmer farmer) {
        farmer.setAmountResources(farmer.getAmountResources() + egg);
        System.out.println("Курочка дает яйца +" + egg + ", всего " + farmer.getAmountResources());
    }

    @Override
    public void eaten(Farmer farmer) {
        farmer.setAmountResources(farmer.getAmountResources() + this.getWeight());
        this.health = 0;
        this.setOnFarm(false);
        System.out.println("(°□°) Фермер съел курицу");
    }

    @Override
    public String toString() {
        return "\nChicken{" +
                "egg=" + egg +
                ", health=" + health +
                ", onFarm=" + onFarm +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
