package StyleControllers;

import java.util.ArrayList;
import BookstoreData.*;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Manager;
import Style.*;
import StaffFolder.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    private static Double TotalBookPrice = 0.0;
    private static VBox bookInfo;
    private static ArrayList <String> bookIsbns = new ArrayList<String>();
    private static SettingStyles styles = new SettingStyles();
    
    //LogOut
//    public static void LogOut(Stage primaryStage){
//        primaryStage.setMinHeight(600);
//        primaryStage.setMinWidth(800);
//        primaryStage.setScene(new Scene(new LoginPage(primaryStage).getRoot(), 800, 600));
//    }

    //Add Worker
    public static void addWorker(ArrayList<Worker> listOfWorkers, Stage primaryStage, Worker worker) throws PermissionDeniedException {
        Worker newWorker = new WorkerForm().newWorkerForm(primaryStage);
        worker.getAccessLevel().addNewWorker(listOfWorkers, newWorker);
    }

    //Add Book
//    public static void addBook(ArrayList<Book> listOfBooks, Stage primaryStage, Worker worker, ArrayList<Worker> listOfWorkers){
//        BookForm bookForm = new BookForm();
//        bookForm.newBookForm(primaryStage, worker, listOfBooks, listOfWorkers);
//
//    }

    //Book Table
    public static TableView<Book> bookTable(ArrayList<Book> listOfBooks, Stage primaryStage, String... args){
        TableView Table = new TableView();

        ObservableList<Book> data = FXCollections.observableArrayList(listOfBooks);
        Table.setItems(data);

        for(int i = 0; i < args.length; i++) {
            TableColumn titleCol = new TableColumn(args[i]);
            titleCol.setPrefWidth(200);
            titleCol.setStyle(styles.getTableColumnStyle());
            titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>(args[i]));
            Table.getColumns().add(titleCol);

        }
            
        return Table;
    }

    //Worker Table
    public static TableView<Worker> workerTable(ArrayList<Worker> listOfWorkers,Worker worker, Stage primaryStage, String... args){

        TableView<Worker> Table = new TableView<>();

        ObservableList<Worker> data ;

            if(worker.getAccessLevel() instanceof Manager){

                ArrayList<Worker> librarians = new ArrayList<Worker>();
                for(Worker temp : listOfWorkers){
                    if(temp.getAccessLevel().getAccessLevel().equals("Librarian")){
                        librarians.add(temp);
                    }
                }
                data = FXCollections.observableArrayList(librarians);
            }

            else{
                data = FXCollections.observableArrayList(listOfWorkers);
            }

            Table.setItems(data);

            for(int i = 0; i < args.length; i++) {
                TableColumn titleCol = new TableColumn(args[i]);
                titleCol.setStyle(styles.getTableColumnStyle());
                titleCol.setPrefWidth(185);
                titleCol.setCellValueFactory(new PropertyValueFactory<Worker, String>(args[i]));
                
                Table.getColumns().add(titleCol);
            }
        return Table;
    }
}
