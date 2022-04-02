package test.library.app;

public enum Option {
    EXIT(0,"Wyjście z programu"),
    ADD_BOOK(1, "Dodaj nową książkę"),
    ADD_MAGAZINE(2,"Dodaj nowy magazyn"),
    PRINT_BOOKS(3,"Wyświetl dostępne książki"),
    PRINT_MAGAZINES(4,"Wyświetl dostępne magazyny");

    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static Option createFromint(int i){
        return Option.values()[i];
    }
}