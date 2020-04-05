package ru.ifmo.farm;

public class Cow extends Pets {
    private int milk = 5;

    public Cow(String name, int weight, int speed, int health, Boolean onFarm, Boolean haveRes) {
        super(name, weight, speed, health, onFarm, haveRes);
        setMilk(milk);
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    @Override
    public void setWeight(int weight) {
        if(weight > 0 && weight < 400) {
            this.weight = weight;
        }
    }

    @Override
    public void giveResource(Farmer farmer) {
        farmer.setAmountResources(farmer.getAmountResources() + milk);
        System.out.println("Коровка дает молоко +" + milk + ", всего " + farmer.getAmountResources());
    }

    @Override
    public void eaten(Farmer farmer) {
        farmer.setAmountResources(farmer.getAmountResources() + this.getWeight());
        this.health = 0;
        this.setOnFarm(false);
        System.out.println("(°□°) Фермер съел корову");
    }

    @Override
    public String toString() {
        return "\nCow{" +
                "milk=" + milk +
                ", health=" + health +
                ", onFarm=" + onFarm +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
