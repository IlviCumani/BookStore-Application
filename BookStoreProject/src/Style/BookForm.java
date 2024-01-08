package Style;

import BookstoreData.Book;
import IO.BookFileIOService;
import IO.CompatibleTypes;
import IO.FileIO;
import StaffFolder.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class BookForm {
    private final FileIO FILESAVER = new FileIO(new BookFileIOService());
    public void newBookForm(Stage primaryStage, Worker temp, ArrayList<Book> listOfBooks, ArrayList<Worker> listOfWorkers) {
        SettingStyles settingStyles = new SettingStyles();
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50, 10, 50, 10));
        pane.setVgap(15);
        pane.setHgap(10);
        pane.setStyle(settingStyles.getLoginRootStyle());

        TextField titleTF = new TextField();
        titleTF.setStyle(settingStyles.getLoginTextFieldStyle());
        titleTF.setPromptText("Title");
        Label titleLbl = new Label("Title");

        TextField isbnTF = new TextField();
        isbnTF.setStyle(settingStyles.getLoginTextFieldStyle());
        isbnTF.setPromptText("ISBN 13");
        Label isbnLbl = new Label("ISBN 13");

        TextField priceTF = new TextField();
        priceTF.setStyle(settingStyles.getLoginTextFieldStyle());
        priceTF.setPromptText("Price");
        Label priceLbl = new Label("Price");

        Label versionLbl = new Label("Version");
        RadioButton rbPaperback = new RadioButton("Paperback");
        rbPaperback.setStyle(settingStyles.getRadioBtn());
        RadioButton rbEbook = new RadioButton("E-book");
        rbEbook.setStyle(settingStyles.getRadioBtn());
        ToggleGroup group = new ToggleGroup();
        rbPaperback.setToggleGroup(group);
        rbEbook.setToggleGroup(group);
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(rbPaperback, rbEbook);

//		Label descriptionLbl = new Label("Description");
//		TextArea descriptionTA = new TextArea();
//		descriptionTA.setStyle("-fx-background-color: #0E273C; -fx-text-fill: #0E273C; -fx-font-size: 14px; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-border-color: #35CE8D; -fx-border-width: 3px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px;");
//		descriptionTA.setPrefColumnCount(20);
//		descriptionTA.setPrefRowCount(5);
//		descriptionTA.setWrapText(true);

        Label authosLbl = new Label("Select an author: ");
        TextField authorTF = new TextField();
        authorTF.setStyle(settingStyles.getLoginTextFieldStyle());
        authorTF.setPromptText("Author");

        Label genreLbl = new Label("Genres: ");
        TextField genreTF = new TextField();
        genreTF.setStyle(settingStyles.getLoginTextFieldStyle());
        genreTF.setPromptText("Genres");

        Label publisherLbl = new Label("Publisher: ");
        TextField publisherTF = new TextField();
        publisherTF.setStyle(settingStyles.getLoginTextFieldStyle());
        publisherTF.setPromptText("Publisher");

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(settingStyles.getLogOutBtnStyle());




        pane.add(titleTF, 1, 0);
        pane.add(isbnTF, 1, 1);
        pane.add(priceTF, 1, 2);
        pane.add(hbox, 1, 3);
        pane.add(publisherTF, 1, 4);
        pane.add(authorTF, 1, 5);
        pane.add(genreTF, 1, 6);
        pane.add(submitBtn, 1, 7);

        Scene scene = new Scene(pane, 700, 700);
        stage.setScene(scene);
        stage.setTitle("Bookstore");
        stage.show();


        submitBtn.setOnAction(e -> {
//            if(titleTF.getText().isEmpty() || isbnTF.getText().isEmpty() || priceTF.getText().isEmpty() || /*descriptionTA.getText().isEmpty() ||*/ authorTF.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Error");
//                alert.setContentText("Please fill all the fields");
//                alert.showAndWait();
//            }
//            else if(!checkIsbn13( isbnTF.getText())){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Error");
//                alert.setContentText("ISBN 13 is not valid");
//                alert.showAndWait();
//            }
//            else {
//                String isbn13 = isbnTF.getText();
//                String title = titleTF.getText();
//                float price = Float.parseFloat(priceTF.getText());
////				String description = descriptionTA.getText();
//                String publisher = publisherTF.getText();
//                String genre = genreTF.getText();
//                String author = authorTF.getText();
//                boolean isPaperback = rbPaperback.isSelected();
////				Book newBook = new Book(isbn13, title /* description*/, price, author, isPaperback,0);
//                Book newBook = new Book(title, isbn13, author, genre, publisher, price, isPaperback);
//
//                boolean res = writeBookToFile(newBook);
//                books.add(newBook);

//            }
            try {
                listOfBooks.add(temp.getAccessLevel().addNewBook(titleTF.getText(), isbnTF.getText(), authorTF.getText(), genreTF.getText(), publisherTF.getText(), Double.parseDouble(priceTF.getText()), rbPaperback.isSelected()));
                ArrayList<Serializable> listOfSerializibles = CompatibleTypes.fromBookToSerializble(listOfBooks);
                FILESAVER.write(listOfSerializibles);
            }catch (IllegalStateException es) {
                System.out.println(es.getMessage());
            }



            primaryStage.setScene(new Scene(new MainPage(primaryStage, temp, listOfWorkers).getRoot()));
            primaryStage.setFullScreen(true);
            stage.close();
        });
    }
}
