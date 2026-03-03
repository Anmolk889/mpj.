import java.util.Scanner;


class PrimeCheck {
    boolean isPrime(int num) {
        if (num <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}


class NearestPrime extends PrimeCheck {

    void findPrime(int n) {
        int nearest = n;
        int distance = 0;

        while (true) {
            if (isPrime(n - distance)) {
                nearest = n - distance;
                break;
            }
            if (isPrime(n + distance)) {
                nearest = n + distance;
                break;
            }
            distance++;
        }

        System.out.println("Nearest Prime Number: " + nearest);

        System.out.println("Next 5 Prime Numbers:");
        int count = 0;
        int num = nearest + 1;

        while (count < 5) {
            if (isPrime(num)) {
                System.out.println(num);
                count++;
            }
            num++;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        NearestPrime obj = new NearestPrime();
        obj.findPrime(n);

        sc.close();
    }
}