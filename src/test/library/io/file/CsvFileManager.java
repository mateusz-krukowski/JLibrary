package test.library.io.file;

import test.library.exception.DataExportException;
import test.library.exception.DataImportException;
import test.library.exception.CheckListException;
import test.library.exception.InvalidDataException;
import test.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;


public class CsvFileManager implements FileManager{
    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void importPublications(Library library) {
        try(Scanner scanner = new Scanner(new File(FILE_NAME))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }
        }
        catch (FileNotFoundException e ){
            throw new DataImportException("Brak pliku " + FILE_NAME);
        }
    }

    private void importUsers(Library library) {
        try(Scanner scanner = new Scanner(new File(USERS_FILE_NAME))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                LibraryUser libraryUser = createUserFromString(line);
                library.addUser(libraryUser);
            }
        }
        catch (FileNotFoundException e ){
            throw new DataImportException("Brak pliku " + USERS_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String line) {
        try {
            String[] split = line.split(";");
            String firstName = split[0];
            String lastName = split[1];
            String pesel = split[2];
            return new LibraryUser(firstName, lastName, pesel);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new CheckListException("Zle formatowanie pliku csv");
        }
    }

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users,USERS_FILE_NAME);
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications,FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection <T> collection,String fileName) {
        try(
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            for (T o : collection) {
                bufferedWriter.write(o.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e ){
            throw new DataExportException("Blad zapisu danych do pliku");
        }
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if(Book.TYPE.equals(type)){
            return createBookFromCsv(split);
        } else if(Magazine.TYPE.equals(type)){
            return createMagazineFromCsv(split);
        } throw new InvalidDataException("Nieznany typ publikacji " + type);

    }

    private Magazine createMagazineFromCsv(String[] s) {
        String title = s[1];
        String publisher = s[2];
        int year = Integer.valueOf(s[3]);
        int month = Integer.valueOf((s[4]));
        int day = Integer.valueOf((s[5]));
        String language = s[6];

        return new Magazine(title,publisher,language,year,month,day);
    }

    private Book createBookFromCsv(String[] s) {
        String title = s[1];
        String author = s[2];
        int year = Integer.valueOf(s[3]);
        int pages = Integer.valueOf(s[4]);
        String publisher = s[5];
        String isbn = s[6];

        return new Book(title,author,year,pages,publisher,isbn);
    }
}