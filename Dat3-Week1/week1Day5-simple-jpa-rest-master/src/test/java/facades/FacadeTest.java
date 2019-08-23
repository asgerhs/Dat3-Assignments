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
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
         EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new BankCustomer("Andreas", "Vikke", "999", 1234.15, 30, "fattig studerende" ));
            em.persist(new BankCustomer("William", "Housefield", "420", 100000.00, 15, "rig studerende" ));
            em.persist(new BankCustomer("Martin", "Eli", "250", 19240.75, 28, "Ikke indvandre" ));
            em.persist(new BankCustomer("Asger", "Hermind", "111", 100000000.15, 1, "studerende" ));
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
  

    /**
     * Test of getCustomerByID method, of class Facade.
     */
    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        BankCustomerDTO result = fc.getCustomerByID(2);
        assertNotNull(result);
        assertEquals("Andreas Vikke", result.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class Facade.
     */
    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        List<BankCustomerDTO> result = fc.getCustomerByName("Andreas Vikke");
        assertEquals("999", result.get(0).getAccountNumber());
    }

    /**
     * Test of addCustomer method, of class Facade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer cust = null;
        Facade instance = null;
        BankCustomer expResult = null;
        BankCustomer result = instance.addCustomer(cust);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllBankCustomers method, of class Facade.
     */
    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        Facade instance = null;
        List<BankCustomer> expResult = null;
        List<BankCustomer> result = instance.getAllBankCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
