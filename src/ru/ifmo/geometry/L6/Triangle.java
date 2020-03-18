package ru.ifmo.geometry.L6;

public class Triangle {
    private double[] p1 = {0,0};
    private double[] p2 = {1,0};
    private double[] p3 = {0,1};
    private double[] sides = {0, 0, 0};
    private double area; //площпдь
    private double perimetr; //периметр

    private double[] getSides(double[] p1, double[] p2, double[] p3) {
        sides[0] = Math.abs(Math.sqrt(Math.pow((p2[0] - p1[0]), 2) + Math.pow((p2[1] - p1[1]), 2)));
        sides[1] = Math.abs(Math.sqrt(Math.pow((p3[0] - p2[0]), 2) + Math.pow((p3[1] - p2[1]), 2)));
        sides[2] = Math.abs(Math.sqrt(Math.pow((p1[0] - p3[0]), 2) + Math.pow((p1[1] - p3[1]), 2)));
        return sides;
    }

    public double getPerimetr(double[] p1, double[] p2, double[] p3){
        sides = getSides(p1, p2, p3);
        perimetr = sides[0] + sides[1] + sides[2];
        return perimetr;
    }

    public double getArea(double[] p1, double[] p2, double[] p3){
        sides = getSides(p1, p2, p3);
        double halfSides = (sides[0] + sides[1] + sides[2])/2;
        area = Math.sqrt(halfSides * (halfSides - sides[0]) * (halfSides - sides[1]) * (halfSides - sides[2]));
        return area;
    }

}
