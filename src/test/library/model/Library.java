package test.library.model;

public class Library {

    private final int MAX_BOOKS = 10;
    private final int MAX_MAGAZINES = 10;

    private int booksNum = 0;
    private int magazinesNum = 0;

    private Book[] books = new Book[MAX_BOOKS];
    private Magazine[] magazines = new Magazine[MAX_MAGAZINES];


    public void addBook(Book book){
        if(booksNum < MAX_BOOKS){
            books[booksNum] = book;
            booksNum++;
        }
        else {
            System.out.println("Maksymalna liczba ksiazek zostala osiagnieta");
        }
    }

    public void printBooks(){
        if(booksNum == 0){
            System.out.println("Brak ksiazek");
        }
        else {
            for (int i = 0; i < booksNum; i++) {
               books[i].printInfo();
            }
        }
    }

    public void addMagazine(Magazine magazine){
        if(magazinesNum < MAX_MAGAZINES){
            magazines[magazinesNum] = magazine;
            magazinesNum++;
        }
        else {
            System.out.println("Maksymalna liczba magazynow zostala osiagnieta");
        }
    }

    public void printMagazines(){
        if(magazinesNum ==0){
            System.out.println("Brak magazynow");
        } else {
            for (int i = 0; i < magazinesNum; i++) {
                magazines[i].printInfo();
            }
        }
    }
}