package ru.oreshnikova.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PurchaseDto {
    private String name;
    private Double tax;
    private Double taxPercent;
    private Double price;
    private Double totalPrice;
    private String supplierName;
    private String fromCity;
    private Long productId;
}
