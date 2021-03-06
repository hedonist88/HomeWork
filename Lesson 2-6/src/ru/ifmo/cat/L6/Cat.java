package ru.ifmo.cat.L6;

public class Cat {
    private String name;
    private int weight;
    private int age;
    private String color;
    private int health;
    private int power;
    private String adress;

    public Cat(String name) {
        setName(name);
    }

    public Cat(String name, int health, int age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public Cat(int weight, String color) {
        this.name = name;
        this.color = color;
    }

    public Cat(int weight, String color, String adress) {
        this.weight = weight;
        this.color = color;
        this.adress = adress;
    }

    public Cat(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!"null".equals(name) && name.length() > 0) {
            this.name = name;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void viewInfo() {
        System.out.println(getName() + " " + getAge() + " " + getPower());
    }

    public void fightCat(Cat enemyCat) {
        health -= enemyCat.getPower();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", health=" + health +
                ", power=" + power +
                ", adress='" + adress + '\'' +
                '}';
    }
}
