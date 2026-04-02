import java.time.LocalDateTime;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

public class BikeService {
public static Stack<ERyderLog> logStack = new Stack<>();
public static Queue<BikeRequest> bikeRequest = new ArrayDeque<>();

    public boolean reserveBike(String bikeID, String userEmail, String location) {
        boolean isBikeAvailable = checkBikeAvailability(bikeID, location);

        if (isBikeAvailable) {
            String logId = "BR" + (int)(Math.random() * 1000); 
            String eventDesc = "Bike with " + bikeID + " was rented by " + userEmail + " from location " + location;
            LocalDateTime now = LocalDateTime.now();
            ERyderLog rentLog = new ERyderLog(logId, eventDesc, now);
            logStack.push(rentLog);
            return true;
        } else {
            LocalDateTime requestTime = LocalDateTime.now();
            BikeRequest request = new BikeRequest(userEmail, location, requestTime);
            bikeRequest.add(request); 
            return false;
        }
    }

    public void removeTrip(String bikeID, String location) {
        endTripCoreLogic(bikeID);

        String logId = "TE" + (int)(Math.random() * 1000); // TE=Trip End
        String eventDesc = "Trip for bike " + bikeID + " ended at location " + location;
        LocalDateTime now = LocalDateTime.now();
        ERyderLog tripEndLog = new ERyderLog(logId, eventDesc, now);
        logStack.push(tripEndLog);

        if (!bikeRequest.isEmpty()) {
            BikeRequest nextRequest = bikeRequest.poll(); 
            assignBikeToUser(nextRequest, bikeID);
            System.out.println("Assigned bike " + bikeID + " to pending request: " + nextRequest);
        }
    }

    public void startTrip(String bikeID, String location) {
        startTripCoreLogic(bikeID);

        String logId = "TS" + (int)(Math.random() * 1000); // TS=Trip Start
        String eventDesc = "Trip for bike " + bikeID + " started at location " + location;
        LocalDateTime now = LocalDateTime.now();
        ERyderLog tripStartLog = new ERyderLog(logId, eventDesc, now);
        logStack.push(tripStartLog);
    }

    public void viewSystemLogs() {
        if (logStack.isEmpty()) {
            System.out.println("No system logs available.");
            return;
        }
        for (ERyderLog log : logStack) {
            System.out.println(log); 
        }
    }

    private boolean checkBikeAvailability(String bikeID, String location) { return false; }
    private void endTripCoreLogic(String bikeID) {}
    private void startTripCoreLogic(String bikeID) {}
    private void assignBikeToUser(BikeRequest request, String bikeID) {}
}