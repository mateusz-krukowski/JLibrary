package test.library.app;

import test.library.model.Book;

import java.util.LinkedList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        List<Book> books = new LinkedList<Book>();
        books.add( new Book("W pustyni i w puszczy","Henryk Sienkiewicz",2010,296,"Greg","9788420764641") );
        books.add( new Book("Java, efektywne programowanie","Joshua Bloch", 2009,352,"Helion","9788324620845") );
        books.add( new Book("Mikrokontrolery z rdzeniem ARM9 w przykladach", "Lucjan Bryndza",2009,264,"BTC") );

        System.out.println("Ksiazki dostepne w bibliotece: ");
        for(Book i:books) i.printInfo();
    }
}