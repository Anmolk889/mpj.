import java.util.Scanner;


class Employee {

    double salary;

    Employee(double s) {
        salary = s;
    }

    void displaySalary(double newSalary) {
        System.out.println("Salary Before Hike : " + salary);
        System.out.println("Salary After Hike  : " + newSalary);
        System.out.println("---------------------------");
    }
}

class FullTimeEmployee extends Employee {

    FullTimeEmployee(double s) {
        super(s);
    }

    
    void calculateSalary() {
        double newSalary = salary + (salary * 0.50);
        System.out.println("Full Time Employee");
        displaySalary(newSalary);
    }
}


class InternEmployee extends Employee {

    InternEmployee(double s) {
        super(s);
    }

  
    void calculateSalary() {
        double newSalary = salary + (salary * 0.25);
        System.out.println("Intern Employee");
        displaySalary(newSalary);
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Full Time Employees: ");
        int fCount = Integer.parseInt(sc.nextLine());

        System.out.print("Enter number of Intern Employees: ");
        int iCount = Integer.parseInt(sc.nextLine());

        
        for (int i = 0; i < fCount; i++) {

            System.out.print("\nEnter salary of Full Time Employee " + (i + 1) + ": ");
            double sal = Double.parseDouble(sc.nextLine());

            FullTimeEmployee fte = new FullTimeEmployee(sal);
            fte.calculateSalary();
        }

       
        for (int i = 0; i < iCount; i++) {

            System.out.print("\nEnter salary of Intern Employee " + (i + 1) + ": ");
            double sal = Double.parseDouble(sc.nextLine());

            InternEmployee intern = new InternEmployee(sal);
            intern.calculateSalary();
        }

        sc.close();
    }
}
