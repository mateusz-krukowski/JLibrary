package test.library.io;

import test.library.model.Book;
import test.library.model.Magazine;
import test.library.model.Publication;

public class ConsolePrinter {

    public void printBooks(Publication[] publications){
        boolean isEmpty = true;
        for(Publication i : publications){
            if (i instanceof Book) {
                i.printInfo();
                isEmpty = false;
            }
        }
        if(isEmpty) printLine("Brak ksiazek w bibliotece");
    }

    public void printMagazines(Publication[] publications){
        boolean isEmpty = true;
        for(Publication i : publications){
            if (i instanceof Magazine) {
                i.printInfo();
                isEmpty = false;
            }
        }
        if(isEmpty) printLine("Brak magazynow w bibliotece");
    }

    public void printLine(String text){
        System.out.println(text);
    }
}

