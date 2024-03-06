package cz.daniellinda.pv.Database.Customers;

import cz.daniellinda.pv.Database.CustomerTypes.CustomerTypes;
import cz.daniellinda.pv.Database.Orders.Orders;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customers {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "customerTypes_id")
    private CustomerTypes customerTypes;
    @OneToMany(mappedBy = "customers")
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public CustomerTypes getCustomerTypes() {
        return customerTypes;
    }

    public void setCustomerTypes(CustomerTypes customerTypes) {
        this.customerTypes = customerTypes;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }
}
