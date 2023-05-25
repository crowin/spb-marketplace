package ru.oreshnikova.marketplace.dto;

import ru.oreshnikova.marketplace.entity.Product;
import ru.oreshnikova.marketplace.entity.User;

public record OrderDto(Long id, User user, Double totalSum, String orderDate, Product product) { }
