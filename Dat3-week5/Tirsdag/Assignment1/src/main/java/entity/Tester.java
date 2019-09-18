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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("Andreas", "Sørensen");
        Customer c2 = new Customer("Martin", "Housefield");
        c1.addHobbies("hej");
        c2.addHobbies("hall");
        c1.addPhone("29410261", "Asger");
        c2.addPhone("‭22155812", "William");
        try{
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}
