package test.library.app;

import test.library.exception.NoSuchOptionException;
import test.library.io.ConsolePrinter;
import test.library.io.DataReader;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

import java.util.InputMismatchException;

public class LibraryControl {

    private Library library = new Library();
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer); //dependency injection

    public void controlLoop(){
        Option option;
        do {
            printOptions();
            option = getOption();
            switch(option){
                case EXIT: exit(); break;
                case ADD_BOOK: addBook(); break;
                case ADD_MAGAZINE: addMagazine(); break;
                case PRINT_BOOKS: printBooks(); break;
                case PRINT_MAGAZINES: printMagazines(); break;
            }
        } while(option !=Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while(!optionOk){
            try{
                option = Option.createFromint(dataReader.getInt());
                optionOk = true;
                return option;
            } catch (NoSuchOptionException e){
                    printer.printLine(e.getMessage());
            }
            catch(InputMismatchException i){
                printer.printLine("Podaj liczbę!");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("Wybierz opcje: ");
        for(Option i: Option.values()){
            printer.printLine(i.toString());
        }
    }

    private void exit() {
        printer.printLine("Do widzenia");
        dataReader.close();
    }

    private void addBook() {
        try {
            Book book = dataReader.createBook();
            library.addBook(book);
        } catch(InputMismatchException e){
            printer.printLine("Nie udało się dodać książki, nieprawidłowa dana");
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.createMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się dodać magazynu, nieprawidłowa dana");
        }
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications());
    }
}