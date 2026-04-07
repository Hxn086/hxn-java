public class RegularUser extends RegisteredUsers {
    public RegularUser(String fullName, String emailAddress, String phoneNumber) {
        super(fullName, emailAddress, phoneNumber);
    }

    @Override
    public double calculateFare(double baseFare) {
        return super.calculateFare(baseFare);
    }

    @Override
    public void displayUserType() {
        super.displayUserType();
    }
}