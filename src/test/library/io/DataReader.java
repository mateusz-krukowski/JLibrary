package test.library.io;

import test.library.model.Book;
import test.library.model.LibraryUser;
import test.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer){
        this.printer = printer;
    } //dependency injection

    public Book createBook(){
        printer.printLine("Tytul: ");
        String title = sc.nextLine();
        printer.printLine("Autor:");
        String author = sc.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Liczba stron: ");
        int pages = getInt();
        printer.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn = sc.nextLine();

        return new Book(title,author,releaseDate,pages,publisher,isbn);
    }

    public Magazine createMagazine(){
        printer.printLine("Tytul: ");
        String title = sc.nextLine();
        printer.printLine("Wydawca:");
        String publisher = sc.nextLine();
        printer.printLine("Jezyk: ");
        String language = sc.nextLine();
        printer.printLine("Rok: ");
        int year = getInt();
        printer.printLine("Miesiac: ");
        int month = getInt();
        printer.printLine("Dzien: ");
        int day = getInt();

        return new Magazine(title,publisher,language,year,month,day);
    }

    public LibraryUser createLibraryUser(){
        printer.printLine("Imie: ");
        String firstName = sc.nextLine();
        printer.printLine("Nazwisko: ");
        String lastName = sc.nextLine();
        printer.printLine("Pesel: ");
        String pesel = sc.nextLine();

        return new LibraryUser(firstName, lastName, pesel);
    }

    public int getInt(){
        try {
            return sc.nextInt();
        }
        finally {               //mastermind
            sc.nextLine();
        }
    }
    public String getString(){
        return sc.nextLine();
    }
    public void close(){ sc.close(); }
}