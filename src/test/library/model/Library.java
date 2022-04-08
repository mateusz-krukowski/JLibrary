package test.library.model;

import test.library.exception.PublicationAlreadyExistsException;
import test.library.exception.UserAlreadyExistsException;
import test.library.model.comparator.AlphabeticalComparator;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private static final int INITIAL_CAPACITY = 10;
    private int publicationsNum = 0;
    private HashMap<String, Publication> publications= new HashMap();
    private HashMap<String, LibraryUser> users = new HashMap();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator) {
        ArrayList<Publication> pub = new ArrayList<>(this.publications.values());
        pub.sort(comparator);
        return pub;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator){
        ArrayList<LibraryUser> u = new ArrayList<>(this.users.values());
        u.sort(comparator);
        return u;
    }

    public void addPublication(Publication publication){
        if(publications.containsKey(publication.getTitle())){
            throw new PublicationAlreadyExistsException("Publikacja o takim tytule juz istnieje: "+ publication.getTitle());
        } else publications.put(publication.getTitle(),publication);
    }

    public void addUser(LibraryUser user){
        if(users.containsKey(user.getPesel())){
            throw new UserAlreadyExistsException("Uzytkownik o takim peselu juz istnieje: " + user.getPesel());
        } else users.put(user.getPesel(),user);
    }

    public boolean removePublication(Publication pub){
        if(publications.containsValue(pub)){
            publications.remove(pub.getTitle());
            return true;
        } else return false;
    }
}