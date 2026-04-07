public class AdminPanel {
    public static void main(String[] args) {
        UserService userService = new UserService();
        RentalService rentalService = new RentalService();
        RegisteredUsers vipUser = userService.addNewUsers("Zhang San", "zhangsan@test.com", "13800138000", "VIP");
        rentalService.simulateApplicationInput(vipUser);

        RegisteredUsers regularUser = userService.addNewUsers("Li Si", "lisi@test.com", "13900139000", "Regular");
        rentalService.simulateApplicationInput(regularUser);

        RegisteredUsers regularUser2 = userService.addNewUsers("Wang Wu", "wangwu@test.com", "13700137000", "regular");
        rentalService.simulateApplicationInput(regularUser2);
    }
}