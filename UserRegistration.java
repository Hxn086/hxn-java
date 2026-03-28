public class UserRegistration {
    public void registration() {
        System.out.println("The user registration process is being completed... Registration is successful!");
    }

    public String validate(String location) {
        BikeRental bikeRental = new BikeRental();
        return bikeRental.validateLocation(location);
    }
}