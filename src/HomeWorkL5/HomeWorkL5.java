package HomeWorkL5;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWorkL5 {
    public static void main(String[] args) {
        // Задача 1
        // Даны 2 слова, состоящие из четного числа букв. Получить слово состоящее из первой половины первого слова
        // и второй половины второго слова.
        String word1 = "йцукен";
        String word2 = "qwertyui";
        String result = "";
        String[] word1arr = word1.split("");
        String[] word2arr = word2.split("");

        for(int i = 0; i <= word1arr.length/2 - 1; i++){
            result += word1arr[i];
        }
        for(int i = word2arr.length/2; i <=  word2arr.length - 1; i++){
            result += word2arr[i];
        }
        System.out.println("// Задача 1");
        System.out.println("Склееное слово: " + result);
        // !!! Но зачем все это, когда есть substring !!!
        System.out.println("Склееное слово через substring: " + word1.substring(0, word1.length()/2) + word2.substring(word2.length()/2, word2.length()));


        // Задача 2
        // Найдите самое длинное слово в предложении, при условии, что в предложении все слова разной длины.
        String text1 = "В этом разделе можно найти информацию и рекомендации ВОЗ в связи с нынешней вспышкой коронавирусной инфекции COVID-19, которая была впервые зарегистрирована 31 декабря 2019 г. в г. Ухань, Китай.";
        String[] text1arr = text1.split("\\s");
        result = "";
        for(int i = 0; i <= text1arr.length - 1; i++){
            if(text1arr[i].length() > result.length()){
                result = text1arr[i];
            }
        }
        System.out.println("\n// Задача 2");
        System.out.println("Самое длинное слово в предложении: '" + result + "'");


        // Задача 3
        // Имеются две строки. Найти количество вхождений одной строки в другую.
        // !!! Не совсем четкая задача
        String text2 = "Следите за новейшей информацией о вспышке COVID-19, которую можно найти на веб-сайте ВОЗ, а также получить от органов общественного здравоохранения вашей страны и населенного пункта. Наибольшее число случаев COVID-19 по-прежнему выявлено в Китае, тогда как в других странах отмечаются вспышки локального характера.";
        String text3 = "COVID-19";
        String[] strings = text2.split(text3);
        System.out.println("\n// Задача 3");
        System.out.println("Количество ахождений одной строки в другую: " + (strings.length - 1));


        // Задача 4
        // Написать функцию, которая проверяет, является ли строка палиндромом. Палиндром — это слово или фраза,
        // которые одинаково читаются по буквам или по словам как слева направо, так и справа налево.
        String text4 = "Лилипут сома на мосту пилил";
        System.out.println("\n// Задача 4");
        System.out.printf("Предложение: " + text4);
        text4 = text4.toLowerCase().replace(" ","");
        String[] strings4 = text4.split("");
        Boolean resultP = false;

        for(int i = 0; i <= strings4.length - 1; i++){
            // если нечетная количество символов
            if(i == strings4.length - 1 - i){
                break;
            }
            if(strings4[i].equals(strings4[strings4.length - 1 - i])){
                resultP = true;
            } else {
                resultP = false;
                break;
            }
        }
        System.out.println("\n");
        if(resultP == true){
            System.out.printf("Предложение является палиндромом!");
        } else {
            System.out.printf("Предложение не является палиндромом, увы :(");
        }


        // Задача 5
        // Даны два слова и словарь (массив слов). Необходимо найти алгоритм превращения в другое, меняя за один шаг одну
        // букву, причем каждое новое слово должно быть в словаре (массиве слов). Например,даны слова "hit" и "cog"
        // и словарь(массив слов) ["hot", "dot", "dog", "log", "lot"]. Один из вариантов цепочки: "hit"->"hot"->"dot"->"dog"->"cog".
        String word5 = "hit";
        String word6 = "cog";
        String tmpWord = "";
        String symbolStr = "";
        String[] arrWords = {"hot", "dot", "dog", "log", "lot"};
        System.out.println("\n\n// Задача 5");
        // отберем возможные символы
        for(int i = 0; i <= arrWords.length - 1; i++) {
            String[] tempArr = arrWords[i].split("");
            for (int j = 0; j <= tempArr.length - 1; j++) {
                symbolStr += tempArr[j];
            }
        }
        String[] symbols = symbolStr.split("");
        Arrays.sort(symbols);
        symbolStr = "";
        for(int i = 0; i <= symbols.length - 1; i++){
            if (symbolStr.endsWith(symbols[i]) == true){
                continue;
            } else {
                symbolStr += symbols[i];
            }
        }
        symbols = symbolStr.split("");
        // Теперь перебираем возможные слова
        /*
        tmpWord = word5;
        while (true){
            String[] tempArr = tmpWord.split("");
            for(int i = 0; i <= tempArr.length - 1; i++){
                for (int j = 0; j <= symbols.length - 1; j++) {

                }
            }
        }

        System.out.println(Arrays.toString(symbols));
        System.out.println(symbolStr);
         */

        // Задача 6
        // Пользователь вводит названия городов через пробел. Вы их присваиваете переменной. Переставьте названия так,
        // чтобы они были упорядочены по алфавиту.
        Scanner in = new Scanner(System.in);
        String input = "";
        String cities = "";
        System.out.println("\n// Задача 6");
        System.out.println("Введите названия городов через пробел, next — привести в порядок/выход");
        while (true) {
            input = in.nextLine();
            if(input.equals("next")){
                System.out.println("...");
                break;
            } else {
                cities = input;
            }
        }
        String[] text6arr = cities.split(" "); //Москва Питер Мурманск Владивосток Новгород Обнинск Архангельск Вырица Крыжопль Мухосранск
        if(text6arr.length > 1) { //Нам же нужно несколько городов
            Arrays.sort(text6arr);
            System.out.printf("Отсортированные города по алфавиту: ");
            for(int i = 0; i <= text6arr.length - 1; i++){
                System.out.printf(text6arr[i]);
                if(i != (text6arr.length - 1)) {
                    System.out.printf(", ");
                }
            }
        } else {
            System.out.printf("Пустая строка, либо нечего сортировать :(");
        }

    }
}
