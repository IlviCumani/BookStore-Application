import org.junit.jupiter.api.Test;

public class TestAddWorkerFx extends TestAbstractLogInSetup{

    private String nameText = "#nameText";

    private String emailText = "#emailText";

    private String phoneText = "#phoneText";

    private String salaryText = "#salaryText";

    private String passwordText = "#passwordText";

    private String datePicker = "#datePicker";

    private String buttonRegister = "#buttonRegister";

    public final String EmployeeTab = "#EmployeeTab";

    public final String addWorkerBtn = "#addWorkerBtn";

    public final String locationChoiceBox = "#locationChoiceBox";


    public String[] names = {"Ilvi", "Erli", "Nomi"};
    void createNewWorker(String role, String nameAndEmail) {
        this.adminLogIn();
        clickOn(EmployeeTab);
        sleep(3000);
        clickOn(addWorkerBtn);
        sleep(1000);
        clickOn(nameText);
        write(nameAndEmail);
        clickOn(emailText);
        write(nameAndEmail);
        clickOn(phoneText);
        write("455699191");
        clickOn(salaryText);
        write("700");
        clickOn(passwordText);
        write("1");
        clickOn(datePicker);
        sleep(1000);
        write("01/01/2002");
        sleep(1000);
        clickOn(locationChoiceBox);
        clickOn(role);
        sleep(1000);
        clickOn("#buttonRegister");
        sleep(2000);
        clickOn(EmployeeTab);
        sleep(3000);

    }

    @Test
    void createNewAdmin() {
        this.createNewWorker("Administrator", names[0]);
    }

    @Test
    void createNewManager() {
        this.createNewWorker("Manager", names[1]);
    }

    @Test
    void createNewLibrarian() {
        this.createNewWorker("Librarian", names[2]);
    }


}
