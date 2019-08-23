/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author asgerhs
 */
public class BookFacade {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = BookFacade.getBookFacade(emf);
        Book b1 = facade.addBook("Author 1");
        Book b2 = facade.addBook("author 2");
        
        System.out.println("Book 1: " + facade.findBook(b1.getId()).getAuthor());
        System.out.println("Book 2: " + facade.findBook(b2.getId()).getAuthor());
        
        System.out.println("Number of books: " + facade.getAllBooks().size());
    }

    private static EntityManagerFactory emf;
    private static BookFacade instance;

    private BookFacade() {

    }
    
    public static BookFacade getBookFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new BookFacade();
        }
        return instance; 
    }
    
    public Book addBook(String author){
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        }finally{
            em.close();
        }
    }
    
    public Book findBook(int id){
        EntityManager em = emf.createEntityManager();
        try{
            Book book = em.find(Book.class, id);
            return book;
        }finally{
            em.close();
        }
    }
    
    public List<Book> getAllBooks(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = em.createQuery("select book from Book book", Book.class);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

}
