import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    String fullName;
    int salary;
    Position position;

    public User(String fullName, int salary, Position position) {
        setFullName(fullName);
        setPosition(position);
        setSalary(salary);
    }

    public static void printUsersList(){
        Position[] positions = Position.values();
        for(Position p:positions){
            if(p.getUsers().size() > 0){
                System.out.println(">>> Должность: " + p.getName());
                System.out.println("Работники: \n" + p.getUsers() + "\n");
            }
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if(fullName.length() > 4 && fullName != null){
            this.fullName = fullName;
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ФИО: " + fullName +
                " / Зарплата: " + salary;
    }
}

enum Position {
    ACCOUNTANT("бухгалтер"),
    DISPATCHER("диспетчер"),
    ENGINEER("инженер"),
    CONSTRUCTOR("конструктор"),
    METALLURGIST("металлург"),
    WELDER("сварщик");

    private String name;
    private ArrayList<User> users = new ArrayList<>();

    Position(String name) {
        setName(name);
    }

    Position(String name, ArrayList<User> users) {
        setName(name);
        setUsers(users);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
