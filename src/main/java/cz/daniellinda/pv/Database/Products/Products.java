package cz.daniellinda.pv.Database.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "products")
    private List<Items> items;

    @JsonIgnore
    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
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

    public void setId(Long id) {
        this.id = id;
    }
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
