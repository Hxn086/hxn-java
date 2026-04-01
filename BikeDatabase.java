import java.util.ArrayList;
import java.util.List;

public class BikeDatabase {
    private List<Bike> bikes = new ArrayList<>();
    private List<String> validLocations = List.of("LocationA", "LocationB", "LocationC");

    public List<Bike> getBikes() { return bikes; }
    public List<String> getValidLocations() { return validLocations; }
}