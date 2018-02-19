package nk.dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBUtil {
    private final static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("NotesKeeperPU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
        
    }
    
    public static void closeEmFactory(){
        emf.close();
    }
}