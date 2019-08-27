/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.BankCustomerDTO;
import facades.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asgerhs
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
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
    
}
