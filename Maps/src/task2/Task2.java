package task2;

import task1.Task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
        // TODO:: дана мапа (customerMap).
        //  Написать метод, который принимает на вход агрументы int from и int to и возвращает новую мапу,
        //  в которую войдут все покупатели, возраст которых находится в диапазоне [from, to)
        customerMap.put("1", new Customer("Павел", "1", 23));
        customerMap.put("2", new Customer("Олег", "2", 17));
        customerMap.put("3", new Customer("Максим", "3", 48));
        customerMap.put("4", new Customer("Евгения", "4", 67));
 */

public class Task2 {
    private static HashMap<String, Customer> customerMap = new HashMap<>();
    private static String[] names = {"Павел","Олег","Максим","Евгения"};

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 1; i < 32; i++){
            customerMap.put(Integer.toString(i),
                    new Customer(names[rand.nextInt(names.length)], Integer.toString(i), rand.nextInt(77) + 19));
        }

        System.out.println("=== Исходный список ===");
        for (Map.Entry<String, Customer> entry:customerMap.entrySet()){
            System.out.println(entry.getKey() + ". "
                    + entry.getValue().getName() + ", "
                    + entry.getValue().getAge() + " "
                    + Task1.morphWord(entry.getValue().getAge(), "год","года","лет"));
        }

        int from = 32;
        int to = 55;

        System.out.println("\n=== Отобранный список от "
                + from + " "
                + Task1.morphWord(from, "года","лет","лет")
                + " до "
                + to + " "
                + Task1.morphWord(to, "года","лет","лет") + " ===");

        for (Map.Entry<Integer, Customer> entry:getCustomersAgesRange(customerMap, from, to).entrySet()){
            System.out.println(entry.getKey() + ". "
                    + entry.getValue().getName() + ", "
                    + entry.getValue().getAge() + " "
                    + Task1.morphWord(entry.getValue().getAge(), "год","года","лет"));
        }

    }

    private static Map<Integer, Customer> getCustomersAgesRange(HashMap<String, Customer> customers, int from, int to){
        Map<Integer, Customer> result = new HashMap<>();
        if(from > to){
            System.out.println("Неверный интервал");
            return result;
        }
        int i = 1;
        for(Map.Entry<String, Customer> entry:customers.entrySet()){
            if(entry.getValue().getAge() >= from && entry.getValue().getAge() < to){
                result.put(i, entry.getValue());
                i++;
            }
        }
        return result;
    }
}

/*
=== Исходный список ===
22. Олег, 90 лет
23. Евгения, 59 лет
24. Павел, 33 года
25. Павел, 85 лет
26. Максим, 91 год
27. Павел, 69 лет
28. Олег, 66 лет
29. Павел, 45 лет
30. Максим, 45 лет
31. Павел, 26 лет
10. Павел, 49 лет
11. Максим, 82 года
12. Максим, 58 лет
13. Олег, 90 лет
14. Евгения, 90 лет
15. Евгения, 66 лет
16. Евгения, 88 лет
17. Евгения, 41 год
18. Максим, 91 год
19. Павел, 67 лет
1. Евгения, 30 лет
2. Евгения, 74 года
3. Максим, 28 лет
4. Евгения, 73 года
5. Максим, 60 лет
6. Олег, 26 лет
7. Максим, 73 года
8. Павел, 84 года
9. Евгения, 95 лет
20. Павел, 69 лет
21. Максим, 50 лет

=== Отобранный список от 32 лет до 55 лет ===
1. Павел, 33 года
2. Павел, 45 лет
3. Максим, 45 лет
4. Павел, 49 лет
5. Евгения, 41 год
6. Максим, 50 лет
 */