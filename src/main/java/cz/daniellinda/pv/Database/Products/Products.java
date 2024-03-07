package cz.daniellinda.pv.Database.Products;

import cz.daniellinda.pv.Database.Items.Items;
import jakarta.persistence.*;

import java.util.List;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "items_products", joinColumns = @JoinColumn(name = "items_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"))
    private List<Items> itemss;

    public List<Items> getItemss() {
        return itemss;
    }

    public void setItemss(List<Items> itemss) {
        this.itemss = itemss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Items> getItems() {
        return itemss;
    }

    public void setItems(List<Items> items) {
        this.itemss = items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
