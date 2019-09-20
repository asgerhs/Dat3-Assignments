package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author asgerhs
 */
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<OrderLine> ol = new ArrayList();

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOl() {
        return ol;
    }

    public void setOl(List<OrderLine> ol) {
        this.ol = ol;
    }

    public void addOl(OrderLine o){
        ol.add(o);
    }
    
}
