package test.library.app;

import test.library.io.DataReader;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

public class LibraryControl {
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;
        do {
            printOptions();
            option = dataReader.getInt();
            switch(option){
                case 0: exit(); break;
                case 1: addBook(); break;
                case 2: printBooks(); break;
            }
        } while(option !=0);
    }


    private void addBook() {
        Book book = dataReader.createBook();
        library.addBook(book);
    }

    private void printBooks() {
        library.printBooks();
    }

    private void exit() {
        System.out.println("Do widzenia");
        dataReader.close();
    }

    private void printOptions() {
        System.out.println("Wybierz opcje: ");
        System.out.println("0 - Wyjscie z programu");
        System.out.println("1 - Dodanie nowej ksiazki");
        System.out.println("2 - Wyswietl dostepne ksiazki");
    }

}