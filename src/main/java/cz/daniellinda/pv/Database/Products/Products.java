package cz.daniellinda.pv.Database.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.daniellinda.pv.Database.Items.Items;
import jakarta.persistence.*;

import java.util.List;

/**
 * The type Products.
 */
@Entity
public class Products {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String text;
    @Column(nullable = false)
    private Long price;
    @OneToMany(mappedBy = "products")
    private List<Items> items;

    /**
     * Gets items.
     *
     * @return the items
     */
    @JsonIgnore
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
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Long price) {
        this.price = price;
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
    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                '}';
    }
}
