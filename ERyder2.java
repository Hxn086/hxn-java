public class ERyder {
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;

    public ERyder(String account, String phone) {
        this.LINKED_ACCOUNT = account;
        this.LINKED_PHONE_NUMBER = phone;
        this.isAvailable = true; // Start as available
    }

    public void setBikeID(String id) {
        this.bikeID = id;
    }

    public String getBikeID() {
        return this.bikeID;
    }

    public void setBatteryLevel(int level) {
        this.batteryLevel = level;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public double calculateRideCost(int minutes) {
        return BASE_FARE + (minutes * PER_MINUTE_FARE);
    }

    public void endRide() {
        this.isAvailable = true;
        System.out.println("Ride ended, bike returned.");
    }
}