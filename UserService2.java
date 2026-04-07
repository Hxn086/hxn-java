import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public RegisteredUsers addNewUsers(String fullName, String emailAddress, String phoneNumber, String userType) {
        RegisteredUsers newUser;
        if (userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(fullName, emailAddress, phoneNumber);
        } else {
            newUser = new RegularUser(fullName, emailAddress, phoneNumber);
        }
        registeredUsersList.add(newUser); 
        return newUser; 
    }

    // Helper method: get all registered users
    public List<RegisteredUsers> getRegisteredUsersList() {
        return registeredUsersList;
    }
}