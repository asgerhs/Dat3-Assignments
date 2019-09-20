package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author asgerhs
 */
@Entity
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fName, lName, phone;
    private Address address;

    public PersonDTO() {
    }
    
    
    public PersonDTO(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
        this.address = new Address(p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getZip());
        this.address.setId(p.getAddress().getId());
    }

    public PersonDTO(Integer id, String fName, String lName, String phone, String street, String city, String zip) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.address = new Address(street, city, zip);
        
    }


   
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

   
    
}
