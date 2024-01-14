package Style;

import IO.CompatibleTypes;
import IO.FileIO;
import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class WorkerForm {

    public void newWorkerForm(Stage primarystage, Worker activeWorker, ArrayList<Worker> listOfWorkers, FileIO fileIO)throws PermissionDeniedException {
        System.out.println("Hello From Worker Form");
        Stage stage = primarystage;
        SettingStyles settingStyles = new SettingStyles();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 10, 50, 10));
        pane.setVgap(15);
        pane.setHgap(10);
        pane.setStyle(settingStyles.getLoginRootStyle());

        TextField nameText = new TextField();
        nameText.setId("nameText");
        nameText.setPromptText("name");
        nameText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField emailText = new TextField();
        emailText.setId("emailText");
        emailText.setPromptText("email");
        emailText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField phoneText = new TextField();
        phoneText.setId("phoneText");
        phoneText.setPromptText("phone number");
        phoneText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField salaryText = new TextField();
        salaryText.setId("salaryText");
        salaryText.setPromptText("salary");
        salaryText.setStyle(settingStyles.getLoginTextFieldStyle());

        //Text Field for Phone
        PasswordField passwordText = new PasswordField();
        passwordText.setId("passwordText");
        passwordText.setPromptText("password");
        passwordText.setStyle(settingStyles.getLoginTextFieldStyle());

        //date picker to choose date
        DatePicker datePicker = new DatePicker();
        datePicker.setId("datePicker");
        datePicker.setPromptText("Date of birth");
        datePicker.setStyle(settingStyles.getDatePicker());
        datePicker.setPrefWidth(270);

        //Check box for permissions
        CheckBox javaCheckBox = new CheckBox("Sell Books");
        javaCheckBox.setIndeterminate(false);
        CheckBox PurchaseBooksCheckBox = new CheckBox("Purchase Books");
        javaCheckBox.setIndeterminate(false);
        CheckBox checkLibrariansCheckBox = new CheckBox("CheckLibrarians");
        javaCheckBox.setIndeterminate(false);

        GridPane gridPane = new GridPane();
        //Choice box for location
        ChoiceBox<String> locationChoiceBox = new ChoiceBox<>();
        locationChoiceBox.getItems().addAll("Librarian", "Manager", "Administrator");
        locationChoiceBox.setId("locationChoiceBox");
        locationChoiceBox.setValue("Librarian");
        locationChoiceBox.setStyle(settingStyles.getSearchListStyle());
        //setOnMouseEntered

        //Label for register
        Button buttonRegister = new Button("Register");
        buttonRegister.setStyle(settingStyles.getLogOutBtnStyle());
        buttonRegister.setId("buttonRegister");
        pane.add(nameText, 1, 0);
        pane.add(emailText, 1, 1);
        pane.add(phoneText, 1, 2);
        pane.add(salaryText, 1, 3);
        pane.add(passwordText, 1, 4);
        pane.add(datePicker, 1, 5);
        pane.add(locationChoiceBox, 1, 6);
        pane.add(buttonRegister, 1, 7);

        stage.setScene(new Scene(pane, 800, 600));
        stage.setFullScreen(true);
        stage.show();

        final Worker[] worker = {null};

        buttonRegister.setOnMouseClicked(e -> {
            System.out.println("Hello from button register");
            String name = nameText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();
            double salary = Double.parseDouble(salaryText.getText());
            String password = passwordText.getText();
            Date dateOfBirth =  java.sql.Date.valueOf(datePicker.getValue());
            AccessLevel accessLevel = null;
            if(locationChoiceBox.getSelectionModel().getSelectedItem().equals("Librarian")){
                accessLevel = new Librarian();
            }
            else if(locationChoiceBox.getSelectionModel().getSelectedItem().equals("Manager")){
                accessLevel = new Manager();
            }
            else if(locationChoiceBox.getSelectionModel().getSelectedItem().equals("Administrator")){
                accessLevel = new Administrator();
            }

            try {
                activeWorker.getAccessLevel().addNewWorker(listOfWorkers, new Worker(name, email, password, phone, dateOfBirth, accessLevel, salary));
                ArrayList<Serializable> listOfSerializables = CompatibleTypes.fromWorkerToSerializible(listOfWorkers);
                fileIO.write(listOfSerializables);
                stage.setScene(new Scene(new MainPage(stage, activeWorker, listOfWorkers).getRoot()));
                stage.setFullScreen(true);
                stage.show();
            } catch (PermissionDeniedException ex) {
                System.out.println(ex.getMessage());
            }
        });


    }
}
