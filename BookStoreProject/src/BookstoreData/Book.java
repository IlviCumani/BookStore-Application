package BookstoreData;

import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable{

	@Serial
	private static final long serialVersionUID = 5296705482940410483L;
	private String bookTitle;
	private String bookISBN13;
	private String bookAuthor;
	private String bookGenre;
	private String bookPublisher;
	private boolean paperback; // or e-book
	private double bookPrice;
	private int nrBookInStock;

	public Book(String bookTitle,String ISBN13, String bookAuthor, String bookGenre, String bookPublisher, double bookPrice, boolean paperback) {
		this.bookTitle = bookTitle;
		this.bookISBN13 = ISBN13;
		this.bookAuthor = bookAuthor;
		this.bookGenre = bookGenre;
		this.bookPublisher = bookPublisher;
		this.nrBookInStock = 100;
		this.bookPrice = bookPrice;
		this.paperback = paperback;
	}

	public Book() {

	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookISBN13() {
		return bookISBN13;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public int getNrBookInStock() {
		return nrBookInStock;
	}

	public double getBookPrice() {
		return bookPrice;
	}
	public boolean getPaperback() {
		return paperback;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookISBN13(String bookISBN13) {
		this.bookISBN13 = bookISBN13;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public void setNrBookInStock(int nrBookInStock) {
		this.nrBookInStock = nrBookInStock;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public void setPaperback(boolean paperback) {
		this.paperback = paperback;
	}
	@Override
	public String toString() {
		return "Book{" +
				"bookTitle='" + bookTitle + '\'' +
				", bookAuthor='" + bookAuthor + '\'' +
				", bookGenre='" + bookGenre + '\'' +
				", bookPublisher='" + bookPublisher + '\'' +
				", nrBookInStock=" + nrBookInStock +
				", bookPrice=" + bookPrice +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Book))
			return false;
        return (
				(this.bookTitle == null ? ((Book) o).getBookTitle() == null : this.bookTitle.equals(((Book) o).getBookTitle())) &&
				(this.bookAuthor == null ? ((Book) o).getBookAuthor() == null : this.bookAuthor.equals(((Book) o).getBookAuthor())) &&
				(this.bookGenre == null ? ((Book) o).getBookGenre() == null : this.bookGenre.equals(((Book) o).getBookGenre()))	 &&
				(this.bookPublisher == null ? ((Book) o).getBookPublisher() == null : this.bookPublisher.equals(((Book) o).getBookPublisher())) &&
				(this.nrBookInStock == ((Book) o).getNrBookInStock()) &&
				(this.bookPrice == ((Book) o).bookPrice)
				);
	}
}

//public class Book implements Serializable {
//	@Serial
//	private static final long serialVersionUID = 5296705482940410483L;
//	private transient SimpleStringProperty isbn13P, titleP, priceP,stockP ,genreP,authorP,paperBackP;
//    private String isbn13;
//	private String title;
//	private String description;
//	private float price;
//    private int stock;
//    private String author;
//    private String genre;
//    private ArrayList<Genre> genres = new ArrayList<>();
//	private boolean paperback; // or e-book
//
//	public Book(String isbn13, String title,  float price, String author, boolean paperback,int stock) {
//		this.isbn13 = isbn13;
//		this.title = title;
//		this.price = price;
//		this.author = author;
////		this.description = description;
//		this.paperback = paperback;
//        this.stock = stock;
////        for (Genre g: genres) {
////            this.genre.concat(g.toString() + ", ");
////        }
//        setIsbn13(isbn13);
//		setTitle(title);
//		setPrice(price);
//		setAuthor(author);
//		setPaperBack(paperback);
//		setStock(stock);
//	}
//
//
//	public ArrayList<Genre> getGenres() {
//		return genres;
//	}
//
//	public void addGenre(Genre genre) {
//		this.genres.add(genre);
//	}
//
//	public void addGenres(Genre...genres) {
//		for(Genre genre : genres)
//			this.addGenre(genre);
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public boolean isPaperback() {
//		return paperback;
//	}
//
//	public void setPaperback(boolean paperback) {
//		this.paperback = paperback;
//	}
//
//	@Override
//	public String toString() {
//		return this.title + " by " + this.author.toString() + ", " + this.price + " leke";
//
//    }
//
//    public enum Genre {
//        MYSTRERY, ACTION, HISTORICAL, DYSTOPIAN, FANTASY
//    }
//
//    public static long getSerialversionuid() {
//        return serialVersionUID;
//    }
//
//    public String getIsbn13() {
//
//		if(isbn13P == null) {
//			setIsbn13(isbn13);
//		}
//
//        return isbn13P.get();
//    }
//
//    public void setIsbn13(String isbn13p) {
//        this.isbn13P = new SimpleStringProperty(isbn13p) ;
//    }
//
//    public String getTitle() {
//        if(titleP == null) {
//			setTitle(title);
//		}
//		return titleP.get();
//    }
//
//    public void setTitle(String titleP) {
//        this.titleP = new SimpleStringProperty(titleP);
//    }
//
//    public String getAuthor() {
//        if(authorP == null) {
//			setAuthor(author);
//		}
//		return authorP.get();
//    }
//
//    public void setAuthor(String authorP) {
//        this.authorP = new SimpleStringProperty(authorP);
//    }
//
//    public void setGenres(ArrayList<Genre> genres) {
//        String temp = "";
//		for (Genre g: genres) {
//			temp = temp.concat(g.toString() + ", ");
//		}
//		this.genreP = new SimpleStringProperty(temp);
//    }
//
//	public String getGenre() {
//		if(genreP == null) {
//			setGenres(genres);
//		}
//		return genreP.get();
//	}
//
//	public void setStock (int stock) {
//		this.stockP = new SimpleStringProperty(Integer.toString(stock));
//	}
//
//	public String getStock() {
//		if(stockP == null) {
//			setStock(stock);
//		}
//		return stockP.get();
//	}
//
//	public String getPaperBack() {
//		if(paperBackP == null) {
//			setPaperBack(paperback);
//		}
//		return paperBackP.get();
//	}
//
//	public void setPaperBack(boolean paperBackP) {
//		this.paperBackP = new SimpleStringProperty(Boolean.toString(paperBackP));
//	}
//
//    public void setPrice(float priceP) {
//		this.priceP = new SimpleStringProperty(Float.toString(priceP));
//	}
//
//    public String getPrice() {
//        if(priceP == null) {
//			setPrice(price);
//		}
//		return priceP.get();
//    }
//
//    public void removeStock(int amount) {
//        this.stock -= amount;
//    }
//
//    public void addStock(int amount) {
//        this.stock += amount;
//    }
//
//    public int getStockInt() {
//        return stock;
//    }
//
//
//	public Object getPUBLISHER() {
//		return null;
//	}
//
//
//    public Object getQUANTITY() {
//        return null;
//    }
//
//
//    public Object getLANGUAGE() {
//        return null;
//    }
//}
