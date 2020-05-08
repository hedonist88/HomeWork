import garage.Repairing;
import garage.Transport;

public class Garage<T extends Transport & Repairing> {

    private T carOnService;

    public T getCarOnService() {
        return carOnService;
    }

    public void setCarOnService(T carOnService) {
        this.carOnService = carOnService;
    }

    public void service(){
        carOnService.repair();
    }
}
