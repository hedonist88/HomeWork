package teak1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pupil {

    private int number; // Id каждого ученика
    private static final int START_IDS = 15000;
    public static final int TOTAL_PUPILS = 50;
    private static final int MIN_AGE = 15;
    private String name;
    private Gender gender;
    private LocalDate birth;

    static String[] femaleNames = {"Альбина","Аманда","Амина","Амира","Клавдия","Клара","Клементина","Констанция","Консуэло","Лейла","Леля","Варвара","Василиса","Маргарита","Марианна","Марина","Мариса","Джанет","Дженифер","Дженна","Джоан","Наталья","Невена","Нелли","Павла","Павлина","Параскева"};
    static String[] maleNames = {"Авигдор","Авирмэд","Авксентий","Авл","Авнер","Аврелий","Автандил","Бехруз","Билял","Богдан","Вадим","Валентин","Валерий","Германн","Геронтий","Герхард","Гильем","Гинкмар","Глеб","Гней","Леонид","Леонтий","Матео","Матиас","Матфей","Матфий"};

    enum Gender {
        MALE, FEMALE
    }

    public Pupil(int number, String name, Gender gender, LocalDate birth) {
        setName(name);
        setGender(gender);
        setNumber(number);
        setBirth(birth);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number >= START_IDS && number <= START_IDS + TOTAL_PUPILS) {
            this.number = number;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 1) {
            this.name = name;
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public int getBirthYear() {
        return birth.getYear();
    }

    public void setBirth(LocalDate birth) {
        if(birth.isBefore(LocalDate.now().minusYears(MIN_AGE))) {
            this.birth = birth;
        }
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                '}';
    }

    public static List<Pupil> pupilsGenerator(int quantity){
        if (quantity < 1 && quantity >= TOTAL_PUPILS) return null;
        List<Pupil> pupils = new ArrayList<>();
        int id = START_IDS;
        int minDay = (int) LocalDate.of(1960, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().minusYears(MIN_AGE).toEpochDay();
        for (int i = 0; i <= quantity; i++){
            Random rand = new Random();
            Boolean b = rand.nextBoolean();
            long randomDay = minDay + rand.nextInt(maxDay - minDay);
            pupils.add(new Pupil(
                 id,
                 b ? maleNames[rand.nextInt(maleNames.length)] : femaleNames[rand.nextInt(femaleNames.length)],
                 b ? Gender.MALE : Gender.FEMALE,
                 LocalDate.ofEpochDay(randomDay)
            ));
            id++;
        }
        return pupils;
    }


}
