package battleunit;

import battlefield.Battlefield;
import battlefield.Pixel;

import java.util.Arrays;
import java.util.Random;

public class Ship extends BattleUnit {
    int size;
    int[] startPos = new int[2];
    String orientation;
    Pixel[][] pixels;

    public Ship(int unitId, int size, int[] startPos, String orientation, Boolean death) {
        super(unitId, death);
        setSize(size);
        setStartPos(startPos);
        setOrientation(orientation);
    }

    public Ship(int unitId, int size, int[] startPos, String orientation, Pixel[][] pixels, Boolean death) {
        super(unitId, death);
        setSize(size);
        setStartPos(startPos);
        setOrientation(orientation);
        setPixels(pixels);
    }

    /* === Main functions === */
    // params { size, startPos, orientation}
    public static Ship getShipUnit(int unitId, String[] params, Battlefield battlefield){
        int size =  Integer.parseInt(params[0]);
        Pixel[][] pixels = new Pixel[size][1];
        int[] startCoords = strToInt(params[1],";");

        int x = startCoords[0];
        int y = startCoords[1];
        String orientation = params[2];

        for (int i = 0; i < size; i++){
            pixels[i][0] = battlefield.getPixels()[y - 1][x - 1];
            pixels[i][0].setHealth(true);
            pixels[i][0].setType("unitPixel");
            pixels[i][0].setAvailable(false);
            pixels[i][0].setObjId(unitId);
            if("h".equals(orientation)) {
                x++;
            } else if("v".equals(orientation)) {
                y++;
            }
        }

        Ship ship = new Ship(
                unitId,
                size,
                startCoords,
                params[2],
                pixels,
                false
        );

        setShipArea(ship, battlefield);
        return ship;
    }

    /* === Secondary functions === */
    private static void setShipArea(Ship ship, Battlefield battlefield) {
        for (Pixel[] p:ship.getPixels()){

            int istart = -1;
            int iend = 1;

            if(p[0].getCoords()[0] == 1){
                istart = 0;
            } else if (p[0].getCoords()[0] == Battlefield.getFIELD_SIZE()[1]) {
                iend = 0;
            }
            for(int i = istart; i <= iend; i++){
                if(battlefield.getPixels()[p[0].getCoords()[1] - 1][p[0].getCoords()[0] - 1 + i].getType() != "unitPixel"){
                    battlefield.getPixels()[p[0].getCoords()[1] - 1][p[0].getCoords()[0] - 1 + i].setType("unitAreaPixel");
                }
                if(p[0].getCoords()[1] > 1 &&
                        p[0].getCoords()[0] > 1 &&
                        battlefield.getPixels()[p[0].getCoords()[1] - 2][p[0].getCoords()[0] - 1 + i].getType() != "unitPixel"
                ) {
                    battlefield.getPixels()[p[0].getCoords()[1] - 2][p[0].getCoords()[0] - 1 + i].setType("unitAreaPixel");
                }
                if(p[0].getCoords()[1] < Battlefield.getFIELD_SIZE()[1] &&
                        p[0].getCoords()[0] < Battlefield.getFIELD_SIZE()[0] &&
                        battlefield.getPixels()[p[0].getCoords()[1]][p[0].getCoords()[0] - 1 + i].getType() != "unitPixel"
                ) {
                    battlefield.getPixels()[p[0].getCoords()[1]][p[0].getCoords()[0] - 1 + i].setType("unitAreaPixel");
                }
            }

            istart = -1;
            iend = 1;

            if(p[0].getCoords()[1] == 1){
                istart = 0;
            } else if (p[0].getCoords()[1] == Battlefield.getFIELD_SIZE()[1]) {
                iend = 0;
            }
            for(int i = istart; i <= iend; i++){
                if(battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0] - 1].getType() != "unitPixel"){
                    battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0] - 1].setType("unitAreaPixel");
                }
                if(p[0].getCoords()[1] > 1 &&
                        p[0].getCoords()[0] > 1 &&
                        battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0] - 2].getType() != "unitPixel"
                ) {
                    battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0] - 2].setType("unitAreaPixel");
                }
                if(p[0].getCoords()[1] < Battlefield.getFIELD_SIZE()[1] &&
                        p[0].getCoords()[0] < Battlefield.getFIELD_SIZE()[0] &&
                        battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0]].getType() != "unitPixel"
                ) {
                    battlefield.getPixels()[p[0].getCoords()[1] - 1 + i][p[0].getCoords()[0]].setType("unitAreaPixel");
                }
            }
        }
    }

    /* === GetSet === */
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        if(size > 0) {
            this.size = size;
        }
    }

    public int[] getStartPos() {
        return this.startPos;
    }

    public void setStartPos(int[] startPos) {
        if (
                startPos[0] > 0 &&
                startPos[0] <= Battlefield.getFIELD_SIZE()[0] &&
                startPos[1] > 0 &&
                startPos[1] <= Battlefield.getFIELD_SIZE()[1]
        ) {
            this.startPos = startPos;
        } else {
            System.out.println("Стартовая позиция должна быть на поле боя");
        }
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    @Override
    public String toString() {
        return "\nShip{" +
                "\n  size=" + size +
                ", \n  startPos=" + Arrays.toString(startPos) +
                ", \n  orientation='" + orientation + '\'' +
                ", \n  pixels=" + Arrays.deepToString(pixels) +
                ", \n  unitId=" + unitId +
                ", \n  death=" + death +
                ", \n  type='" + type + '\'' +
                '}';
    }

    @Override
    public void beAttacked() {
        Boolean isDead = true;
        for(Pixel[] p:this.getPixels()){
            if(p[0].getHealth() == true){
                isDead = false;
                break;
            }
        }
        if(isDead == true) {
            this.setDeath(true);
        }
    }
}
