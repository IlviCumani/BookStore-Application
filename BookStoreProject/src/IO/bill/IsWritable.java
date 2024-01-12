package IO.bill;

import BookstoreData.Book;

import java.util.HashMap;

public interface IsWritable {

    boolean write(HashMap<Book, Integer> dictOfBooks);
}
