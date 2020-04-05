package ru.ifmo.farm;

import java.util.Date;
import java.util.Random;

public class Farmer {
    private int amountResources = 5;

    public Farmer(int amountResources) {
        setAmountResources(amountResources);
    }

    public void farmDay(Farmer farmer, Pets[] farmAnimals, Wild[] wildAnimals) {
        // Фермер тратит 2 единицы ресурсов
        spendRes(farmer, 2);
        System.out.println("\n// Текущие ресурсы: " + farmer.getAmountResources() + " ед. //");

        // Приходит животное из леса
        Random random = new Random();
        int i = random.nextInt(wildAnimals.length);
        Boolean defense = kickOut(farmer, wildAnimals[i]);
        if(defense == false) {
            // Если прорвалось, выбирает жертву
            while (true) {
                int j = random.nextInt(farmAnimals.length);
                if(farmAnimals[j].getHealth() > 0 && farmAnimals[j].getOnFarm() == true) {
                    farmAnimals[j].run(wildAnimals[i]);
                    break;
                }
            }
        }

        // Фермер кормит животных
        for(Pets a:farmAnimals){
            farmFeed(farmer, a);
        }

        // Фермер доит животных
        int out = 0;
        for(Pets a:farmAnimals){
            if(a.getOnFarm() == true && a.getHealth() > 0 && a.getHaveRes() == true) {
                getRes(farmer, a);
                out++;
            }
        }
        // Едим животное
        if(out == 0){
            for(Pets a:farmAnimals){
                if(a.getOnFarm() == true && a.getHealth() > 0 && a.getHaveRes() == false) {
                    eatAnimal(farmer, a);
                    break;
                }
            }
        }
    }

    public void eatAnimal(Farmer farmer, Pets animal){
        animal.eaten(farmer);
    }

    public void getRes(Farmer farmer, Pets animal){
         animal.giveResource(farmer);
    }

    public void spendRes(Farmer farmer, int amount) {
        farmer.setAmountResources(farmer.getAmountResources() - amount);
    }

    public boolean kickOut(Farmer farmer, Wild wildAnimal) {
        Boolean result = true;
        if(wildAnimal.getCountAttack() < 3) {
            if (wildAnimal.goAway() == false) {
                result = false;
            } else {
                wildAnimal.setCountAttack(wildAnimal.getCountAttack() + 1);
                System.out.println(wildAnimal.getCountAttack() + " раз");
            }
        }
        return result;
    }

    public void farmFeed(Farmer farmer, Pets animal) {
        if(animal.getOnFarm() == true && animal.getHealth() > 0) {
            Random r = new Random();
            int e = r.nextInt(3) + 1;
            animal.addHealth(animal, e);
        }
    }

    public int getAmountResources() {
        return amountResources;
    }

    public void setAmountResources(int amountResources) {
        this.amountResources = amountResources;
    }

}