/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author asgerhs
 */
public class facade {

    private static EntityManagerFactory emf;
    private static facade instance;

    public facade() {

    }
    
      public static facade getBookFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new facade();
        }
        return instance; 
      }

     public Customer findByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, id);
            return c;
        } finally {
            em.close();
        }

    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :name", Customer.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList().size();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        Customer customer = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally{
            em.close();
        }
    }
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");
        
//        facade fc = facade.getBookFacade(emf);
//        System.out.println(fc.addCustomer("Martin", "E-9"));
//        System.out.println(fc.allCustomers());
//        System.out.println(fc.findByID(2));
//        System.out.println(fc.getNumberOfCustomers());
//        System.out.println(fc.findByLastName("E-9"));
        
        
    }

}
