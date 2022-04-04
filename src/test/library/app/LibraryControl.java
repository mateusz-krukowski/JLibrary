package test.library.app;

import test.library.exception.NoSuchOptionException;
import test.library.io.ConsolePrinter;
import test.library.io.DataReader;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

import java.util.InputMismatchException;

public class LibraryControl {

    private DataReader dataReader = new DataReader();
    private Library library = new Library();
    private ConsolePrinter printer = new ConsolePrinter();

    public void controlLoop(){
        Option option;
        do {
            printOptions();
            option = getOption();
            switch(option){
                case EXIT: exit(); break;
                case ADD_BOOK: addBook(); break;
                case ADD_MAGAZINE: addMagazie(); break;
                case PRINT_BOOKS: printBooks(); break;
                case PRINT_MAGAZINES: printMagazines(); break;
            }
        } while(option !=Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option  option = null;
        while(!optionOk){
            try{
                option = Option.createFromint(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e){
                    printer.printLine(e.getMessage());
            }
            catch(InputMismatchException e){
                printer.printLine(e.getMessage());
            }
        }
        return option;
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
        printer.printBooks(library.getPublications());
    }

    private void addMagazie() {
        Magazine magazine = dataReader.createMagazine();
        library.addMagazine(magazine);
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications());
    }
}