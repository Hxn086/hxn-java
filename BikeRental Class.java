import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;

    private UserRegistration userRegistration = new UserRegistration();
    private ActiveRental activeRental;

    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    private Scanner scanner = new Scanner(System.in);

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.print("Registered user (true/false): ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();// Absorb line break
        System.out.print("Enter user email: ");
        emailAddress = scanner.nextLine();
        System.out.print("Enter rental location: ");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        if (!locationValid) {
            return;
        }

        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals…");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();

        scanner.close();
    }

    private String analyseRequest(boolean isRegistered, String emailAddress, String location) {
        if (isRegistered) {
            System.out.println("Welcome back, " + emailAddress + "!");
        } else {
            System.out.println("You’re not our registered user. Please consider registering.");
            userRegistration.registration();
        }
        return userRegistration.validate(location);
    }
    public String validateLocation(String location) {
        // Traverse bikes in the database
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
    }

    private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with ID " + bikeID + ". Please follow the on-screen instructions to locate the bike and start your pleasant journey.");
                    // Create ActiveRental object and add to the linked list
                    activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }

    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
    public void simulateApplication() {
        simulateApplicationInput();
    }
}