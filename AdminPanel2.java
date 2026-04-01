import java.util.Scanner;

public class AdminPanel {
    private final UserService userService;
    private final BikeService bikeService;
    private final RentalService rentalService;
    private final Scanner scanner;

    public AdminPanel(UserService userService, BikeService bikeService, RentalService rentalService) {
        this.userService = userService;
        this.bikeService = bikeService;
        this.rentalService = rentalService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("===== eRyder Admin Panel =====");
        System.out.println("1. User Management");
        System.out.println("2. Bike Management");
        System.out.println("3. Rental Management");
        System.out.println("0. Exit System");
        System.out.println("============================");
    }

    public void acceptUserInput() {
        int choice;
        do {
            displayMenu();
            System.out.print("Please enter your operation option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> handleUserManagement();
                case 2 -> handleBikeManagement();
                case 3 -> handleRentalManagement();
                case 0 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid option, please try again!");
            }
        } while (choice != 0);
    }

    private void handleUserManagement() {
        System.out.println("[User Management] Specific operations to be implemented, call UserService for execution");
    }

    private void handleBikeManagement() {
        System.out.println("[Bike Management] Specific operations to be implemented, call BikeService for execution");
    }

    private void handleRentalManagement() {
        System.out.println("[Rental Management] Specific operations to be implemented, call RentalService for execution");
    }

    public static void main(String[] args) {
        RegisteredUsers registeredUsers = new RegisteredUsers();
        BikeDatabase bikeDatabase = new BikeDatabase();
        ActiveRental activeRental = new ActiveRental();

        UserService userService = new UserService(registeredUsers);
        BikeService bikeService = new BikeService(bikeDatabase);
        RentalService rentalService = new RentalService(activeRental);

        AdminPanel adminPanel = new AdminPanel(userService, bikeService, rentalService);
        adminPanel.acceptUserInput();
    }
}