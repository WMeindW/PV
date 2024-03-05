package cz.daniellinda.pv.Database.Orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.daniellinda.pv.Database.Customers.Customers;
import cz.daniellinda.pv.Database.Items.Items;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
