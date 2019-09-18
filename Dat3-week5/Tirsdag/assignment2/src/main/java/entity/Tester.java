package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asgerhs
 */
public class Tester {
    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//        EntityManager em = emf.createEntityManager();
//        Customer c1 = new Customer("Asger", "Sørensen");
//        Customer c2 = new Customer("William", "Housefield");
//        c1.addAddresses(new Address("Trekronergade", "Valby"));
//        c2.addAddresses(new Address("Krusågade 8", "København"));
//        c1.addAddresses(new Address("Stockholmsgade 17", "Østerbro"));
//        
//        try{
//            em.getTransaction().begin();
//            em.persist(c1);
//            em.persist(c2);
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
    

    }
}
