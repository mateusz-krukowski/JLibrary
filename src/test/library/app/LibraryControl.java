package test.library.app;

import test.library.io.DataReader;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

public class LibraryControl {

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        Option option;
        do {
            printOptions();
            option = Option.createFromint(dataReader.getInt());
            switch(option){
                case EXIT: exit(); break;
                case ADD_BOOK: addBook(); break;
                case ADD_MAGAZINE: addMagazie(); break;
                case PRINT_BOOKS: printBooks(); break;
                case PRINT_MAGAZINES: printMagazines(); break;
            }
        } while(option !=Option.EXIT);
    }

    private void printOptions() {
        System.out.println("Wybierz opcje: ");
        for(Option i: Option.values()){
            System.out.println(i);
        }
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