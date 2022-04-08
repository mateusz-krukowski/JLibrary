package test.library.model;

import java.io.Serializable;

public abstract class Publication implements Serializable, Comparable<Publication>, CsvConvertible {
    private int year;
    private String title;
    private String publisher;

    public Publication(int year, String title, String publisher) {
        this.year = year;
        this.title = title;
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void printInfo(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getTitle() + ", " + getPublisher() + ", " + getYear();
    }



    @Override
    public int compareTo(Publication p){
        return title.compareToIgnoreCase(p.title);
    }
}
