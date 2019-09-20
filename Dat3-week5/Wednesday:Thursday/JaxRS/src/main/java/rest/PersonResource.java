package rest;

import Exceptions.GenericExceptionMapper;
import Exceptions.PersonNotFoundException;
import Exceptions.PersonNotFoundExceptionMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Address;
import entities.Person;
import entities.PersonDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/week5day3",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade fc = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = fc.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPeople() {
        List<PersonDTO> dto = new ArrayList();
        for (Person p : fc.getAllPeople()) {
            dto.add(new PersonDTO(p));
        }
        return new Gson().toJson(dto);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSingle(@PathParam("id") int id) {
        try{
        PersonDTO dto = new PersonDTO(fc.getPerson(id));
        return Response.ok(GSON.toJson(dto)).build();
        }catch(PersonNotFoundException ex){
            return new PersonNotFoundExceptionMapper().toResponse(ex);
        }catch(RuntimeException ex){
            return new GenericExceptionMapper().toResponse(ex);
        }
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) {
        try{
            fc.deletePerson(id);
        return Response.ok("{\"status\": \"the person with id="+ id+ "has been deleted\"}").build();
        }catch(PersonNotFoundException ex){
            return new PersonNotFoundExceptionMapper().toResponse(ex);
        }catch(RuntimeException ex){
            return new GenericExceptionMapper().toResponse(ex);
        }
    }

    @Path("/add")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addPerson(String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO dto = new PersonDTO(fc.addPerson(p.getfName(), p.getlName(), p.getPhone(), p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getZip()));
        return Response.ok(dto).build();
    }
    
    @Path("/edit/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPerson(@PathParam("id")int id, String person) {
        try{
            PersonDTO pdto = GSON.fromJson(person, PersonDTO.class);
            Person p = fc.getPerson(id);


            p.setFirstName(pdto.getfName());
            p.setLastName(pdto.getlName());
            p.setPhone(pdto.getPhone());
            Person checked = fc.editPerson(p);
            return Response.ok(new PersonDTO(checked)).build();
        }catch(PersonNotFoundException ex){
            return new PersonNotFoundExceptionMapper().toResponse(ex);
        }catch(RuntimeException ex){
            return new GenericExceptionMapper().toResponse(ex);
        }
        
        
    }
    
    
    
    

    public static void main(String[] args) {
        EntityManager em = EMF.createEntityManager();
        Address a1 = new Address("Krusågade 8", "København", "2200");
        Address a2 = new Address("Trekronergade 70", "Valby", "2500");
        Person p1 = new Person("William", "Housefield", "42069", a1);
        Person p2 = new Person("Asger", "Sørensen", "1234", a2);
        Person p3 = new Person("Andreas", "Vikke", "123", a2);
        
        try {
            em.getTransaction().begin();
//            em.persist(a1);
//            em.persist(a2);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
