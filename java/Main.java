public class Main {
    public static void main(String[] args) {
        // 使用默认构造方法创建对象
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();

        System.out.println("------------------------");

        // 使用带参数的构造方法创建对象
        ERyder bike2 = new ERyder("EB-001", 75, true, 125.5);
        bike2.ride();
        bike2.printBikeDetails();
    }
}