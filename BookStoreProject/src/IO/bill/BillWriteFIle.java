package IO.bill;

import BookstoreData.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;

public class BillWriteFIle implements IsWritable{

    private String actionOfBooks;

    private String PATH = "BookStoreProject/src/Files/Bill.txt";
    private final File file;

    public BillWriteFIle() {
        this.file = new File(PATH);
        this.actionOfBooks = "sold";

    }

    protected BillWriteFIle(String path, String action) {
        this.file = new File(path);
        this.actionOfBooks = action;
    }
    @Override
    public boolean write(HashMap<Book, Integer> dictOfBooks) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        stringBuilder.append("\tBill of the Books\n");
        stringBuilder.append("Today's date:").append(stringBuilder.toString()).append('\n');
        stringBuilder.append("Books " + this.actionOfBooks +": \n");
        stringBuilder.append("\t\tBook Title\tisbn13\tPrice\tCopies\tTotal\n");
        double sumOfTotalPrice = 0;
        for(Book book: dictOfBooks.keySet()) {
            System.out.println("Here we are man: " + book);
            String bookTitle = book.getBookTitle();
            String isbn13 = book.getBookISBN13();
            double price = book.getBookPrice();
            int numberSold = dictOfBooks.get(book);
            double total = price * numberSold;
            sumOfTotalPrice += total;
            stringBuilder.append("\t\t"+bookTitle+"\t"+isbn13+"\t"+price+"\t"+numberSold+"\t"+total+"\n");
        }
        stringBuilder.append(sumOfTotalPrice);
        try(PrintWriter printWriter = new PrintWriter(file)){
            printWriter.write(stringBuilder.toString());
        }
        catch (FileNotFoundException e) {
            stringBuilder = new StringBuilder();
            return false;
        }
        stringBuilder = new StringBuilder();
        return true;
    }

}
