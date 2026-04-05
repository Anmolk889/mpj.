import java.io.*;
import java.util.*;

// User-defined exception class
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

class Customer implements Serializable {
    int cid;
    String cname;
    double amount;

    public Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return cid + "," + cname + "," + amount;
    }
}

public class BankingSystem {
    private static final String FILE_NAME = "customers.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Display All Customers");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        createAccount(sc);
                        break;
                    case 2:
                        displayAccounts();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (BankingException e) {
                System.out.println("Banking Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Clear buffer
            }
        }
    }

    public static void createAccount(Scanner sc) throws BankingException, IOException {
        System.out.print("Enter Customer ID (1-20): ");
        int id = sc.nextInt();
        if (id < 1 || id > 20) {
            throw new BankingException("Customer ID must be between 1 and 20.");
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.next();

        System.out.print("Enter Initial Deposit (Min 1000): ");
        double amt = sc.nextDouble();
        if (amt < 0) {
            throw new BankingException("Amount cannot be negative.");
        }
        if (amt < 1000) {
            throw new BankingException("Minimum 1000 Rs required to open account.");
        }

        Customer c = new Customer(id, name, amt);

        // File I/O: writing to file (append mode)
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(c.toString());
        writer.newLine();
        writer.close();

        System.out.println("Account created successfully and saved to file.");
    }

    public static void displayAccounts() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\nID\tName\tBalance");
        System.out.println("-----------------------");

        // File I/O: reading from file
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println(data[0] + "\t" + data[1] + "\t" + data[2]);
        }
        reader.close();
    }
}