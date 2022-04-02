package test.library.model;

public class Library {
    private final int MAX_BOOKS = 10;
    private Book[] books = new Book[MAX_BOOKS];
    private int booksNum = 0;

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
}