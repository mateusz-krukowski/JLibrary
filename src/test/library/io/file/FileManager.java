package test.library.io.file;

import test.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
