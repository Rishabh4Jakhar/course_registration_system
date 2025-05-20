import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        System.out.println("Welcome to the University Course Registration System");
        System.out.println("Please select an option:");
        System.out.println("1. Enter");
        System.out.println("2. Exit");
        int input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        if (input == 1) {
            Login.enterSystem();
        } else if (input == 2) {
            System.out.println("Exiting the system...");
            Login.exitSystem();
        } else {
            System.out.println("Invalid input. Exiting the system...");
            Login.exitSystem();
        }
    }
}