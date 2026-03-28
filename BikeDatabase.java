import java.time.LocalDateTime;
import java.util.ArrayList;

public class BikeDatabase {
    public static ArrayList<Bike> bikes;

    static {
        bikes = new ArrayList<>();
        bikes.add(new Bike("B001", true, 85, LocalDateTime.now().minusHours(2), "Central Park"));
        bikes.add(new Bike("B002", false, 40, LocalDateTime.now().minusHours(1), "Downtown"));
        bikes.add(new Bike("B003", true, 90, LocalDateTime.now().minusHours(3), "Central Park"));
        bikes.add(new Bike("B004", true, 65, LocalDateTime.now().minusHours(1), "Airport"));
    }
}