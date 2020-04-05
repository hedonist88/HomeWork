package ru.ifmo.farm;

import java.util.Date;
import java.util.Random;

abstract class Wild extends Animals implements CanBeKickOut, EatAnimal {
    protected int power = 0;
    protected int countAttack = 0;

    public Wild(String name, int weight, int speed, int power, int countAttack) {
        super(name, weight, speed);
        setPower(power);
        setCountAttack(0);
    }

    public static Animals getWildAnimal(String type) {
        Animals wildAnimal = null;
        Random weightRandom = new Random(new Date().getTime());
        Random speedRandom = new Random(new Date().getTime());
        Random powerRandom = new Random(new Date().getTime());
        if("bear".equals(type)){
            wildAnimal = new Bear(
                    "Георгий",
                    weightRandom.nextInt(350) + 50,
                    speedRandom.nextInt(100),
                    powerRandom.nextInt(100),
                    0
            );
        } else if("wolf".equals(type)){
            wildAnimal = new Wolf(
                    "Василий",
                    weightRandom.nextInt(30) + 20,
                    speedRandom.nextInt(100),
                    powerRandom.nextInt(100),
                    0
            );
        } else if("fox".equals(type)){
            wildAnimal = new Fox(
                    "Рыжая зараза",
                    weightRandom.nextInt(30) + 20,
                    speedRandom.nextInt(100),
                    powerRandom.nextInt(100),
                    0
            );
        }
        return wildAnimal;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if(power > 0 && power < 100) {
            this.power = power;
        } else  {
            System.out.println("POWER больше 0 и до 100");
        }
    }

    public int getCountAttack() {
        return countAttack;
    }

    public void setCountAttack(int countAttack) {
        this.countAttack = countAttack;
    }

    @Override
    public boolean goAway() {
        Random b = new Random();
        if(b.nextBoolean() == true) {
            System.out.println("o=|===> Фермер прогнал захватчика");
            return true;
        } else {
            System.out.println("-> Захватчик прорвался");
            return false;
        }
    }

}
