package ru.oreshnikova.marketplace.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String material;
    private String colour;
    private String type;
    private Double price;
    private String supplierId;
}
