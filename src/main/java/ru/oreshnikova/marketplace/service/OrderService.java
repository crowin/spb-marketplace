package ru.oreshnikova.marketplace.service;

import ru.oreshnikova.marketplace.dto.PurchaseDto;
import ru.oreshnikova.marketplace.entity.Order;
import ru.oreshnikova.marketplace.entity.Product;
import ru.oreshnikova.marketplace.repository.OrderRepository;
import ru.oreshnikova.marketplace.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service @AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private ProductService productService;
    private final UserRepository userRepository;

    public PurchaseDto saveOrder(String userName, Long productId) {
        var product = productService.findById(productId);
        var user = userRepository.findByName(userName).get();
        var purchase = prepareOrder(product);
        var newOrder = new Order()
                .setUser(user)
                .setProduct(product)
                .setTotalSum(purchase.getTotalPrice());

        orderRepository.save(newOrder);

        return purchase;
    }

    public PurchaseDto prepareOrder(Product product) {
        var decimalFormat = new DecimalFormat("#########0.##");
        var taxRate = 0.13;
        var taxes = Double.parseDouble(decimalFormat.format(product.getPrice() * taxRate).replace(",", "."));
        var totalPrice = Double.parseDouble(decimalFormat.format(product.getPrice() + taxes).replace(",", "."));

        return new PurchaseDto(
                product.getName(), taxes, taxRate * 100, product.getPrice(), totalPrice,
                product.getSupplier().getName(), product.getSupplier().getCity(), product.getId()
        );
    }
}
