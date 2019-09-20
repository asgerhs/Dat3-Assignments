package facades;

import Exceptions.PersonNotFoundException;
import entities.Address;
import entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long PersonCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return PersonCount;
        } finally {
            em.close();
        }

    }

    @Override
    public Person addPerson(String fName, String lName, String phone, String street, String city, String zip) {
        EntityManager em = emf.createEntityManager();
        Person p;
        try {
            List<Address> query = em.createQuery("SELECT a FROM Address a where a.street = :street AND a.city = :city AND a.zip = :zip", Address.class)
                    .setParameter("street", street)
                    .setParameter("city", city)
                    .setParameter("zip", zip)
                    .getResultList();
            if (query.size() > 0) {
                p = new Person(fName, lName, phone, query.get(0));
            } else {
                p = new Person(fName, lName, phone, new Address(street, city, zip));
            }
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                throw new PersonNotFoundException("No person with that id found.");
            } else {
                return p;
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPeople() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                throw new PersonNotFoundException("Couldn't find a person with given id.");
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person p) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Person check = em.find(Person.class, p.getId());
            if (check == null) {
                throw new PersonNotFoundException("Person with this id does not exist");
            }
            p.setLastEdited(new Date());
            em.getTransaction().begin();
            p = em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
