package test.library.io.file;

import test.library.exception.DataExportException;
import test.library.exception.DataImportException;
import test.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager{

    public static final String FILE_NAME = "src/Library.o";

    @Override
    public Library importData() {

        try (   //try-with-resources - streams are automatically closed
              FileInputStream fis = new FileInputStream(FILE_NAME);
              ObjectInputStream ois = new ObjectInputStream(fis);
        ){

            return (Library)ois.readObject(); //casting object into library

        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("B³¹d odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + FILE_NAME);
        }

    }

    @Override
    public void exportData(Library library) {

        try( //try-with-resources
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){

            oos.writeObject(library);
        }
        catch(FileNotFoundException e){
            throw new DataExportException("Brak pliku " + FILE_NAME);
        }
        catch (IOException e) {
            throw new DataExportException("B³¹d zapisu danych do pliku " + FILE_NAME);
        }

    }
}