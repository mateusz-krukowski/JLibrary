package test.library.app;

import test.library.exception.*;
import test.library.io.ConsolePrinter;
import test.library.io.DataReader;
import test.library.io.file.FileManager;
import test.library.io.file.FileManagerBuilder;
import test.library.model.*;

import java.util.Comparator;
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
        catch(DataImportException | InvalidDataException | CheckListException e){
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
                case DELETE_BOOK: deleteBook(); break;
                case DELETE_MAGAZINE: deleteMagazine(); break;
                case ADD_USER: addUser(); break;
                case PRINT_USERS: printUsers(); break;
                default: printer.printLine("Nie ma takiej opcji, wprowadz ponownie");
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
    private void deleteBook(){
        Book book = dataReader.createBook();
        if(library.removePublication(book)){
            printer.printLine("ksiazka " + book + " zostala pomyslnie usunieta");
        } else {
            printer.printLine("Brak wskazanej ksiazki");
        }
    }

    private void printBooks() {
        printer.printBooks(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.createMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udalo sie dodac magazynu, nieprawidlowa dana");
        }
    }
    private void deleteMagazine(){
        Magazine magazine = dataReader.createMagazine();
        if(library.removePublication(magazine)){
            printer.printLine("Magazyn " + magazine + " zostal pomyslnie usuniety");
        } else printer.printLine("Brak wskazanego magazynu");
    }
    private void printMagazines() {
        printer.printMagazines(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void printUsers() { //comparator is a functional interface
        printer.printUsers(library.getSortedUsers(
                //(Comparator<User>)//
                (o1, o2) -> o1.getLastName().compareToIgnoreCase(o2.getLastName()) //lambda
                //Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER) //method reference
        ));
    }

    private void addUser() {
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try{
            library.addUser(libraryUser);
        } catch(UserAlreadyExistsException e){
            printer.printLine(e.getMessage());
        }
    }
//======================================================================//
    private enum Option {
        EXIT(0,"Wyjscie z programu"),
        ADD_BOOK(1, "Dodaj nowa ksiazke"),
        ADD_MAGAZINE(2,"Dodaj nowy magazyn"),
        PRINT_BOOKS(3,"Wyswietl dostepne ksiazki"),
        PRINT_MAGAZINES(4,"Wyswietl dostepne magazyny"),
        DELETE_BOOK(5,"Usun ksiazke"),
        DELETE_MAGAZINE(6, "Usun magazyn"),
        ADD_USER(7, "Dodaj uzytkownika"),
        PRINT_USERS(8,"Wyswietl uzytkownikow");

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