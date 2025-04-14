/**
 * The Login class handles user registration and login functionality.
 * It includes methods for validating usernames, passwords, and cellphone numbers,
 * and manages login status based on registered credentials.
 *
 * Author: Manuel Mavungo
 * DISD G2 ST10466974
 */
public class Login {

    //Attributes
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellphoneNumber;

    /**
     * Constructs a new Login object with the given user details.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's chosen username
     * @param password the user's chosen password
     * @param cellphoneNumber the user's cellphone number
     */
    public Login(String firstName, String lastName, String username, String password, String cellphoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    /**
     * Validates if the username is correctly formatted.
     * A valid username must contain an underscore and be no more than 5 characters.
     *
     * @param username the username to validate
     * @return true if valid, false otherwise
     */
    static boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    /**
     * Validates if the password meets complexity requirements.
     * A valid password must be at least 8 characters long and include at least
     * one uppercase letter, one number, and one special character.
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#_$%^&*()].*");
    }

    /**
     * Validates if the cellphone number is correctly formatted.
     * A valid number must start with +27 and contain 9 additional digits.
     *
     * @param cellphoneNumber the phone number to validate
     * @return true if valid, false otherwise
     */
    static boolean checkCellPhoneNumber(String cellphoneNumber) {
        String pattern = "^\\+27\\d{9}$";
        return cellphoneNumber.matches(pattern);
    }

    /**
     * Validates the username, password, and phone number.
     * If all inputs are valid, updates the user's credentials and confirms registration.
     *
     * @param username the username to validate and register
     * @param password the password to validate and register
     * @param cellphoneNumber the phone number to validate and register
     * @return a message indicating the result of the registration
     */
    public String registerUser(String username, String password, String cellphoneNumber) {
        StringBuilder messages = new StringBuilder();
        boolean allValid = true;

        String usernameMessage = checkUserName(username) ? "Username is correctly formatted." : "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        if (!usernameMessage.equals("Username is correctly formatted.")) {
            allValid = false;
        }
        messages.append(usernameMessage).append("\n");

        String passwordMessage = checkPasswordComplexity(password) ? "Password is correctly formatted." : "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        if (!passwordMessage.equals("Password is correctly formatted.")) {
            allValid = false;
        }
        messages.append(passwordMessage).append("\n");

        String phoneMessage = checkCellPhoneNumber(cellphoneNumber) ? "Cell number successfully captured." : "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again";
        if (!phoneMessage.equals("Cell number successfully captured.")) {
            allValid = false;
        }
        messages.append(phoneMessage).append("\n");

        if (allValid) {
            this.username = username;
            this.password = password;
            this.cellphoneNumber = cellphoneNumber;
            messages.append("User registered successfully.");
        } else {
            messages.append("\nFix the errors above and try again.");
        }

        return messages.toString().trim();
    }

    /**
     * Checks if the provided login credentials match the registered details.
     *
     * @param inputUsername the username entered during login
     * @param inputPassword the password entered during login
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(this.password);
    }

    /**
     * Returns the login status message based on input credentials.
     *
     * @param inputUsername the username entered
     * @param inputPassword the password entered
     * @return a welcome message if credentials are valid, otherwise an error message
     */
    String returnLoginStatus(String inputUsername, String inputPassword) {
        if (loginUser(inputUsername, inputPassword)) {
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again!";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }
}