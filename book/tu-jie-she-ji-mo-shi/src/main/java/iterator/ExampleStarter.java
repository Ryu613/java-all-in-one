package iterator;

import iterator.ex1.Book;
import iterator.ex1.BookShelf;
import iterator.ex1.Iterator;

import java.util.ArrayList;
import java.util.List;

public class ExampleStarter {
    public static void main(String[] args) {
        startEx1();
        List a = new ArrayList<>();
    }

    public static void startEx1() {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));
        Iterator it = bookShelf.iterator();
        while(it.hasNext()) {
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
    }
}
