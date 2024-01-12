import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

public class FxTestingBase extends ApplicationTest {

    //Please god give me the strength to finish this project, give me your blessing and help me write this code as fast as possible
    //Amen

    // The winds of change are blowing, and they are blowing in my favor


    @BeforeEach//Before each test, launch the window
    public void setUpClass() throws Exception {
        ApplicationTest.launch(App.class);
    }

    @Override//
    public void start (Stage stage) throws Exception {
        stage.show();
    }

    @AfterEach//After each test, close the window
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    /* Helper method to retrieve Java FX GUI components */
    public <T extends javafx.scene.Node> T find(final String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }
}
