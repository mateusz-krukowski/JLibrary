package test.library.io;

import test.library.model.Book;
import test.library.model.LibraryUser;
import test.library.model.Magazine;
import test.library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications){
        boolean isEmpty = true;
        for(Publication publication : publications){
            if (publication instanceof Book) {
                printLine(publication.toString());
                isEmpty = false;
            }
        }
        if(isEmpty) printLine("Brak ksiazek w bibliotece");
    }

    public void printMagazines(Collection<Publication> publications){
        boolean isEmpty = true;
        for(Publication i : publications){
            if (i instanceof Magazine) {
                i.printInfo();
                isEmpty = false;
            }
        }
        if(isEmpty) printLine("Brak magazynow w bibliotece");
    }

    public void printUsers(Collection<LibraryUser> users){
        if(users.isEmpty()) {
            printLine("Brak uzytkownikow w bibliotece");
            return;
        }
        for (LibraryUser user : users){
            printLine(user.toString());
        }
    }

    public void printLine(String text){
        System.out.println(text);
    }
}

