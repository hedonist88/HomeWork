package ru.ifmo.geometry.L6;

public class Circle {
    private int[] c = {0,0};
    private double r = 1;
    private double area; //площпдь
    private double lc; //длина окружности

    public double getArea(double r) {
        area = Math.PI * r * r;
        return area;
    }

    public double getLC(double r) {
        lc = 2 * Math.PI * r;
        return lc;
    }

    public int[] getC() {
        return c;
    }

    public void setC(int[] c) {
        this.c = c;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

}
