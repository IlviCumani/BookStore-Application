package Style;

import BookstoreData.Book;

import IO.BookFileIOService;
import IO.FileIO;

import IO.WorkerFileIOService;
import Orders.BillData;

import StaffFolder.AccessLevels.AccessLevel;
import StaffFolder.AccessLevels.Administrator;
import StaffFolder.AccessLevels.Behaviours.CheckWorkers.NoPermissionToCheckWorker;
import StaffFolder.AccessLevels.Behaviours.Exceptions.PermissionDeniedException;
import StaffFolder.AccessLevels.Behaviours.ManageBooks.NoPermissionToAddNewBooks;
import StaffFolder.AccessLevels.Behaviours.SellBooks.NoPermissionToSellBooks;
import StaffFolder.AccessLevels.Librarian;
import StaffFolder.AccessLevels.Manager;
import StaffFolder.Worker;

import IO.CompatibleTypes;

import StyleControllers.MainController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainPage{

    private final ArrayList<Worker> listOfWorkers;
    private final ArrayList<Book> listOfBooks = new ArrayList<Book>();
    private final BorderPane root = new BorderPane();;
    private HBox top;
    private VBox PersonalInfo;
    private Stage primaryStage;
    private Button addBookBtn;
    private Button addWorkerBtn;
    private SettingStyles styles;
    private VBox center;
    private Worker worker;
    private TableView<Book> bookTableView;
    private TableView<Worker> workerTableView;
    private BorderPane right;
    private VBox BookInfoHolder;
    private Button addBookToStockBtn;
    private Double TotalBookPrice = 0.0;
    ArrayList <String> bookIsbns = new ArrayList<String>();
    ArrayList <Double> prices = new ArrayList<Double>();
    private HBox TotalLabelHbox;
    private VBox EmployeeInfoHolder;
    private Button purchaseBookBtn;
    private Label workerFullName;
    private Label workerEmail;
    private Label workerAccessLevel;
    private Label workerSalary;
    private Label workerPhoneNumber;
    private boolean permitionToPurchase;
    private boolean permitionToCheckLib;
    private boolean permitionToBill;
    private BillData billData;
    private HashMap<Book, Integer> booksToSell = new HashMap<>();
    private  Book clickedBook;

    private final FileIO FILESAVER = new FileIO(new WorkerFileIOService());
    private final FileIO BOOKFILESAVER = new FileIO(new BookFileIOService());


    public MainPage(Stage primaryStage, Worker worker, ArrayList<Worker> listOfWorkers) {
        System.out.println("We are in main page");
        this.primaryStage = primaryStage;
        this.worker=worker;
        this.listOfWorkers=listOfWorkers;
        ArrayList<Serializable> books = new FileIO(new BookFileIOService()).read();
        for (Serializable book : books) {
            listOfBooks.add((Book) book);
        }

        this.billData= new BillData();

        if(!(worker.getAccessLevel() instanceof Librarian)){
           int a=0;
           StringBuilder names= new StringBuilder("\n");
            for (Book b : listOfBooks) {
                if(b.getNrBookInStock() < 5){
                    System.out.println(b.getNrBookInStock());
                    names.append(b.getBookTitle()).append("\n");
                    a++;
                }
            }
            if(a!=0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Low Stock");
                alert.setHeaderText("Low Stock");
                alert.setContentText("There are books with low stock"+names);
                alert.showAndWait();
            }
        }
        try {
            createMainPage();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File not found");
        }
    }

    private void createMainPage() throws FileNotFoundException {
        styles = new SettingStyles();
        root.setStyle(styles.getLoginRootStyle());
        primaryStage.setMinHeight(850);
        primaryStage.setMinWidth(1635);
        
        //Left Pane
        PersonalInfo = new VBox(25);
        Image profileImage = new Image(new FileInputStream("BookStoreProject/src/Images/man.png"));

        Circle circle = new Circle(125, 125, 125);
        circle.setFill(new javafx.scene.paint.ImagePattern(profileImage));

        Text name = new Text(worker.getFullname());
        name.setStyle(styles.getMainPagePersonalInfoStyle());
        Text email = new Text(worker.getEmail());
        email.setStyle(styles.getMainPagePersonalInfoStyle());
        Text phone = new Text(worker.getPhone());
        phone.setStyle(styles.getMainPagePersonalInfoStyle());
        Text status = new Text(worker.getAccessLevelName());
        status.setStyle(styles.getMainPagePersonalInfoStyle());
        
        PersonalInfo.getChildren().addAll(circle, name, email, phone, status);
        PersonalInfo.setStyle(styles.getMainPageLeftPaneStyle());
        root.setLeft(PersonalInfo);

        //Top Pane
        top = new HBox(500);
        Button LogOutBtn = new Button("Log Out");
        LogOutBtn.setStyle(styles.getLogOutBtnStyle());

        HBox searchBox = new HBox(15);
        TextField searchBar = new TextField();
        searchBar.setStyle(styles.getSearchBarStyle());
        searchBar.setPromptText("Search");
        searchBar.setPrefHeight(50);
        searchBar.setPrefWidth(500);

        ChoiceBox<String> searchList = new ChoiceBox<>();
        searchList.getItems().addAll("Title", "Author", "Genre");
        searchList.setValue("Title");
        searchList.setStyle(styles.getSearchListStyle());
        searchBox.getChildren().addAll(searchList, searchBar);

        top.getChildren().addAll(searchBox, LogOutBtn);
        top.setAlignment(Pos.TOP_RIGHT);
        top.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        root.setTop(top);
       

        //Center Pane
        TabPane tabPane = new TabPane();

        Tab BookTab = new Tab("Books");
        BookTab.setStyle(styles.getBookTableStyle());
        BookTab.setClosable(false);
        tabPane.getTabs().add(BookTab);
        bookTableView = MainController.bookTable(listOfBooks, primaryStage, "bookTitle", "bookAuthor", "bookPrice", "nrBookInStock", "bookISBN13", "Paperback", "bookPublisher", "bookGenre");
        
        HBox bookBottomPane = new HBox(20);
        bookBottomPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        bookBottomPane.setStyle(styles.getBtnPane());
        addBookBtn = new Button("Add Book");
        addBookBtn.setStyle(styles.getLogOutBtnStyle());
        addBookToStockBtn = new Button("Add To Stock");
        addBookToStockBtn.setStyle(styles.getLogOutBtnStyle());
        
        if(worker.getAccessLevel() instanceof Librarian) {
            addBookBtn.setDisable(true);
            addBookToStockBtn.setDisable(true);
        }
        
        bookBottomPane.getChildren().addAll(addBookBtn, addBookToStockBtn);
        bookBottomPane.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane bookBorderPane = new BorderPane();
        bookBorderPane.setCenter(bookTableView);
        bookBorderPane.setBottom(bookBottomPane);

        BookTab.setContent(bookBorderPane);

        //Employee Tab
    
        Tab EmployeeTab = new Tab("Employees");
        EmployeeTab.setStyle(styles.getBookTableStyle());
        EmployeeTab.setClosable(false);

        if(worker.getAccessLevel().getCheckWorkerBehaviour() instanceof NoPermissionToCheckWorker){
            EmployeeTab.setDisable(true);
        }

        tabPane.getTabs().add(EmployeeTab);

        workerTableView = MainController.workerTable(listOfWorkers, worker, primaryStage, "Fullname", "Email", "Phone", "Status","dateOfBirth", "salary");

        HBox workerBottomPane = new HBox(20);
        workerBottomPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 0));
        workerBottomPane.setStyle(styles.getBtnPane());

        addWorkerBtn = new Button("Add Worker");

        if(!(worker.getAccessLevel() instanceof Administrator)){
            addWorkerBtn.setDisable(true);
        }

        addWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        workerBottomPane.getChildren().add(addWorkerBtn);
        workerBottomPane.setAlignment(Pos.BOTTOM_RIGHT);
        
        
        BorderPane workerBorderPane = new BorderPane();
        workerBorderPane.setCenter(workerTableView);
        workerBorderPane.setBottom(workerBottomPane);
        
        EmployeeTab.setContent(workerBorderPane);
        
        root.setCenter(tabPane);

        //Right Pane
        root.setRight(getRightBook());

        
        //Button Action ETC

        searchBar.setOnKeyPressed(e -> {
            if(KeyCode.ENTER == e.getCode()){
                String choice = searchList.getValue();
                String content = searchBar.getText();
                System.out.println(content);
                if(choice.equals("Title")){
                    System.out.println("hyri");
                    Book book = listOfBooks.get(listOfBooks.indexOf(new Book(content, "", "", "", "", 0, false)));
                    System.out.println("U gjet libri" + book.getBookTitle());
                    BookInfoHolder.getChildren().add(getPurchaseBookPane(book));
                }else if(choice.equals("Author")){
                    System.out.println("Autori");
                }else{
                    System.out.println("Genre");
                }

            }
        });

        LogOutBtn.setOnAction(e -> {
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(800);
            primaryStage.setScene(new Scene(new LoginPage(primaryStage).getRoot(), 800, 600));
        });

        addWorkerBtn.setOnAction(e-> {
            try {
                MainController.addWorker(listOfWorkers, primaryStage, worker, FILESAVER);
            } catch (PermissionDeniedException ex) {
                System.out.println(ex.getMessage());
            }
        });

        addBookBtn.setOnAction(e-> {
                BookForm bookForm = new BookForm();
                bookForm.newBookForm(primaryStage, worker, listOfBooks, listOfWorkers);
        });

        bookTableView.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                Book book = bookTableView.getSelectionModel().getSelectedItem();
                clickedBook = book;
                BookInfoHolder.getChildren().add(getPurchaseBookPane(book));
            }
        });
        
        workerTableView.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                if(workerTableView.getSelectionModel().getSelectedItem().getAccessLevel() instanceof Administrator){
                    return;
                }
                Worker employee = workerTableView.getSelectionModel().getSelectedItem();
                EmployeeInfoHolder.getChildren().clear();
                EmployeeInfoHolder.getChildren().add(getEmployeePane(employee));
            }
        });
        
        addBookToStockBtn.setOnAction(e -> {

            for (Book book : booksToSell.keySet()) {
                worker.getAccessLevel().resupplyStock(book, booksToSell.get(book));
            }
            this.saveBookFile(listOfBooks);
            booksToSell.clear();
            BookInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, worker, listOfWorkers).getRoot(), 800, 600));
            primaryStage.setFullScreen(true);
       });

        purchaseBookBtn.setOnAction(e -> {
            System.out.println("BOOKS TO SELL" + booksToSell);
            for (Book book : booksToSell.keySet()) {
                try{
                    worker.getAccessLevel().sellBooks(book, booksToSell.get(book));
                }catch (PermissionDeniedException | IllegalArgumentException ex){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                    primaryStage.setScene(new Scene(new MainPage(primaryStage, worker, listOfWorkers).getRoot(), 800, 600));
                    primaryStage.setFullScreen(true);
                    return;
                }
            }
            this.saveBookFile(listOfBooks);
            booksToSell.clear();
            BookInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, worker, listOfWorkers).getRoot(), 800, 600));
            primaryStage.setFullScreen(true);
        });
       
//       purchaseBookBtn.setOnAction(e -> {
//        System.out.println("purchase");
//        ArrayList<Integer> quantity = new ArrayList<>();
//        for (Node node: BookInfoHolder.getChildren()) {
//            GridPane g=(GridPane) node;
//            for (Node el: g.getChildren()) {
//               int q=0;
//
//                if(el instanceof TextField)
//                {
//                    TextField t=(TextField) el;
//                    q = Integer.parseInt(t.getText());
//
//                    quantity.add(q);
//                }
//                if(q>books.getBookQuantity(bookIsbns.get(BookInfoHolder.getChildren().indexOf(node)))){
//                    Alert alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Error");
//                    alert.setContentText("Quantity is greater than the stock");
//                    alert.showAndWait();
//                    return;
//                }
//
//            }
//
//        }
//        BookInfoHolder.getChildren().clear();
//        primaryStage.setScene(new Scene(new MainPage(primaryStage, worker, listOfWorkers).getRoot(), 800, 600));
//        primaryStage.setFullScreen(true);
//        });


        BookTab.setOnSelectionChanged(e -> {
            if(BookTab.isSelected()){
                right.getChildren().clear();
                right.setRight(getRightBook());
            }
        });

        EmployeeTab.setOnSelectionChanged(e -> {
            if(EmployeeTab.isSelected()){
                right.getChildren().clear();
                right.setRight(getRightEmployee());
            }
        });
    }
    
    //Create MAIN PAGE FUNCTION END'S HERE

    public BorderPane getRoot() {
        return root;
    }

    public BorderPane getRightBook() {
        right = new BorderPane();
        BookInfoHolder = new VBox(10);
        right.setCenter(BookInfoHolder);

        VBox BottomVBox = new VBox(10);
        TotalLabelHbox = new HBox(10);
        HBox purchaseBookBtnHolder = new HBox(20);
        BottomVBox.getChildren().addAll(TotalLabelHbox, purchaseBookBtnHolder);
        purchaseBookBtnHolder.setStyle(styles.getBtnPane());
        purchaseBookBtn = new Button("Purchase Books");

        //! FIXING THIS PART
        //! FIXING THIS PART

        if(worker.getAccessLevel().getSellBooksBehaviour() instanceof NoPermissionToSellBooks){
            purchaseBookBtn.setDisable(true);
        }

        if(worker.getAccessLevel().getAddNewBooksBehaviour() instanceof NoPermissionToAddNewBooks){
            addBookToStockBtn.setDisable(true);
        }

        purchaseBookBtn.setStyle(styles.getLogOutBtnStyle());

        purchaseBookBtnHolder.setAlignment(Pos.BOTTOM_CENTER);
        purchaseBookBtnHolder.getChildren().add(purchaseBookBtn);
        right.setBottom(BottomVBox);
        return right;
    }



    public BorderPane getRightEmployee() {

        right = new BorderPane();
        EmployeeInfoHolder = new VBox(10);

        right.setCenter(EmployeeInfoHolder);

        return right;
    }

//? HEJFJFEUJHEUEHVUHEFUHUHEUEEEeervgrbrbrtbrtbrbrbrbrbrbrbrbrbbrfbrfbr
//? HEJFJFEUJHEUEHVUHEFUHUHEUEEEeervgrbrbrtbrtbrbrbrbrbrbrbrbrbbrfbrfbr
//? HEJFJFEUJHEUEHVUHEFUHUHEUEEEeervgrbrbrtbrtbrbrbrbrbrbrbrbrbbrfbrfbr//? HEJFJFEUJHEUEHVUHEFUHUHEUEEEeervgrbrbrtbrtbrbrbrbrbrbrbrbrbbrfbrfbr


    public GridPane getPurchaseBookPane(Book book){

        GridPane grid = new GridPane();
        grid.setStyle(styles.getSalesGridPane());
        grid.setPrefHeight(100);
        grid.setPrefWidth(300);
        grid.setHgap(20);
        grid.setVgap(20);

        Label bookISBN = new Label(book.getBookISBN13());
        bookIsbns.add(book.getBookISBN13());
        prices.add(book.getBookPrice());
        bookISBN.setStyle(styles.getSalesLabel());
        Label bookPrice = new Label("" + book.getBookPrice());
        bookPrice.setStyle(styles.getSalesLabel());
        TextField nrBooks = new TextField();
        nrBooks.setPrefHeight(20);
        nrBooks.setPrefWidth(50);

        nrBooks.setOnKeyPressed(e -> {
            Label total = new Label();
            total.setStyle(styles.getSalesLabel());
            grid.getChildren().remove(total);
            if(KeyCode.ENTER == e.getCode()){
                booksToSell.put(book, Integer.parseInt(nrBooks.getText()));
                total.setText("" + (Double.parseDouble(bookPrice.getText()) * Double.parseDouble(nrBooks.getText())));
                TotalBookPrice += Double.parseDouble(total.getText());
                System.out.println(TotalBookPrice);
                grid.add(total, 1, 1);
                Label totalLabel = new Label("Total: " + TotalBookPrice);
                totalLabel.setStyle(styles.getSalesLabel());
                if(!TotalLabelHbox.getChildren().isEmpty()){
                    TotalLabelHbox.getChildren().clear();;
                }
                TotalLabelHbox.getChildren().add(totalLabel);
            }
        });


        
        grid.add(bookISBN, 0, 0);
        grid.add(bookPrice, 1, 0);
        grid.add(nrBooks, 0, 1);


        return grid;
    }

    public GridPane getEmployeePane(Worker tempworker){
        
        GridPane grid = new GridPane();
        grid.setStyle(styles.getSalesGridPane());
        grid.setPrefHeight(300);
        grid.setPrefWidth(500);
        grid.setHgap(20);
        grid.setVgap(20);

        workerFullName = new Label(tempworker.getFullname());
        workerFullName.setStyle(styles.getSalesLabel());
        TextField newName = new TextField();
        newName.setStyle(styles.getLoginTextFieldStyle());

        workerEmail = new Label(tempworker.getEmail());
        workerEmail.setStyle(styles.getSalesLabel());
        TextField newEmail = new TextField();
        newEmail.setStyle(styles.getLoginTextFieldStyle());
                        
        ChoiceBox<String> newAccessLevelString = new ChoiceBox<>();
        final AccessLevel[] newAccessLevel = {tempworker.getAccessLevel()};
        newAccessLevelString.getItems().addAll("Librarian", "Manager", "Administrator");//! Check this out later
        newAccessLevelString.setStyle(styles.getSearchListStyle());
        newAccessLevelString.setValue(tempworker.getAccessLevelName());

        newAccessLevelString.setOnKeyPressed(e-> {
            if(KeyCode.ENTER == e.getCode()){
                if(newAccessLevelString.getValue().equals("Librarian")){
                    newAccessLevel[0] = new Librarian();
                }else if(newAccessLevelString.getValue().equals("Manager")){
                    newAccessLevel[0] = new Manager();
                }else{
                    newAccessLevel[0] = new Administrator();
                }
            }
        });
        
//        CheckBox newPermitionToPurchaseCheckBox = new CheckBox("Permission to purchase");
//
//        newPermitionToPurchaseCheckBox.setOnAction(e->{
//            if(newPermitionToPurchaseCheckBox.isSelected()){
//                permitionToPurchase = true;
//            } else {
//                permitionToPurchase = false;
//            }
//            System.out.println(permitionToPurchase);
//        });
//
//        CheckBox newPremitionToCheckLib = new CheckBox("Check Librarians");
//
//        newPremitionToCheckLib.setOnAction(e->{
//            if(newPremitionToCheckLib.isSelected()){
//                permitionToCheckLib = true;
//            } else {
//                permitionToCheckLib = false;
//            }
//            System.out.println(permitionToCheckLib);
//        });
//
//        CheckBox newPremitionToBill = new CheckBox("Permition to bill");
//
//        newPremitionToBill.setOnAction(e->{
//            permitionToBill = newPremitionToBill.isSelected();
//            System.out.println(permitionToBill);
//        });

        workerSalary = new Label("" + tempworker.getSalary());
        workerSalary.setStyle(styles.getSalesLabel());
        TextField newSalary = new TextField();
        newSalary.setStyle(styles.getLoginTextFieldStyle());

        workerPhoneNumber = new Label(tempworker.getPhone());
        workerPhoneNumber.setStyle(styles.getSalesLabel());
        TextField newPhoneNumber = new TextField();
        newPhoneNumber.setStyle(styles.getLoginTextFieldStyle());

        Button deletWorkerBtn = new Button("Delete Worker");
        deletWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        if(!(worker.getAccessLevel() instanceof Administrator)){
            deletWorkerBtn.setDisable(true);
        }
        Button editWorkerBtn = new Button("Edit Worker");
        editWorkerBtn.setStyle(styles.getLogOutBtnStyle());
        if(!(worker.getAccessLevel() instanceof Administrator)){
            editWorkerBtn.setDisable(true);
        }

        deletWorkerBtn.setOnAction(e -> {
            try {
                worker.getAccessLevel().fireWorker(listOfWorkers, tempworker);
                this.saveFile(listOfWorkers);
            } catch (PermissionDeniedException ex) {
                throw new RuntimeException(ex);
            }
            EmployeeInfoHolder.getChildren().clear();
            primaryStage.setScene(new Scene(new MainPage(primaryStage, this.worker, listOfWorkers).getRoot(),800, 600));
            primaryStage.setFullScreen(true);
        });

        editWorkerBtn.setOnAction(e -> {
            System.out.println("Edit worker");
            try {
                worker.getAccessLevel().editWorker(tempworker, workerFullName.getText(), workerEmail.getText(), workerPhoneNumber.getText(), Double.parseDouble(workerSalary.getText()), newAccessLevel[0]);
                this.saveFile(listOfWorkers);
            }catch (PermissionDeniedException ex) {
                System.out.println(ex.getMessage());
            }

            primaryStage.setScene(new Scene(new MainPage(primaryStage, this.worker, listOfWorkers).getRoot(),800, 600));
            primaryStage.setFullScreen(true);
        });

        workerEmail.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerEmail);
            grid.add(newEmail, 0, 1);

            newEmail.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerEmail.setText(newEmail.getText());
                    workerEmail.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newEmail);
                    grid.add(workerEmail, 0, 1);
                }
            });
        });

        workerSalary.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerSalary);
            grid.add(newSalary, 0, 3);

            newSalary.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerSalary.setText(newSalary.getText());
                    workerSalary.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newSalary);
                    grid.add(workerSalary, 0, 3);
                }
            });
        });
        
        workerPhoneNumber.setOnMouseClicked(e -> {
            grid.getChildren().remove(workerPhoneNumber);
            grid.add(newPhoneNumber, 0, 4);

            newPhoneNumber.setOnKeyPressed(event -> {
                if(KeyCode.ENTER == event.getCode()){
                    workerPhoneNumber.setText(newPhoneNumber.getText());
                    workerPhoneNumber.setStyle(styles.getSalesLabel());
                    grid.getChildren().remove(newPhoneNumber);
                    grid.add(workerPhoneNumber, 0, 4);
                }
            });
        });

        grid.add(workerFullName, 0, 0);
        grid.add(workerEmail, 0, 1);
        grid.add(newAccessLevelString, 0, 2);
        grid.add(workerSalary, 0, 3);
        grid.add(workerPhoneNumber, 0, 4);
        grid.add(deletWorkerBtn, 0, 8);
        grid.add(editWorkerBtn, 1, 8);
        
        return grid;
    }

    public void saveFile(ArrayList<Worker> listOfWorkers) {
        ArrayList<Serializable>  listOfSerializibles = CompatibleTypes.fromWorkerToSerializible(listOfWorkers);
        FILESAVER.write(listOfSerializibles);
    }

    public void saveBookFile(ArrayList<Book> listOfBooks) {
        ArrayList<Serializable>  listOfSerializibles = CompatibleTypes.fromBookToSerializble(listOfBooks);
        BOOKFILESAVER.write(listOfSerializibles);
    }


}