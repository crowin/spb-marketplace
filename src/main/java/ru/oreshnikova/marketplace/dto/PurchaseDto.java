package ru.oreshnikova.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public final class PurchaseDto {
    public String name;
    public Double tax;
    public Double taxPercent;
    public Double price;
    public Double totalPrice;
    public String supplierName;
    public String fromCity;
    public Long productId;
}
