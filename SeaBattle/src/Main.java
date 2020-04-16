import battlefield.Battlefield;
import battleunit.BattleUnit;
import game.SeaBattle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

public class Main {

    // !!!!!!!!!!!!!!!!!!!!
    // Морской бой еще в работе
    // Генерация кораблей готова
    // Атака и убиство корабля готова
    // не доделал саму схему игры
    public static void main(String[] args) {
        SeaBattle game = new SeaBattle();
        game.startGame();


    }

}

/* print out */
/*
========= Поле противника =========
        ░░ ░░ ░░ ░░ ░░ ░░ ┆┆ ██ ██ ██
        ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ┆┆ ██ ██ ┆┆ ██ ┆┆ ░░ ░░ ░░ ░░
        ┆┆ ┆┆ ┆┆ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ░░ ┆┆ ┆┆ ┆┆ ██ ┆┆ ██ ██ ██ ┆┆
        ░░ ┆┆ ██ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ░░ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ░░ ░░ ░░ ░░
        ░░ ┆┆ ██ ┆┆ ██ ┆┆ ░░ ░░ ░░ ░░
        ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ░░
        ██ ██ ┆┆ ┆┆ ██ ██ ┆┆ ██ ┆┆ ░░

        Легенда:
        ░░ — Пустое; ██ — Корабль; ╳╳ — Промах; ╋╋ — Уничтожено; ┆┆ — Территория корабля


        ========= Поле игрока =========
        ┆┆ ┆┆ ┆┆ ┆┆ ██ ┆┆ ┆┆ ██ ██ ██
        ██ ██ ┆┆ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ░░ ░░ ░░
        ██ ┆┆ ██ ██ ██ ██ ┆┆ ░░ ┆┆ ┆┆
        ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ░░ ┆┆ ██
        ┆┆ ┆┆ ┆┆ ░░ ░░ ░░ ░░ ░░ ┆┆ ┆┆
        ┆┆ ██ ┆┆ ░░ ░░ ░░ ░░ ░░ ┆┆ ██
        ┆┆ ┆┆ ┆┆ ┆┆ ░░ ░░ ░░ ░░ ┆┆ ██
        ░░ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ░░ ┆┆ ██
        ░░ ┆┆ ┆┆ ┆┆ ┆┆ ██ ┆┆ ░░ ┆┆ ┆┆

        Легенда:
        ░░ — Пустое; ██ — Корабль; ╳╳ — Промах; ╋╋ — Уничтожено; ┆┆ — Территория корабля
        Мимо!
        Мимо!
        Мимо!
        Мимо!
        Мимо!
        Мимо!
        ░░ ░░ ░░ ░░ ░░ ░░ ┆┆ ██ ██ ██
        ┆┆ ╳╳ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ╳╳ ┆┆ ┆┆
        ┆┆ ██ ██ ┆┆ ██ ┆┆ ░░ ░░ ░░ ░░
        ┆┆ ╳╳ ╳╳ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ░░ ╳╳ ┆┆ ┆┆ ██ ┆┆ ██ ██ ██ ┆┆
        ░░ ┆┆ ██ ┆┆ ██ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆
        ░░ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ░░ ░░ ░░ ░░
        ░░ ┆┆ ██ ┆┆ ██ ┆┆ ░░ ░░ ░░ ░░
        ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ┆┆ ╳╳ ░░
        ██ ██ ┆┆ ┆┆ ██ ██ ┆┆ ██ ┆┆ ░░

        Легенда:
        ░░ — Пустое; ██ — Корабль; ╳╳ — Промах; ╋╋ — Уничтожено; ┆┆ — Территория корабля

 */