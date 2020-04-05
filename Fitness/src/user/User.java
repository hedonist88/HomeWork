package user;

import fitness.Fitness;
import subscription.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class User {
    protected static String[] userNames = {"Азамат","Азат","Азиз","Аид","Айдар","Айрат","Акакий","Аким","Алан","Александр","Алексей","Анатолий","Анвар","Андрей","Анзор","Бахрам","Бенджамин","Блез","Богдан","Борис","Борислав","Бронислав","Булат","Виктор","Вильгельм","Вит","Виталий","Влад","Владимир","Владислав","Владлен","Влас","Всеволод","Вячеслав","Гавриил","Гамлет","Гарри","Геннадий","Генри","Даурен","Демид","Демьян","Денис","Джамал","Джан","Евгений","Евдоким","Евсей","Евстахий","Заур","Захар","Зенон","Зигмунд","Иннокентий","Ираклий","Исаак","Исаакий","Исидор","Искандер","Кир","Кирилл","Клаус","Клим","Конрад","Константин","Корнелий","Кристиан","Леонард","Леонид","Леопольд","Лоренс","Лукиллиан","Максим","Максимилиан","Максуд","Мансур","Мар","Марат","Марк","Микула","Милослав","Мирон","Мирослав","Михаил","Моисей","Мстислав","Мурат","Муслим","Нестор","Ник","Никита","Никодим","Никола","Николай","Нильс","Олег","Оливер","Орест","Орландо","Осип","Оскар","Осман","Педро","Перри","Пётр","Платон","Потап","Радик","Радомир","Радослав","Разиль","Раиль","Райан","Раймонд","Рамазан","Рамзес","Рамиз","Рамиль","Роберт","Родион","Рожден","Санжар","Сани","Святослав","Севастьян","Семён","Серафим","Сергей","Сидор","Симба","Тамаз","Тамерлан","Тарас","Тахир","Тигран","Тимофей","Тимур","Федот","Феликс","Филипп","Флор","Фома","Шамиль","Шарль","Эркюль","Эрмин","Эрнест","Юстус","Яков"};
    protected static String[] userLastNames = {"Алексеев", "Андреев", "Борисов", "Васильев", "Волков", "Воробьев", "Григорьев", "Егоров", "Зайцев", "Захаров", "Иванов", "Козлов", "Кузнецов", "Кузьмин", "Лебедев", "Макаров", "материала", "Михайлов", "Морозов", "Никитин", "Николаев", "Новиков", "Орлов", "Павлов", "Петров", "Попов", "Романов", "Семенов", "Сергеев", "Смирнов", "Соколов", "Соловьев", "Степанов", "Федоров", "Фролов", "Яковлев", };
    protected String name;
    protected String lastName;
    protected int bdYear;
    protected Boolean inClub = false;

    public User(String name, String lastName, int bdYear, Boolean inClub) {
        setName(name);
        setLastName(lastName);
        setInClub(inClub);
        setBdYear(bdYear);
    }

    public static User getUsers() {
        User result = null;
        Random rand = new Random();
        LocalDate date = LocalDate.now();
        int diffYears = (date.getYear() - Fitness.MINYEAR) - (date.getYear() - Fitness.MAXYEAR);
        result = new User(
                userNames[rand.nextInt(userNames.length)],
                userLastNames[rand.nextInt(userLastNames.length)],
                rand.nextInt( diffYears + 1) + (date.getYear() - Fitness.MAXYEAR),
                false
        );
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && name.length() > 2) {
            this.name = name;
        } else {
            System.out.println("Имя больше 2 символов");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBdYear() {
        return bdYear;
    }

    public void setBdYear(int bdYear) {
        LocalDate date = LocalDate.now();
        if(bdYear <=  date.getYear() - Fitness.MINYEAR && bdYear >=  (date.getYear() - Fitness.MAXYEAR)) {
            this.bdYear = bdYear;
        } else {
            System.out.println("В клубе есть ограничения по возрасту от " + Fitness.MINYEAR + " до " + Fitness.MAXYEAR);
        }
    }

    public Boolean getInClub() {
        return inClub;
    }

    public void setInClub(Boolean inClub) {
        this.inClub = inClub;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bdYear=" + bdYear +
                ", inClub=" + inClub +
                '}';
    }
}
