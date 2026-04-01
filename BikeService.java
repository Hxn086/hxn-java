import java.util.List;

public class BikeService {
    private BikeDatabase bikeDatabase;

    public BikeService(BikeDatabase bikeDatabase) {
        this.bikeDatabase = bikeDatabase;
    }

    public List<Bike> findAvailableBikes(String location) {
        return bikeDatabase.getBikes().stream()
                .filter(bike -> bike.isAvailable() && bike.getLocation().equals(location))
                .toList();
    }

    public boolean validateLocation(String location) {
        return bikeDatabase.getValidLocations().contains(location);
    }

    public boolean reserveBike(Bike bike) {
        if (bike.isAvailable()) {
            bike.setAvailable(false);
            return true;
        }
        return false;
    }

    public void releaseBike(Bike bike) {
        bike.setAvailable(true);
    }
}