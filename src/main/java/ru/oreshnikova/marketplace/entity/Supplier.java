package ru.oreshnikova.marketplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
@Getter @Setter
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String region;
    private String city;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="products",
            joinColumns = @JoinColumn( name="supplier_id"),
            inverseJoinColumns = @JoinColumn( name="id")
    )
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
