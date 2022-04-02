package test.library.io;

import test.library.model.Book;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

    public Book createBook(){
        System.out.println("Tytul: ");
        String title = sc.nextLine();
        System.out.println("Autor:");
        String author = sc.nextLine();
        System.out.println("Rok wydania: ");
        int releaseDate = sc.nextInt();
        sc.nextLine();
        System.out.println("Liczba stron: ");
        int pages = sc.nextInt();
        sc.nextLine();
        System.out.println("Wydawnictwo: ");
        String publisher = sc.nextLine();
        System.out.println("ISBN: ");
        String isbn = sc.nextLine();

        return new Book(title,author,releaseDate,pages,publisher,isbn);
    }

    public int getInt(){
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public void close(){ sc.close(); }

}
