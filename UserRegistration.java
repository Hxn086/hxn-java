import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {

    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;

    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    private Scanner scanner = new Scanner(System.in);

    public void registration() {
        minorAndBirthday = false;
        minor = false;

        System.out.println("Welcome to ERyder Registration.");
        System.out.println("You have two options:");
        System.out.println("1. Register as Regular User");
        System.out.println("2. Register as VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }

        System.out.print("Please enter your full name: ");
        fullName = scanner.nextLine();

        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.print("Please enter date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("Only VISA, MasterCard, and American Express cards are accepted.");
        System.out.print("Please enter card number: ");
        cardNumber = scanner.nextLong();
        scanner.nextLine();
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.print("Please enter card expiry date (MM/YY): ");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("Please enter card CVV: ");
        cvv = scanner.nextInt();
        scanner.nextLine();
        validCVV = analyseCVV(cvv);

        finalCheckpoint();

        scanner.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Returning to registration start.");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();

        boolean isBirthday = (dob.getMonth() == now.getMonth()
                && dob.getDayOfMonth() == now.getDayOfMonth());

        if (userType.equals("VIP User")) {
            if (age >= 13 && age <= 18) {
                if (isBirthday) {
                    System.out.println("Happy Birthday! Born today and under 18, you get a 25% discount on VIP fee!");
                    minorAndBirthday = true;
                } else {
                    System.out.println("Under 18 – you get a 20% discount on VIP subscription!");
                    minor = true;
                }
            }
        }

        if (age <= 12 || age > 120) {
            System.out.println("You appear to be too young or too old. Sorry, you cannot be our user. Have a nice day.");
            System.exit(0);
        }

        return true;
    }

    private boolean analyseCardNumber(long cardNum) {
        String cardNumStr = String.valueOf(cardNum);
        int len = cardNumStr.length();

        if (len < 2) {
            System.out.println("Sorry, we only accept VISA, MasterCard, or American Express. Try again with valid card. Returning to start.");
            registration();
            return false;
        }

        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = len >= 4 ? Integer.parseInt(cardNumStr.substring(0, 4)) : 0;

        if ((len == 13 || len == 16) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        }

        if (len == 16) {
            if ((firstTwoDigits >= 51 && firstTwoDigits <= 55)
                    || (firstFourDigits >= 2221 && firstFourDigits <= 2720)) {
                cardProvider = "MasterCard";
                return true;
            }
        }

        if (len == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }

        System.out.println("Sorry, we only accept VISA, MasterCard, or American Express. Try again with valid card. Returning to start.");
        registration();
        return false;
    }

    private boolean analyseCardExpiryDate(String expiry) {
        try {
            int month = Integer.parseInt(expiry.substring(0, 2));
            int year = 2000 + Integer.parseInt(expiry.substring(3, 5));

            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            if (year > currentYear || (year == currentYear && month >= currentMonth)) {
                System.out.println("Card is still valid.");
                return true;
            } else {
                System.out.println("Sorry, your card has expired. Use another card. Returning to registration start.");
                registration();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid expiry format. Returning to registration start.");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        boolean valid = false;

        if (cardProvider == null) {
            valid = false;
        } else if (cardProvider.equals("American Express") && cvvStr.length() == 4) {
            valid = true;
        } else if ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3) {
            valid = true;
        }

        if (valid) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV. Returning to registration start.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("\nSorry, your registration failed for the following reason(s):");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Returning to registration start.");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardStr = String.valueOf(cardNumber);
        String lastFour = cardStr.substring(cardStr.length() - 4);
        System.out.printf("Thank you for your payment. Charged %.2f to your card ending in %s.%n", feeToCharge, lastFour);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        int len = cardNumberStr.length();

        String censoredPart = cardNumberStr.substring(0, len - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(len - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "\nRegistration Successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate + "\n";
    }
}