package test.library.app;

import test.library.model.Book;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("W pustyni i w puszczy","Henryk Sienkiewicz",2010,296,"Greg","9788420764641");
        Book book2 = new Book("Java, efektywne programowanie","Joshua Bloch", 2009,352,"Helion","9788324620845");
        Book book3 = new Book("Mikrokontrolery z rdzeniem ARM9 w przykładach", "Lucjan Bryndza",2009,264,"BTC");

        System.out.println("Ksiazki dostepne w bibliotece: ");
        book1.printInfo();
        book2.printInfo();
        book3.printInfo();
    }
}