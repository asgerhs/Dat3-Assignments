package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf; 

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAll(){
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        List<Employee> emp = fc.getAllEmployees();
        List<EmployeeDTO> dto = new ArrayList();
        for(Employee e : emp){
            dto.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(dto);
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getByID(@PathParam("id") int id) {
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        Employee e = fc.getEmployeeByID(id);
        List<Employee> dto = new ArrayList();
        dto.add(e);
        return new Gson().toJson(dto);
    }
    
    @GET
    @Path("/highestpaid")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getHighestPaid(){
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        List<Employee> emp = fc.getEmployeesWithHighestSalary();
        List<EmployeeDTO> dto = new ArrayList();
        for(Employee e : emp){
            dto.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(dto);
    }

    @GET
    @Path("/name/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getByName(@PathParam("name") String name){
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        List<EmployeeDTO> dto = new ArrayList();
        List<Employee> emp = fc.getEmployeeByName(name);
        for(Employee e : emp){
            dto.add(new EmployeeDTO(e));
        }
        return new Gson().toJson(dto);
        
    }
}
