import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    List<RegisteredUsers> registeredUsers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("Welcome to E-Ryder Admininstrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Exiting Admin Panel...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again\n");
            }
            System.out.println("-------------------------\n");
        }
    }

    private void addNewUsers() {
        System.out.println(">>> Add New Users <<<");
        System.out.print("How many users would you like to add? ");
        int num;
        try {
            num = Integer.parseInt(scanner.nextLine());
            if (num <= 0) {
                System.out.println("Please enter a positive number!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
            return;
        }

        for (int i = 0; i < num; i++) {
            System.out.println("\nEnter details for User " + (i + 1) + ":");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Email Address: ");
            String email = scanner.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            System.out.print("Card Number: ");
            String cardNum = scanner.nextLine();
            System.out.print("Card Expiry Date: ");
            String cardExp = scanner.nextLine();
            System.out.print("Card Provider: ");
            String cardPro = scanner.nextLine();
            System.out.print("CVV: ");
            String cvv = scanner.nextLine();
            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\nTrip " + (j + 1) + " Details:");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();
                System.out.print("Source: ");
                String source = scanner.nextLine();
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();
                System.out.print("Feedback (NULL for no feedback): ");
                String feedback = scanner.nextLine();
                StringBuilder tripSb = new StringBuilder();
                tripSb.append("Date: ").append(tripDate)
                      .append(", Source: ").append(source)
                      .append(", Destination: ").append(dest)
                      .append(", Fare (€): ").append(fare)
                      .append(", Feedback: ").append(feedback);
                lastThreeTrips[j] = tripSb.toString();
            }

            RegisteredUsers newUser = new RegisteredUsers(fullName, email, dob, cardNum,
                                                          cardExp, cardPro, cvv, userType, lastThreeTrips);
            registeredUsers.add(newUser);
        }
        System.out.println("All users added successfully!");
    }

    private void viewRegisteredUsers() {
        System.out.println(">>> View Registered Users <<<");
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        for (RegisteredUsers user : registeredUsers) {
            System.out.println("\n" + user);
        }
    }

    private void removeRegisteredUsers() {
        System.out.println(">>> Remove Registered Users <<<");
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        System.out.print("Enter email address of the user to remove: ");
        String targetEmail = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> it = registeredUsers.iterator();
        while (it.hasNext()) {
            RegisteredUsers user = it.next();
            if (user.getEmailAddress().equals(targetEmail)) {
                it.remove();
                found = true;
                System.out.println("User removed successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        System.out.println(">>> Update Registered Users <<<");
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        System.out.print("Enter email address of the user to update: ");
        String targetEmail = scanner.nextLine();
        RegisteredUsers targetUser = null;

        for (RegisteredUsers user : registeredUsers) {
            if (user.getEmailAddress().equals(targetEmail)) {
                targetUser = user;
                break;
            }
        }
        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\nEnter new details (Press ENTER for no change | Enter 0 for number no change):");
        System.out.print("New Full Name: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) targetUser.setFullName(newName);

        System.out.print("New Date of Birth (YYYY-MM-DD): ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) targetUser.setDateOfBirth(newDob);

        System.out.print("New Card Number (0 for no change): ");
        String newCardNum = scanner.nextLine();
        if (!newCardNum.equals("0")) targetUser.setCardNumber(newCardNum);

        System.out.print("New Card Expiry Date: ");
        String newCardExp = scanner.nextLine();
        if (!newCardExp.isEmpty()) targetUser.setCardExpiryDate(newCardExp);

        System.out.print("New Card Provider: ");
        String newCardPro = scanner.nextLine();
        if (!newCardPro.isEmpty()) targetUser.setCardProvider(newCardPro);

        System.out.print("New CVV (0 for no change): ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0")) targetUser.setCvv(newCvv);

        System.out.print("New User Type: ");
        String newUserType = scanner.nextLine();
        if (!newUserType.isEmpty()) targetUser.setUserType(newUserType);

        System.out.println("User updated successfully!");
    }
}
