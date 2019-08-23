package facades;

import entities.Employee;
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

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee e = em.find(Employee.class, id);
            return e;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.salary =(SELECT MAX(e1.salary) FROM Employee e1)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String address, int salary) {
        EntityManager em = emf.createEntityManager();
        Employee e = new Employee(name, address, salary);
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        }finally{
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        EntityManager em = emf.createEntityManager();
//        Employee e = new Employee("Asger", "Trekroner", 10000);
//        try{
//            em.getTransaction().begin();
//            em.persist(e);
//            em.getTransaction().commit();
//            
//        }finally{
//            em.close();
//        }
        System.out.println(fc.getEmployeesWithHighestSalary());
//        fc.createEmployee("Martin", "Martinvejen", 10);
        

    }
}
