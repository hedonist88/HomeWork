package task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
        // TODO:: дан список слов (words). Написать метод, который будет возвращать количество одинаковых слов с списке
        //  в виде Map<String, Integer>, где String - слово и Integer - количество повторений
 */

public class Task1 {
    private static List<String> words = new ArrayList<>();

    public static void main(String[] args) {
        words.add("may");
        words.add("august");
        words.add("june");
        words.add("may");
        words.add("may");
        words.add("may");
        words.add("may");
        words.add("may");
        words.add("may");
        words.add("may");
        words.add("july");
        words.add("may");
        words.add("august");
        words.add("august");

        System.out.println("=== Исходный список ===");
        for (String w:words){
            System.out.println(w);
        }

        System.out.println("\n=== Количество одинаковых слов с списке ===");

        for (Map.Entry<String, Integer> entry:getCountUniqWords(words).entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue() + " " + morphWord(entry.getValue(), "слово","слова","слов"));
        }
    }

    private static Map<String, Integer> getCountUniqWords(List<String> words){
        Map<String, Integer> result = new HashMap<>();
        for(String word:words){
            try {
                result.put(word, result.get(word) + 1);
            } catch (NullPointerException e){
                result.put(word, 0);
                result.put(word, result.get(word) + 1);
            }

        }
        return result;
    }

    public static String morphWord(int n, String w1, String w2, String w5)
    {
        n = Math.abs(n) % 100;
        int n1 = n % 10;
        if (n > 10 && n < 20) return w5;
        if (n1 > 1 && n1 < 5) return w2;
        if (n1 == 1) return w1;
        return w5;
    }

}

/* out */
/*
=== Исходный список ===
may
august
june
may
may
may
may
may
may
may
july
may
august
august

=== Количество одинаковых слов с списке ===
june: 1 слово
may: 9 слов
august: 3 слова
july: 1 слово
 */