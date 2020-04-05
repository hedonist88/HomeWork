package ru.ifmo.farm;

public class Rabbit extends Pets {

    public Rabbit(String name, int weight, int speed, int health, Boolean onFarm, Boolean haveRes) {
        super(name, weight, speed, health, onFarm, haveRes);
    }

    @Override
    public void setWeight(int weight) {
        if(weight > 0 && weight < 5) {
            this.weight = weight;
        }
    }

    @Override
    public void giveResource(Farmer farmer) {}

    @Override
    public void eaten(Farmer farmer) {
        farmer.setAmountResources(farmer.getAmountResources() + this.getWeight());
        this.health = 0;
        this.setOnFarm(false);
        System.out.println("(°□°) Фермер съел кролика");
    }

    @Override
    public String toString() {
        return "\nRabbit{" +
                "health=" + health +
                ", onFarm=" + onFarm +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }
}
