package employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Employee implements Comparable<Employee> {
    private String name;
    private String company;
    private int salary;
    private int age;
    private static final int[] AGES = {21, 60};
    private static final int[] SALARIES = {2000, 25000};

    public Employee(String name, String company, int salary, int age) {
        setName(name);
        setCompany(company);
        setSalary(salary);
        setAge(age);
    }

    public static List<Employee> employeeGenerator(int num) {
        List employees = new ArrayList<>();
        if(num < 1) {
            employees.add(null);
            return employees;
        }

        // метод для создания списка объектов класса Employee
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний

        // добавление num объектов Employee в список (employees)
        Random rand = new Random();
            for (int i = 0; i < num; i++) {
            employees.add(new Employee(
                names[rand.nextInt(names.length)],
                companies[rand.nextInt(companies.length)],
                    (rand.nextInt(SALARIES[1] - SALARIES[0]) + SALARIES[0]), //$$$
                    (rand.nextInt(AGES[1] - AGES[0] + 1) + AGES[0])
            ));
        }
        return employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary >= SALARIES[0] && salary <=  SALARIES[1]) {
            this.salary = salary;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= AGES[0] && age <= AGES[1]) {
            this.age = age;
        }
    }

    @Override
    public String toString() {
        return name +
                " работает в компании '" + company + '\'' +
                " с зарплатой " + salary +
                ", его возраст " + age;
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}

class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getSalary(),o2.getSalary());
    }
}

class EmployeeAgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getAge(),o2.getAge());
    }
}

class EmployeeCompanyComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getCompany().compareTo(o2.getCompany());
    }
}