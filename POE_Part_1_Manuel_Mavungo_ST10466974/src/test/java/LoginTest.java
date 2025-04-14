/**
 * This class contains unit tests for the Login class.
 * It verifies the correctness of username, password, and cellphone number validation,
 * as well as user registration and login functionality.
 *
 * Author: Manuel Mavungo
 * DISD G2 ST10466974
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


class LoginTest {

    private Login login;

    /* Java POE Assignment part 1
     *  Author: Manuel Mavungo
     *  ST10466974 DISD G2
     */


    @BeforeEach
    void setUp() {
        login = new Login("Kyle", "Brown", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    /**
     * Tests a successful registration with correct username, password, and phone number,
     * followed by a successful login attempt.
     */
    @Test
    public void testUsernameFormatCorrect() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");

        String expected = "Welcome Kyle Brown, it is great to see you again!";
        String actual = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");

        assertEquals(expected, actual);
    }


    /**
     * Tests registration with an invalid username, password, and phone number.
     * Expects detailed error messages and a failed registration response.
     */
    @Test
    public void testRegisterUserFailure() {
        String expected = String.join("\n",
                "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.",
                "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again",
                "",
                "Fix the errors above and try again."
        );

        String result = login.registerUser("wrongname", "password", "08234567");

        assertEquals(expected, result);
    }

    /**
     * Tests registration with valid credentials.
     * Expects success messages for all validation checks and registration.
     */
    @Test
    public void testRegisterUserSuccess() {
        String expected = String.join("\n",
                "Username is correctly formatted.",
                "Password is correctly formatted.",
                "Cell number successfully captured.",
                "User registered successfully."
        );

        String result = login.registerUser("kyl_1", "P@ssword1", "+27831234567");

        assertEquals(expected, result);
    }



    //TEST (assertTrue/false)
    /**
     * Tests successful login after valid registration.
     */
    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Password1!", "+27123456789");

        assertTrue(login.loginUser("kyl_1", "Password1!")); // Expected: true
    }

    /**
     * Tests failed login attempts using incorrect username and password.
     */
    @Test
    public void testLoginFailure() {
        login.registerUser("js_", "Password1!", "+271234567890");

        assertFalse(login.loginUser("wrong", "Password1!"));  // wrong username
        assertFalse(login.loginUser("js_", "WrongPass"));     // wrong password
    }

    /**
     * Tests that a valid username passes validation.
     */
    @Test
    public void testUsernameCorrectlyFormatted() {
        boolean isValid = Login.checkUserName("usr_");
        assertTrue(isValid, "Expected the username to be correctly formatted.");
    }

    /**
     * Tests that an invalid username fails validation.
     */
    @Test
    public void testUsernameIncorrectlyFormatted() {
        boolean notValid = Login.checkUserName("user");
        assertFalse(notValid, "Expected the username to be incorrectly formatted (missing underscore or too long).");
    }

    /**
     * Tests that a valid password passes the complexity check.
     */
    @Test
    public void testPasswordTrue() {
        boolean isValid = Login.checkPasswordComplexity("P@ssword12");
        assertTrue(isValid, "Expected password to meet complexity requirements.");
    }

    /**
     * Tests that an invalid password fails the complexity check.
     */
    @Test
    public void testPasswordFalse() {
        boolean notValid = Login.checkPasswordComplexity("WordPass");
        assertFalse(notValid, "Expected password to fail complexity requirements.");
    }

    /**
     * Tests that a valid cellphone number (with +27) passes the format check.
     */
    @Test
    public void testCellPhoneTrue() {
        boolean isValid = Login.checkCellPhoneNumber("+27631337416");
        assertTrue(isValid, "Expected cellphone number to match valid format.");
    }

    /**
     * Tests that an invalid cellphone number fails the format check.
     */
    @Test
    public void testCellPhoneFalse() {
        boolean notValid = Login.checkCellPhoneNumber("0631337416");
        assertFalse(notValid, "Expected cellphone number to be invalid.");
    }


    /**
     * Tests multiple invalid usernames using parameterized input.
     * Each input is expected to fail the validation rules.
     */
    @ParameterizedTest
    @ValueSource(strings = { "username", "longname_", "no_underscore", "" })
    void testInvalidUsernames(String input) {
        assertFalse(Login.checkUserName(input), "Expected invalid username to fail validation: " + input);
    }
}
