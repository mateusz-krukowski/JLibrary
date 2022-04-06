package test.library.model;

import java.io.Serializable;

public class Library implements Serializable {

    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNum = 0;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public Publication[] getPublications(){
        Publication[] result = new Publication[publicationsNum];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addPublication(Publication publication){
        if(publicationsNum>=MAX_PUBLICATIONS){
            throw new ArrayIndexOutOfBoundsException("Max publications exceeded " + MAX_PUBLICATIONS);
        }
        publications[publicationsNum] = publication;
        publicationsNum++;
    }

}