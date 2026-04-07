public class RentalService {
    public static final double BASE_FARE = 3.0;

    public void simulateApplicationInput(RegisteredUsers user) {
        System.out.println("Rental application successful! User: " + user.getFullName() + ", User Type:");
        user.displayUserType();
        removeTrip(user);
    }

    public void removeTrip(RegisteredUsers user) {
        double finalFare = user.calculateFare(BASE_FARE);
        System.out.println("Trip ended! Base Fare: " + BASE_FARE + ", Final Fare: " + finalFare);
        System.out.println("------------------------");
    }
}