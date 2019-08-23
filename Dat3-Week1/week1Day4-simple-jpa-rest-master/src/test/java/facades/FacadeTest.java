/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        fc = Facade.getFacade(emf);
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(new Employee("Martin Martinsen", "Martinsensvej 1", 1000));
            em.persist(new Employee("William Housefield", "weedstreet 420", 300));
            em.persist(new Employee("Andreas Ukrudtsblomst", "Pigevejen 40", 100));
            em.persist(new Employee("Asger Hermind", "Trekroner 70", 1000));
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
    }
    

    /**
     * Test of getEmployeeByID method, of class Facade.
     */
    @Test
    public void testGetEmployeeByID() {
        System.out.println("getEmployeeByID");
        Employee result = fc.getEmployeeByID(2L);
        assertNotNull(result);
        assertEquals("Martin Martinsen", result.getName());
    }

    /**
     * Test of getEmployeeByName method, of class Facade.
     */
    @Test
    public void testGetEmployeeByName() {
        System.out.println("getEmployeeByName");
        String name = "William Housefield";
        List<Employee> result = fc.getEmployeeByName(name);
        assertNotNull(result);
        assertEquals(name, result.get(0).getName());
    }

    /**
     * Test of getAllEmployees method, of class Facade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        List<Employee> result = fc.getAllEmployees();
        assertEquals(4, result.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class Facade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        List<Employee> result = fc.getEmployeesWithHighestSalary();
        assertEquals(2, result.size());
    }

    /**
     * Test of createEmployee method, of class Facade.
     */
    @Test
    public void testCreateEmployee() {
        System.out.println("createEmployee");
        String name = "Test";
        String address = "Test 123";
        int salary = 15;
        Employee result = fc.createEmployee(name, address, salary);
        assertEquals("Test", result.getName());
        EntityManager em1 = emf.createEntityManager();
        try{
            em1.getTransaction().begin();
            em1.remove(em1.find(Employee.class, 5L));
            em1.getTransaction().commit();
        }finally{
            em1.close();
        }
    }

   
    
}
