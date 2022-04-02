package test.library.app;

import test.library.io.DataReader;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

public class LibraryControl {

    private static final byte EXIT = 0;
    private static final byte ADD_BOOK = 1;
    private static final byte ADD_MAGAZINE = 2;
    private static final byte PRINT_BOOKS = 3;
    private static final byte PRINT_MAGAZINES = 4;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;
        do {
            printOptions();
            option = dataReader.getInt();
            switch(option){
                case EXIT: exit(); break;
                case ADD_BOOK: addBook(); break;
                case ADD_MAGAZINE: addMagazie(); break;
                case PRINT_BOOKS: printBooks(); break;
                case PRINT_MAGAZINES: printMagazines(); break;
            }
        } while(option !=0);
    }

    private void printOptions() {
        System.out.println("Wybierz opcje: ");
        System.out.println( EXIT + " - Wyjscie z programu" );
        System.out.println( ADD_BOOK + " - Dodaj nowa ksiazke" );
        System.out.println( ADD_MAGAZINE + " - Dodaj nowy magazyn" );
        System.out.println( PRINT_BOOKS + " - Wyswietl dostepne ksiazki" );
        System.out.println( PRINT_MAGAZINES + " - Wyswietl dostepne magazyny" );
    }

    private void exit() {
        System.out.println("Do widzenia");
        dataReader.close();
    }

    private void addBook() {
        Book book = dataReader.createBook();
        library.addBook(book);
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addMagazie() {
        Magazine magazine = dataReader.createMagazine();
        library.addMagazine(magazine);
    }

    private void printMagazines() {
        library.printMagazines();
    }
}