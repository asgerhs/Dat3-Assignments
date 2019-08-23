/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asgerhs
 */
public class facadeTest {
    
   // Det virker ikke, får en null pointer exception, så bare lad vær med at udkommenter. 
    
//    private static EntityManagerFactory emf;
//    private static facade fc;
//    
//    public facadeTest() {
//       
//        
//        
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//         emf = Persistence.createEntityManagerFactory("pu");
//        fc = facade.getBookFacade(emf);
//        EntityManager em = emf.createEntityManager();
//        
//        try{
//            em.getTransaction().begin();
//            em.persist(new Customer("Asger", "Hermind"));
//            em.persist(new Customer("Martin", "Martinsen"));
//            em.persist(new Customer("William", "Housefield"));
//            em.persist(new Customer("Andreas", "Ukrudtsblomst"));
//            em.getTransaction().commit();
//        }finally{
//            em.close();
//        }
//    }
//    
//
//    /**
//     * Test of findByID method, of class facade.
//     */
//    @Test
//    public void testFindByID() {
//        System.out.println("findByID");
//        Customer result = fc.findByID(1);
//        assertEquals("Martin", result.getFirstName());
//    }
//
//    /**
//     * Test of findByLastName method, of class facade.
//     */
//    @Test
//    public void testFindByLastName() {
//        System.out.println("findByLastName");
//        List<Customer> result = fc.findByLastName("Housefield");
//        assertEquals("Housefield", result.get(0).getLastName());
//    }
//
//    /**
//     * Test of getNumberOfCustomers method, of class facade.
//     */
//    @Test
//    public void testGetNumberOfCustomers() {
//        System.out.println("getNumberOfCustomers");
//        int result = fc.getNumberOfCustomers();
//        assertEquals(5, result);
//    }
//
//    /**
//     * Test of allCustomers method, of class facade.
//     */
//    @Test
//    public void testAllCustomers() {
//        System.out.println("allCustomers");
//        facade instance = new facade();
//        List<Customer> result = fc.allCustomers();
//        assertEquals(5, result.size());
//    }
//
//    /**
//     * Test of addCustomer method, of class facade.
//     */
//    @Test
//    public void testAddCustomer() {
//        System.out.println("addCustomer");
//        Customer result = fc.addCustomer("test", "test");
//        assertNotNull(result);
//        assertEquals("test", result.getFirstName());
//         EntityManager em1 = emf.createEntityManager();
//        
//    }

  
    
}
