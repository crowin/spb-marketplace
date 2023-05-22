package ru.oreshnikova.marketplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;


@Entity
@Table(name = "orders")
@Getter @Setter
@Accessors(chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;
    private Double totalSum;
    @CreationTimestamp
    private Date orderDate;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Product product;
}
