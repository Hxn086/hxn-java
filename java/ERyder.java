public class ERyder {
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    public ERyder() {
        this.bikeID = "DEFAULT_ID";
        this.batteryLevel = 100;
        this.isAvailable = true;
        this.kmDriven = 0.0;
    }
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel); // 使用setter做参数校验
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("The bike is fine.");
        } else {
            System.out.println("The bike is not available.");
        }
    }

    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Is Available: " + isAvailable);
        System.out.println("Total Distance Driven: " + kmDriven + " km");
    }

    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("Invalid battery level! Setting to 0.");
            this.batteryLevel = 0;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}