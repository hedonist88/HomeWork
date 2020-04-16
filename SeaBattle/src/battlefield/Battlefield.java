package battlefield;

import battleunit.BattleUnit;
import battleunit.Ship;

import java.util.ArrayList;
import java.util.Arrays;

import static battleunit.BattleUnit.strToInt;

public class Battlefield implements Shooting{
    private static final int[] FIELD_SIZE = {10,10};
    Pixel[][] pixels;
    ArrayList<BattleUnit> units;

    public Battlefield(){}
    public Battlefield(Pixel[][] pixels) {}

    public Battlefield(ArrayList<BattleUnit> units) {
        setUnits(units);
    }

    /* === Main functions === */
    public static Battlefield getBattlefield(){
        Battlefield battlefield = new Battlefield();
        Pixel[][] pixels = new Pixel[Battlefield.getFIELD_SIZE()[0]][ Battlefield.getFIELD_SIZE()[1]];
        for (int i = 0; i < Battlefield.getFIELD_SIZE()[0]; i++){
            for (int j = 0; j < Battlefield.getFIELD_SIZE()[1]; j++){
                int[] pixelCoords = {j + 1,i + 1};
                pixels[i][j] = new Pixel(pixelCoords, "fieldPixel", true);
            }
        }

        battlefield.setPixels(pixels);
        return battlefield;
    }

    public void printBattleField() {
        String render = "";
        for (int i = 0; i < Battlefield.getFIELD_SIZE()[0]; i++){
            for (int j = 0; j < Battlefield.getFIELD_SIZE()[1]; j++) {
                // система координат
                // render += Arrays.toString(this.getPixels()[i][j].getCoords());
                if (this.getPixels()[i][j].getType().equals("fieldPixel")) {
                    render += "░░ ";
                } else if (this.getPixels()[i][j].getType().equals("missPixel")) {
                    render += "╳╳ ";
                } else if (this.getPixels()[i][j].getType().equals("unitPixel")) {
                    render += "██ ";
                } else if (this.getPixels()[i][j].getType().equals("deathPixel")) {
                    render += "╋╋ ";
                } else if (this.getPixels()[i][j].getType().equals("unitAreaPixel")) {
                    render += "┆┆ ";
                }
            }
            render += "\n";
        }
        render += "\nЛегенда:";
        render += "\n░░ — Пустое; ██ — Корабль; ╳╳ — Промах; ╋╋ — Уничтожено; ┆┆ — Территория корабля";
        System.out.println(render);
    }

    /* === GetSet === */
    public static int[] getFIELD_SIZE() {
        return Battlefield.FIELD_SIZE;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public ArrayList<BattleUnit> getUnits() {
        return this.units;
    }

    public void setUnits(ArrayList<BattleUnit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "\n  Battlefield{" +
                "\n    pixels=" + Arrays.deepToString(pixels) +
                '}';
    }

    @Override
    public void shoot(String coords) {
        int[] attackCoords = strToInt(coords,";");
        int objId;
        Pixel pixel = this.getPixels()[attackCoords[1] - 1][attackCoords[0] - 1];

        if("unitPixel".equals(pixel.getType())){
            pixel.setType("deathPixel");
            pixel.setHealth(false);
            System.out.println("Попал!");

            objId = pixel.getObjId();
            BattleUnit unit = this.getUnits().get(objId);
            unit.beAttacked();

        } else{
            pixel.setType("missPixel");
            System.out.println("Мимо!");
        }
    }

}
