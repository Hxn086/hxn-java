import java.util.List;

public class RentalService {
    private ActiveRental activeRental;

    public RentalService(ActiveRental activeRental) {
        this.activeRental = activeRental;
    }

    public void startRental(Bike bike, RegisteredUsers user) {
        activeRental.addRental(bike, user);
    }

    public void endRental(Bike bike) {
        activeRental.removeRental(bike);
    }

    public void cancelRental(Bike bike) {
        activeRental.removeRental(bike);
    }

    public List<ActiveRental.RentalRecord> trackActiveRentals() {
        return activeRental.getRentalRecords();
    }
}