/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PhoneFacade {
    private static PhoneFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PhoneFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PhoneFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getPhoneCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long phoneCount = (long)em.createQuery("SELECT COUNT(p) FROM Phone p").getSingleResult();
            return phoneCount;
        }finally{  
            em.close();
        }
        
    }
    
    public Phone getPhoneById(long id){
            EntityManager em = getEntityManager();
        try {
            Phone phone = em.find(Phone.class, id);
            return phone;
        } finally {
            em.close();
        }
    }  

    public Phone deletePhoneNumber(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}