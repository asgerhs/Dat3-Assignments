package facades;

import Exceptions.PersonNotFoundException;
import entities.Address;
import utils.EMF_Creator;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade fc;
    private static Person p1, p2;
    private static Address a1, a2;

    public PersonFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/week5day3_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        fc = PersonFacade.getPersonFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       fc = PersonFacade.getPersonFacade(emf);
       a1 = new Address("Krusågade 8", "København", "2200");
       a2 = new Address("Trekronergade 70", "Valby", "2500");
       p1 = new Person("William", "Housefield", "123", a1);
       p2 = new Person("Asger", "Hermind", "123", a2);
       
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson() throws PersonNotFoundException {
        System.out.println("addPerson");
        Person p = fc.addPerson("test", "test", "test12", a1.getStreet(), a1.getCity(), a1.getZip());
        assertEquals("test", fc.getPerson(p.getId()).getFirstName());
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson() throws PersonNotFoundException {
        System.out.println("getPerson");
        Person result = fc.getPerson(p1.getId());
        assertEquals(p1.getFirstName(), result.getFirstName());
    }

    /**
     * Test of getAllPeople method, of class PersonFacade.
     */
    @Test
    public void testGetAllPeople() {
        System.out.println("getAllPeople");
        List<Person> result = fc.getAllPeople();
        assertEquals(2, result.size());
    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson() throws PersonNotFoundException {
        System.out.println("deletePerson");
        assertEquals("William", fc.deletePerson(p1.getId()).getFirstName());
    }


    /**
     * Test of editPerson method, of class PersonFacade.
     */
    @Test
    public void testEditPerson() throws PersonNotFoundException {
        System.out.println("editPerson");
        p1.setFirstName("Henrik");
        fc.editPerson(p1);
        assertEquals("Henrik", p1.getFirstName());
    }
    
    @Test
    public void test404(){
        System.out.println("404 exception");
        try{
            fc.getPerson(9999);
        }catch(PersonNotFoundException ex){
            assertEquals(HttpStatus.NOT_FOUND_404, 404);
        }
    }
    
    
}
