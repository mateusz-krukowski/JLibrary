package test.library.io.file;

import test.library.app.LibraryControl;
import test.library.exception.DataExportException;
import test.library.exception.DataImportException;
import test.library.exception.InvalidDataException;
import test.library.model.Book;
import test.library.model.Library;
import test.library.model.Magazine;
import test.library.model.Publication;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CsvFileManager implements FileManager{
    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }
        return library;
        }
        catch (FileNotFoundException e ){
            throw new DataImportException("Brak pliku " + FILE_NAME);
        }
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if(Book.TYPE.equals(type)){
            return createBookFromCsv(split);
        } else if(Magazine.TYPE.equals(type)){
            return createMagazineFromCvs(split);
        } throw new InvalidDataException("Nieznany  typ publikacji " + type);

    }

    private Magazine createMagazineFromCvs(String[] s) {
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

    @Override
    public void exportData(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        try(
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ) {
            for (Publication publication : publications) {
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e ){
            throw new DataExportException("Blad zapisu danych do pliku");
        }
    }
}