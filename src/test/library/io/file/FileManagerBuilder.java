package test.library.io.file;

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
        return null;
    }
}
