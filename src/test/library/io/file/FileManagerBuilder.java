package test.library.io.file;

import test.library.exception.NoSuchFileTypeException;
import test.library.io.ConsolePrinter;
import test.library.io.DataReader;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader){
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build(){
        printer.printLine("Wybierz format danych: ");
        FileType fileType =  getFileType();
        switch(fileType){
            case SERIAL: return new SerializableFileManager();
            case CSV: return new CsvFileManager();
            default: throw new NoSuchFileTypeException("Nieobslugiwany typ danych");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String s = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(s);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Nieobslugiwany typ danych. Wybierz ponownie");
            }

        } while(!typeOk);

        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()){
            printer.printLine(value.name());
        }
    }
}
