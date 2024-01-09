package IO;

import BookstoreData.Book;
import StaffFolder.Worker;

import java.io.Serializable;
import java.util.ArrayList;

public class CompatibleTypes {

    public static ArrayList<Serializable> fromWorkerToSerializible(ArrayList<Worker> listOfWorkers) {
        return new ArrayList<>(listOfWorkers);

    }

    public static ArrayList<Worker> fromSerializbleToWorker(ArrayList<Serializable> listOfSerializibles) {
        ArrayList<Worker> workers = new ArrayList<>();
        if (listOfSerializibles.getFirst() instanceof Worker) {
            for (Serializable worker : listOfSerializibles) {
                workers.add((Worker) worker);
            }
        }
        return workers;

    }

    public static ArrayList<Serializable> fromBookToSerializble(ArrayList<Book> listOfBooks)  {
        return new ArrayList<Serializable>(listOfBooks);
    }

    public static ArrayList<Book> fromSerializbleToBook(ArrayList<Serializable> listOfSerializibles) {
        ArrayList<Book> books = new ArrayList<>();
        if (listOfSerializibles.getFirst() instanceof Book) {
            for (Serializable book : listOfSerializibles) {
                books.add((Book) book);
            }
        }
        return books;

    }
}
