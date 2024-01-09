package Style;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Date;

public class WorkerForm {

    public Worker newWorkerForm(Stage primarystage) {
        System.out.println("Hello From Worker Form");
        Stage stage = primarystage;
        SettingStyles settingStyles = new SettingStyles();

        TextField nameText = new TextField();
        nameText.setPromptText("name");
        nameText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField emailText = new TextField();
        emailText.setPromptText("email");
        emailText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField phoneText = new TextField();
        phoneText.setPromptText("phone number");
        phoneText.setStyle(settingStyles.getLoginTextFieldStyle());

        TextField salaryText = new TextField();
        salaryText.setPromptText("salary");
        salaryText.setStyle(settingStyles.getLoginTextFieldStyle());

        //Text Field for Phone
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("password");
        passwordText.setStyle(settingStyles.getLoginTextFieldStyle());

        //date picker to choose date
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date of birth");
        datePicker.setStyle(settingStyles.getDatePicker());
        datePicker.setPrefWidth(270);

        //Toggle group of radio button
//        ToggleGroup groupGender = new ToggleGroup();
//        RadioButton maleRadio = new RadioButton("male");
//        maleRadio.setStyle(settingStyles.getRadioBtn());
//        maleRadio.setToggleGroup(groupGender);
//        RadioButton femaleRadio = new RadioButton("female");
//        femaleRadio.setStyle(settingStyles.getRadioBtn());
//        femaleRadio.setToggleGroup(groupGender);
//        HBox radioBox = new HBox(20);
//        radioBox.getChildren().addAll(maleRadio, femaleRadio);

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
        //locationChoiceBox.setValue("Librarian");
        locationChoiceBox.setStyle(settingStyles.getSearchListStyle());
        //setOnMouseEntered
        locationChoiceBox.setOnKeyPressed(e -> {
            KeyCombination keyCombination = KeyCombination.valueOf("Enter");

//            if (keyCombination.match(e)) {
//                if (locationChoiceBox.getValue().toString().equals("Librarian")) {
//                    gridPane.getChildren().removeAll(PurchaseBooksCheckBox, checkLibrariansCheckBox);
//                    gridPane.add(javaCheckBox, 1, 8);
//                } else if (locationChoiceBox.getValue().toString().equals("Manager")) {
//                    gridPane.getChildren().remove(javaCheckBox);
//                    gridPane.add(PurchaseBooksCheckBox, 1, 8);
//                    gridPane.add(checkLibrariansCheckBox, 1, 9);
//                } else {
//                    gridPane.getChildren().removeAll(javaCheckBox, PurchaseBooksCheckBox, checkLibrariansCheckBox);
//                }
//            }
        });

        //Label for register
        Button buttonRegister = new Button("Register");
//
//        buttonRegister.setOnMouseClicked(event ->{
//            String name = nameText.getText();
//            String email=emailText.getText();
//            String phone=phoneText.getText();
//            float salary=Float.parseFloat(salaryText.getText());
//            String password=passwordText.getText();
//            String date= datePicker.getValue().toString();
//            Gender gender;
//            if(maleRadio.isSelected())gender=Gender.MALE;
//            else gender=Gender.FEMALE;
//            boolean librarian=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Librarian");
//            boolean manager=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Manager");
//            boolean admin=locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Administrator");
//            boolean permitionToBill,permitionToPurchase,permitionToCheckLibrarians;
//            permitionToBill=javaCheckBox.isSelected();
//            permitionToPurchase=PurchaseBooksCheckBox.isSelected();
//            permitionToCheckLibrarians=checkLibrariansCheckBox.isSelected();
//
//            if (admin){
//                Worker worker=new Admin(name,phone,email,salary,date,gender,password, Worker.ACCESSLEVEL.ADMIN);
//                writeWorkerToFile(worker);
//                workerData.add(worker);
//                stage.close();
//
//            }
//            else if(manager){
//                Worker worker=new Manager(name,phone,email,salary,date,gender,password, Worker.ACCESSLEVEL.MANAGER,permitionToPurchase,permitionToCheckLibrarians);
//                writeWorkerToFile(worker);
//                workerData.add(worker);
//                stage.close();
//            }
//            else if(librarian){
//                Worker worker=new Librarian(name,phone,email,date,gender,salary,password, Worker.ACCESSLEVEL.LIBRARIAN,permitionToBill);
//                writeWorkerToFile(worker);
//                workerData.add(worker);
//                stage.close();
//            }
//            primarystage.setScene(new Scene(new MainPage(primarystage, workertemp).getRoot(),800, 600));
//            primarystage.setFullScreen(true);
//
//
//        });
        final Worker[] worker = {null};
        buttonRegister.setOnMouseClicked(e -> {
            String name = nameText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();
            double salary = Double.parseDouble(salaryText.getText());
            String password = passwordText.getText();
            Date dateOfBirth =  java.sql.Date.valueOf(datePicker.getValue());
            AccessLevel accessLevel = null;
            if(locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Librarian")){
                accessLevel = new Librarian();
            }
            else if(locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Manager")){
                accessLevel = new Manager();
            }
            else if(locationChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Administrator")){
                accessLevel = new Administrator();
            }
            worker[0] = new Worker(name, email, password, phone, dateOfBirth, accessLevel, salary);
        });

        return worker[0];
    }
}
