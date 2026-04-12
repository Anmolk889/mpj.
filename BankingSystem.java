import java.io.*;
import java.util.*;

// Custom Exceptions
class InvalidAmountException extends Exception {
    public InvalidAmountException(String msg) {
        super(msg);
    }
}

class InvalidCIDException extends Exception {
    public InvalidCIDException(String msg) {
        super(msg);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class DuplicateIDException extends Exception {
    public DuplicateIDException(String msg) {
        super(msg);
    }
}

// Customer Class
class Customer implements Serializable {
    int cid;
    String cname;
    double amount;

    Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }

    void display() {
        System.out.println(cid + " | " + cname + " | " + amount);
    }
}

// Main Class
public class BankingSystem {

    static final String FILE_NAME = "customers.dat";

    // ----------- Read all customers -----------
    static List<Customer> readCustomers() {
        List<Customer> list = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return list;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            list = (List<Customer>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    // Write all customers
    static void writeCustomers(List<Customer> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // ----------- Check Duplicate ID -----------
    static boolean idExists(List<Customer> list, int cid) {
        for (Customer c : list) {
            if (c.cid == cid) return true;
        }
        return false;
    }

    // ----------- Main Menu -----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Customer> customers = readCustomers();

        int choice;

        do {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display All");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {

                    // ----------- CREATE ACCOUNT -----------
                    case 1:
                        System.out.print("Enter CID: ");
                        int cid = sc.nextInt();

                        if (cid < 1 || cid > 20)
                            throw new InvalidCIDException("CID must be between 1 and 20");

                        if (idExists(customers, cid))
                            throw new DuplicateIDException("CID already exists!");

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Amount: ");
                        double amt = sc.nextDouble();

                        if (amt < 1000)
                            throw new InvalidAmountException("Minimum balance is 1000");

                        customers.add(new Customer(cid, name, amt));
                        writeCustomers(customers);
                        System.out.println("Account Created Successfully!");
                        break;

                    // ----------- DEPOSIT -----------
                    case 2:
                        System.out.print("Enter CID: ");
                        cid = sc.nextInt();

                        System.out.print("Enter Deposit Amount: ");
                        amt = sc.nextDouble();

                        if (amt <= 0)
                            throw new InvalidAmountException("Amount must be positive");

                        for (Customer c : customers) {
                            if (c.cid == cid) {
                                c.amount += amt;
                                writeCustomers(customers);
                                System.out.println("Deposit Successful!");
                                break;
                            }
                        }
                        break;

                    // ----------- WITHDRAW -----------
                    case 3:
                        System.out.print("Enter CID: ");
                        cid = sc.nextInt();

                        System.out.print("Enter Withdraw Amount: ");
                        amt = sc.nextDouble();

                        if (amt <= 0)
                            throw new InvalidAmountException("Amount must be positive");

                        for (Customer c : customers) {
                            if (c.cid == cid) {
                                if (amt > c.amount)
                                    throw new InsufficientBalanceException("Not enough balance!");

                                c.amount -= amt;
                                writeCustomers(customers);
                                System.out.println("Withdrawal Successful!");
                                break;
                            }
                        }
                        break;

                    // ----------- DISPLAY -----------
                    case 4:
                        System.out.println("\nCID | Name | Balance");
                        for (Customer c : customers) {
                            c.display();
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InvalidCIDException | InvalidAmountException |
                     InsufficientBalanceException | DuplicateIDException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }
}
