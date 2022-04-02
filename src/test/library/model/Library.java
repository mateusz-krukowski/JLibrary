package test.library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNum = 0;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public void addBook(Book book){
        if(publicationsNum < MAX_PUBLICATIONS){
            publications[publicationsNum] = book;
            publicationsNum++;
        }
        else {
            System.out.println("Maksymalna liczba ksiazek zostala osiagnieta");
        }
    }

    public void printBooks(){
        boolean isEmpty = true;
        for (int i = 0; i < publicationsNum; i++) {
            if (publications[i] instanceof Book) {
                publications[i].printInfo();
                isEmpty = false;
            }
        }
        if(isEmpty) System.out.println("Brak ksiazek w bibliotece");
    }

    public void addMagazine(Magazine magazine){
        if(publicationsNum < MAX_PUBLICATIONS){
            publications[publicationsNum] = magazine;
            publicationsNum++;
        }
        else {
            System.out.println("Maksymalna liczba magazynow zostala osiagnieta");
        }
    }

    public void printMagazines(){
        boolean isEmpty = true;
        for (int i = 0; i < publicationsNum; i++) {
            if (publications[i] instanceof Magazine) {
                publications[i].printInfo();
                isEmpty = false;
            }
        }
        if(isEmpty) System.out.println("Brak magazynow w bibliotece");
    }
}