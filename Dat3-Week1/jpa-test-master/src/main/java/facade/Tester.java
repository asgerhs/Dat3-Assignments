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

/**
 *
 * @author thomas
 */
public class Tester {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public Customer getCustomer(int id){
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }
    public Customer createCustomer(Customer c){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }
    public List<Customer> getAllCustomers(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }
    public void populate(){
        createCustomer(new Customer("Jesper", "Olesen", 44, "jesper.olesen@mail.dk", "+4540302010"));
        createCustomer(new Customer("Hanne", "Olesen", 42, "hanne.olesen@mail.dk", "+4540302001"));
        createCustomer(new Customer("Jasmin", "Olesen", 4, "hanne.olesen@mail.dk", "+4540302001"));
    }
    
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.populate();
        tester.getAllCustomers().forEach(el->System.out.println(el.getFirstName()+" "+el.getLastName()));
    }
}
