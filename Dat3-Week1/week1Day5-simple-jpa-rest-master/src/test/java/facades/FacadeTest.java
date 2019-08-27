/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
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
public class FacadeTest {
    
    private static EntityManagerFactory emf;
    private static Facade fc;
    private static EntityManager em;
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
        em = emf.createEntityManager();
        fc = Facade.getFacade(emf);
        
        BankCustomer c1 = new BankCustomer("Asger", "hermind", "123", 123.0, 12, "what");
        BankCustomer c2 = new BankCustomer("Vikke", "Vikkesen", "13", 0, 0, "what");
        BankCustomer c3 = new BankCustomer("Martin", "Martinsen", "321", 0, 0, "what");
        
        try{
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);
        
        
    }

    /**
     * Test of getCustomerByID method, of class Facade.
     */
    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        BankCustomerDTO result = fc.getCustomerByID(1L);
        assertEquals("Asger hermind", result.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class Facade.
     */
    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        List<BankCustomerDTO> result = fc.getCustomerByName("Asger");
        assertEquals("Asger hermind", result.get(0).getFullName());
    }

    /**
     * Test of addCustomer method, of class Facade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer cust = new BankCustomer("test", "test", "000", 0, 0, "test");
        BankCustomer result = fc.addCustomer(cust);
        assertEquals("test", result.getFirstName());
    }

    /**
     * Test of getAllBankCustomers method, of class Facade.
     */
    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        List<BankCustomer> result = fc.getAllBankCustomers();
        assertEquals(4, result.size());
    }
    
}
