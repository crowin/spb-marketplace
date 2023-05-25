package ru.oreshnikova.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.oreshnikova.marketplace.entity.Product;
import ru.oreshnikova.marketplace.entity.User;

import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
public class OrderDto {
    public Long id;
    public User user;
    public Double totalSum;
    public String orderDate;
    public Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
