import Passengers.Passenger;
import garage.Bus;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Passenger[] Passengers = {new Passenger("Иван", 18), new Passenger("Татьяна", 28)};
        Bus bus = new Bus("Спб", "Тверь", "234", false, "red", new String[]{"Кондиционер", "Панорама", "Телевизор"}, Passengers);

        System.out.println(Classinfo.toString(bus));

    }

}

/* out */
/*
@ Класс: Bus
   wiFi: false
   color: red
   equip: Кондиционер / Панорама / Телевизор
   passengers:
      @ Класс: Passenger
         name: Иван
         age: 18 /
      @ Класс: Passenger
         name: Татьяна
         age: 28
   departureSt: Спб
   destSt: Тверь
   num: 234
 */