package ru.ifmo.geometry.L6;

public class Main {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        double r = 3;
        System.out.println(circle1.getArea(r));
        System.out.println(circle1.getLC(r));

        Rectangle rectangle1 = new Rectangle();
        int[] p1 = {0,0};
        int[] p2 = {3,2};
        System.out.println(rectangle1.geArea(p1, p2));
        System.out.println(rectangle1.getPerimetr(p1, p2));

        Triangle triangle1 = new Triangle();
        double[] p1_1 = {0,0};
        double[] p1_2 = {3,2};
        double[] p1_3 = {1,20};
        System.out.println(triangle1.getPerimetr(p1_1, p1_2, p1_3));
        System.out.println(triangle1.getArea(p1_1, p1_2, p1_3));

    }
}
