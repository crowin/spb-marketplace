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
}
