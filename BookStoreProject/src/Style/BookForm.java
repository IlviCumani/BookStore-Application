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
