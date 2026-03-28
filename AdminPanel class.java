import java.util.Scanner;

public class AdminPanel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ERyder Admin Panel =====");
            System.out.println("1. Demo the Bike Rental System");
            System.out.println("0. Exit the system");
            System.out.print("Please select an operation option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BikeRental bikeRental = new BikeRental();
                    bikeRental.simulateApplication();
                    break;
                case 0:
                    System.out.println("Exited the system successfully!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please select again!");
            }
        }
    }
}