package cz.daniellinda.pv.Database.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(Long numberOf) {
        this.numberOf = numberOf;
    }
    @JsonIgnore
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" +
                ", numberOf=" + numberOf +
                ", products=" + products +
                '}';
    }
}
