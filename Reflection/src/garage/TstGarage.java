package garage;

public class TstGarage {
    public static void main(String[] args) {
        //Bus bus = new Bus("Спб", "Тверь", "234", false, "red");
        Train train = new Train("Спб", "Москва", "23-Ф", 12);

        /*
        Garage<Bus> garage1 = new Garage<>();
        garage1.setCarOnService(bus); // тип поля сarOnService - Bus (не транспорт)
        System.out.println(garage1.getCarOnService().isWiFi());

        Garage<Train> garage2 = new Garage<>();
        garage2.setCarOnService(train); // тип поля carOnService - Train
        System.out.println(garage2.getCarOnService().getCarCount());

        Garage<Transport> garage3 = new Garage<>();
        garage3.setCarOnService(bus);
        garage3.setCarOnService(train);
        // тут можн использовать только методы Transport
        // .isWiFi и .getCarCount() не доступны

        Garage<ShuttleBus> garageSh = new Garage<>();
        garageSh.setCarOnService(shuttleBus);
        System.out.println(garageSh.getCarOnService().isWiFi());

*/
    }
}
