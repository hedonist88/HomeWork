package task1;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*


    Доработать класс Pupil:
       + Дописать необходимые методы, геттеры-сеттеры, конструкторы.
       + Сделать генератор коллекции учеников (например, листа).
    Реализовать следующие операции (например, в отдельных методах), используя Stream API:
       + Разделить учеников на две группы: мальчиков и девочек. Результат: Map<Pupil.Gender, ArrayList>
       + Найти средний возраст учеников
       + Найти самого младшего ученика
       + Найти самого старшего ученика
       + Собрать учеников в группы по году рождения
       + Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль
       + Отсортировать по полу, потом по дате, потом по имени (в обратном порядке), собрать в список.
       + Вывести в консоль всех учеников в возрасте от N до M лет.
       + Собрать в список всех учеников с именем равным заданному.
       + Собрать Map<Pupil.Gender,Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола.


 */
public class Main {
    public static void main(String[] args) {
        List<Pupil> pupils = Pupil.pupilsGenerator(Pupil.TOTAL_PUPILS);
        System.out.println("--- Наши Пупилы: ---");
        pupils.forEach(System.out::println);

        System.out.println("\n--- Группировка по гендеру ---");
        getGenderGroup(pupils).entrySet().forEach(System.out::println);

        System.out.println("\n--- Cредний возраст ---");
        System.out.println(getAverageAge(pupils));

        System.out.println("\n--- Самый молодой ---");
        System.out.println(getMinMaxAge(pupils,"min"));

        System.out.println("\n--- Самый взрослый ---");
        System.out.println(getMinMaxAge(pupils,"max"));

        System.out.println("\n--- Группировка по году рождения ---");
        getBirthYearGroup(pupils).entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        System.out.println("\n--- Группировка по имени ---");
        getNameGroup(pupils).entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

        System.out.println("\n--- Всех в возрасте от 20 до 30 ---");
        getInAgesRange(pupils, 20, 30).forEach(System.out::println);

        System.out.println("\n--- Пользователи с именем «Джоан» ---");
        getFilterByName(pupils, "Джоан").forEach(System.out::println);

        System.out.println("\n--- Суммарный возраст учеников по полу ---");
        System.out.println(getSumAgeByGender(pupils));

        System.out.println("\n--- Отсортировать по полу, потом по дате, потом по имени (в обратном порядке), собрать в список ---");
        System.out.println(customSort(pupils));

        System.out.println("\n--- Вывести имена + даты рождения ---");
        getNamesAndBirths(pupils).entrySet().forEach(System.out::println);

    }

    private static Map<Pupil.Gender, ArrayList<Pupil>> getGenderGroup(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.groupingBy(Pupil::getGender, Collectors.toCollection(ArrayList::new)));
    }

    private static Map<Integer, ArrayList<Pupil>> getBirthYearGroup(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.groupingBy(Pupil::getBirthYear, Collectors.toCollection(ArrayList::new)));
    }

    private static Map<String, ArrayList<Pupil>> getNameGroup(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.groupingBy(Pupil::getName, Collectors.toCollection(ArrayList::new)));
    }

    private static double getAverageAge(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.averagingDouble(pupil -> LocalDate.now().getYear() - pupil.getBirth().getYear()));
    }

    private static Pupil getMinMaxAge(List<Pupil> pupils, String flag){
        Comparator<Pupil> byАge = Comparator.comparing(Pupil::getBirth);
        if("max".equals(flag)){
            return pupils.stream().min(byАge).get();
        }
        if("min".equals(flag)){
            return pupils.stream().max(byАge).get();
        }
        return null;
    }

    private static List<Pupil> getInAgesRange(List<Pupil> pupils, int from, int to){
        return pupils
                .stream()
                .filter(pupil -> LocalDate.now().getYear() - pupil.getBirth().getYear() >= from
                        && LocalDate.now().getYear() - pupil.getBirth().getYear() <= to)
                .collect(Collectors.toList());
    }

    private static List<Pupil> getFilterByName(List<Pupil> pupils, String name){
        return pupils
                .stream()
                .filter(pupil -> pupil.getName().equals(name))
                .collect(Collectors.toList());
    }

    private static Map<Pupil.Gender, Integer> getSumAgeByGender(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.groupingBy(Pupil::getGender,
                        Collectors.summingInt(pupil -> LocalDate.now().getYear() - pupil.getBirth().getYear())));
    }

    private static List<Pupil> customSort(List<Pupil> pupils){
        return pupils
                .stream()
                .sorted(Comparator.comparing(Pupil::getGender)
                        .thenComparing(Pupil::getBirth)
                        .thenComparing(Comparator.comparing(Pupil::getName).reversed()))
                        .collect(Collectors.toList());
    }

    private static Map<String, LocalDate> getNamesAndBirths(List<Pupil> pupils){
        return pupils
                .stream()
                .collect(Collectors.toMap(Pupil::getName, Pupil::getBirth, (p1, p2) -> p1));
    }



}

/* out */
/*
    --- Наши Пупилы: ---
Pupil{number=15000, name='Авксентий', gender=MALE, birth=1980-02-02}
Pupil{number=15001, name='Лейла', gender=FEMALE, birth=1972-02-28}
Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}
Pupil{number=15003, name='Клементина', gender=FEMALE, birth=1984-02-06}
Pupil{number=15004, name='Аманда', gender=FEMALE, birth=1969-02-09}
Pupil{number=15005, name='Наталья', gender=FEMALE, birth=1975-08-30}
Pupil{number=15006, name='Павлина', gender=FEMALE, birth=1961-10-27}
Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}
Pupil{number=15008, name='Дженна', gender=FEMALE, birth=1966-12-01}
Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}
Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}
Pupil{number=15011, name='Варвара', gender=FEMALE, birth=1973-08-29}
Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}
Pupil{number=15013, name='Марина', gender=FEMALE, birth=1967-03-17}
Pupil{number=15014, name='Лейла', gender=FEMALE, birth=1970-05-08}
Pupil{number=15015, name='Лейла', gender=FEMALE, birth=1976-04-15}
Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}
Pupil{number=15017, name='Нелли', gender=FEMALE, birth=2003-09-13}
Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}
Pupil{number=15019, name='Германн', gender=MALE, birth=2003-03-27}
Pupil{number=15020, name='Павла', gender=FEMALE, birth=1984-11-08}
Pupil{number=15021, name='Дженна', gender=FEMALE, birth=1975-11-18}
Pupil{number=15022, name='Авнер', gender=MALE, birth=1971-01-10}
Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}
Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}
Pupil{number=15025, name='Валентин', gender=MALE, birth=1983-11-19}
Pupil{number=15026, name='Констанция', gender=FEMALE, birth=1979-09-11}
Pupil{number=15027, name='Мариса', gender=FEMALE, birth=1978-08-13}
Pupil{number=15028, name='Наталья', gender=FEMALE, birth=1967-09-25}
Pupil{number=15029, name='Клементина', gender=FEMALE, birth=1967-01-26}
Pupil{number=15030, name='Германн', gender=MALE, birth=1975-01-21}
Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}
Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}
Pupil{number=15033, name='Автандил', gender=MALE, birth=2001-03-04}
Pupil{number=15034, name='Бехруз', gender=MALE, birth=1972-12-20}
Pupil{number=15035, name='Матфий', gender=MALE, birth=1989-07-10}
Pupil{number=15036, name='Нелли', gender=FEMALE, birth=1981-01-06}
Pupil{number=15037, name='Клементина', gender=FEMALE, birth=1968-01-15}
Pupil{number=15038, name='Матфей', gender=MALE, birth=1980-11-07}
Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}
Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}
Pupil{number=15041, name='Амира', gender=FEMALE, birth=1983-05-15}
Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}
Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}
Pupil{number=15044, name='Клементина', gender=FEMALE, birth=1980-03-16}
Pupil{number=15045, name='Матео', gender=MALE, birth=1960-07-10}
Pupil{number=15046, name='Матфий', gender=MALE, birth=1977-07-22}
Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}
Pupil{number=15048, name='Автандил', gender=MALE, birth=1989-07-04}
Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}
Pupil{number=15050, name='Леля', gender=FEMALE, birth=1970-05-05}

--- Группировка по гендеру ---
MALE=[Pupil{number=15000, name='Авксентий', gender=MALE, birth=1980-02-02}, Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}, Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}, Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}, Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}, Pupil{number=15019, name='Германн', gender=MALE, birth=2003-03-27}, Pupil{number=15022, name='Авнер', gender=MALE, birth=1971-01-10}, Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}, Pupil{number=15025, name='Валентин', gender=MALE, birth=1983-11-19}, Pupil{number=15030, name='Германн', gender=MALE, birth=1975-01-21}, Pupil{number=15033, name='Автандил', gender=MALE, birth=2001-03-04}, Pupil{number=15034, name='Бехруз', gender=MALE, birth=1972-12-20}, Pupil{number=15035, name='Матфий', gender=MALE, birth=1989-07-10}, Pupil{number=15038, name='Матфей', gender=MALE, birth=1980-11-07}, Pupil{number=15045, name='Матео', gender=MALE, birth=1960-07-10}, Pupil{number=15046, name='Матфий', gender=MALE, birth=1977-07-22}, Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}, Pupil{number=15048, name='Автандил', gender=MALE, birth=1989-07-04}, Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}]
FEMALE=[Pupil{number=15001, name='Лейла', gender=FEMALE, birth=1972-02-28}, Pupil{number=15003, name='Клементина', gender=FEMALE, birth=1984-02-06}, Pupil{number=15004, name='Аманда', gender=FEMALE, birth=1969-02-09}, Pupil{number=15005, name='Наталья', gender=FEMALE, birth=1975-08-30}, Pupil{number=15006, name='Павлина', gender=FEMALE, birth=1961-10-27}, Pupil{number=15008, name='Дженна', gender=FEMALE, birth=1966-12-01}, Pupil{number=15011, name='Варвара', gender=FEMALE, birth=1973-08-29}, Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}, Pupil{number=15013, name='Марина', gender=FEMALE, birth=1967-03-17}, Pupil{number=15014, name='Лейла', gender=FEMALE, birth=1970-05-08}, Pupil{number=15015, name='Лейла', gender=FEMALE, birth=1976-04-15}, Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}, Pupil{number=15017, name='Нелли', gender=FEMALE, birth=2003-09-13}, Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}, Pupil{number=15020, name='Павла', gender=FEMALE, birth=1984-11-08}, Pupil{number=15021, name='Дженна', gender=FEMALE, birth=1975-11-18}, Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}, Pupil{number=15026, name='Констанция', gender=FEMALE, birth=1979-09-11}, Pupil{number=15027, name='Мариса', gender=FEMALE, birth=1978-08-13}, Pupil{number=15028, name='Наталья', gender=FEMALE, birth=1967-09-25}, Pupil{number=15029, name='Клементина', gender=FEMALE, birth=1967-01-26}, Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}, Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}, Pupil{number=15036, name='Нелли', gender=FEMALE, birth=1981-01-06}, Pupil{number=15037, name='Клементина', gender=FEMALE, birth=1968-01-15}, Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}, Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}, Pupil{number=15041, name='Амира', gender=FEMALE, birth=1983-05-15}, Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}, Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}, Pupil{number=15044, name='Клементина', gender=FEMALE, birth=1980-03-16}, Pupil{number=15050, name='Леля', gender=FEMALE, birth=1970-05-05}]

--- Cредний возраст ---
37.94117647058823

--- Самый молодой ---
Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}

--- Самый взрослый ---
Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}

--- Группировка по году рождения ---
1960=[Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}, Pupil{number=15045, name='Матео', gender=MALE, birth=1960-07-10}]
1961=[Pupil{number=15006, name='Павлина', gender=FEMALE, birth=1961-10-27}]
1966=[Pupil{number=15008, name='Дженна', gender=FEMALE, birth=1966-12-01}]
1967=[Pupil{number=15013, name='Марина', gender=FEMALE, birth=1967-03-17}, Pupil{number=15028, name='Наталья', gender=FEMALE, birth=1967-09-25}, Pupil{number=15029, name='Клементина', gender=FEMALE, birth=1967-01-26}]
1968=[Pupil{number=15037, name='Клементина', gender=FEMALE, birth=1968-01-15}, Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}]
1969=[Pupil{number=15004, name='Аманда', gender=FEMALE, birth=1969-02-09}]
1970=[Pupil{number=15014, name='Лейла', gender=FEMALE, birth=1970-05-08}, Pupil{number=15050, name='Леля', gender=FEMALE, birth=1970-05-05}]
1971=[Pupil{number=15022, name='Авнер', gender=MALE, birth=1971-01-10}]
1972=[Pupil{number=15001, name='Лейла', gender=FEMALE, birth=1972-02-28}, Pupil{number=15034, name='Бехруз', gender=MALE, birth=1972-12-20}]
1973=[Pupil{number=15011, name='Варвара', gender=FEMALE, birth=1973-08-29}]
1975=[Pupil{number=15005, name='Наталья', gender=FEMALE, birth=1975-08-30}, Pupil{number=15021, name='Дженна', gender=FEMALE, birth=1975-11-18}, Pupil{number=15030, name='Германн', gender=MALE, birth=1975-01-21}]
1976=[Pupil{number=15015, name='Лейла', gender=FEMALE, birth=1976-04-15}]
1977=[Pupil{number=15046, name='Матфий', gender=MALE, birth=1977-07-22}]
1978=[Pupil{number=15027, name='Мариса', gender=FEMALE, birth=1978-08-13}]
1979=[Pupil{number=15026, name='Констанция', gender=FEMALE, birth=1979-09-11}]
1980=[Pupil{number=15000, name='Авксентий', gender=MALE, birth=1980-02-02}, Pupil{number=15038, name='Матфей', gender=MALE, birth=1980-11-07}, Pupil{number=15044, name='Клементина', gender=FEMALE, birth=1980-03-16}]
1981=[Pupil{number=15036, name='Нелли', gender=FEMALE, birth=1981-01-06}]
1983=[Pupil{number=15025, name='Валентин', gender=MALE, birth=1983-11-19}, Pupil{number=15041, name='Амира', gender=FEMALE, birth=1983-05-15}]
1984=[Pupil{number=15003, name='Клементина', gender=FEMALE, birth=1984-02-06}, Pupil{number=15020, name='Павла', gender=FEMALE, birth=1984-11-08}]
1989=[Pupil{number=15035, name='Матфий', gender=MALE, birth=1989-07-10}, Pupil{number=15048, name='Автандил', gender=MALE, birth=1989-07-04}]
1990=[Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}, Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}]
1991=[Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}]
1993=[Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}, Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}]
1994=[Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}, Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}, Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}]
1995=[Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}]
1996=[Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}]
1998=[Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}, Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}]
1999=[Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}]
2000=[Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}]
2001=[Pupil{number=15033, name='Автандил', gender=MALE, birth=2001-03-04}]
2003=[Pupil{number=15017, name='Нелли', gender=FEMALE, birth=2003-09-13}, Pupil{number=15019, name='Германн', gender=MALE, birth=2003-03-27}]
2004=[Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}]

--- Группировка по имени ---
Авигдор=[Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}]
Авксентий=[Pupil{number=15000, name='Авксентий', gender=MALE, birth=1980-02-02}]
Авнер=[Pupil{number=15022, name='Авнер', gender=MALE, birth=1971-01-10}]
Аврелий=[Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}]
Автандил=[Pupil{number=15033, name='Автандил', gender=MALE, birth=2001-03-04}, Pupil{number=15048, name='Автандил', gender=MALE, birth=1989-07-04}]
Аманда=[Pupil{number=15004, name='Аманда', gender=FEMALE, birth=1969-02-09}]
Амина=[Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}]
Амира=[Pupil{number=15041, name='Амира', gender=FEMALE, birth=1983-05-15}]
Бехруз=[Pupil{number=15034, name='Бехруз', gender=MALE, birth=1972-12-20}]
Валентин=[Pupil{number=15025, name='Валентин', gender=MALE, birth=1983-11-19}]
Варвара=[Pupil{number=15011, name='Варвара', gender=FEMALE, birth=1973-08-29}]
Германн=[Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}, Pupil{number=15019, name='Германн', gender=MALE, birth=2003-03-27}, Pupil{number=15030, name='Германн', gender=MALE, birth=1975-01-21}]
Дженифер=[Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}, Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}]
Дженна=[Pupil{number=15008, name='Дженна', gender=FEMALE, birth=1966-12-01}, Pupil{number=15021, name='Дженна', gender=FEMALE, birth=1975-11-18}]
Джоан=[Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}, Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}]
Клементина=[Pupil{number=15003, name='Клементина', gender=FEMALE, birth=1984-02-06}, Pupil{number=15029, name='Клементина', gender=FEMALE, birth=1967-01-26}, Pupil{number=15037, name='Клементина', gender=FEMALE, birth=1968-01-15}, Pupil{number=15044, name='Клементина', gender=FEMALE, birth=1980-03-16}]
Констанция=[Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}, Pupil{number=15026, name='Констанция', gender=FEMALE, birth=1979-09-11}]
Лейла=[Pupil{number=15001, name='Лейла', gender=FEMALE, birth=1972-02-28}, Pupil{number=15014, name='Лейла', gender=FEMALE, birth=1970-05-08}, Pupil{number=15015, name='Лейла', gender=FEMALE, birth=1976-04-15}, Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}]
Леля=[Pupil{number=15050, name='Леля', gender=FEMALE, birth=1970-05-05}]
Леонид=[Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}, Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}]
Леонтий=[Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}]
Марианна=[Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}, Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}]
Марина=[Pupil{number=15013, name='Марина', gender=FEMALE, birth=1967-03-17}]
Мариса=[Pupil{number=15027, name='Мариса', gender=FEMALE, birth=1978-08-13}]
Матео=[Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}, Pupil{number=15045, name='Матео', gender=MALE, birth=1960-07-10}]
Матфей=[Pupil{number=15038, name='Матфей', gender=MALE, birth=1980-11-07}]
Матфий=[Pupil{number=15035, name='Матфий', gender=MALE, birth=1989-07-10}, Pupil{number=15046, name='Матфий', gender=MALE, birth=1977-07-22}]
Наталья=[Pupil{number=15005, name='Наталья', gender=FEMALE, birth=1975-08-30}, Pupil{number=15028, name='Наталья', gender=FEMALE, birth=1967-09-25}, Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}]
Нелли=[Pupil{number=15017, name='Нелли', gender=FEMALE, birth=2003-09-13}, Pupil{number=15036, name='Нелли', gender=FEMALE, birth=1981-01-06}]
Павла=[Pupil{number=15020, name='Павла', gender=FEMALE, birth=1984-11-08}]
Павлина=[Pupil{number=15006, name='Павлина', gender=FEMALE, birth=1961-10-27}]

--- Всех в возрасте от 20 до 30 ---
Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}
Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}
Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}
Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}
Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}
Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}
Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}
Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}
Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}
Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}
Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}
Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}
Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}
Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}

--- Пользователи с именем «Джоан» ---
Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}
Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}

--- Суммарный возраст учеников по полу ---
{MALE=644, FEMALE=1291}

--- Отсортировать по полу, потом по дате, потом по имени (в обратном порядке), собрать в список ---
[Pupil{number=15045, name='Матео', gender=MALE, birth=1960-07-10}, Pupil{number=15022, name='Авнер', gender=MALE, birth=1971-01-10}, Pupil{number=15034, name='Бехруз', gender=MALE, birth=1972-12-20}, Pupil{number=15030, name='Германн', gender=MALE, birth=1975-01-21}, Pupil{number=15046, name='Матфий', gender=MALE, birth=1977-07-22}, Pupil{number=15000, name='Авксентий', gender=MALE, birth=1980-02-02}, Pupil{number=15038, name='Матфей', gender=MALE, birth=1980-11-07}, Pupil{number=15025, name='Валентин', gender=MALE, birth=1983-11-19}, Pupil{number=15048, name='Автандил', gender=MALE, birth=1989-07-04}, Pupil{number=15035, name='Матфий', gender=MALE, birth=1989-07-10}, Pupil{number=15009, name='Матео', gender=MALE, birth=1990-12-21}, Pupil{number=15049, name='Леонид', gender=MALE, birth=1991-08-27}, Pupil{number=15007, name='Германн', gender=MALE, birth=1993-07-03}, Pupil{number=15010, name='Леонтий', gender=MALE, birth=1994-07-10}, Pupil{number=15002, name='Авигдор', gender=MALE, birth=1994-11-19}, Pupil{number=15024, name='Аврелий', gender=MALE, birth=1996-06-26}, Pupil{number=15047, name='Леонид', gender=MALE, birth=1998-08-30}, Pupil{number=15033, name='Автандил', gender=MALE, birth=2001-03-04}, Pupil{number=15019, name='Германн', gender=MALE, birth=2003-03-27}, Pupil{number=15032, name='Марианна', gender=FEMALE, birth=1960-05-20}, Pupil{number=15006, name='Павлина', gender=FEMALE, birth=1961-10-27}, Pupil{number=15008, name='Дженна', gender=FEMALE, birth=1966-12-01}, Pupil{number=15029, name='Клементина', gender=FEMALE, birth=1967-01-26}, Pupil{number=15013, name='Марина', gender=FEMALE, birth=1967-03-17}, Pupil{number=15028, name='Наталья', gender=FEMALE, birth=1967-09-25}, Pupil{number=15037, name='Клементина', gender=FEMALE, birth=1968-01-15}, Pupil{number=15040, name='Джоан', gender=FEMALE, birth=1968-12-21}, Pupil{number=15004, name='Аманда', gender=FEMALE, birth=1969-02-09}, Pupil{number=15050, name='Леля', gender=FEMALE, birth=1970-05-05}, Pupil{number=15014, name='Лейла', gender=FEMALE, birth=1970-05-08}, Pupil{number=15001, name='Лейла', gender=FEMALE, birth=1972-02-28}, Pupil{number=15011, name='Варвара', gender=FEMALE, birth=1973-08-29}, Pupil{number=15005, name='Наталья', gender=FEMALE, birth=1975-08-30}, Pupil{number=15021, name='Дженна', gender=FEMALE, birth=1975-11-18}, Pupil{number=15015, name='Лейла', gender=FEMALE, birth=1976-04-15}, Pupil{number=15027, name='Мариса', gender=FEMALE, birth=1978-08-13}, Pupil{number=15026, name='Констанция', gender=FEMALE, birth=1979-09-11}, Pupil{number=15044, name='Клементина', gender=FEMALE, birth=1980-03-16}, Pupil{number=15036, name='Нелли', gender=FEMALE, birth=1981-01-06}, Pupil{number=15041, name='Амира', gender=FEMALE, birth=1983-05-15}, Pupil{number=15003, name='Клементина', gender=FEMALE, birth=1984-02-06}, Pupil{number=15020, name='Павла', gender=FEMALE, birth=1984-11-08}, Pupil{number=15043, name='Наталья', gender=FEMALE, birth=1990-07-30}, Pupil{number=15018, name='Джоан', gender=FEMALE, birth=1993-08-12}, Pupil{number=15023, name='Лейла', gender=FEMALE, birth=1994-08-22}, Pupil{number=15039, name='Амина', gender=FEMALE, birth=1995-03-29}, Pupil{number=15042, name='Дженифер', gender=FEMALE, birth=1998-05-05}, Pupil{number=15012, name='Констанция', gender=FEMALE, birth=1999-07-23}, Pupil{number=15016, name='Дженифер', gender=FEMALE, birth=2000-12-02}, Pupil{number=15017, name='Нелли', gender=FEMALE, birth=2003-09-13}, Pupil{number=15031, name='Марианна', gender=FEMALE, birth=2004-01-11}]

--- Вывести имена + даты рождения ---
Леля=1970-05-05
Амира=1983-05-15
Лейла=1972-02-28
Клементина=1984-02-06
Автандил=2001-03-04
Авигдор=1994-11-19
Леонид=1998-08-30
Дженна=1966-12-01
Авксентий=1980-02-02
Нелли=2003-09-13
Дженифер=2000-12-02
Варвара=1973-08-29
Валентин=1983-11-19
Матфей=1980-11-07
Джоан=1993-08-12
Бехруз=1972-12-20
Марианна=2004-01-11
Амина=1995-03-29
Леонтий=1994-07-10
Германн=1993-07-03
Павлина=1961-10-27
Аврелий=1996-06-26
Аманда=1969-02-09
Мариса=1978-08-13
Марина=1967-03-17
Матео=1990-12-21
Наталья=1975-08-30
Матфий=1989-07-10
Павла=1984-11-08
Авнер=1971-01-10
Констанция=1999-07-23
 */