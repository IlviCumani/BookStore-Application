package IO.bill;

import BookstoreData.Book;

import java.util.HashMap;

public class MOCKBillWriteFile implements IsWritable{
    private double total = .0;
    private final double checkRealPrice;

    public MOCKBillWriteFile(double checkRealPrice) {
        this.checkRealPrice = checkRealPrice;
    }



    @Override
    public boolean write(HashMap<Book, Integer> dictOfBooks) {
        for (Book b: dictOfBooks.keySet()) {
            this.total += b.getBookPrice() * dictOfBooks.get(b);
        }

        return this.total == checkRealPrice;
    }
}
