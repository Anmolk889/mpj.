import java.util.Scanner;

class Student {

    int rollNo;
    String name;
    double marks;

    // Constructor
    Student(int r, String n, double m) {
        rollNo = r;
        name = n;
        marks = m;
    }

    // Method to display details
    void displayInfo() {
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Marks   : " + marks);
        System.out.println("---------------------");
    }
}

public class StudentDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = Integer.parseInt(sc.nextLine());

        // Array of objects
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details of Student " + (i + 1));

            System.out.print("Enter Roll No: ");
            int r = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double m = Double.parseDouble(sc.nextLine());

            // Creating object
            students[i] = new Student(r, name, m);
        }

        System.out.println("\n--- Student Details ---");

        // Displaying details
        for (int i = 0; i < n; i++) {
            students[i].displayInfo();
        }

        sc.close();
    }
}
