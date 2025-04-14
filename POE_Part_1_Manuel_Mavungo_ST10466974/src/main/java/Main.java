import java.util.Scanner;

/*
 * Java POE Assignment - Part 1
 * Author: Manuel Mavungo
 * Student Number: ST10466974
 * Class: DISD G2
 *
 * Description:
 * This program allows a user to register with input validation
 * and then log in using the provided credentials.
 * Registration enforces username and password format requirements.
 */

public class Main {

    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        Login user;
        String registrationMessage;

        // ===== USER REGISTRATION =====
        // Loop until user provides valid input that meets the registration rules

        while (true) {

            // Prompt for user details with validation rules:
            // - Username must be <= 5 characters and contain an underscore
            // - Password must be >= 8 characters with a capital letter, number, and special character
            // - Phone number must follow South African format (+27xxxxxxxxx)

            System.out.println("=*= User Registration =*=");
            System.out.print("Enter User's First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter User's Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter Username (max 5 characters and must include '_'): ");
            String username = scanner.nextLine();

            System.out.print("Enter Password (min 8 characters, must include a capital letter, number, special character): ");
            String password = scanner.nextLine();

            System.out.print("Enter Phone Number (format: +27xxxxxxxxx): ");
            String cellphoneNumber = scanner.nextLine();

            // Create a Login object with the provided user details
            user = new Login(firstName, lastName, username, password, cellphoneNumber);
            
            // Call registerUser to validate and store user credentials
            registrationMessage = user.registerUser(username, password, cellphoneNumber);
            System.out.println(registrationMessage);

            // If registration is successful, break the loop
            if (registrationMessage.contains("User registered successfully.")) {
                break;
            }

            System.out.println("\nPlease re-enter your details.\n");
        }

        // ===== USER LOGIN =====
        // Loop until correct credentials are entered
        System.out.println("\n=*= User Login =*=");
        while (true) {
            // Prompt for username and password
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            // Call returnLoginStatus to validate credentials
            String loginMessage = user.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginMessage);

            // If login is successful, display welcome message and exit loop
            if (loginMessage.startsWith("Welcome")) {
                break;
            }

            System.out.println("Please try again.\n");
        }

        // Close the scanner to free up system resources
        scanner.close();
    }
}