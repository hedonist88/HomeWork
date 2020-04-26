package employee;

// Создать список объетов List<Employee> (использовать метод employeeGenerator)
// и сортировать по:
// имени
// имени и зарплете
// имени, зарплете, возрасту и компании

import java.util.ArrayList;
import java.util.Comparator;

public class EmployeeTask {
    public static void main(String[] args) {

        // Генерим пользователей
        ArrayList<Employee> employees = (ArrayList<Employee>) Employee.employeeGenerator(20);

        System.out.println("== Исходный список ==");
        for (Employee employee:employees){
            System.out.println(employee.toString());
        }

        // По имени
        Comparator<Employee> employeeComparator1 = new EmployeeNameComparator();
        employees.sort(employeeComparator1);
        System.out.println("\n== Сортировка по имени ==");
        for (Employee employee:employees){
            System.out.println(employee.toString());
        }

        // По имени и зарплете
        Comparator<Employee> employeeComparator2 = new EmployeeNameComparator()
                .thenComparing(new EmployeeSalaryComparator());
        employees.sort(employeeComparator2);
        System.out.println("\n== Сортировка по имени и зарплете ==");
        for (Employee employee:employees){
            System.out.println(employee.toString());
        }

        // По имени, зарплете, возрасту и компании
        Comparator<Employee> employeeComparator3 = new EmployeeNameComparator()
                .thenComparing(new EmployeeSalaryComparator()
                        .thenComparing(new EmployeeAgeComparator()
                                .thenComparing(new EmployeeCompanyComparator())
                        )
                );
        employees.sort(employeeComparator3);
        System.out.println("\n== Сортировка по имени и зарплете ==");
        for (Employee employee:employees){
            System.out.println(employee.toString());
        }

    }
}

/* out */
/*
== Исходный список ==
Jack работает в компании 'IBM' с зарплатой 17816, его возраст 39
Peter работает в компании 'General Electric' с зарплатой 12097, его возраст 60
Tom работает в компании 'Microsoft' с зарплатой 20392, его возраст 27
Peter работает в компании 'General Electric' с зарплатой 7531, его возраст 21
Elizabeth работает в компании 'IBM' с зарплатой 14874, его возраст 33
Jack работает в компании 'Google' с зарплатой 16294, его возраст 30
Alex работает в компании 'IBM' с зарплатой 16954, его возраст 22
Alex работает в компании 'Samsung' с зарплатой 17825, его возраст 58
Tom работает в компании 'General Electric' с зарплатой 13647, его возраст 54
Jenifer работает в компании 'Microsoft' с зарплатой 5364, его возраст 25
Max работает в компании 'Apple' с зарплатой 6779, его возраст 52
Linda работает в компании 'Samsung' с зарплатой 22561, его возраст 60
Alex работает в компании 'Google' с зарплатой 23200, его возраст 30
Charlie работает в компании 'IBM' с зарплатой 20259, его возраст 46
Mike работает в компании 'IBM' с зарплатой 9660, его возраст 59
Tom работает в компании 'Apple' с зарплатой 7721, его возраст 50
Mike работает в компании 'Siemens' с зарплатой 16917, его возраст 30
Charlie работает в компании 'IBM' с зарплатой 18931, его возраст 57
Alex работает в компании 'General Electric' с зарплатой 12062, его возраст 34
Alex работает в компании 'Apple' с зарплатой 4619, его возраст 35

== Сортировка по имени ==
Alex работает в компании 'IBM' с зарплатой 16954, его возраст 22
Alex работает в компании 'Samsung' с зарплатой 17825, его возраст 58
Alex работает в компании 'Google' с зарплатой 23200, его возраст 30
Alex работает в компании 'General Electric' с зарплатой 12062, его возраст 34
Alex работает в компании 'Apple' с зарплатой 4619, его возраст 35
Charlie работает в компании 'IBM' с зарплатой 20259, его возраст 46
Charlie работает в компании 'IBM' с зарплатой 18931, его возраст 57
Elizabeth работает в компании 'IBM' с зарплатой 14874, его возраст 33
Jack работает в компании 'IBM' с зарплатой 17816, его возраст 39
Jack работает в компании 'Google' с зарплатой 16294, его возраст 30
Jenifer работает в компании 'Microsoft' с зарплатой 5364, его возраст 25
Linda работает в компании 'Samsung' с зарплатой 22561, его возраст 60
Max работает в компании 'Apple' с зарплатой 6779, его возраст 52
Mike работает в компании 'IBM' с зарплатой 9660, его возраст 59
Mike работает в компании 'Siemens' с зарплатой 16917, его возраст 30
Peter работает в компании 'General Electric' с зарплатой 12097, его возраст 60
Peter работает в компании 'General Electric' с зарплатой 7531, его возраст 21
Tom работает в компании 'Microsoft' с зарплатой 20392, его возраст 27
Tom работает в компании 'General Electric' с зарплатой 13647, его возраст 54
Tom работает в компании 'Apple' с зарплатой 7721, его возраст 50

== Сортировка по имени и зарплете ==
Alex работает в компании 'Apple' с зарплатой 4619, его возраст 35
Alex работает в компании 'General Electric' с зарплатой 12062, его возраст 34
Alex работает в компании 'IBM' с зарплатой 16954, его возраст 22
Alex работает в компании 'Samsung' с зарплатой 17825, его возраст 58
Alex работает в компании 'Google' с зарплатой 23200, его возраст 30
Charlie работает в компании 'IBM' с зарплатой 18931, его возраст 57
Charlie работает в компании 'IBM' с зарплатой 20259, его возраст 46
Elizabeth работает в компании 'IBM' с зарплатой 14874, его возраст 33
Jack работает в компании 'Google' с зарплатой 16294, его возраст 30
Jack работает в компании 'IBM' с зарплатой 17816, его возраст 39
Jenifer работает в компании 'Microsoft' с зарплатой 5364, его возраст 25
Linda работает в компании 'Samsung' с зарплатой 22561, его возраст 60
Max работает в компании 'Apple' с зарплатой 6779, его возраст 52
Mike работает в компании 'IBM' с зарплатой 9660, его возраст 59
Mike работает в компании 'Siemens' с зарплатой 16917, его возраст 30
Peter работает в компании 'General Electric' с зарплатой 7531, его возраст 21
Peter работает в компании 'General Electric' с зарплатой 12097, его возраст 60
Tom работает в компании 'Apple' с зарплатой 7721, его возраст 50
Tom работает в компании 'General Electric' с зарплатой 13647, его возраст 54
Tom работает в компании 'Microsoft' с зарплатой 20392, его возраст 27

== Сортировка по имени и зарплете ==
Alex работает в компании 'Apple' с зарплатой 4619, его возраст 35
Alex работает в компании 'General Electric' с зарплатой 12062, его возраст 34
Alex работает в компании 'IBM' с зарплатой 16954, его возраст 22
Alex работает в компании 'Samsung' с зарплатой 17825, его возраст 58
Alex работает в компании 'Google' с зарплатой 23200, его возраст 30
Charlie работает в компании 'IBM' с зарплатой 18931, его возраст 57
Charlie работает в компании 'IBM' с зарплатой 20259, его возраст 46
Elizabeth работает в компании 'IBM' с зарплатой 14874, его возраст 33
Jack работает в компании 'Google' с зарплатой 16294, его возраст 30
Jack работает в компании 'IBM' с зарплатой 17816, его возраст 39
Jenifer работает в компании 'Microsoft' с зарплатой 5364, его возраст 25
Linda работает в компании 'Samsung' с зарплатой 22561, его возраст 60
Max работает в компании 'Apple' с зарплатой 6779, его возраст 52
Mike работает в компании 'IBM' с зарплатой 9660, его возраст 59
Mike работает в компании 'Siemens' с зарплатой 16917, его возраст 30
Peter работает в компании 'General Electric' с зарплатой 7531, его возраст 21
Peter работает в компании 'General Electric' с зарплатой 12097, его возраст 60
Tom работает в компании 'Apple' с зарплатой 7721, его возраст 50
Tom работает в компании 'General Electric' с зарплатой 13647, его возраст 54
Tom работает в компании 'Microsoft' с зарплатой 20392, его возраст 27
*/