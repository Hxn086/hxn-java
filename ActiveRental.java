import java.util.ArrayList;
import java.util.List;

public class ActiveRental {
    private List<RentalRecord> rentalRecords = new ArrayList<>();

    public static class RentalRecord {
        private Bike bike;
        private RegisteredUsers.User user;

        public RentalRecord(Bike bike, RegisteredUsers.User user) {
            this.bike = bike;
            this.user = user;
        }
    }

    public void addRental(Bike bike, RegisteredUsers.User user) {
        rentalRecords.add(new RentalRecord(bike, user));
    }

    public void removeRental(Bike bike) {
        rentalRecords.removeIf(record -> record.bike.getBikeId().equals(bike.getBikeId()));
    }

    public List<RentalRecord> getRentalRecords() { return rentalRecords; }
}