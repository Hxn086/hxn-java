public class VIPUser extends RegisteredUsers {
    public VIPUser(String fullName, String emailAddress, String phoneNumber) {
        super(fullName, emailAddress, phoneNumber);
    }

    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 0.8;
    }

    @Override
    public void displayUserType() {
        System.out.println("VIP User");
    }
}