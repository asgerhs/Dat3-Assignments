/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asgerhs
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

           Customer c1 = new Customer("William", "Housefield");
           Customer c2 = new Customer("Andreas", "Ukrudtsblomst");
       try{
           em.getTransaction().begin();
           em.persist(c1);
           em.persist(c2);
           em.getTransaction().commit();
           System.out.println(c1.getId() + c1.getLastName());
           System.out.println(c2.getId() + c2.getLastName());
       }finally{
           em.close();
       }
    }

}


