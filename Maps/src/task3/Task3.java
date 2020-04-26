package task3;

/*
// TODO:: Задания по тексту (text). На каждый пункт - минимум один метод:
//  1. написать метод, принимающий на вход слово и возвращающий частоту встречаемости данного слова в тексте
//  2. написать метод, который собирает все слова в группы по количеству букв:
//  например, в одну группу попадут слова: 3 - [the, war, jar, get, met...], в другую 2 - [on, up, no, of...]
//  3. написать метод, который выводит в консоль топ 10 самых частых слов
//  4. Вывести частоту встречаемости букв анг алфавита в данном тексте. Вывести в процентах для каждой буквы
*/

import task1.Task1;

import java.text.DecimalFormat;
import java.util.*;

public class Task3 {
    private static String text = "It is a uncover long established fact that a reader will be distracted uncover by the readable content of a page, " +
            "when looking at its layout The point of using uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters" +
            "as uncover opposed to still using Content here humour uncover content here making it look like readable English Many. desktop publishing " +
            "packages and web page editors now use Lorem Ipsum as their default model text and a search for lorem ipsum will " +
            "uncover many web sites still uncover in their infancy Various versions uncover have evolved over the years uncover sometimes by accident" +
            " sometimes on purpose injected humour and the like";
    // Думаю стоит вычистить предлоги
    private static final String[] EXCLUDEWORDS = {"a", "at", "on", "in", "about", "by", "for", "from", "of", "to", "with", "the", "and"};

    public static void main(String[] args) {
        List<String> words = Arrays.asList(clearPunctuation(text).split(" "));

        Random rand = new Random();
        String rWord = clearWords(words).get(rand.nextInt(clearWords(words).size()));

        System.out.println("\n=== Вхождение слова «" + rWord + "» в текст ===");
        System.out.println("Всего: " + getCountWord(words, rWord));

        System.out.println("\n=== Слова разбитые на группы по количеству букв ===");
        for (Map.Entry<Integer, ArrayList<String>> entry:getGroupLengthWords(words).entrySet()){
            System.out.println(entry.getKey() + " "
                    + Task1.morphWord(entry.getKey(), "символ","символа","символов") + " "
                    + entry.getValue().toString());
        }

        System.out.println("\n=== ТОП 10 слов ===");
        int i = 1;
        for (Map.Entry<String, Integer> entry:getTopWords(words)){
            if(i > 10) break;
            System.out.println(entry.getKey() + ": " + entry.getValue().toString() + " "
                    + Task1.morphWord(entry.getValue(), "раз","раза","раз"));
            i++;
        }

        System.out.println("\n=== Статистика по буквам ===");
        System.out.println("Всего букв: " + countChars(clearPunctuation(text)));
        for (Map.Entry<String, Double> entry:getCharStatistics(clearPunctuation(text))){
            System.out.println(entry.getKey() + ": " + df.format(entry.getValue()) + "%");
        }

    }

    private static int getCountWord(List<String> words, String word){
        int count = 0;
        if(word.contains(word)){
            for(String w:words) {
                if(word.equals(w)) count++;
            }
        }
        return count;
    }

    private static Map<Integer, ArrayList<String>> getGroupLengthWords(List<String> words){
        Map<Integer, ArrayList<String>> result = new HashMap<>();
        int index = 0;
        for(String w:clearWords(words)) {
            index = w.length();
            try {
                if(!result.get(index).contains(w)) {
                    result.get(index).add(w);
                }
            } catch (NullPointerException e){
                result.put(index, new ArrayList<>(Arrays.asList(w)));
            }
        }
        return result;
    }

    private static List<Map.Entry<String, Integer>> getTopWords(List<String> words){
        HashMap<String,Integer> arr = new HashMap<>();
        for(String w:clearWords(words)) {
            try {
                arr.put(w, arr.get(w) + 1);
            } catch (NullPointerException e){
                arr.put(w, 1);
            }
        }
        List<Map.Entry<String, Integer>> sorted = new LinkedList<Map.Entry<String, Integer> >(arr.entrySet());
        Collections.sort(sorted, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return -1 * (o1.getValue()).compareTo(o2.getValue());
            }
        });
        //sorted.addAll(arr.entrySet());
        return sorted;
    }

    private static List<Map.Entry<String, Double>> getCharStatistics(String text){
        if(text.length() == 0) return null;
        HashMap<String, Double> arr = new HashMap<>();
        double totalChars = (double) countChars(text);
        double percent = 0;
        for(String w:getChars(text)) {
            try {
                arr.put(w, arr.get(w) + (double) 1);
            } catch (NullPointerException e){
                arr.put(w, (double) 1);
            }
        }
        List<Map.Entry<String, Double>> sorted = new LinkedList<Map.Entry<String, Double> >(arr.entrySet());
        Collections.sort(sorted, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2)
            {
                return -1 * (o1.getValue()).compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, Double> entry:sorted){
            percent = (entry.getValue() * 100)/totalChars;
            entry.setValue(percent);
        }
        return sorted;
    }

    private static int countChars(String text){
        if(text.length() == 0) return 0;
        String[] result = clearText(text).split("");
        return result.length;
    }

    private static ArrayList<String> getChars(String text){
        if(text.length() == 0) return null;
        ArrayList<String> result = new ArrayList<>(Arrays.asList(clearText(text).split("")));
        return result;
    }

    private static String clearText(String text){
        return text.toLowerCase().replaceAll("\\p{P}","").replaceAll(" ","");
    }

    private static String clearPunctuation(String text){
        return text.toLowerCase().replaceAll("\\p{P}","");
    }

    private static List<String> clearWords(List<String> words){
        List<String> result = new ArrayList<>();
        Boolean check = false;
        for(String w:words) {
            check = false;
            for(int i = 0; i < EXCLUDEWORDS.length; i++){
                if(EXCLUDEWORDS[i].equals(w)){
                    check = true;
                    break;
                }
            }
            if(!check){
                result.add(w);
            }
        }
        return result;
    }

    private static DecimalFormat df = new DecimalFormat("0.00");

}

/* out */
/*
=== Вхождение слова «lorem» в текст ===
Всего: 3

=== Слова разбитые на группы по количеству букв ===
2 символа [it, is, be, as]
3 символа [its, has, web, now, use]
4 символа [long, fact, that, will, page, when, here, look, like, many, text, have, over]
5 символов [point, using, lorem, ipsum, sites, still, their, model, years]
6 символов [reader, layout, normal, humour, making, search]
7 символов [uncover, content, looking, opposed, english, desktop, editors, default, infancy, various, evolved, purpose]
8 символов [readable, packages, versions, accident, injected]
9 символов [lettersas, sometimes]
10 символов [distracted, moreorless, publishing]
11 символов [established]
12 символов [distribution]

=== ТОП 10 слов ===
uncover: 9 раз
lorem: 3 раза
it: 3 раза
ipsum: 3 раза
content: 3 раза
sometimes: 2 раза
sites: 2 раза
that: 2 раза
readable: 2 раза
using: 2 раза

=== Статистика по буквам ===
Всего букв: 564
e: 12,77%
o: 8,33%
t: 7,98%
i: 7,27%
s: 7,27%
n: 6,91%
a: 6,74%
r: 6,38%
l: 4,96%
u: 4,43%
c: 3,55%
h: 3,37%
d: 3,19%
m: 3,19%
v: 2,66%
p: 2,30%
b: 1,77%
g: 1,77%
f: 1,24%
k: 1,24%
y: 1,24%
w: 1,06%
j: 0,18%
x: 0,18%

 */