package cz.daniellinda.pv.Database.Customers;

import cz.daniellinda.pv.Database.CustomerTypes.CustomerTypes;
import cz.daniellinda.pv.Database.Orders.Orders;
import jakarta.persistence.*;

import java.util.List;

/**
 * The type Customers.
 */
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

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     * Gets customer types.
     *
     * @return the customer types
     */
    public CustomerTypes getCustomerTypes() {
        return customerTypes;
    }

    /**
     * Sets customer types.
     *
     * @param customerTypes the customer types
     */
    public void setCustomerTypes(CustomerTypes customerTypes) {
        this.customerTypes = customerTypes;
    }


    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
}
