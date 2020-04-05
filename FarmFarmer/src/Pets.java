package ru.ifmo.farm;

import java.util.Date;
import java.util.Random;

abstract class Pets extends Animals implements CanBeRun, CanBeEaten, AddHealth, GiveResouces {
    protected int health = 0;
    protected int fullHealth = 0;
    protected Boolean onFarm = false;
    protected Boolean haveRes = false;

    public Pets(String name, int weight, int speed, int health, Boolean onFarm, Boolean haveRes) {
        super(name, weight, speed);
        setHealth(health);
        setOnFarm(onFarm);
        setHaveRes(haveRes);
    }

    public static Pets getPetAnimal(String type) {
        Pets petAnimal = null;
        Random weightRandom = new Random(new Date().getTime());
        Random speedRandom = new Random(new Date().getTime());
        Random powerRandom = new Random(new Date().getTime());
        Random healthRandom = new Random(new Date().getTime());
        Random resourceRandom = new Random(new Date().getTime());
        if("cat".equals(type)){
            petAnimal = new Cat(
                    "Котик",
                    weightRandom.nextInt(20),
                    speedRandom.nextInt(100),
                    healthRandom.nextInt(100),
                    true,
                    false
            );
        } else if("cow".equals(type)){
            petAnimal = new Cow(
                    "Коровка",
                    weightRandom.nextInt(200) + 100,
                    speedRandom.nextInt(100),
                    healthRandom.nextInt(100),
                    true,
                    true
            );
        } else if("chicken".equals(type)){
            petAnimal = new Chicken(
                    "Курочка",
                    weightRandom.nextInt(10) + 1,
                    speedRandom.nextInt(100),
                    healthRandom.nextInt(100),
                    true,
                    true
            );
        } else if("rabbit".equals(type)){
            petAnimal = new Rabbit(
                    "Кроличек",
                    weightRandom.nextInt(5) + 1,
                    speedRandom.nextInt(100),
                    healthRandom.nextInt(100),
                    true,
                    false
            );
        }
        petAnimal.setFullHealth(petAnimal.getHealth());
        return petAnimal;
    }

    public int getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(int fullHealth) {
        this.fullHealth = fullHealth;
    }

    public Boolean getOnFarm() {
        return onFarm;
    }

    public void setOnFarm(Boolean onFarm) {
        this.onFarm = onFarm;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        //if(health > 0 && health < 100) {
            this.health = health;
        //} else {
          //  System.out.println("HP больше 0 и до 100");
        //}
    }

    public Boolean getHaveRes() {
        return haveRes;
    }

    public void setHaveRes(Boolean haveRes) {
        this.haveRes = haveRes;
    }

    @Override
    public void run(Wild animal) {
        if(this.getSpeed() > animal.getSpeed()) {
            System.out.println("-> Животное убежало от хищника");
        } else {
            this.health -= animal.getPower();
            System.out.println("Хищник укусил животное " + this.getName() + " на " + animal.getPower() + "ед.");
            if(this.getHealth() < 0) {
                System.out.println("×_× Животное " + this.getName() + " померло :(\n");
                this.setOnFarm(false);
            }
        }
    }

    @Override
    public void addHealth(Pets animal, int energy) {
        if(animal instanceof Pets && energy > 0 && animal.getOnFarm() != false && animal.getHealth() > 0){
            if(animal.getFullHealth() >=  animal.getHealth() + energy){
                animal.setHealth(animal.getHealth() + energy);
                System.out.println("Накормили животное " + animal.getName() + " на " + energy + "HP (" + animal.getHealth() + "HP)");
            } else {
                animal.setHealth(animal.getFullHealth());
                System.out.println("Максимум здоровья у " + animal.getName() + " " + animal.getHealth() + "HP");
            }
        }
    }

    @Override
    public void eaten(Farmer farmer) {}
}
