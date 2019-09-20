package facades;

import entities.Customer;
import entities.ItemType;
import entities.Order;
import entities.OrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Facade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long CustomerCount = (long)em.createQuery("SELECT COUNT(c) FROM Customer c").getSingleResult();
            return CustomerCount;
        }finally{  
            em.close();
        }
        
    }
    
    public Customer createCustomer(String name, String email){
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer(name, email);
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally{
            em.close();
        }
    }
    
    public Customer getCustomer(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Customer.class, id);
        }finally{
            em.close();
        }
    }
    
    public List<Customer> getAllCustomer(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        }finally{
            em.close();
        }
    }
    
    public ItemType createItemType(String name, String description, String price){
        EntityManager em = emf.createEntityManager();
        ItemType it = new ItemType(name, description, price);
        try{
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
            return it;
        }finally{
            em.close();
        }
        
    }
    
    public ItemType getItemType(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(ItemType.class, id);
        }finally{
            em.close();
        }
    }
    
    public void addOrderToCustomer(Order o, int id){
        EntityManager em = emf.createEntityManager();
        
        try{
         Order order = em.find(Order.class, o.getOrderID());
         Customer c = em.find(Customer.class, id);
         c.addToOrder(order);
        }finally{
            em.close();
        }
    }
    
    
//    public Order createOrder(int quantity, String name, String description, String price){
//        EntityManager em = emf.createEntityManager();
//        ItemType it = new ItemType(name, description, price);
//        OrderLine ol = new OrderLine(quantity, it);
//        Order order;
//        try{
//            order.addOl(ol);
//        }
//        
//    }
    
    
}
