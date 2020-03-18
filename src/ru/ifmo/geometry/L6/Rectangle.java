package ru.ifmo.geometry.L6;

public class Rectangle {
    private int[] p1 = {0,0};
    private int[] p2 = {1,1};
    private int area; //площадь
    private int perimetr; //периметр

    public int geArea(int[] p1, int[] p2){
        area = (int) ((p2[0] - p1[0]) * (p2[1] - p1[1]));
        return area;
    }

    public int getPerimetr(int[] p1, int[] p2){
        perimetr = (int) (2 * (p2[0] - p1[0]) + 2 * (p2[1] - p1[1]));
        return perimetr;
    }

    public int[] getP1() {
        return p1;
    }

    public void setP1(int[] p1) {
        this.p1 = p1;
    }

    public int[] getP2() {
        return p2;
    }

    public void setP2(int[] p2) {
        this.p2 = p2;
    }
}
