import java.util.Scanner;

public class AdminPanel {
    private BikeService bikeService;
    private Scanner scanner;

    public AdminPanel() {
        this.bikeService = new BikeService();
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n=== ERyder Admin Panel ===");
            System.out.println("1. Original Option 1 (Retain your original options)");
            System.out.println("2. Original Option 2 (Retain your original options)");
            System.out.println("3. View System Logs (New)");
            System.out.println("4. Manage Pending Bike Requests (New)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 3:
                    bikeService.viewSystemLogs(); 
                    break;
                case 4:
                    managePendingRequests(); 
                    break;
                case 0:
                    System.out.println("Exiting Admin Panel...");
                    scanner.close();
                    return;
                default:
                    
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void managePendingRequests() {
        while (true) {
            System.out.println("\n=== Manage Pending Bike Requests ===");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue (Remove first element)");
            System.out.println("3. Exit (Return to main menu)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewRequestQueue();
                    break;
                case 2:
                    updateRequestQueue();
                    break;
                case 3:
                    return; 
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void viewRequestQueue() {
        if (BikeService.bikeRequest.isEmpty()) {
            System.out.println("No pending bike requests in queue.");
            return;
        }
        System.out.println("\n=== Pending Bike Requests ===");
        for (BikeRequest request : BikeService.bikeRequest) {
            System.out.println(request); 
        }
    }

    private void updateRequestQueue() {
        if (BikeService.bikeRequest.isEmpty()) {
            System.out.println("Queue is empty, nothing to remove.");
            return;
        }
        BikeRequest removedRequest = BikeService.bikeRequest.poll(); 
        System.out.println("Removed first pending request: " + removedRequest);
    }

    public static void main(String[] args) {
        new AdminPanel().showAdminMenu();
    }
}