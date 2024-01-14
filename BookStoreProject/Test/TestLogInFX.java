import org.junit.jupiter.api.Test;

public class TestLogInFX extends TestAbstractSetUp{

    private final String logInButton = "#logInButton";
    private final String emailTextField = "#emailTextField";
    private final String passwordTextField = "#passwordTextField";



    @Test
    public void clickOnBogusElement() {
        clickOn("#logInButton");
        sleep(3000);
    }

    @Test
    public void test_logInWithCorrectEmailWrongPassword() {
        clickOn(emailTextField);
        sleep(1000);
        write("Admin");
        sleep(1000);
        clickOn(passwordTextField);
        sleep(1000);
        write("2");
        clickOn(logInButton);
        sleep(1000);
    }

    @Test
    public void test_logInWithWrongEmailWrongPassword() {
        clickOn(emailTextField);
        sleep(1000);
        write("Shapa");
        sleep(1000);
        clickOn(passwordTextField);
        sleep(1000);
        write("2");
        clickOn(logInButton);
        sleep(1000);
    }

    @Test
    public void test_logInWithRightEmailRightPasswordAsAdmin() {
        clickOn(emailTextField);
        sleep(1000);
        write("Admin");
        sleep(1000);
        clickOn(passwordTextField);
        sleep(1000);
        write("1");
        clickOn(logInButton);
        sleep(1000);
    }

    @Test
    public void test_logInWithRightEmailRightPasswordAsManager() {
        clickOn(emailTextField);
        sleep(1000);
        write("Manager");
        sleep(1000);
        clickOn(passwordTextField);
        sleep(1000);
        write("1");
        clickOn(logInButton);
        sleep(1000);
    }

    @Test
    public void test_logInWithRightEmailRightPasswordAsLibrarian() {
        clickOn(emailTextField);
        sleep(1000);
        write("Librarian");
        sleep(1000);
        clickOn(passwordTextField);
        sleep(1000);
        write("1");
        clickOn(logInButton);
        sleep(1000);
    }








}
