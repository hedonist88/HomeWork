package HomeWorkL3;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWorkL3 {
    public static void main(String[] args) {
        // Задача 1
        // Найти наибольший общий делитель (НОД) двух чисел
        int[] arr0  = {1555, 225};
        int tmpNum1 = 0;
        int tmpNum2 = 0;
        int tmpNum3 = 0;
        int s = 1;

        System.out.println("// Задача 1");

        Arrays.sort(arr0); //отсортируем точно определить большее
        if(arr0[0] != arr0[1]) {
            int num1 = arr0[0];
            int num2 = arr0[1];
            while (num1 != 0){
                tmpNum3 = num2 % num1;
                num2 = num1;
                num1 = tmpNum3;
            }
            System.out.println("НОД чисел " + arr0[1] + " и " + arr0[0] + " - " + num2);
        } else {
            System.out.println("Числа равны!");
        }


        // Задача 2
        // Дан массив целых чисел. Массив не отсортирован, числа могут повторяться. Необходимо найти в данном массиве
        // такие два числа n и m, чтобы их сумма была равна 7. Например, 2 + 5 = 7, 6 + 1 = 7, -2 + 9 = 7. Постарайтесь решить задачу наиболее оптимальным способом
        int[] arr2 = { -3, 2, 8, -12, 15, 1, 7, -5, 13, 10, 13, 1 };
        int tmp = 0;
        Boolean end = false;
        for (int i = 0; i < arr2.length; i++){
            tmp = arr2[i];
            for (int j = i + 1; j < arr2.length; j++){
                if((arr2[i] + arr2[j]) == 7) {
                    System.out.println("\n// Задача 2");
                    System.out.println("Числа " + arr2[i] + " и " + arr2[j] + " в сумме дают 7");
                    end = true;
                    break;
                }
            }
            if(end == true){
                break;
            }
        }

        // Задача 3
        // Заполните массив на N элементов случайным числами и выведете максимальное, минимальное и среднее значение.
        int[] arr3  = new int[25];
        int sum = 0;
        for (int i = 0; i < arr3.length; i++){
            arr3[i] = (int) (-98 + (Math.random() * 256));
            sum += arr3[i];
        }
        Arrays.sort(arr3);
        System.out.println("\n// Задача 3");
        System.out.println("Массив: " + Arrays.toString(arr3));
        System.out.println("Минимальное значение: " + arr3[0]);
        System.out.println("Максимальное значение: " + arr3[arr3.length - 1]);
        System.out.println("Среднее значение: " + sum/(arr3.length - 1)); //Среднее в плане арифметическое?

        // Задача 4
        // Пользователь вводит с клавиатуры натуральное число большее 3, которое сохраняется в переменную n.
        // Если пользователь ввёл не подходящее число, то программа должна просить пользователя повторить ввод. Создать массив из n случайных целых чисел из отрезка [0;n]
        // и вывести его на экран. Создать второй массив только из чётных элементов первого массива, если они там есть, и вывести его в консоль

        Scanner in = new Scanner(System.in);
        int[] arr4 = new int[5];
        int[] arr5 = new int[arr4.length];
        int n = 0;
        int m = 0;
        Boolean show = false;
        System.out.println("\n// Задача 4");
        System.out.println("Введите " + arr4.length + " целых чисел, больше 3, 0 — выход");
        while (true){
            int num = in.nextInt();
            if(num == 0){
                System.out.println("Выход из программы");
                break;
            }
            if(num > 3){
                arr4[n] = num;
                n++;
                if(arr4.length == n){
                    System.out.println("Закончилось место");
                    System.out.println("Вы ввели: " + Arrays.toString(arr4));
                    for (int i = 0; i < arr4.length; i++){
                        if(arr4[i] % 2 == 0) {
                            arr5[m] = arr4[i];
                            m++;
                            show = true;
                            continue; //четные числа
                        }
                    }
                    if(show == true) {
                        System.out.println("Четные числа из введенных: " + Arrays.toString(arr5));
                    } else {
                        System.out.println("Четные числа не введены");
                    }
                    break;
                }
            } else {
                System.out.println("Повторите ввод (>3)");
            }
        }

        // Задача 5
        // Создать двумерный массив из 5 строк по 8 столбцов в каждой из случайных целых чисел из отрезка [-99;99]. Вывести массив в консоль. После на отдельной строке вывести в консоль значение максимального элемента этого массива.
        int[][] arr6 = new int[5][8];
        int maxNum = -99;
        for (int i = 0; i < arr6.length; i++){
            for (int j = 0; j < arr6[i].length; j++){
                arr6[i][j] = (int) (-99 + (Math.random() * 198));
                if(maxNum < arr6[i][j]) maxNum = arr6[i][j];
            }
        }
        System.out.println("\n// Задача 5");
        System.out.println(Arrays.deepToString(arr6));
        System.out.println("Максимальное значение в массиве: " + maxNum);

    }

}
