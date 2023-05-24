package ru.oreshnikova.marketplace.dto;

public record PurchaseDto(String name,
                          Double tax,
                          Double taxPercent,
                          Double price,
                          Double totalPrice,
                          String supplierName,
                          String fromCity,
                          Long productId) {}
