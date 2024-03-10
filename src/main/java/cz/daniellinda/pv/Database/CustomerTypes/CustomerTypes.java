package cz.daniellinda.pv.Database.CustomerTypes;

import cz.daniellinda.pv.Database.Customers.Customers;
import jakarta.persistence.*;

import java.util.List;

/**
 * The type Customer types.
 */
@Entity
public class CustomerTypes {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean isTaxFree;
    @OneToMany(mappedBy = "customerTypes")
    private List<Customers> customers;

    /**
     * Gets customers.
     *
     * @return the customers
     */
    public List<Customers> getCustomers() {
        return customers;
    }

    /**
     * Sets customers.
     *
     * @param customers the customers
     */
    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
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
     * Is tax free boolean.
     *
     * @return the boolean
     */
    public boolean isTaxFree() {
        return isTaxFree;
    }

    /**
     * Sets tax free.
     *
     * @param taxFree the tax free
     */
    public void setTaxFree(boolean taxFree) {
        isTaxFree = taxFree;
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
