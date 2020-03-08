package HomeWorkL2;

import java.util.Scanner;

public class HomeWorkLesson2 {
    public static void main(String[] args) {
        /*
            Задача 1
            В переменной n хранится натуральное трёхзначное число. Вывсети в консоль сумму цифр числа n.
        */
        int num1, sum1, tmpnum;
        num1 = tmpnum = 847;
        sum1 = 0;
        while(tmpnum != 0){
            sum1 += (tmpnum % 10); //получаем остаток в десятичной системе
            tmpnum /= 10; //убираем разряд
        }
        System.out.println("// Задача 1");
        System.out.println("Число: " + num1);
        System.out.println("Сумма чисел: " + sum1);

        /*
            Задача 2
            Проверить является ли целое число записанное в переменную n, чётным либо нечётным. Результат вывсети в консоль.
         */
        int num2 = 842;
        System.out.println("// Задача 2");
        System.out.println("Число: " + num2);
        if(num2 % 2 == 0) {
            System.out.println("Число четное"); //четные числа
        } else {
            System.out.println("Число нечетное"); //четные числа
        }

        /*
            Задача 3
            Даны два целых числа n и m. Наименьшее из них сохранить в переменную res и вывести ее на экран.
        */
        int num3 = 354;
        int num4 = 127;
        String res;
        System.out.println("// Задача 3");
        System.out.println("Числа: " + num3 + ", " + num4);
        if(num3 == num4) {
            res = "Числа равны";
        } else if(num3 < num4) {
            res = "Число " + num3 + " меньше";
        } else {
            res = "Число " + num4 + " меньше";
        }
        System.out.println(res);

        /*
            Задача 4
            Написать код, который будет проверять попало ли случайно сгенерированное целое число из отрезка [5;122] в интервал (25;100) и выводить результат в консоль.
            Примеры работы программы:
            Число 113 не содержится в интервале (25,100)
            Число 72 содержится в интервале (25,100)
        */
        int leftNum = 25;
        int rightNum = 100;
        int randomNum = (int) (5 + (Math.random() * 123));
        System.out.println("// Задача 4");
        System.out.println("Сгенерированное число: " + randomNum);
        if(randomNum > leftNum && randomNum < rightNum) {
            System.out.println("Число " + randomNum + " входит в диапазон (" + leftNum + ", " + rightNum + ")");
        } else {
            System.out.println("Число " + randomNum + " не входит в диапазон (" + leftNum + ", " + rightNum + ")");
        }

        /*
            Задача 5
            Написать код, выводящий на экран случайно сгенерированное трёхзначное число и его наибольшую цифру.
            Примеры работы программы:
            В числе 208 наибольшая цифра 8
            В числе 774 наибольшая цифра 7
            В числе 613 наибольшая цифра 6
        */
        int randomNum2 = (int) (Math.random() * 728);
        int maxNumOld, maxNumNew, tmpnum2;
        tmpnum2 = randomNum2;
        maxNumNew = -1;
        System.out.println("// Задача 5");
        while(tmpnum2 != 0){
            if(maxNumNew == -1){
                maxNumNew = (tmpnum2 % 10);
                continue;
            }
            maxNumOld = (tmpnum2 % 10); //получаем остаток в десятичной системе
            if(maxNumOld > maxNumNew) maxNumNew = maxNumOld;
            tmpnum2 /= 10; //убираем разряд
        }
        System.out.println("В числе " + randomNum2 + " наибольшая цифра " + maxNumNew);

        /*
            Задача 6
            Создайте программу, выводящую на экран все четырёхзначные числа последовательности 1000 1003 1006 1009 1012 1015 ….
        */
        System.out.println("// Задача 6");
        for (int i = 1000; i <= 1015; i = i + 3){ // 9999 для сех чисел, мах для краткости
            System.out.println(i);
        }

        /*
            Задача 7
            Создайте программу, выводящую на экран первые 55 элементов последовательности 1 3 5 7 9 11 13 15 17 ….
        */
        System.out.println("// Задача 7");
        for (int i = 1; i <= 55 * 2; i = i + 2){
            System.out.println(i);
        }

        /*
            Задача 8
            Создайте программу, выводящую на экран все неотрицательные элементы последовательности 90 85 80 75 70 65 60 ….
        */
        System.out.println("// Задача 8");
        for (int i = 90; i >= 0; i = i - 5){
            System.out.println(i);
        }

        /*
            Задача 9
            Создайте программу, выводящую на экран первые 20 элементов последовательности 2 4 8 16 32 64 128 ….
        */
        System.out.println("// Задача 9");
        for (int i = 2; i <= Math.pow(2, 20) ; i = i * 2){
            System.out.println(i);
        }

        /*
            Задача 10
            Создайте программу, вычисляющую факториал натурального числа n, которое пользователь введёт с клавиатуры.
        */
        int factorial, tmpnum3, j;
        j = 1;
        factorial = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("// Задача 10");
        while (true){
            System.out.println("Введите целое число (0 для выхода)");
            int numEnter = in.nextInt();
            tmpnum3 = numEnter;
            if(numEnter < 0){
                System.out.println("Введите число больше нуля");
            } else {
                while (tmpnum3 != 0) {
                    factorial = factorial * j++;
                    tmpnum3--;
                }
                System.out.println("Факториал числа " + numEnter + " равен " + factorial);
                j = 1;
                factorial = 1;
            }
            if(numEnter == 0) break;
        }

        /*
            Задача 11
            Выведите на экран первые 11 членов последовательности Фибоначчи.
        */
        int iend = 11;
        int Fibonacci = 0;
        int num7 = 1;
        int num8 = 1;
        System.out.println("// Задача 11");
        System.out.println("Первые 11 членов последовательности Фибоначчи:");

        // первые 2 числа "1"
        System.out.println(num7);
        System.out.println(num8);
        for (int i = 3; i <= iend ; i++){
            Fibonacci = num7 + num8;
            num7 = num8;
            num8 = Fibonacci;
            System.out.println(Fibonacci);
        }

        /*
           Задача 12
           Электронные часы показывают время в формате от 00:00 до 23:59. Подсчитать сколько раз за сутки случается так,
           что слева от двоеточия показывается симметричная комбинация для той, что справа от двоеточия (например, 02:20, 11:11 или 15:51).
        */
        //int h1 = 0;
        //int h2 = 0;
        //int m1 = 0;
        //int m2 = 0;
        int result = 0;
        System.out.println("// Задача 12");
        System.out.println("Совпадения:");
        for (int h1 = 0 ; h1 <= 2; h1++){
            for (int h2 = 0; h2 <= 9 ; h2++){
                if(h1 == 2 && h2 > 3) break;
                for (int m1 = 0; m1 <= 5 ; m1++){
                    for (int m2 = 0; m2 <= 9 ; m2++){
                        if(h1 == m2 && h2 == m1){
                            result++;
                            System.out.println(h1 + "" + h2 + ":" + m1 + "" + m2);
                        }
                    }
                }
            }
        }
        System.out.println("Число симметричных повторений: " + result);

        // Более изящно
        int res1 = 0;
        for(int h = 0; h <= 23; h++){
            for(int m = 0; m<=59; m++){
                if(h/10%10 == m%10 && h%10 == m/10%10){
                    res1++;
                    System.out.println(h/10%10+ "" + h%10 + ":" + m/10%10 + "" + m%10);
                }
            }
        }
        System.out.println("Всего " + res1);

        //update --
    }
}
