package cz.daniellinda.pv.Database.Items;

import cz.daniellinda.pv.Database.Orders.Orders;
import cz.daniellinda.pv.Database.Products.Products;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Items {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long numberOf;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    @ManyToMany(mappedBy = "itemss")
    private List<Products> products;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
