import java.util.List;

public class UserService {
    private RegisteredUsers registeredUsers;

    public UserService(RegisteredUsers registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public boolean addUser(RegisteredUsers.User user) {
        return registeredUsers.getUsers().add(user);
    }

    public boolean removeUser(String userId) {
        return registeredUsers.getUsers().removeIf(user -> user.getUserId().equals(userId));
    }

    public boolean updateUser(RegisteredUsers.User updatedUser) {
        List<RegisteredUsers.User> users = registeredUsers.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }

    public RegisteredUsers.User retrieveUser(String userId) {
        return registeredUsers.getUsers().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}