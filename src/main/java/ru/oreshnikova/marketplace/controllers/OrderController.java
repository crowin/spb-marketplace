package ru.oreshnikova.marketplace.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.oreshnikova.marketplace.service.OrderService;
import ru.oreshnikova.marketplace.service.UserService;

@Controller @AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @GetMapping("/orders")
    public String getOrders(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        var orders = userService.getUserOrders(currentUser.getUsername());
        model.addAttribute("orders", orders);
        return "orders.html";
    }

    /**Верните страницу получения продукта и удалите продукт из базы данных
     * @return HTML-страницу, которая действует как страница получения продукта
     */
    @GetMapping("/product/purchase/{id}")
    public String purchaseProduct(Model material, @PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser) {
        var purchase = orderService.saveOrder(currentUser.getUsername(), id);
        material.addAttribute("purchase", purchase);
        return "receipt.html";
    }
}
