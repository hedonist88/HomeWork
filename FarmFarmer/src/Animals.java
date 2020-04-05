package ru.ifmo.farm;

abstract class Animals {
    protected String name = "Животное";
    protected int weight = 1;
    protected int speed = 0;

    public Animals(String name, int weight, int speed) {
        setName(name);
        setWeight(weight);
        setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && name.length() > 3) {
            this.name = name;
        } else {
            System.out.println("Имя больше 3 символов");
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if(weight > 0 && weight < 1000) {
            this.weight = weight;
        } else {
            System.out.println("Вес должен быть больше 0 и вы видели зайца больше тонны?");
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed > 0 && speed < 100) {
            this.speed = speed;
        } else {
            System.out.println("Скорость больше 0 и до 100");
        }
    }

}
