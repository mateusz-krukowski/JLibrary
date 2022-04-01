public class Book {
    String title;
    String author;
    int releaseDate;
    int pages;
    String publisher;
    String isbn;

    Book(String bookTitle, String bookAuthor, int bookRelease, int bookPages, String bookPublisher, String bookIsbn) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.releaseDate = bookRelease;
        this.pages = bookPages;
        this.publisher = bookPublisher;
        this.isbn = bookIsbn;
    }

}
