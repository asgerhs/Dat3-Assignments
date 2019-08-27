package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Facade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }


    public BankCustomerDTO getCustomerByID(long id) {
        EntityManager em = getEntityManager();
        try {
            return new BankCustomerDTO(em.find(BankCustomer.class, id));
        } finally {
            em.close();
        }
    }

    public List<BankCustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class).setParameter("name", name);
            List<BankCustomerDTO> result = new ArrayList();
            for(BankCustomer bc : query.getResultList()){
                result.add(new BankCustomerDTO(bc));
            }
            return result;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
            return query.getResultList();
        }finally{
            em.close();
        }
    }
    
    

}
