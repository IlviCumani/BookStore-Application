import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import java.awt.*;
import java.util.concurrent.TimeoutException;

public class LoginTestFX extends FxTestingBase{
    final FxRobot robot = new FxRobot();
    final String username = "Admin";
    final String password = "1";
    final String wrongPassword = "2";
    final String wrongUsername = "admin2";
    String emailTextField = "#EmailTextField";
    String passwordTextFieldId = "#PasswordTextField";
    final String LOGIN_BUTTON_ID = "#LogInBtn";


    @Test
    public void testLoginWithCorrectCredentials(){
        Node emailNode = find(emailTextField);
        Node passwordNode = find(passwordTextFieldId);
        Node loginBtnNode = find(LOGIN_BUTTON_ID);

        TextField emailTf = (TextField) emailNode;
        TextField passwordTf = (TextField) passwordNode;
        Button loginBtn = (Button) loginBtnNode;

        emailTf.setText(username);
        passwordTf.setText(password);
//        loginBtn.fire();

//        robot.clickOn(emailTf);
//        robot.write(username);
//        robot.clickOn(passwordTf);
//        robot.write(password);
//        robot.clickOn(loginBtn);

//        robot.doubleClickOn(emailTf);
        sleep(30000);
    }
}
