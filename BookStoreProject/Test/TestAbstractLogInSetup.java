public abstract class TestAbstractLogInSetup extends TestAbstractSetUp{

    private final String logInButton = "#logInButton";
    private final String emailTextField = "#emailTextField";
    private final String passwordTextField = "#passwordTextField";

    void adminLogIn(){
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

    void managerLogIn(){
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

    void librarianLogIn(){
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

}
