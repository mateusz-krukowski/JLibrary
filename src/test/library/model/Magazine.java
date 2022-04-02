package test.library.model;

public class Magazine extends Publication {
    private int month;
    private int day;
    private String language;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        this.setTitle(title);
        this.setPublisher(publisher);
        this.setYear(year);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return getTitle() + "; " + getPublisher() + "; " + getLanguage() + "; " + getYear() + "; " + getMonth() + "; " + getDay();
    }
}
