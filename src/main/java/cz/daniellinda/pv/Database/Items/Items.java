package cz.daniellinda.pv.Database.Items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.daniellinda.pv.Database.Orders.Orders;
import cz.daniellinda.pv.Database.Products.Products;
import jakarta.persistence.*;

import java.util.List;

/**
 * The type Items.
 */
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

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets number of.
     *
     * @return the number of
     */
    public Long getNumberOf() {
        return numberOf;
    }

    /**
     * Sets number of.
     *
     * @param numberOf the number of
     */
    public void setNumberOf(Long numberOf) {
        this.numberOf = numberOf;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @JsonIgnore
    public Orders getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    public Products getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(Products products) {
        this.products = products;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
