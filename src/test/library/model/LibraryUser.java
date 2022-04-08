package test.library.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser extends User {
    private List<Publication> publicationHistory = new ArrayList<>();
    private List<Publication> borrowedPublications = new ArrayList<>();

    public LibraryUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }


    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrowedPublications() {
        return borrowedPublications;
    }
    public void addPublicationToHistory(Publication pub){
        publicationHistory.add(pub);
    }
    public void borrowPublication(Publication pub){
        borrowedPublications.add(pub);
    }
    public boolean returnPublication(Publication pub){

        if(borrowedPublications.contains(pub)){
            borrowedPublications.remove(pub);
            publicationHistory.add(pub);
            return true;
        }
        return false;
    }
    @Override
    public String toCsv() {
        return getFirstName() + "; " + getLastName() + " ;" + getPesel();
    }

}