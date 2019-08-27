package rest.service;

import com.google.gson.Gson;
import dto.BankCustomerDTO;
import entities.BankCustomer;
import facades.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf;
    Facade fc;
    EntityManager em;
    

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/setupDatabase")
    public String setupDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        List<BankCustomer> bcs = new ArrayList();
        bcs.add(new BankCustomer("Andreas", "Vikke", "1234", 858473, 5, "Info 1"));
        bcs.add(new BankCustomer("Martin", "Frederiksen", "1235", 50000, 4, "Info 2"));
        bcs.add(new BankCustomer("Asger", "Klamydia", "1236", 200, 2, "Info 3"));
        bcs.add(new BankCustomer("William", "Huusfeldt", "1237", 400, 3, "Info 4"));

        em.getTransaction().begin();
        for (BankCustomer bc : bcs) {
            em.persist(bc);
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
        return "Setup Completed!";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") int id) {
        emf = Persistence.createEntityManagerFactory("pu");
        fc = Facade.getFacade(emf);
        BankCustomerDTO bc = fc.getCustomerByID(id);

        return new Gson().toJson(bc);
    }

    @GET
    @Path("/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAll() {
        emf = Persistence.createEntityManagerFactory("pu");
        fc = Facade.getFacade(emf);
        List<BankCustomerDTO> result = new ArrayList();
        List<BankCustomer> bc = fc.getAllBankCustomers();
        for (BankCustomer b : bc) {
            result.add(new BankCustomerDTO(b));
        }
        return new Gson().toJson(result);

    }

}
