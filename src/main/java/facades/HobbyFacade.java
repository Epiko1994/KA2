package facades;

import entities.Hobby;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getHobbyCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long HobbyCount = (long)em.createQuery("SELECT COUNT(r) FROM Hobby r").getSingleResult();
            return HobbyCount;
        }finally{
            em.close();
        }

    }

    public Hobby getHobbyById(long id){
        EntityManager em = getEntityManager();
        try {
            Hobby hobby = em.find(Hobby.class, id);
            return hobby;
        } finally {
            em.close();
        }
    }

}
