package game;

import battlefield.Battlefield;
import battleunit.BattleUnit;
import battleunit.Ship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SeaBattle implements Start {
    ArrayList<BattleUnit> units;
    ArrayList<BattleUnit> unitsTwo;
    Battlefield battlefield;
    Battlefield battlefieldTwo;

    public void seaBattle(){}

    public static ArrayList<BattleUnit> generateUnits (Battlefield battlefield, String type){
        ArrayList<BattleUnit> units = new ArrayList<>();
        int id = 0;
        if(type.equals("ship")) {
            // Сортируем от большего к меньшему
            Arrays.sort(BattleUnit.UNITS_SIZES, (v1, v2) -> Integer.compare(v2[1], v1[1]));
            // генерируем корабли
            int c = 0;
            for(int i = 0; i < BattleUnit.UNITS_SIZES.length; i++){
                for (int j = 0; j < BattleUnit.UNITS_SIZES[i][0]; j++){
                    String orientation = BattleUnit.randOrientation();
                    String size = Integer.toString(BattleUnit.UNITS_SIZES[i][1]);
                    String startPos = BattleUnit.getFreeArea(battlefield, BattleUnit.UNITS_SIZES[i][1], orientation);
                    units.add(id, BattleUnit.getBattleUnit(
                            "ship",
                            id,
                            new String[]{size, startPos, orientation},
                            battlefield
                    ));
                    id++;
                    c++;
                }
            }
        }
        return units;
    }

    @Override
    public void startGame() {
        System.out.println("========= Поле противника =========");
        battlefield = Battlefield.getBattlefield();
        units = generateUnits(battlefield, "ship");
        battlefield.setUnits(units);
        battlefield.printBattleField();

        System.out.println("\n\n========= Поле игрока =========");
        battlefieldTwo = Battlefield.getBattlefield();
        unitsTwo = generateUnits(battlefieldTwo, "ship");
        battlefield.setUnits(unitsTwo);
        battlefieldTwo.printBattleField();

        battlefield.shoot("2;2");
        battlefield.shoot("2;4");
        battlefield.shoot("2;5");
        battlefield.shoot("3;4");
        battlefield.shoot("8;2");
        battlefield.shoot("9;9");

        battlefield.printBattleField();


    }
}
