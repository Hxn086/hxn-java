public class ERyder {
    // 类属性
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    // 默认构造方法
    public ERyder() {
        this.bikeID = "DEFAULT_ID";
        this.batteryLevel = 100;
        this.isAvailable = true;
        this.kmDriven = 0.0;
    }

    // 带参数的构造方法
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel); // 使用setter做参数校验
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    // ride()方法
    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("The bike is fine.");
        } else {
            System.out.println("The bike is not available.");
        }
    }

    // printBikeDetails()方法
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Is Available: " + isAvailable);
        System.out.println("Total Distance Driven: " + kmDriven + " km");
    }

    // Getter和Setter
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
        // 校验电池范围0-100
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