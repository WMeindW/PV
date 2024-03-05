package cz.daniellinda.pv.Database.CustomerTypes;

import cz.daniellinda.pv.Database.Customers.Customers;
import jakarta.persistence.*;

import java.util.List;

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

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
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

    public boolean isTaxFree() {
        return isTaxFree;
    }

    public void setTaxFree(boolean taxFree) {
        isTaxFree = taxFree;
    }

    public Long getId() {
        return id;
    }
}
