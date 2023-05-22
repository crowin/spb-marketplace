package ru.oreshnikova.marketplace.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter @Setter @Accessors(chain = true)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String material;
    private String colour;
    private String type;
    private Double price;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Supplier supplier;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="orders",
            joinColumns = @JoinColumn( name="product_id"),
            inverseJoinColumns = @JoinColumn( name="id")
    )
    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
