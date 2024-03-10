package cz.daniellinda.pv.Database.Orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.daniellinda.pv.Database.Customers.Customers;
import cz.daniellinda.pv.Database.Items.Items;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

/**
 * The type Orders.
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customers customers;
    @OneToMany(mappedBy = "orders")
    private List<Items> items;

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Items> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<Items> items) {
        this.items = items;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets customers.
     *
     * @return the customers
     */
    public Customers getCustomers() {
        return customers;
    }

    /**
     * Sets customers.
     *
     * @param customers the customers
     */
    public void setCustomers(Customers customers) {
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
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
}
