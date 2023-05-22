package ru.oreshnikova.marketplace.repository;

import ru.oreshnikova.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContainingIgnoreCaseOrderByPrice(String productName);

    List<Product> findAllByMaterialContainingIgnoreCaseOrderByPrice(String material);

    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPrice(Double minPrice, Double maxPrice);
}