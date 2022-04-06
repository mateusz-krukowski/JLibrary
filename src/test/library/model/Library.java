package test.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {

    private static final int INITIAL_CAPACITY = 10;
    private int publicationsNum = 0;
    private Publication[] publications = new Publication[INITIAL_CAPACITY];

    public Publication[] getPublications(){
        Publication[] result = new Publication[publicationsNum];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addPublication(Publication publication){
        if(publicationsNum>= INITIAL_CAPACITY){
            publications = Arrays.copyOf(publications, publications.length*2);
        }
        publications[publicationsNum] = publication;
        publicationsNum++;
    }

    public boolean removePublication(Publication pub){
        final int notFound = -1;
        int found = notFound;
        int i = 0;
        while (i < publicationsNum && found == notFound){
            if(pub.toString().equals(publications[i].toString())){
                found = i;
            } else { i++;
            }
        }
        if(found!=notFound){
            System.arraycopy(publications,found +1, publications, found, publications.length - found - 1);
            publicationsNum--;
            return true;
        }
        return false;
    }
}