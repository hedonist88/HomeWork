package battleunit;

import battlefield.Battlefield;
import battlefield.Pixel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class BattleUnit implements Damage {
    protected int unitId;
    protected Boolean death = false;
    protected String type;
    protected String[] params = new String[4];
    // 1 корабль с 4мя палубами
    // 2 корабля с 3мя палубами
    // 3 корабля с 2мя палубами
    // 4 корабля с 1й палубой
    // { count, size  }
    public static int[][] UNITS_SIZES = {{1,4},{4,1},{3,2},{2,3}};

    public BattleUnit(int unitId, Boolean death) {}

    /* === Main functions === */
    public static BattleUnit getBattleUnit(String type, int unitId, String[] params, Battlefield battlefield){
        BattleUnit battleUnit = null;
        if(type == "ship"){
            battleUnit = Ship.getShipUnit(unitId, params, battlefield);
            battleUnit.setType("Ship " + params[0]);
            battleUnit.setUnitId(unitId);
        }
        return battleUnit;
    }

    // Поиск доступных зон
    // Возвращаем String с координатами x;y
    public static String getFreeArea(Battlefield battlefield, int size, String orientation){
        String result = "";
        ArrayList<String> arr = new ArrayList<>();
        int iRes = 0;
        Boolean isFree = true;
        for(int i = 0; i < Battlefield.getFIELD_SIZE()[0]; i++){
            // скидываем, если не помещается по горизонтали
            if(orientation == "h" && (i + size) > Battlefield.getFIELD_SIZE()[0]){
                break;
            }
            for(int j = 0; j < Battlefield.getFIELD_SIZE()[1]; j++){
                // скидываем, если не помещается по вертикали
                if(orientation == "v" && (j + size) > Battlefield.getFIELD_SIZE()[1]){
                    break;
                }
                isFree = true;

                // Проверяем доступность полей юнита и территорию вокруг
                if("h".equals(orientation)){
                    for (int c = 0; c < size; c++){
                        if((i + c) == Battlefield.getFIELD_SIZE()[0]) break;

                        // для первого пикселя перед
                        if(c == 0 && !checkPixelStartArea(battlefield, orientation, battlefield.getPixels()[j][i + c])){
                            isFree = false;
                            break;
                        }
                        // провека самого пикселя
                        if(!battlefield.getPixels()[j][i + c].getAvailable()){
                            isFree = false;
                            break;
                        }
                        // проверим доступность территории
                        if(!checkPixelArea(battlefield, orientation, battlefield.getPixels()[j][i + c])){
                            isFree = false;
                            break;
                        }
                        // для последнего пикселя после
                        if(c == (size - 1) && !checkPixelEndArea(battlefield, orientation, battlefield.getPixels()[j][i + c])){
                            isFree = false;
                            break;
                        }
                    }
                } else if ("v".equals(orientation)){
                    for (int c = 0; c < size; c++){
                        if((j + c) == Battlefield.getFIELD_SIZE()[1]) break;

                        // для первого пикселя перед
                        if (c == 0 && !checkPixelStartArea(battlefield, orientation, battlefield.getPixels()[j + c][i])) {
                            isFree = false;
                            break;
                        }
                        // провека самого пикселя
                        if(!battlefield.getPixels()[j + c][i].getAvailable()) {
                            isFree = false;
                            break;
                        }
                        // проверим доступность территории
                        if(!checkPixelArea(battlefield, orientation, battlefield.getPixels()[j + c][i])){
                            isFree = false;
                            break;
                        }
                        // для последнего пикселя после
                        if (c == (size - 1) && !checkPixelEndArea(battlefield, orientation, battlefield.getPixels()[j + c][i])) {
                            isFree = false;
                            break;
                        }
                    }
                }

                if(isFree){
                    arr.add(iRes,
                            Integer.toString(battlefield.getPixels()[j][i].getCoords()[0])
                                    + ";"
                                    + Integer.toString(battlefield.getPixels()[j][i].getCoords()[1])
                    );
                    iRes ++;
                }
            }
        }
        Random rand = new Random();
        result = arr.get(rand.nextInt(arr.size()));
        return result;
    }

    /* === Secondary functions === */
    public static int getCountUnits() {
        int count = 0;
        for(int i = 0; i < BattleUnit.UNITS_SIZES.length; i++) {
            count +=  BattleUnit.UNITS_SIZES[i][0];
        }
        return count;
    }

    // Преобразователь координат из строки
    public static int[] strToInt(String string, String pattern){
        String[] arrStr = string.split(pattern);
        int[] arrInt = new int[arrStr.length];
        for (int i = 0; i < arrStr.length; i++){
            arrInt[i] = Integer.parseInt(arrStr[i]);
        }
        return arrInt;
    }

    // проверка пикселей перед юнитом
    private static Boolean checkPixelStartArea(Battlefield battlefield, String orientation, Pixel pixel){
        Boolean isFree = true;
        int istart = -1;
        int iend = 1;
        // проверка 3 пикселей в начале
        if("h".equals(orientation)){
            if(pixel.getCoords()[0] > 1){
                if(pixel.getCoords()[1] == 1){
                    istart = 0;
                } else if (pixel.getCoords()[1] == Battlefield.getFIELD_SIZE()[1]) {
                    iend = 0;
                }
                for(int i = istart; i <= iend; i++){
                    if(!battlefield.getPixels()[pixel.getCoords()[1] - 1 + i][pixel.getCoords()[0] - 2].getAvailable()){
                        isFree = false;
                    }
                }
            }
        } else if ("v".equals(orientation)){
            if(pixel.getCoords()[1] > 1){
                if(pixel.getCoords()[0] == 1){
                    istart = 0;
                } else if (pixel.getCoords()[0] == Battlefield.getFIELD_SIZE()[0]) {
                    iend = 0;
                }
                for(int i = istart; i <= iend; i++){
                    if(!battlefield.getPixels()[pixel.getCoords()[1] - 2][pixel.getCoords()[0] - 1 + i].getAvailable()){
                        isFree = false;
                    }
                }
            }
        }
        return isFree;
    }

    // проверка пикселей после юнита
    private static Boolean checkPixelEndArea(Battlefield battlefield, String orientation, Pixel pixel){
        Boolean isFree = true;
        int istart = -1;
        int iend = 1;
        // проверка 3 клетки в начале
        if("h".equals(orientation)){
            if(pixel.getCoords()[0] < Battlefield.getFIELD_SIZE()[0]){
                if(pixel.getCoords()[1] == 1){
                    istart = 0;
                } else if (pixel.getCoords()[1] == Battlefield.getFIELD_SIZE()[1]) {
                    iend = 0;
                }
                for(int i = istart; i <= iend; i++){
                    if(!battlefield.getPixels()[pixel.getCoords()[1] - 1 + i][pixel.getCoords()[0]].getAvailable()){
                        isFree = false;
                    }
                }
            }
        } else if ("v".equals(orientation)){
            if(pixel.getCoords()[1] < Battlefield.getFIELD_SIZE()[1]){
                if(pixel.getCoords()[0] == 1){
                    istart = 0;
                } else if (pixel.getCoords()[0] == Battlefield.getFIELD_SIZE()[0]) {
                    iend = 0;
                }
                for(int i = istart; i <= iend; i++){
                    if(!battlefield.getPixels()[pixel.getCoords()[1]][pixel.getCoords()[0] - 1 + i].getAvailable()){
                        isFree = false;
                    }
                }
            }
        }
        return isFree;
    }

    // проверка соседних пикселей
    private static Boolean checkPixelArea(Battlefield battlefield, String orientation, Pixel pixel){
        Boolean isFree = true;
        if("h".equals(orientation)){
            // проверка верх низ
            if(pixel.getCoords()[1] > 1){
                if(!battlefield.getPixels()[pixel.getCoords()[1] - 2][pixel.getCoords()[0] - 1].getAvailable()){
                    isFree = false;
                }
            }
            if(pixel.getCoords()[1] < Battlefield.getFIELD_SIZE()[1]) {
                if(!battlefield.getPixels()[pixel.getCoords()[1]][pixel.getCoords()[0] - 1].getAvailable()){
                    isFree = false;
                }
            }
        } else if ("v".equals(orientation)){
            // проверка лево право
            if(pixel.getCoords()[0] > 1){
                if(!battlefield.getPixels()[pixel.getCoords()[1] - 1][pixel.getCoords()[0] - 2].getAvailable()){
                    isFree = false;
                }
            }
            if(pixel.getCoords()[0] < Battlefield.getFIELD_SIZE()[0]) {
                if(!battlefield.getPixels()[pixel.getCoords()[1] - 1][pixel.getCoords()[0]].getAvailable()){
                    isFree = false;
                }
            }
        }
        return isFree;
    }

    // Рандомноя ориентация
    public static String randOrientation() {
        Random b = new Random();
        if(b.nextBoolean()) {
            return "v";
        } else {
            return "h";
        }
    }

    /* === GetSet === */
    public Boolean getDeath() {
        return death;
    }

    public void setDeath(Boolean death) {
        this.death = death;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[][] getUNITS_SIZES() {
        return UNITS_SIZES;
    }

    @Override
    public String toString() {
        return "BattleUnit{" +
                "unitId=" + unitId +
                ", death=" + death +
                ", type='" + type + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }

    @Override
    public void beAttacked() {}
}
