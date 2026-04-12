import java.util.Scanner;

// parent class
class Hillstations {
    void famousFood() {
        System.out.println("Standard local street food.");
    }

    void famousFor() {
        System.out.println("Scenic views and pleasant weather.");
    }
}

// subclass 1
class Manali extends Hillstations {
    @Override
    void famousFood() {
        System.out.println("Manali Food: Siddu and Khatta.");
    }

    @Override
    void famousFor() {
        System.out.println("Manali is famous for: Rohtang Pass and Adventure Sports.");
    }
}

// subclass2
class Mussoorie extends Hillstations {
    @Override
    void famousFood() {
        System.out.println("Mussoorie Food: Aloo ke Gutke and Phaanu.");
    }

    @Override
    void famousFor() {
        System.out.println("Mussoorie is famous for: Kempty Falls and The Mall Road.");
    }
}

// Subclass 3
class Gulmarg extends Hillstations {
    @Override
    void famousFood() {
        System.out.println("Gulmarg Food: Rogan Josh and Wazwan.");
    }

    @Override
    void famousFor() {
        System.out.println("Gulmarg is famous for: Skiing and the Gondola Ride.");
    }
}

public class HillstationInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // parent class reference
        Hillstations h;

        System.out.println("--- Hill Station Information ---");
        System.out.println("Enter Choice: 1. Manali  2. Mussoorie  3. Gulmarg");
        int choice = sc.nextInt();

        // referencing the base class object using the Parent class variable
        if (choice == 1) {
            h = new Manali();
        } else if (choice == 2) {
            h = new Mussoorie();
        } else if (choice == 3) {
            h = new Gulmarg();
        } else {
            System.out.println("Invalid choice! Defaulting to generic Hillstation.");
            h = new Hillstations();
        }

        System.out.println("\nDisplaying details using Parent Class Reference:");
        // Runtime polymorphism: The overridden method of the child class is called
        h.famousFood();
        h.famousFor();

        sc.close();
    }
}