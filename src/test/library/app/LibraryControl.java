package test.library.app;

import test.library.exception.DataExportException;
import test.library.exception.DataImportException;
import test.library.exception.InvalidDataException;
import test.library.exception.NoSuchOptionException;
import test.library.io.ConsolePrinter;
import test.library.io.DataReader;
import test.library.io.file.FileManager;
import test.library.io.file.FileManagerBuilder;
import test.library.model.Library;
import test.library.model.Book;
import test.library.model.Magazine;

import java.util.InputMismatchException;

public class LibraryControl {

    private Library library = new Library();
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer); //dependency injection
    private FileManager fileManager;

    public LibraryControl(){
        fileManager = new FileManagerBuilder(printer,dataReader).build();

        try{
            library = fileManager.importData();
        }
        catch(DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nowa baze");
            library = new Library();
        }

    }

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
                printer.printLine("Podaj liczbe!");
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

        try {   fileManager.exportData(library);
        } catch (DataExportException e){
         printer.printLine(e.getMessage());
        }
        printer.printLine("Do widzenia");
        dataReader.close();
    }

    private void addBook() {
        try {
            Book book = dataReader.createBook();
            library.addPublication(book);
        } catch(InputMismatchException e){
            printer.printLine("Nie udalo się dodac ksiazki, nieprawidlowa dana");
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.createMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udalo sie dodac magazynu, nieprawidlowa dana");
        }
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications());
    }
//======================================================================//
    private enum Option {
        EXIT(0,"Wyjscie z programu"),
        ADD_BOOK(1, "Dodaj nowa ksiazke"),
        ADD_MAGAZINE(2,"Dodaj nowy magazyn"),
        PRINT_BOOKS(3,"Wyswietl dostepne ksiazki"),
        PRINT_MAGAZINES(4,"Wyswietl dostepne magazyny");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromint(int i) {
            try {
                return Option.values()[i];
            } catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("Brak opcji o ID "+ i);
            }
        }
    }
}