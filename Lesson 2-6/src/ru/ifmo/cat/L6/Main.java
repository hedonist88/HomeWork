package ru.ifmo.cat.L6;

public class Main {
    public static void main(String[] args) {

        Cat[] cats = new Cat[6];

        for (int i = 0; i < cats.length - 1; i++) {
            cats[i] = new Cat("Ctg", 78, 128);
            /*cats[i].setName("Cat");
            cats[i].setPower((int)(Math.random() * 100) + 1);
            cats[i].setHealth((int)(Math.random() * 100) + 1);
            cats[i].setAdress("adress " + i);*/
        }

        for (int i = 0; i < cats.length - 1; i++) {
            cats[i].viewInfo();
        }

        cats[0].fightCat(cats[1]);
        System.out.println(cats[0].getHealth());
        cats[1].fightCat(cats[0]);
        System.out.println(cats[1].getHealth());

    }
}
