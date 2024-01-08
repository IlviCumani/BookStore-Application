package StyleControllers;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import Style.MainPage;
import Style.SettingStyles;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import IO.*;
import StaffFolder.*;

public class LoginController{

    private static final Label WRONG_PASSWORD = new Label("Wrong Password");;
    private static final Label WRONG_EMAIL = new Label("Email Doesn't Exist");
    SettingStyles styles = new SettingStyles();

    private static final FileIO fileIO = new FileIO(new WorkerFileIOService());

    public static void login(String email, String password, Stage primaryStage, GridPane center){
        ArrayList<Serializable> listOfWorkersSerial = fileIO.read();

        ArrayList<Worker> listOfWorkers = new ArrayList<>();

        for(Serializable worker : listOfWorkersSerial){
            listOfWorkers.add((Worker)worker);
        }

        Worker worker = getWorkerFromEmail(email, listOfWorkers);

        if(worker != null){
            if(worker.getPassword().equals(password)){
                Scene scene = new Scene(new MainPage(primaryStage, worker, listOfWorkers).getRoot(), 800, 600);
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
            }
            else {
                WRONG_PASSWORD.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
                center.add(WRONG_PASSWORD, 0, 4);
            }
        }
        else{
            WRONG_EMAIL.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
            center.add(WRONG_EMAIL, 0, 4);
        }
    }

    private static Worker getWorkerFromEmail(String email, ArrayList<Worker> listOfWorkers){
        if(listOfWorkers == null){
            return null;
        }
        for(Worker worker : listOfWorkers){
            if(worker.getEmail().equals(email)){
                return worker;
            }
        }
        return null;
    }
            
} 

