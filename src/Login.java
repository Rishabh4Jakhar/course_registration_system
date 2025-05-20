import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Login {
    private  static ArrayList<String> users = new ArrayList<>(Arrays.asList("2023435", "2022465", "2024052", "P01", "P02", "'P03"));
    private static ArrayList<String> passwords = new ArrayList<>(Arrays.asList("1234", "5678", "9101", "5678", "9101", "1901"));
    private static final String ADMIN_PASS = "0000";
    public static void enterSystem() throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        System.out.println("Welcome to the University Course Registration System");
        System.out.println("Please choose an option:");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Professor");
        System.out.println("3. Login as Admin");
        System.out.println("4. Exit");
        int input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        String username;
        String password;
        if (input == 1) {
            System.out.println("Enter your User ID:");
            username = scanner.next();
            System.out.println("Enter your password:");
            password = scanner.next();
            if (users.contains(username) && passwords.get(users.indexOf(username)).equals(password)) {
                System.out.println("Login successful");
                Student student = new Student(username, Data.getStudentData(username));
                Menu menu = new Menu(student);
            } else {
                try {
                    throw new InvalidLoginException("Invalid username or password");
                } catch (InvalidLoginException e) {
                    System.out.println(e);
                }
                System.out.println("Exiting to Main Menu...");
                enterSystem();
            }
        } else if (input == 2) {
            System.out.println("Enter your Professor ID:");
            username = scanner.next();
            System.out.println("Enter your password:");
            password = scanner.next();
            if (users.contains(username) && passwords.get(users.indexOf(username)).equals(password)) {
                System.out.println("Login successful");
                Professor professor = new Professor(username, Data.getProfData(username));
                Menu menu = new Menu(professor);
            } else {
                try {
                    throw new InvalidLoginException("Invalid username or password");
                }
                catch (InvalidLoginException e) {
                    System.out.println(e);
                }
                //System.out.println("Invalid username or password");
                System.out.println("Exiting to Main Menu...");
                enterSystem();
            }
        } else if (input == 3) {
            System.out.println("Enter the Admin password:");
            password = scanner.next();
            if (password.equals(ADMIN_PASS)) {
                System.out.println("Login successful");
                Admin admin = new Admin("ADMIN");
                Menu menu = new Menu(admin);
            } else {
                try {
                    throw new InvalidLoginException("Invalid password");
                } catch (InvalidLoginException e) {
                    System.out.println(e);
                }
                //System.out.println("Invalid password");
                System.out.println("Exiting to Main Menu...");
                enterSystem();
            }
        } else if (input == 4) {
            System.out.println("Exiting the system...");
            exitSystem();
        } else {
            System.out.println("Invalid input. Exiting to Main Menu...");
           enterSystem();
        }
    }
    public static void exitSystem() {
        System.exit(0);
    }
}
