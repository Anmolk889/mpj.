import java.util.Scanner;

// The shapes class should be output the shapeinput class
class Shapes {
    // constructor overloading
    Shapes(double side) {
        System.out.println("Area of Square: " + (side * side));
    }

    Shapes(double l, double w) {
        System.out.println("Area of Rectangle: " + (l * w));
    }

    // method overloading
    void calculateArea(double radius) {
        System.out.println("Area of Circle: " + (3.14 * radius * radius));
    }

    void calculateArea(double base, double height) {
        System.out.println("Area of Triangle: " + (0.5 * base * height));
    }
}

public class ShapeInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Shape Area Calculator ---");
        System.out.println("1. Square (Constructor Overloading)");
        System.out.println("2. Rectangle (Constructor Overloading)");
        System.out.println("3. Circle (Method Overloading)");
        System.out.println("4. Triangle (Method Overloading)");
        System.out.print("Choose (1-4): ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter side: ");
                new Shapes(sc.nextDouble());
                break;
            case 2:
                System.out.print("Enter length and width: ");
                new Shapes(sc.nextDouble(), sc.nextDouble());
                break;
            case 3:
                System.out.print("Enter radius: ");
                Shapes c = new Shapes(0.0); // Changed to 0.0 for double
                c.calculateArea(sc.nextDouble());
                break;
            case 4:
                System.out.print("Enter base and height: ");
                Shapes t = new Shapes(0.0);
                t.calculateArea(sc.nextDouble(), sc.nextDouble());
                break;
            default:
                System.out.println("Invalid Choice!");
        }
        sc.close();
    }
}