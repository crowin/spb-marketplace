package ru.oreshnikova.marketplace.service;

import ru.oreshnikova.marketplace.dto.ProductDto;
import ru.oreshnikova.marketplace.entity.Product;
import ru.oreshnikova.marketplace.entity.Supplier;
import ru.oreshnikova.marketplace.repository.ProductsRepository;
import ru.oreshnikova.marketplace.repository.SuppliersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

@Service @Slf4j @AllArgsConstructor
public class ProductService {
    private ProductsRepository productsRepository;
    private SuppliersRepository suppliersRepository;

    public Product findById(Long productId) {
        log.info("Find {} product", productId);
        return productsRepository.findById(productId).orElse(null);
    }

    public void updateById(Long productId, Product product) {
        log.info("Update {} product", product);
        productsRepository.findById(productId).ifPresent(p -> {
            p.setName(product.getName());
            p.setColour(product.getColour());
            p.setType(product.getType());
            p.setMaterial(product.getMaterial());
            p.setPrice(product.getPrice());
            //if (!p.getSuppliers().isEmpty()) p.setSuppliers(p.getSuppliers());

            productsRepository.save(p);
        });
    }

    @Transactional
    public void removeById(Long productId) {
        log.info("Remove {} product", productId);
        productsRepository.deleteById(productId);
    }

    @Transactional
    public void addNewProduct(ProductDto product) {
        log.info("Add new {} product", product);

        suppliersRepository.findById(Long.valueOf(product.getSupplierId()))
                .ifPresent(s -> {
                    var newProduct = new Product();
                    newProduct.setName(product.getName());
                    newProduct.setColour(product.getColour());
                    newProduct.setType(product.getType());
                    newProduct.setMaterial(product.getMaterial());
                    newProduct.setPrice(product.getPrice());
                    newProduct.setPrice(product.getPrice());
                    newProduct.setSupplier(s);
                    productsRepository.save(newProduct);
                });
    }

    public List<Supplier> searchByParam(Map<String, String> params) {
        switch (new ArrayList<>(params.keySet()).get(0)) {
            case "name" -> {
                var result = productsRepository.findAllByNameContainingIgnoreCaseOrderByPrice(params.get("name"));
                return mapToSuppliers(result);
            }
            case "material" -> {
                var result = productsRepository.findAllByMaterialContainingIgnoreCaseOrderByPrice(params.get("material"));
                return mapToSuppliers(result);
            }
            case "minPrice" -> {
                var minPrice = parseDouble(params.get("minPrice"));
                var maxPrice = parseDouble(params.get("maxPrice"));
                var result = productsRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPrice(minPrice, maxPrice);
                return mapToSuppliers(result);
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    private List<Supplier> mapToSuppliers(List<Product> products) {
        var suppliers = new ArrayList<Supplier>();

        for (var p : products) {
            var foundSupplier = suppliers.stream().filter(s -> s.getId().equals(p.getSupplier().getId())).findFirst();
            if (foundSupplier.isEmpty()) {
                var supplier = p.getSupplier();
                supplier.getProducts().clear();
                supplier.getProducts().add(p);
                suppliers.add(supplier);
            } else {
                foundSupplier.get().getProducts().add(p);
            }

        }

        return suppliers;
    }
}
