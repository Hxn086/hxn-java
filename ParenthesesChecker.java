import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ParenthesesChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a parentheses string: ");
        String input = scanner.nextLine();
        scanner.close();

        if (isBalanced(input)) {
            System.out.println("Parentheses are balanced and paired!");
        } else {
            System.out.println("Parentheses are unbalanced or unpaired!");
        }
    }

    public static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}